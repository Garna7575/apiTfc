package com.tfc.apitfc.service;

import org.springframework.stereotype.Service;

import java.io.File;
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

    public String sendEmail(String to, String asunto, String texto) throws Exception {
        try {
            Session sesion = this.getSesionSmtp();
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress("garna75@gmx.es"));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            mensaje.setSubject(asunto);

            MimeBodyPart cuerpoTexto = new MimeBodyPart();
            cuerpoTexto.setText(texto);

            Multipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(cuerpoTexto);

            mensaje.setContent(multiparte);
            Transport.send(mensaje);

            return "Email enviado con éxito";
        } catch (AuthenticationFailedException e) {
            return "Error de autenticación: Verifica tu usuario y contraseña.";
        } catch (MessagingException e) {
            return "Error en el envío del correo";
        } catch (Exception e) {
            return "Error inesperado";
        }
    }

//    public String forgotPasswordEmail(String to){
//        try {
//            Session sesion = this.getSesionSmtp();
//            Message mensaje = new MimeMessage(sesion);
//            mensaje.setFrom(new InternetAddress("garna75@gmx.es"));
//            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//            mensaje.setSubject("Recuperación de contraseña");
//
//            MimeBodyPart cuerpoTexto = new MimeBodyPart();
//            cuerpoTexto.setText(texto);
//
//            Multipart multiparte = new MimeMultipart();
//            multiparte.addBodyPart(cuerpoTexto);
//
//            mensaje.setContent(multiparte);
//            Transport.send(mensaje);
//
//            return "Email enviado con éxito";
//        } catch (AuthenticationFailedException e) {
//            return "Error de autenticación: Verifica tu usuario y contraseña.";
//        } catch (MessagingException e) {
//            return "Error en el envío del correo";
//        } catch (Exception e) {
//            return "Error inesperado";
//        }
//    }
}
