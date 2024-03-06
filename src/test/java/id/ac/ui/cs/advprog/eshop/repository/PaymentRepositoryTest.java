package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    public void setUp() {
        Map<String,String> paymentDataBank = new HashMap<>();
        Map<String,String> paymentDataVoucher = new HashMap<>();
        paymentDataBank.put("bankName","Bank Test");
        paymentDataBank.put("referenceCode","90807010");
        paymentDataVoucher.put("voucherCode","ESHOP12345678TES");
        Payment payment1 = new Payment("1", "BANK", paymentDataBank);
        Payment payment2 = new Payment("2", "VOUCHER", paymentDataVoucher);
        payments.add(payment1); payments.add(payment2);
    }

    @Test
    void testSaveCreate(){
        Payment payment = payments.get(0);
        paymentRepository.save(payment);
        Payment compare = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(),compare.getId());
        assertEquals(payment.getMethod(),compare.getMethod());
        assertEquals(payment.getPaymentData(),compare.getPaymentData());
    }

    @Test
    void testSaveUpdate(){
        Payment payment = payments.get(0);
        paymentRepository.save(payment);
        Payment compare = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(),compare.getId());
        assertEquals(payment.getMethod(),compare.getMethod());
        assertEquals(payment.getPaymentData(),compare.getPaymentData());

        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("bankName","Bank Test");
        paymentData.put("referenceCode","90807010");
        Payment paymentUpdate = new Payment(payment.getId(),"BANK",paymentData);
        paymentRepository.save(paymentUpdate);
        Payment compareUpdate = paymentRepository.findById(payment.getId());
        assertEquals(paymentUpdate.getId(),compareUpdate.getId());
        assertEquals(paymentUpdate.getMethod(),compareUpdate.getMethod());
        assertEquals(paymentUpdate.getPaymentData(),compareUpdate.getPaymentData());
    }

    @Test
    void testFindByIdIfFound(){
        for (Payment payment:payments){
            paymentRepository.save(payment);
        }

        Payment result = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payments.get(0).getId(),result.getId());
        assertEquals(payments.get(0).getMethod(),result.getMethod());
        assertEquals(payments.get(0).getPaymentData(),result.getPaymentData());
    }

    @Test
    void testFindByIdIfNotFound(){
        for (Payment payment:payments){
            paymentRepository.save(payment);
        }
        Payment result = paymentRepository.findById("hello");
        assertNull(result);
    }

}
