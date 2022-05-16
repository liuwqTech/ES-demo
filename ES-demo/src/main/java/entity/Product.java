package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private Integer categoryId;
    private Integer kind;
    private String title;
    private Integer brandId;
    private String name;
    private String origin;
    private String productionDate;
    private Double price;
    private Integer amount;
    private Integer sales;
    private String imageUrl;
    private String sendAddress;
    private String parcelType;
    private String salesService;
    private Integer creatorId;
    private Integer status;
    private Date createTime;
    private Date updateTime;

}
