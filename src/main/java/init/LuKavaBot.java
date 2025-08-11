package init;

import handler.CommandHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class LuKavaBot extends TelegramLongPollingBot {
    private final BotMessageSender messageSender;
    private final CommandHandler commandHandler;

    private final String BOT_TOKEN = System.getenv("BOT_TOKEN");
    private final String POSITION = System.getenv("POSITION").replace("\\n","\n");
    private final String BOT_USERNAME = System.getenv("BOT_USERNAME");

    public LuKavaBot() {
        this.messageSender = new BotMessageSender(this);
        this.commandHandler = new CommandHandler(messageSender);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            Long chatId = update.getMessage().getChatId();
            String userText = update.getMessage().getText();

            commandHandler.handleCommand(chatId, userText);
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
