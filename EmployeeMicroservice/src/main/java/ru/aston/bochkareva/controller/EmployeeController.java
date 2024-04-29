package ru.aston.bochkareva.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.bochkareva.dto.CreateEmployeeDto;
import ru.aston.bochkareva.service.EmployeeService;
import ru.aston.bochkareva.service.GreetingService;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final GreetingService greetingService;
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
        String employeeData = employeeService.createEmployee(createEmployeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("New employee %s created", employeeData));
    }

    @GetMapping("/")
    public String test() {
        greetingService.test();
        return "ok";
    }
}
