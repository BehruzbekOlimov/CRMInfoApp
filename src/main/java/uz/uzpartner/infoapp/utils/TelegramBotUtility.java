        package uz.uzpartner.infoapp.utils;

        import com.vdurmont.emoji.EmojiParser;
        import lombok.SneakyThrows;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.stereotype.Component;
        import org.springframework.web.client.RestTemplate;
        import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
        import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
        import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
        import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
        import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
        import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
        import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
        import uz.uzpartner.infoapp.payload.request.SendPhoto;

        import java.util.*;

@Component
public class TelegramBotUtility {


    @Value("${app.telegrambot.token}")
    private String botToken;
    private final String BASE_URL = "https://api.telegram.org/bot";

    @Autowired
    private RestTemplate restTemplate;

    public void sendMessage(SendMessage sendMessage) {
        restTemplate.postForObject(BASE_URL + botToken + "/sendMessage", sendMessage, Void.class);
    }

    public void sendLocation(SendLocation sendLocation) {
        restTemplate.postForObject(BASE_URL + botToken + "/sendLocation", sendLocation, Void.class);
    }

    @SneakyThrows
    public void sendMessage(SendMessage sendMessage, long chat_id, long message_id) {
        try {
            restTemplate.getForObject((BASE_URL + botToken + "/deleteMessage?chat_id=" + chat_id + "&message_id=" + message_id), Void.class);
        } catch (Exception ignored) {
        }
        sendMessage.setChatId(String.valueOf(chat_id));
        sendMessage(sendMessage);
    }

    @SneakyThrows
    public void sendMessage(SendMessage sendMessage, Long id) {
        sendMessage.setChatId(String.valueOf(id));
        sendMessage(sendMessage);
    }

    @SneakyThrows
    public void sendMessage(String message, Long id) {
        SendMessage sendMessage = new SendMessage(String.valueOf(id), message);
        sendMessage(sendMessage);
    }

    public void sendPhoto(SendPhoto photo, long chat_id, long message_id) {
        try {
            restTemplate.getForObject((BASE_URL + botToken + "/deleteMessage?chat_id=" + chat_id + "&message_id=" + message_id), Void.class);
        } catch (Exception ignored) {
        }
        sendPhoto(photo);
    }

    public void sendPhoto(SendPhoto photo) {
        try {
            restTemplate.postForObject(BASE_URL + botToken + "/sendPhoto", photo, Void.class);
        } catch (Exception ignored) {
        }
    }

//    public InlineKeyboardMarkup buildInlineKeyboard(String[][] arr, boolean isBack, boolean isBackToTop, Lang lang) {
//        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
//        for (String[] row : arr) {
//            List<InlineKeyboardButton> buttonRow = new ArrayList<>();
//            for (String buttonString : row) {
//                if (buttonString != null) {
//                    InlineKeyboardButton button = new InlineKeyboardButton(EmojiParser.parseToUnicode(buttonString));
//                    button.setCallbackData(buttonString);
//                    buttonRow.add(button);
//                }
//            }
//            keyboard.add(buttonRow);
//        }
//        if (isBack) {
//            InlineKeyboardButton backButton = new InlineKeyboardButton(EmojiParser.parseToUnicode(":back: " + (lang.equals(Lang.UZ) ? "Orqaga" : "Назад")));
//            backButton.setCallbackData("BACK_ONE");
//            keyboard.add(Collections.singletonList(backButton));
//        }
//        if (isBackToTop) {
//            InlineKeyboardButton backToTopButton = new InlineKeyboardButton(EmojiParser.parseToUnicode(":top: " + (lang.equals(Lang.UZ) ? "Bosh menuga" : "Главный меню")));
//            backToTopButton.setCallbackData("BACK_TO_TOP");
//            keyboard.add(Collections.singletonList(backToTopButton));
//        }
//        markup.setKeyboard(keyboard);
//        return markup;
//    }


    public String formatNumber(int n) {
        String res = String.valueOf(n);
        int index = res.length() - 3;
        while (index > 0) {
            res = res.substring(0, index) + " " + res.substring(index);
            index -= 3;
        }
        return res;
    }
}