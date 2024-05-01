package ru.aston.bochkareva.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.bochkareva.entity.Task;
import ru.aston.bochkareva.repository.TaskRepository;
import ru.aston.bochkareva.service.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private Random random = new Random();

    @Override
    public Task getRandomTask() {
        List<Task> taskList = taskRepository.findAll();
        int randomNumber = random.nextInt(taskList.size());
        return taskList.get(randomNumber);
    }

    @Override
    public List<String> getListRandomTaskNames(int size) {
        List<Task> taskList = taskRepository.findAll();
        List<String> taskNames = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int randomNumber = random.nextInt(taskList.size());
            taskNames.add(taskList.get(randomNumber).getTitle());
        }
        return taskNames;
    }


}
