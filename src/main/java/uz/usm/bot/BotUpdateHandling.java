package uz.usm.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.usm.shop.entity.UserEntity;
import uz.usm.shop.entity.UserState;
import uz.usm.shop.service.UserService;

import java.util.List;

public class BotUpdateHandling{

    UserService userService = new UserService();

    public SendMessage handle(Message message) {
        Chat chat = message.getChat();
        Long chatId = chat.getId();
        UserEntity user = userService.getUserByChatId(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if(user == null){
            UserEntity user1 = new UserEntity();
            user1.setId(chatId);
            user1.setFirstName(chat.getFirstName());
            user1.setLastName(chat.getLastName());
            user1.setBio(chat.getBio());
            user1.setUserState(UserState.SHARE_NUMBER);
            userService.saveUser(user1);

            sendMessage = new SendMessage();
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

            KeyboardRow keyboardRow = new KeyboardRow();

            KeyboardButton keyboardButton = new KeyboardButton();
            keyboardButton.setRequestContact(true);
            keyboardButton.setText("Send your contact");
            keyboardRow.add(keyboardButton);
            replyKeyboardMarkup.setKeyboard(List.of(keyboardRow));
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            sendMessage.setText("Send your contact");
            return sendMessage;
        }else {
            switch (user.getUserState()) {
                case SHARE_NUMBER -> {
                    Contact contact = message.getContact();
                    String phoneNumber = contact.getPhoneNumber();
                    user.setNumber(phoneNumber);
                    user.setUserState(UserState.REGISTERED);
                    sendMessage.setText("Welcome " + user.getFirstName() + " " + user.getLastName());
                    return sendMessage;
                }
                case REGISTERED -> {
                    ReplyKeyboardMarkup replyKeyboardMarkup = Buttons.startMenu();
                    sendMessage.setReplyMarkup(replyKeyboardMarkup);
                    return sendMessage;
                }

            }
        }

        sendMessage = new SendMessage();
        sendMessage.setText("Wrong input message");
        return sendMessage;
    }
}
