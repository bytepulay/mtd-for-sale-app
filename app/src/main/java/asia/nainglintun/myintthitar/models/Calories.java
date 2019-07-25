package asia.nainglintun.myintthitar.models;

import com.google.gson.annotations.SerializedName;

public class Calories {

    @SerializedName("id")
    private int Id;

    @SerializedName("name")
    private String Name;

    @SerializedName("address")
    private String Address;

    public String getName() {
        return Name;
    }

    public int getId() {
        return Id;
    }

    public String getAddress() {
        return Address;


    }
}
