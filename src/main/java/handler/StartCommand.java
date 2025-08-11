package handler;

import init.BotMessageSender;

public class StartCommand implements BotCommand{
    private final BotMessageSender sender;

    public  StartCommand(BotMessageSender sender){
        this.sender = sender;
    }

    @Override
    public void execute(Long chatId) {
        sender.sendTextMessage(chatId, "Привіт. Я телеграм-бот, я допоможу тобі. Напиши свій номер чергування");
    }
}
