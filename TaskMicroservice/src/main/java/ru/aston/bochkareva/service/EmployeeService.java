package ru.aston.bochkareva.service;


import ru.aston.bochkareva.dto.EmployeeCreatedEvent;

public interface EmployeeService {
    String createEmployee(EmployeeCreatedEvent employeeCreatedEvent);
}
