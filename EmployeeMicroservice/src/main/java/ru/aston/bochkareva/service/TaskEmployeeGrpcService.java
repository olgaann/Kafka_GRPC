package ru.aston.bochkareva.service;

import com.example.grpc.TaskEmployeeGrpcServiceGrpc;
import com.example.grpc.TaskEmployeeGrpcServiceOuterClass;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

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

    public String getRandomTask() {
        TaskEmployeeGrpcServiceOuterClass.GetRandomTaskRequest request = TaskEmployeeGrpcServiceOuterClass
                .GetRandomTaskRequest.newBuilder().build();
        TaskEmployeeGrpcServiceOuterClass.GetRandomTaskResponse response = stub.getRandomTask(request);
        System.out.println(response.getTitle());
        return response.getTitle();
    }
}
