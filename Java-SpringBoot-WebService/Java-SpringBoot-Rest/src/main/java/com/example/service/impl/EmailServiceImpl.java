package com.example.service.impl;

import com.example.service.EmailService;
import com.example.service.dto.EmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Debes activar esto para poder accesar a gmail.com
 * Conexion conexiones no encriptadas.
 *
 * https://www.google.com/settings/security/lesssecureapps
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Session session;
    private final Properties props;

    private static final String EMAIL = "brayanmartinez827@gmail.com";
    private static final String EMAIL_PASS = "";
    private static final String PERSONAL = "Mi firma de EMail";



    public EmailServiceImpl() {

        //Propiedades de la secion.
        this.props = new Properties();

        this.props.put("mail.smtp.socketFactory.port", "587");
        this.props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        this.props.put("mail.smtp.auth", "true");
        this.props.put("mail.smtp.starttls.enable", "true");
        this.props.put("mail.smtp.host", "smtp.gmail.com");
        this.props.put("mail.smtp.port", "587");

        //Crear la secion.
        this.session = Session.getInstance(props, new GMailAuthenticator(EMAIL, EMAIL_PASS));
    }


    /**
     * Enviar el EMail.
     * @param emailDTO El EMail a enviar.
     */
    @Override
    public void sendMessage(EmailDTO emailDTO) {
        final MimeMessage message = new MimeMessage(session);

        try {
            message.setRecipient(Message.RecipientType.TO, InternetAddress.parse(emailDTO.getRecepient())[0]);
            message.setContent(emailDTO.getMsg(), "text/html; charset=utf-8");
            message.setSubject(emailDTO.getSubject());
            message.setFrom(new InternetAddress(EMAIL, PERSONAL));

            Transport.send(message);

        } catch (MessagingException | UnsupportedEncodingException ex) {
            this.log.error(ex.getMessage());

        } finally {
            this.log.info("Se ha enviado un EMail.");

        }
    }

    private static class GMailAuthenticator extends Authenticator {

        private String email;
        private String password;

        public GMailAuthenticator(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(email, password);
        }

    }

}
