package ru.aston.bochkareva.service.impl;

import lombok.extern.slf4j.Slf4j;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.aston.bochkareva.dto.CreateEmployeeDto;
import ru.aston.bochkareva.entity.Employee;
import ru.aston.bochkareva.exception.CustomKafkaException;
import ru.aston.bochkareva.mapper.EmployeeMapper;
import ru.aston.bochkareva.repository.EmployeeRepository;
import ru.aston.bochkareva.service.EmployeeService;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final KafkaTemplate<String, CreateEmployeeDto> kafkaTemplate;


    @Override
    public String createEmployee(CreateEmployeeDto createEmployeeDto) {
        Employee employee = employeeMapper.mapCreateEmployeeDTOToEmployee(createEmployeeDto);
        employeeRepository.save(employee);


        SendResult<String, CreateEmployeeDto> result;
        try {
            result = kafkaTemplate
                    .send("employee-created-events-topic", "someKey", createEmployeeDto).get();
        } catch (Exception e) {
            String message = e.getMessage();
            throw new CustomKafkaException(message);
        }

        log.info("Topic: {}", result.getRecordMetadata().topic());
        log.info("Partition: {}", result.getRecordMetadata().partition());
        log.info("Return: {}", employee.toString());

        return String.join(" ", employee.getName(), employee.getSurname());
    }
}
