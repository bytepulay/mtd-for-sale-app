package asia.nainglintun.myintthitar.models;


import com.google.gson.annotations.SerializedName;

import retrofit2.http.Query;

public class Notification {

    @SerializedName("customer_id")
    private String customer_id;


    @SerializedName("noti_one")
    private String noti_one;

    @SerializedName("noti_group")
    private String noti_group;


    public String getNoti_one() {
        return noti_one;
    }

    public String getNoti_group() {
        return noti_group;
    }

    public String getCustomer_id() {
        return customer_id;
    }
}
