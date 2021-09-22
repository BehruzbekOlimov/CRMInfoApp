package uz.uzpartner.infoapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public MailSenderUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String sendMessage(String subject, String text, String... to) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setFrom("info@uzpartner.com");
            msg.setSubject(subject);
            msg.setText(text);

            javaMailSender.send(msg);
            return "Mail sending successfully";
        } catch (Exception e) {
            return "Mail sending failed";
        }
    }
}
