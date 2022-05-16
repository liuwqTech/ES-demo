package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private Long id;
    private String name;
    private Long parentId;
    private Integer level;
    private Integer sort;
    private Date createTime;
    private Date updateTime;
    private String imageUrl;

}
