package init;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotMessageSender {
    private final LuKavaBot bot;
    private String getText;

    public BotMessageSender(LuKavaBot bot){
        this.bot = bot;
    }

    public void sendTextMessage(Long chatId, String text){
        SendMessage message = SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .build();

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendTextMessageWithKeyboard(Long chatId, String text, ReplyKeyboard keyboard){
        SendMessage message = SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .replyMarkup(keyboard)
                .build();

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
