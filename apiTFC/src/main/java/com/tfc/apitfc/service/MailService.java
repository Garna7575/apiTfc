package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.entity.Receipt;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;

@Service
public class MailService {

    private Session getSesionSmtp() {
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "mail.gmx.com");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.ssl.trust", "mail.gmx.com");
        propiedades.put("mail.smtp.ssl.protocols", "TLSv1.2");

        return Session.getInstance(propiedades, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("garna75@gmx.es", "paquito el chocolatero");
            }
        });
    }

    public String sendPasswordRecoveryEmail(String to, int userId) throws Exception {
        try {
            Session session = this.getSesionSmtp();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("garna75@gmx.es"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Restablecer contraseña");

            String link = "http://localhost:4200/change-password/" + userId;

            String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 20px auto;'>" +
                    "<h2 style='color: #2c3e50;'>Restablecer contraseña</h2>" +
                    "<p>Haz clic en el botón para cambiar tu contraseña:</p>" +
                    "<a href='" + link + "' style='display: inline-block; padding: 12px 24px; " +
                    "background-color: #007bff; color: white; text-decoration: none; border-radius: 4px; " +
                    "margin: 15px 0;'>Cambiar contraseña</a>" +
                    "<p style='color: #666;'>Si no solicitaste este cambio, por favor contacta a esta dirección de correo o llama al teléfono de atención al cliente: 661776232.</p>" +
                    "</div>";

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContent, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);

            message.setContent(multipart);
            Transport.send(message);

            return "Correo enviado correctamente";
        } catch (MessagingException e) {
            return "Error al enviar el correo: " + e.getMessage();
        }
    }

    public String sendPendingReceiptsEmail(String to, String customerName, List<Receipt> uncollectedReceipts) throws Exception {
        try {
            Session session = this.getSesionSmtp();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("garna75@gmx.es"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Aviso de recibos pendientes");

            StringBuilder receiptList = new StringBuilder("<ul>");
            for (Receipt receipt : uncollectedReceipts) {
                receiptList.append("<li>").append(receipt.getTitle()).append("</li>");
            }
            receiptList.append("</ul>");

            String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 20px auto;'>" +
                    "<h2 style='color: #2c3e50;'>Aviso de recibos pendientes</h2>" +
                    "<p>Estimado/a " + customerName + ",</p>" +
                    "<p>Te informamos de que tienes los siguientes recibos pendientes de cobrar:</p>" +
                    receiptList.toString() +
                    "<p>Por favor, realiza los pagos correspondientes cuanto antes o contacta con el departamento de atención al cliente.</p>" +
                    "<p style='color: #666;'>Si ya has realizado el pago, por favor ignora este mensaje.</p>" +
                    "<p>Gracias por tu atención.</p>" +
                    "</div>";

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContent, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);

            message.setContent(multipart);

            Transport.send(message);

            return "Correo enviado correctamente";
        } catch (MessagingException e) {
            return "Error al enviar el correo: " + e.getMessage();
        }
    }

    public String sendAccountTerminationEmail(String to, String customerName) throws Exception {
        try {
            Session session = this.getSesionSmtp();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("garna75@gmx.es"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Notificación de baja de la aplicación");

            String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 20px auto;'>" +
                    "<h2 style='color: #2c3e50;'>Notificación de baja</h2>" +
                    "<p>Estimado/a " + customerName + ",</p>" +
                    "<p>Te informamos que has sido dado/a de baja de la aplicación por el administrador.</p>" +
                    "<p>Si tienes algún inconveniente o preguntas relacionadas con esta acción, no dudes en contactarlo a través de su email o teléfono:</p>" +
                    "<p>Gracias por tu atención.</p>" +
                    "</div>";

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContent, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);

            message.setContent(multipart);

            Transport.send(message);

            return "Correo enviado correctamente";
        } catch (MessagingException e) {
            return "Error al enviar el correo: " + e.getMessage();
        }
    }

    public String sendVotingResultEmail(String to, String name, String votingTitle, boolean isApproved) throws Exception {
        try {
            Session session = this.getSesionSmtp();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("garna75@gmx.es"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Resultado de la Votación: " + votingTitle);

            String votingResult = isApproved ? "a favor" : "en contra";

            String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 20px auto;'>" +
                    "<h2 style='color: #2c3e50;'>Resultado de la votación</h2>" +
                    "<p>Estimado/a " + name + ",</p>" +
                    "<p>Te informamos que se ha realizado una votación sobre el tema: <strong>" + votingTitle + "</strong>.</p>" +
                    "<p>El resultado de la votación ha sido: <strong>" + votingResult + "</strong>.</p>" +
                    "<p>Gracias por participar en el proceso de votación.</p>" +
                    "<p>Si tienes alguna duda o consulta, no dudes en contactarnos.</p>" +
                    "</div>";

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContent, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);

            message.setContent(multipart);

            Transport.send(message);

            return "Correo enviado correctamente";
        } catch (MessagingException e) {
            return "Error al enviar el correo: " + e.getMessage();
        }
    }
}
