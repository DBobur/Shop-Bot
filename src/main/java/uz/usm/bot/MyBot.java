package uz.usm.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {

    private static String username;

    public MyBot(String botToken,String username) {
        super(botToken);
        MyBot.username = username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            BotUpdateHandling botUpdateHandling = new BotUpdateHandling();
            SendMessage sendMessage = botUpdateHandling.handle(update.getMessage());
            messageExecute(sendMessage);
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
        }
    }

    private void messageExecute(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBotUsername() {
        return username;
    }
}
