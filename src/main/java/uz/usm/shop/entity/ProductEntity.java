package uz.usm.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    private long id;
    private long userId;
    private String name;
    private String description;
    private String image;
    private String price;
}
