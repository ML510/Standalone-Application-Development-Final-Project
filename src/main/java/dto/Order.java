package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    private Long id;
    private String date;
    private String payType;
    private Double totalCost;
    private String customerId;
    private String employeeId;
//    private List<OrderDetail> orderDetails;

}
