package br.com.andrad3.despesasfinancasbackend.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("coincontroltcc@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject("Ciclano");
        helper.setText(body, true);

        mailSender.send(message);

    }

    String body =  "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            background-color: #f4f4f4;\n" +
            "            padding: 20px;\n" +
            "        }\n" +
            "\n" +
            "        .email-container {\n" +
            "            max-width: 600px;\n" +
            "            margin: 0 auto;\n" +
            "            background-color: #ffffff;\n" +
            "            border-radius: 8px;\n" +
            "            padding: 40px;\n" +
            "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
            "        }\n" +
            "\n" +
            "        .logo {\n" +
            "            text-align: center;\n" +
            "            margin-bottom: 20px;\n" +
            "        }\n" +
            "\n" +
            "        h1, p {\n" +
            "            text-align: center;\n" +
            "            color: #333333;\n" +
            "        }\n" +
            "\n" +
            "        .button {\n" +
            "            display: inline-block;\n" +
            "            padding: 10px 20px;\n" +
            "            margin-top: 30px;\n" +
            "            text-decoration: none;\n" +
            "            color: #ffffff;\n" +
            "            background-color: #007bff;\n" +
            "            border-radius: 5px;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"email-container\">\n" +
            "        <div class=\"logo\">\n" +
            "            <h1 style=\"color: #007bff; font-weight: bold;\">CoinControl</h1>\n" +
            "        </div>\n" +
            "        <h1>Alteração de Senha</h1>\n" +
            "        <p>Prezado Usuário,</p>\n" +
            "        <p>Para alterar sua senha no CoinControl, clique no botão abaixo:</p>\n" +
            "        <a class=\"button\" href=\"https://www.seusite.com/trocar-senha\">Alterar Senha</a>\n" +
            "        <p style=\"margin-top: 20px;\">Se o botão acima não funcionar, copie e cole o seguinte link em seu navegador:</p>\n" +
            "        <p>https://www.seusite.com/trocar-senha</p>\n" +
            "        <p>Mantenha sua senha segura e não a compartilhe com ninguém.</p>\n" +
            "        <p style=\"margin-top: 40px;\">Atenciosamente,<br>Equipe CoinControl</p>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>";


}
