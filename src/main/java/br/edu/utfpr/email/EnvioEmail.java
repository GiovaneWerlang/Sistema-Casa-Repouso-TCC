package br.edu.utfpr.email;

import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnvioEmail {

    @ConfigProperty(name = "email.habilitar", defaultValue="Não configurado!")
    boolean habilitar;

    @ConfigProperty(name = "email.login", defaultValue="Não configurado!")
    String login;

    @ConfigProperty(name = "email.senha", defaultValue="Não configurado!")
    String senha;

    public void enviar(String emailProfissional, String subject, String texto) {

        if(habilitar) {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp-mail.outlook.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new jakarta.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(login, senha);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(login));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailProfissional));
                message.setSubject(subject);
                message.setText(texto);
                Transport.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        System.out.println(habilitar);

    }

}
