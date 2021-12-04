package uz.uzpartner.infoapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderUtil {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    public MailSenderUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public synchronized String sendMessage(String subject, String text, String... to) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setFrom(from);
            msg.setSubject(subject);
            msg.setText(text);

            javaMailSender.send(msg);
            return "Mail sending successfully";
        } catch (Exception e) {
            return e.toString();
        }
    }
}
