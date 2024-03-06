package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class Payment {
    String id;
    String status;
    String method;
    Map<String,String> paymentData;

    private boolean checkVoucherValid(String voucherCode){
        String regex = "^ESHOP.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*\\d.*$";
        return voucherCode.length() == 16 && voucherCode.matches(regex);
    }

    private boolean containsNullOrEmptyValues(Map<String, String> myMap) {
        if (myMap == null) {
            return true;
        }
        if (myMap.containsKey("")){
            return true;
        }
        for (String value : myMap.values()) {
            if (value == null || value.isEmpty() || value.equals("")) {
                return true;
            }
        }
        return false;
    }

    public Payment(String id,String method,String status,Map<String,String> paymentData){
        this(id, method, paymentData);
        setStatus(status);
    }

    public Payment(String id,String method,Map<String,String> paymentData){
        this(id, paymentData);
        setPaymentMethod(method);
        if(method.equals(PaymentMethod.BANK.getValue())){
            if(containsNullOrEmptyValues(paymentData)){
                throw new IllegalArgumentException();
            }
        } else if(method.equals(PaymentMethod.VOUCHER.getValue()) && (containsNullOrEmptyValues(paymentData)||
            !checkVoucherValid(paymentData.get("voucherCode")))){
                throw new IllegalArgumentException();
        }
    }


    public Payment(String id,Map<String,String> paymentData){
        this.id = id;
        if(containsNullOrEmptyValues(paymentData)){
            throw new IllegalArgumentException();
        }
        this.paymentData = paymentData;

    }

    public void setStatus(String status){
        if(PaymentStatus.contains(status)){
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setPaymentMethod(String method){
        if(PaymentMethod.contains(method)){
            this.method = method;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
