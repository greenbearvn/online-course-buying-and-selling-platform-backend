package com.javabackend.orderservice.controller;

import com.javabackend.orderservice.data.Order;
import com.javabackend.orderservice.models.obj.CartItem;
import com.javabackend.orderservice.models.req.OrderInfor;
import com.javabackend.orderservice.models.res.OrderRes;
import com.javabackend.orderservice.models.res.PaymentRes;
import com.javabackend.orderservice.models.res.ResponseObject;
import com.javabackend.orderservice.service.inter.OrderService;
import com.javabackend.orderservice.service.inter.PaymentService;
import com.javabackend.orderservice.service.inter.SendMailService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final SendMailService sendMailService;

    @GetMapping("/list")
    public List<OrderRes> getAll() {

        return  orderService.getAllOrders();
    }

    @GetMapping("/detail/{id}")
    public Order detail(@PathVariable int id) {

        return  orderService.detail(id);
    }

    @PostMapping("/create")
    public boolean createOrder(@RequestBody OrderInfor orderReq) {

        try {
            orderService.createOrder(orderReq);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @PutMapping("/update/{id}")
    public Order update(@PathVariable int id,@RequestBody Order orderReq) {

        return orderService.update(id,orderReq);
    }

    @DeleteMapping("/delete/{id}")
    public boolean createOrder(@PathVariable int id) {

        return  orderService.delete(id);
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



    @PostMapping("/send-mail")
    public void sendMail(@RequestParam(name = "email") String email, @RequestBody List<CartItem> cartItems) throws MessagingException {

       sendMailService.sendMail(cartItems,email);
    }

}
