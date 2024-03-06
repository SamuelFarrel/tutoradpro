package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentDetails) {
        Payment payment = new Payment(order.getId(), method, paymentDetails);
        paymentRepository.save(payment);
        return payment;
    }
    @Override
    public Payment setStatus(Payment payment, String status) {
        Order order = orderRepository.findById(payment.getId());
        if(order != null){
            Order newOrder = new Order(order.getId(),order.getProducts(),
                    order.getOrderTime(),order.getAuthor(),status);
            orderRepository.save(newOrder);
        } else{
            throw new NoSuchElementException();
        }
        Payment paymentSet = paymentRepository.findById(payment.getId());
        if(paymentSet != null){
            Payment newPayment = new Payment(payment.getId(),payment.getMethod(),
                    status,payment.getPaymentData());
            paymentRepository.save(newPayment);
            return newPayment;
        } else{
            throw new NoSuchElementException();
        }
    }
    @Override
    public Payment getPayment(String paymentId) {
        Payment result = paymentRepository.findById(paymentId);
        return result;
    }
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
