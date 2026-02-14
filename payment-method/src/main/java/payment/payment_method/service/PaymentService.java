package payment.payment_method.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public String generatePaymentUrl(double amount, String method){
        if("ESEWA".equalsIgnoreCase(method)){
            return  "https://esewa.com.np/pay?amt=" + amount
                +"&pid = TXN123"
                + "&scd=EPAYTEST"
                +"&su=http://localhost:8080/success"
                + "&fu=http://localhost:8080/failure";
        }
        if ("CONNECTIPS".equalsIgnoreCase(method)) {
            return "https://connectips.com/payment?MERCHANTID=TEST"
                    + "&TXNID=TXN123"
                    + "&TXNAMT=" + amount
                    + "&SUCCESS_URL=http://localhost:8080/success"
                    + "&FAILURE_URL=http://localhost:8080/failure";
        }

        throw new RuntimeException("Invalid payment method");
    }

}

