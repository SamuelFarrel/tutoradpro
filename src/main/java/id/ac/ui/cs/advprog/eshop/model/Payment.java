package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
@Setter
public class Payment {
    String id;
    String status;
    String method;
    Map<String,String> paymentData;

    public Payment(String id,String method,String Status,Map<String,String> paymentData){

    }

    public Payment(String id,String method,Map<String,String> paymentData){

    }


    public Payment(String id,Map<String,String> paymentData){

    }
}
