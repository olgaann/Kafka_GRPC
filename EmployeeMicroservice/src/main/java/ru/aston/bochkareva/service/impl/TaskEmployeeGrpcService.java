package ru.aston.bochkareva.service.impl;

import com.example.grpc.TaskEmployeeGrpcServiceGrpc;
import com.example.grpc.TaskEmployeeGrpcServiceOuterClass;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskEmployeeGrpcService {

    @GrpcClient(value = "task-employee-grpc-service")
    private TaskEmployeeGrpcServiceGrpc.TaskEmployeeGrpcServiceBlockingStub stub;

    public String sayHello() {
        TaskEmployeeGrpcServiceOuterClass.HelloRequest request = TaskEmployeeGrpcServiceOuterClass.HelloRequest.newBuilder()
                .setName("EmployeeMicroservice")
                .build();
        TaskEmployeeGrpcServiceOuterClass.HelloResponse response = stub.greeting(request);
        return response.getGreeting();
    }

    public List<String> getTaskList(int size) {
        TaskEmployeeGrpcServiceOuterClass.GetTaskListRequest request = TaskEmployeeGrpcServiceOuterClass.GetTaskListRequest
                .newBuilder().setSize(size).build();
        TaskEmployeeGrpcServiceOuterClass.GetTaskListResponse response = stub.getTaskList(request);
        return response.getTasksList();
    }
}
