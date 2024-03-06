package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Map<String,String>> testCaseVoucher = new ArrayList<>();
    private List<Map<String,String>> testCaseBank = new ArrayList<>();

    @BeforeEach
    void setUp(){
        Map<String,String> voucherCorrect = new HashMap<>();
        voucherCorrect.put("voucherCode","ESHOP12345678TES");
        testCaseVoucher.add(voucherCorrect);
        Map<String,String> voucherIncorrect = new HashMap<>();
        voucherIncorrect.put("voucherCode","ESHOP1234A678TESTS");
        testCaseVoucher.add(voucherIncorrect);

        Map<String,String> bankCorrect = new HashMap<>();
        bankCorrect.put("bankName","Bank Test");
        bankCorrect.put("referenceCode","90807010");
        testCaseBank.add(bankCorrect);
        Map<String,String> bankIncorrect = new HashMap<>();
        bankIncorrect.put("bankName","");
        testCaseBank.add(bankIncorrect);
    }

    @Test
    void createAndCheckData(){
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("Test","Correct");
        String id = "20";
        String method = PaymentMethod.BANK.getValue();
        String status = PaymentStatus.SUCCESS.getValue();

        Payment payment = new Payment(id,method,status,paymentData);

        assertEquals(id,payment.getId());
        assertEquals(method,payment.getMethod());
        assertEquals(status,payment.getStatus());
        assertEquals(paymentData,payment.getPaymentData());

    }

    @Test
    void testCreateNullValuePaymentData(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1",null);
        });
    }

    @Test
    void testCreateEmptyStringValuePaymentData(){
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("","");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1",paymentData);
        });
    }

    @Test
    void testValidVoucher(){
        Map<String,String> paymentData = testCaseVoucher.get(0);
        Payment payment = new Payment("1",PaymentMethod.VOUCHER.getValue()
                ,paymentData);
        assertEquals("ESHOP12345678TES",payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testInvalidVoucher(){
        Map<String,String> paymentData = testCaseVoucher.get(1);
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1",PaymentMethod.VOUCHER.getValue()
                    ,paymentData);
        });
    }

    @Test
    void testValidBank(){
        Map<String,String> paymentData = testCaseBank.get(0);
        Payment payment = new Payment("1",PaymentMethod.BANK.getValue(),
                paymentData);
        assertEquals("Bank Test",payment.getPaymentData().get("bankName"));
        assertEquals("90807010",payment.getPaymentData().get("referenceCode"));
    }

    @Test
    void testInvalidBank(){
        Map<String,String> paymentData = testCaseBank.get(1);
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1",PaymentMethod.BANK.getValue(),
                    paymentData);
        });
    }

    @Test
    void testCreateInvalidStatus(){
        Map<String,String> paymentData = testCaseBank.get(1);
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1",PaymentMethod.BANK.getValue(),
                    "HELLO",new HashMap<>());
        });
    }

    @Test
    void testSetStatusCorrect(){
        Map<String,String> paymentData = testCaseBank.get(1);
        Payment payment = new Payment("1",PaymentMethod.BANK.getValue(),
                PaymentStatus.FAILED.getValue(),new HashMap<>());
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(),payment.getStatus());
    }

    @Test
    void testSetStatusInvalid(){
        Map<String,String> paymentData = testCaseBank.get(1);
        Payment payment = new Payment("1",PaymentMethod.BANK.getValue(),
                PaymentStatus.FAILED.getValue(),new HashMap<>());
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("HELLO"));
    }
}
