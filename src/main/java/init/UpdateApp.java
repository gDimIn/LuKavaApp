package init;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class UpdateApp extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "testTarasBot";
    }

    @Override
    public String getBotToken() {
        return "2084121730:AAGJLyJu262NI4gEJTYrnqEI8Axb_yT7i74";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String receivedText = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();

            String responseText;
            if (receivedText.equals("/start")) {
                responseText = "Привіт! Я Telegram-бот на Java 🙂";
            } else if (receivedText.equals("/help")) {
                responseText = "Список команд:\n/start - запуск\n/help - допомога";
            } else {
                responseText = "Ти написав: " + receivedText;
            }

            SendMessage message = new SendMessage(chatId, responseText);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}

