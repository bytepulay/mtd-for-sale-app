package asia.nainglintun.myinthidar.models;

import com.google.gson.annotations.SerializedName;

public class Sale {
    @SerializedName("id")
    private int id;

    @SerializedName("response")
    private String Response;

    @SerializedName("profile")
    private String profile;

    @SerializedName("status")
    private String status;

    @SerializedName("name")
    private String name;

    @SerializedName("group_name")
    private String group_name;

    @SerializedName("group_password")
    private String group_password;

    public Sale(int id, String name) {
        id = this.id;
        name = this.name;
    }


    public String getStatus() {
        return status;
    }

    public String getProfile() {
        return profile;
    }

    public String getName() {
        return name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public String getGroup_password() {
        return group_password;
    }

    public String getResponse() {
        return Response;
    }

    public int getId() {
        return id;
    }
}
