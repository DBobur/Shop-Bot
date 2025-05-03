package uz.usm.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new MyBot(
                    "6569229687:AAFrmidOBpO0yTsahn5lO2QNoRf6OefyidM",
                    "@coding_chat_bot"));
            System.out.println("Ready");


        } catch (TelegramApiException e) {

        }
    }
}