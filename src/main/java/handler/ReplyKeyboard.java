package handler;

import init.BotMessageSender;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboard implements iBotCommand {

    private final BotMessageSender sender;

    public ReplyKeyboardMarkup getNumKeyboard(){
        KeyboardRow row1 = new KeyboardRow();
        for(EnumSchedule type : EnumSchedule.values()){
            row1.add(type.getCommadKey());
        }

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(keyboard);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(false);
        return markup;
    }
    public ReplyKeyboard(BotMessageSender sender) {
        this.sender = sender;
    }

    @Override
    public void execute(Long chatId) {
        sender.sendTextMessageWithKeyboard(chatId,"schedule set", getNumKeyboard());
    }
}
