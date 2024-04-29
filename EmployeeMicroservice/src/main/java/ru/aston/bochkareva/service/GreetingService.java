package ru.aston.bochkareva.service;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @GrpcClient(value = "greeting-service")
    private GreetingServiceGrpc.GreetingServiceBlockingStub stub;

    public void test() {

        // Создаем запрос с именем клиента
        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest.newBuilder()
                .setName("Имя1")
                .build();

        // Выполняем вызов метода сервера и получаем ответ
        GreetingServiceOuterClass.HelloResponse response = stub.greeting(request);

        // Выводим ответ сервера
        System.out.println(response.getGreeting());

    }
}
