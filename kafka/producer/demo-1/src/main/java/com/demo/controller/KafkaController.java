package com.demo.controller;

import com.demo.beans.Greeting;
import com.demo.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/send/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent");
    }


    @GetMapping("/greeting")
    public ResponseEntity<String> sendMessage(@RequestBody Greeting greeting) {
        kafkaProducer.sendGreeting(greeting);
        return ResponseEntity.ok("Greeting sent");
    }
}
