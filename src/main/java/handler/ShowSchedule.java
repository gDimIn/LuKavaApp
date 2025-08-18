package handler;

import init.BotMessageSender;

import java.lang.reflect.Array;

public class ShowSchedule implements iBotCommand {
    public final BotMessageSender sender;
    private String recivedText;
    private final String POSITION = System.getenv("POSITION").replace("\\n","\n");

    public ShowSchedule(BotMessageSender sender, String text) {
        this.sender = sender;
        recivedText = text;
    }

    @Override
    public void execute(Long chatId) {
        int indx = -1;
        if (recivedText.matches("\\d+")) {
            indx = Integer.parseInt(recivedText);
            if (indx < 1 || indx > 10) {
                return;
            }
            sender.sendTextMessage(chatId,String.valueOf(Array.get(POSITION.split(","),indx - 1)));
            return;
        }
        sender.sendTextMessage(chatId,"Wrong indx");
    }
}
