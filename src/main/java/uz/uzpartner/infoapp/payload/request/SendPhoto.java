package uz.uzpartner.infoapp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SendPhoto {
    private Long chat_id;
    private String photo;
    private String caption;
    private ReplyKeyboard reply_markup;
}