package ru.aston.bochkareva.service;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request, StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass
                .HelloResponse.newBuilder().setGreeting("Hello from server, " + request.getName())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getRandomTask(GreetingServiceOuterClass.GetRandomTaskRequest request, StreamObserver<GreetingServiceOuterClass.GetRandomTaskResponse> responseObserver) {
        GreetingServiceOuterClass.GetRandomTaskResponse response= GreetingServiceOuterClass
                .GetRandomTaskResponse
                .newBuilder()
                        .setTitle("random task")
                                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
