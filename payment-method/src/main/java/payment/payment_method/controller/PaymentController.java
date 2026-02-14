package payment.payment_method.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Controller
public class PaymentController {

    private final String ESEWA_URL = "https://rc-epay.esewa.com.np/api/epay/main/v2/form"; // test URL
    private final String SECRET_KEY = "8gBm/:&EnhH.1/q";

    @GetMapping("/payment")
    public String home() {
        return "payment"; // returns a simple HTML page with two buttons: eSewa / ConnectIPS
    }

    @PostMapping("/pay-esewa")
    public String payEsewa(
            @RequestParam double amount,
            @RequestParam String transactionUuid,
            Model model
    ) {
        // For demo, tax, service, delivery = 0
        double tax = 0;
        double service = 0;
        double delivery = 0;

        String productCode = "EPAYTEST";
        double totalAmount = amount + tax + service + delivery;

        String signedFields = "total_amount,transaction_uuid,product_code";
        String message = "total_amount=" + totalAmount +
                ",transaction_uuid=" + transactionUuid +
                ",product_code=" + productCode;


        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secretKey);
            String signature = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));

            model.addAttribute("amount", amount);
            model.addAttribute("tax_amount", tax);
            model.addAttribute("total_amount", totalAmount);
            model.addAttribute("transaction_uuid", transactionUuid);
            model.addAttribute("product_code", productCode);
            model.addAttribute("product_service_charge", service);
            model.addAttribute("product_delivery_charge", delivery);
            model.addAttribute("success_url", "http://localhost:8080/success");
            model.addAttribute("failure_url", "http://localhost:8080/failure");
            model.addAttribute("signed_field_names", signedFields);
            model.addAttribute("signature", signature);
            model.addAttribute("esewa_url", ESEWA_URL);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "esewa_form"; // HTML page with auto-submit form to eSewa
    }

    @GetMapping("/success")
    @ResponseBody
    public String success() {
        return "Payment Successful ";
    }

    @GetMapping("/failure")
    @ResponseBody
    public String failure() {
        return "Payment Failed ";
    }

    @PostMapping("/pay-connectips")
    @ResponseBody
    public String payConnectIps(@RequestParam double amount) {
        // Demo redirect URL (replace with actual ConnectIPS URL)
        return "Redirect user to ConnectIPS payment page with amount: " + amount;
    }
}
