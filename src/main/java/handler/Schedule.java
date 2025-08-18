package handler;

import init.BotMessageSender;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Schedule implements iBotCommand {

    private final BotMessageSender sender;

    public ReplyKeyboardMarkup getNumKeyboard(){
        KeyboardRow row1 = new KeyboardRow();
        row1.add("1");
        row1.add("2");
        row1.add("3");

        KeyboardRow row2 = new KeyboardRow();
        row1.add("4");
        row1.add("5");
        row1.add("6");

        KeyboardRow row3 = new KeyboardRow();
        row1.add("7");
        row1.add("8");
        row1.add("9");

        KeyboardRow row4 = new KeyboardRow();
        row1.add("10");

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(keyboard);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(false);
        return markup;
    }
    public Schedule(BotMessageSender sender) {
        this.sender = sender;
    }

    @Override
    public void execute(Long chatId) {
        sender.sendTextMessageWithKeyboard(chatId,"schedule set", getNumKeyboard());
    }
}
