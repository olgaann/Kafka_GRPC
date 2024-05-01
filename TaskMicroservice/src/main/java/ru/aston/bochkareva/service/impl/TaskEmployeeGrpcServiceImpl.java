package ru.aston.bochkareva.service.impl;

import com.example.grpc.TaskEmployeeGrpcServiceGrpc;
import com.example.grpc.TaskEmployeeGrpcServiceOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.aston.bochkareva.service.TaskService;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class TaskEmployeeGrpcServiceImpl extends TaskEmployeeGrpcServiceGrpc.TaskEmployeeGrpcServiceImplBase {

    private final TaskService taskService;
    @Override
    public void greeting(TaskEmployeeGrpcServiceOuterClass.HelloRequest request, StreamObserver<TaskEmployeeGrpcServiceOuterClass.HelloResponse> responseObserver) {
        TaskEmployeeGrpcServiceOuterClass.HelloResponse response = TaskEmployeeGrpcServiceOuterClass
                .HelloResponse.newBuilder().setGreeting("Hello from TaskMicroservice, " + request.getName())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getTaskList(TaskEmployeeGrpcServiceOuterClass.GetTaskListRequest request, StreamObserver<TaskEmployeeGrpcServiceOuterClass.GetTaskListResponse> responseObserver) {
        int size = request.getSize();
        List<String> taskNamesList = taskService.getListRandomTaskNames(size);
        TaskEmployeeGrpcServiceOuterClass.GetTaskListResponse response = TaskEmployeeGrpcServiceOuterClass.GetTaskListResponse
                .newBuilder().addAllTasks(taskNamesList).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
