package com.ms.ist.kafkaconsumer.controller;

import com.google.gson.Gson;
import com.ms.ist.kafkaconsumer.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    Employee employee = null;
    @Autowired
    private Gson jsonConverter;

    @GetMapping("/consume")
    public Employee consumeJson() {
        return employee;
    }

    @KafkaListener(groupId = "ist-pod-group", topics = "ist-pod", containerFactory = "kafkaListenerContainerFactory")
    public void getJsonMsgFromTopic(String stringEmployee) {

        employee = (Employee) jsonConverter.fromJson(stringEmployee, Employee.class);
        System.out.println(employee);

    }

}
