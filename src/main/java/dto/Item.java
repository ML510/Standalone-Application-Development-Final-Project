package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {

    private Long id;
    private String name;
    private String categories;
    private String size;
    private Double price;
    private Integer qty;
    private String supplierId;

}
