package handler;

import init.BotMessageSender;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private final Map<String, iBotCommand> commandMap = new HashMap<>();
    private final BotMessageSender sender;

    public  CommandHandler(BotMessageSender sender){
        this.sender = sender;
        registerCommands();
    }

    private void registerCommands(){
        commandMap.put("/start", new StartCommand(sender));
        commandMap.put("/schedule", new Schedule(sender));
        commandMap.put("1", new ShowSchedule(sender,"1"));
        commandMap.put("2", new ShowSchedule(sender,"2"));
        commandMap.put("3", new ShowSchedule(sender,"3"));
        commandMap.put("4", new ShowSchedule(sender,"4"));
        commandMap.put("5", new ShowSchedule(sender,"5"));
        commandMap.put("6", new ShowSchedule(sender,"6"));
        commandMap.put("7", new ShowSchedule(sender,"7"));
        commandMap.put("8", new ShowSchedule(sender,"8"));
        commandMap.put("9", new ShowSchedule(sender,"9"));
        commandMap.put("10", new ShowSchedule(sender,"10"));
    }

    public void handleCommand(Long chatId, String command){
        iBotCommand botCommand = commandMap.get(command);

        if (botCommand != null) {
            botCommand.execute(chatId);
        } else {
            sender.sendTextMessage(chatId, "Havn't command");
        }
    }
}
