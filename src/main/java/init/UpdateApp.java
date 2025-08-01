package init;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.Array;

public class UpdateApp extends TelegramLongPollingBot {

    private final String BOT_TOKEN = System.getenv("BOT_TOKEN");
    private final String POSITION = System.getenv("POSITION");
//    private final String POSITION = "Пост №1 з 17:00 до 19:00+\n+Пост №1 з 23:00 до 1:00+\n+Пост №1 з 9:00 до 11:00+\n+Пост №1 з 13:00 до 15:00,"+
//    "Пост №1 з 19:00 до 21:00+\n+Пост №4 з 23:00 до 1:00+\n+Пост №1 з 5:00 до 7:00+\n+Пост №4 з 9:00 до 11:00+\n+ Пост №1 з 15:00 до 17:00,"
//    Пост №1 з 21:00 до 23:00+\n+Пост №1 з 2:00 до 3:00+\n+Пост №1 з 7:00 до 9:00+\n+Пост №1 з 11:00 до 1:00,
//    Пост №2 з 17:00 до 19:00+\n+Пост №2 з 23:00 до 1:00+\n+Пост №2 з 9:00 до 11:00+\n+Пост №2 з 13:00 до 15:00,
//    Пост №2 з 19:00 до 21:00+\n+Пост №3 з 23:00 до 1:00+\n+Пост №2 з 5:00 до 7:00+\n+Пост №3 з 9:00 до 11:00+\n+ Пост №2 з 15:00 до 17:00,
//    Пост №2 з 21:00 до 23:00+\n+Пост №2 з 2:00 до 3:00+\n+Пост №2 з 7:00 до 9:00+\n+Пост №2 з 11:00 до 1:00,
//    Пост №3 з 17:00 до 19:00+\n+Пост №3 з 21:00 до 23:00+\n+Пост №3 з 3:00 до 5:00+\n+Пост №3 з 7:00 до 9:00+\n+ Пост №3 з 13:00 до 15:00,
//    Пост №3 з 19:00 до 21:00+\n+Пост №3 з 1:00 до 3:00+\n+Пост №3 з 5:00 до 7:00+\n+Пост №3 з 11:00 до 13:00+\n+ Пост №3 з 15:00 до 17:00,
//    Пост №4 з 17:00 до 19:00+\n+Пост №4 з 21:00 до 23:00+\n+Пост №4 з 3:00 до 5:00+\n+Пост №4 з 7:00 до 9:00+\n+ Пост №4 з 13:00 до 15:00,
//    Пост №4 з 19:00 до 21:00+\n+Пост №4 з 1:00 до 3:00+\n+Пост №4 з 5:00 до 7:00+\n+Пост №4 з 11:00 до 13:00+\n+ Пост №4 з 15:00 до 17:00"

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

