package com.javabackend.orderservice.controller;

import com.javabackend.orderservice.models.req.OrderInfor;
import com.javabackend.orderservice.models.res.PaymentRes;
import com.javabackend.orderservice.models.res.ResponseObject;
import com.javabackend.orderservice.service.inter.OrderService;
import com.javabackend.orderservice.service.inter.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final PaymentService paymentService;

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderInfor orderReq) {

        orderService.createOrder(orderReq);
    }

    @GetMapping("/payment")
    public ResponseObject<PaymentRes.VNPayResponse> Payment(HttpServletRequest request) {

        return new ResponseObject<>(HttpStatus.OK, "Success", paymentService.createVnPayPayment(request));
    }

    @GetMapping("/vn-pay-callback")
    public ResponseEntity<ResponseObject<?>> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        try {
            if ("00".equals(status)) {
                // Payment successful
                return ResponseEntity.ok(new ResponseObject<>(HttpStatus.OK, "Success", null));
            } else {
                // Payment failed
                return ResponseEntity.badRequest().body(new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", null));
            }
        } catch (Exception e) {
            // Internal server error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject<>(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", null));
        }
    }


}
