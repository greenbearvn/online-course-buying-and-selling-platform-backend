package com.javabackend.paymentservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javabackend.paymentservice.data.Payment;
import com.javabackend.paymentservice.data.PaymentRepository;
import com.javabackend.paymentservice.models.dto.OrderDetail;
import com.javabackend.paymentservice.models.events.OrderEvent;
import com.javabackend.paymentservice.models.events.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentRepository repository;

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    private final KafkaTemplate<String, OrderEvent> kafkaOrderTemplate;

    @KafkaListener(topics = "new-order", groupId = "payments-group")
    public void processPayment(String event) throws JsonProcessingException {
        System.out.println("Received event for payment " + event);

        ObjectMapper objectMapper = new ObjectMapper();
        OrderEvent orderEvent = objectMapper.readValue(event, OrderEvent.class);

        OrderDetail orderDetail = orderEvent.getOrderDetail();
        Payment payment = new Payment();

        try {

            payment.setAmount(orderDetail.getAmount());
            payment.setMode(orderDetail.getPaymentMode());
            payment.setOrderId(orderDetail.getOrderId());
            payment.setStatus("SUCCESS");
            repository.save(payment);

            PaymentEvent paymentEvent = new PaymentEvent();
            paymentEvent.setOrderDetail(orderEvent.getOrderDetail());
            paymentEvent.setType("PAYMENT_CREATED");
            kafkaTemplate.send("new-payments", paymentEvent);
        } catch (Exception e) {
            payment.setOrderId(orderDetail.getOrderId());
            payment.setStatus("FAILED");
            repository.save(payment);

            OrderEvent oe = new OrderEvent();
            oe.setOrderDetail(orderDetail);
            oe.setType("ORDER_REVERSED");
            kafkaOrderTemplate.send("reversed-orders", orderEvent);
        }
    }


}
