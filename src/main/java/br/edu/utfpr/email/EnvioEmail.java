package br.edu.utfpr.email;

import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EnvioEmail {

    public void enviar(String emailProfissional, String subject, String texto) {

        String emailTymed = "tymed@hotmail.com";
        final String username = "tymed@hotmail.com";
        final String password = "tymed";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailTymed));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailProfissional));
            message.setSubject(subject);
            message.setText(texto);
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
