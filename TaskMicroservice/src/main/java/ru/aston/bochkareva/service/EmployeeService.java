package ru.aston.bochkareva.service;


import ru.aston.bochkareva.EmployeeCreatedEvent;

public interface EmployeeService {
    String createEmployee(EmployeeCreatedEvent employeeCreatedEvent);
}
