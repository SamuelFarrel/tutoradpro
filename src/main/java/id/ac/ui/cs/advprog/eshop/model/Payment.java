package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;

@Builder
@Getter
@Setter
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
        if (myMap.containsKey("")||myMap.containsKey(null)){
            return true;
        }
        if(myMap.containsValue("")||myMap.containsValue(null)){
            return true;
        }
        return false;
    }

    public Payment(String id,String method,String status,Map<String,String> paymentData){
        this(id, method, paymentData);
        String[] statusList = {"FAILED","SUCCESS"};
        if(Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))){
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }

    public Payment(String id,String method,Map<String,String> paymentData){
        this(id, paymentData);
        this.method = method;
        if(method.equals("BANK")){
            if(containsNullOrEmptyValues(paymentData)){
                throw new IllegalArgumentException();
            }
        } else if(method.equals("VOUCHER") && (containsNullOrEmptyValues(paymentData)||
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
        String[] statusList = {"FAILED","SUCCESS"};
        if(Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))){
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }

}
