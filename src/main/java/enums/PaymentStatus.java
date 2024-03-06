package enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    FAILED("FAILED"),
    SUCCESS("SUCCESS");

    private final String value;

    private PaymentStatus(String value){
        this.value = value;
    }

    public static boolean contains(String param){
        for(OrderStatus orderStatus:OrderStatus.values()){
            if(orderStatus.name().equals(param)){
                return true;
            }
        }
        return false;
    }
}
