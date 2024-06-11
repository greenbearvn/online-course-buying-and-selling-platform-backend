package com.javabackend.orderservice.service.impl;

import com.javabackend.orderservice.models.obj.CartItem;
import com.javabackend.orderservice.service.inter.SendMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {
    private final JavaMailSender getJavaMailSender;
    @Override
    public void sendMail(List<CartItem> cartItems,String email) throws MessagingException {

        String cartItemTable = convertListToHtmlTable(cartItems) ;

        MimeMessage mimeMessage = getJavaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom("sales.couresplus@courseplus.com");
        helper.setTo(email);
        helper.setText("<html><body>" + cartItemTable + "</body></html>",true);
        helper.setSubject("Thanh toán đơn hàng thành công");
        getJavaMailSender.send(mimeMessage);

    }

    private String convertListToHtmlTable(List<CartItem> cartItems) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head>");
        html.append("<style>");
        html.append("table {width: 100%; border-collapse: collapse;}");
        html.append("th, td {border: 1px solid black; padding: 8px; text-align: left;}");
        html.append("th {background-color: #f2f2f2;}");
        html.append("tr:nth-child(even) {background-color: #f9f9f9;}");
        html.append("</style>");
        html.append("</head><body>");
        html.append("<table border=\"1\">");
        html.append("<tr>");
        html.append("<th>Tên khóa học</th>");
        html.append("<th>Cấp độ</th>");
        html.append("<th>Giảng viên</th>");
        html.append("<th>Thời gian</th>");
        html.append("<th>Giá tiền</th>");
        html.append("</tr>");


        for (CartItem obj : cartItems) {
            html.append("<tr>");
            html.append("<td>").append(obj.getCourseName()).append("</td>");
            html.append("<td>").append(obj.getLevelName()).append("</td>");
            html.append("<td>").append(obj.getProfileName()).append("</td>");
            html.append("<td>").append(obj.getCourseDuration()).append("</td>");
            html.append("<td>").append(obj.getNewPrice()).append("</td>");
            // Add more columns as needed
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("</body></html>");

        return html.toString();

    }
}
