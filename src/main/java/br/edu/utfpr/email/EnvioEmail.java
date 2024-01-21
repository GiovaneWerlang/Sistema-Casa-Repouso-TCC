package br.edu.utfpr.email;

import java.util.Properties;

import br.edu.utfpr.configuracaosistema.ConfiguracaoSistemaModel;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnvioEmail {

    public void enviar(String emailProfissional, String subject, String texto, ConfiguracaoSistemaModel configuracaoSistema) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(configuracaoSistema.getEmailLogin(), configuracaoSistema.getEmailSenha());
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(configuracaoSistema.getEmailLogin()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailProfissional));
            message.setSubject(subject);
            message.setText(texto);
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
