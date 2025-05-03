package uz.usm.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private long id;
    private long chatId;
    private String firstName;
    private String lastName;
    private String bio;
    private String number;
    private UserRole role;
    private UserState userState;
}
