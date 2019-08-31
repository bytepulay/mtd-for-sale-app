package asia.nainglintun.myintthitar.models;

import com.google.gson.annotations.SerializedName;

public class Sale {

    @SerializedName("profile")
    private String profile;

    @SerializedName("status")
    private String status;


    public String getStatus() {
        return status;
    }

    public String getProfile() {
        return profile;
    }
}
