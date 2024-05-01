package ru.aston.bochkareva.service;

import com.example.grpc.TaskEmployeeGrpcServiceGrpc;
import com.example.grpc.TaskEmployeeGrpcServiceOuterClass;
import com.example.grpc.TaskEmployeeGrpcServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

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
    public void getRandomTask(TaskEmployeeGrpcServiceOuterClass.GetRandomTaskRequest request, StreamObserver<TaskEmployeeGrpcServiceOuterClass.GetRandomTaskResponse> responseObserver) {
        TaskEmployeeGrpcServiceOuterClass.GetRandomTaskResponse response= TaskEmployeeGrpcServiceOuterClass
                .GetRandomTaskResponse
                .newBuilder()
                        .setTitle(taskService.getRandomTask().getTitle())
                                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
