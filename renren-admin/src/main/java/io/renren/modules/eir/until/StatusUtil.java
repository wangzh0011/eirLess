package io.renren.modules.eir.until;

public class StatusUtil {

    public static String transition(String status){
        String message = "";
        if(status == null){
            status = "";
        }
        switch (status) {
            case "0":
                message = "待回复";
                break;
            case "1":
                message = "已回复";
                break;
            case "2":
                message = "挂起";
                break;
            default:
                message = "待回复";
                break;
        }
        return message;
    }
}
