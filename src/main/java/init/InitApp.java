package init;

import org.telegram.telegrambots.meta.*;
import org.telegram.telegrambots.meta.exceptions.*;
import org.telegram.telegrambots.updatesreceivers.*;

public class InitApp {

    public static void main(String[] args) {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new UpdateApp());
            System.out.println("Chat success");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
