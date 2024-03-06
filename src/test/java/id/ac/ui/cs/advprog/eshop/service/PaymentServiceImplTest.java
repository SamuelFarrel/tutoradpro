package id.ac.ui.cs.advprog.eshop.service;

import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    @Mock
    OrderRepository orderRepository;

    List<Payment> payments;
    List<Order> orders;

    @BeforeEach
    void setUp(){
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudrajat");
        orders = new ArrayList<>();
        orders.add(order) ;

        Map<String,String> paymentDataBank = Map.of("bankName", "Bank Test",
                "referenceCode", "90807010");
        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.BANK.getValue(), paymentDataBank);
        Map<String,String> paymentDataVoucher = Map.of("voucherCode", "ESHOP12345678TES");
        Payment payment2 = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        payments = new ArrayList<>();
        payments.add(payment1); payments.add(payment2);
    }

    @Test
    void testAddPayment(){
        Payment payment = payments.get(0);
        Order order = orders.get(0);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        String method = PaymentMethod.BANK.getValue();
        Map<String,String> paymentDetails = Map.of("bankName", "Bank Test",
                "referenceCode", "90807010");
        Payment result = paymentService.addPayment(order, method, paymentDetails);
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(),result.getId());
    }

    @Test
    void testCorrectSetStatus(){
        Payment payment = payments.get(0);
        Order order = orders.get(0);
        doReturn(order).when(orderRepository).findById(payment.getId());
        doReturn(payment).when(paymentRepository).findById(any(String.class));
        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(PaymentStatus.SUCCESS.getValue(),result.getStatus());
    }

    @Test
    void testInvalidSetStatus(){
        Payment payment = payments.get(0);
        Order order = orders.get(0);
        doReturn(order).when(orderRepository).findById(payment.getId());
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.setStatus(payment, "HELLO");
        });
        verify(orderRepository, times(0)).save(any(Order.class));
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testGetPayment(){
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        Payment result = paymentService.getPayment(payment.getId());
        verify(paymentRepository, times(1)).findById(payment.getId());
        assertEquals(payment.getId(),result.getId());
    }

    @Test
    void testGetPaymentNotFound(){
        Payment payment = payments.get(0);
        doReturn(null).when(paymentRepository).findById(payment.getId());
        assertNull(paymentService.getPayment(payment.getId()));
        verify(paymentRepository, times(1)).findById(payment.getId());
    }

    @Test
    void testGetAllPayments(){
        doReturn(payments).when(paymentRepository).findAll();
        List<Payment> result = paymentService.getAllPayments();
        verify(paymentRepository, times(1)).findAll();
        assertEquals(payments,result);
    }

    @Test
    void testGetAllPaymentsEmpty(){
        doReturn(new ArrayList<>()).when(paymentRepository).findAll();
        List<Payment> result = paymentService.getAllPayments();
        verify(paymentRepository, times(1)).findAll();
        assertEquals(new ArrayList<>(),result);
    }

}
