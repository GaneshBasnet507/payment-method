package payment.payment_method.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PaymentRequest {
    private double amount;
    private String method;

}
