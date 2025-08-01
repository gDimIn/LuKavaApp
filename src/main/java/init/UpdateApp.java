package init;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.Array;

public class UpdateApp extends TelegramLongPollingBot {

    private final String BOT_TOKEN = System.getenv("BOT_TOKEN");
    private final String POSITION = System.getenv("POSITION").replace("\\n","\n");

    private final String BOT_USERNAME = System.getenv("BOT_USERNAME");

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public String getPosition(int indx){
        if (POSITION == null){
            return "Помилка в змінній середовища";
        }
        if (indx < 1 || indx > 10) {
            return "Дані по цьому номеру відсутні";
        }
        //String withLineBreaks = POSITION.replace("+\n+", "\n");
        return String.valueOf(Array.get(POSITION.split(","),indx-1));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String receivedText = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();
            String onlyDigits = receivedText.replaceAll("[^\\d]", "");

            String responseText;
            if (receivedText.equals("/start")) {
                responseText = "Привіт. Я телеграм-бот, я допоможу тобі. Напиши свій номер чергування";
            } else if (receivedText.equals("/help")) {
                responseText = "Список команд:\n/start - запуск\n/help - допомога";
            }else if (receivedText.equals(onlyDigits)){
                responseText = getPosition(Integer.parseInt(onlyDigits));
            } else {
                responseText = "Ти написав: " + receivedText+". Бот не розпізнає таку команду";
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

