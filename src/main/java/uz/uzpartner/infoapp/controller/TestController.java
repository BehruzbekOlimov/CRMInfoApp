package uz.uzpartner.infoapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.uzpartner.infoapp.utils.MailSenderUtil;

@RestController
@RequestMapping("/api/test/")
@AllArgsConstructor
public class TestController {
    private final MailSenderUtil mailSenderUtil;

    @GetMapping("send-mail")
    public String sendMail(@RequestParam String sub,@RequestParam String text,@RequestParam String to){
        return mailSenderUtil.sendMessage(sub,text,to);
    }

}
