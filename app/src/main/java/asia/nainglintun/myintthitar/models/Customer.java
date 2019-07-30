package asia.nainglintun.myintthitar.models;

import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("response")
    private String Response;

    @SerializedName("image")
    private String Image;

    //json name ah time call ya mal
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("user_name")
    private String userName;


   @SerializedName("shop_name")
    private String shopName;


   @SerializedName("phone_number")
    private String phoneNumber;


   @SerializedName("address")
    private String address;

    @SerializedName("dob")
    private String dob;


    @SerializedName("nrc")
    private String nrc;

    @SerializedName("town_ship")
    private String town;

    public String getImage() {
        return Image;
    }

    public String getResponse() {
        return Response;
    }

    public String getUserName() {
        return userName;
    }

    public String getShopName() {
        return shopName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getDob() {
        return dob;
    }

    public String getNrc() {
        return nrc;
    }

    public String getTown() {
        return town;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
