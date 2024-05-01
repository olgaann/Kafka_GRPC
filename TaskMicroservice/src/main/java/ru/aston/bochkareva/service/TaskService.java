package ru.aston.bochkareva.service;

import ru.aston.bochkareva.entity.Task;

import java.util.List;

public interface TaskService {
    Task getRandomTask();
    List<String> getListRandomTaskNames(int size);
}
