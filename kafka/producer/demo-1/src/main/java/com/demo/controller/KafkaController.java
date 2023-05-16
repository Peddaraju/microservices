package com.demo.controller;

import com.demo.beans.Greeting;
import com.demo.beans.SendPaymentRequest;
import com.demo.producer.KafkaProducer;
import demo.kafka.event.PaymentSent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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


    @PostMapping("/payment/send")
    public ResponseEntity<String> sendPayment(@RequestBody SendPaymentRequest request) {
        try {
            PaymentSent paymentSent = PaymentSent.newBuilder()
                    .setPaymentId(request.getPaymentId())
                    .setAmount(request.getAmount())
                    .setCurrency(request.getCurrency())
                    .setFromAccount(request.getFromAccount())
                    .setToAccount(request.getToAccount())
                    .build();
            kafkaProducer.sendMessage(request.getPaymentId(), paymentSent);
            return ResponseEntity.ok(request.getPaymentId());
        } catch(Exception e) {
            return ResponseEntity.internalServerError().body(request.getPaymentId());
        }
    }
}
