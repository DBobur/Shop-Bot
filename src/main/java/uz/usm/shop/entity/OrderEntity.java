package uz.usm.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    private long id;
    private long userId;
    private long productId;
    private int quantity;
}
