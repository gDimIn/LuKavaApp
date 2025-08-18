package handler;

import init.BotMessageSender;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;

import java.util.List;

public class StartCommand implements iBotCommand {
    private final BotMessageSender sender;


    public  StartCommand(BotMessageSender sender){
        this.sender = sender;
    }

    public SetMyCommands initMenu(){
        List<BotCommand> commands = List.of(
                new BotCommand("/schedule","Графік по номеру")
        );
        return new SetMyCommands(commands, new BotCommandScopeDefault(), null);
    }

    public void execute(Long chatId) {
        sender.sendStartMenu(initMenu());
        sender.sendTextMessage(chatId, "Привіт. Я телеграм-бот, я допоможу тобі. Обери в меню \"Графік по номеру\"");
    }

}
