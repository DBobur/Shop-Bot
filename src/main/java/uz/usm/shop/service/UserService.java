package uz.usm.shop.service;


import org.telegram.telegrambots.meta.api.objects.User;
import uz.usm.shop.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static List<UserEntity> users = new ArrayList<>();

    public UserEntity saveUser(UserEntity user) {
        users.add(user);
        return user;
    }

    public UserEntity getUserById(long userId) {
        for (UserEntity user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }
    public UserEntity getUserByChatId(long chatId) {
        for (UserEntity user : users) {
            if (user.getId() == chatId) {
                return user;
            }
        }
        return null;
    }
}
