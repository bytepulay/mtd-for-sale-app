package asia.nainglintun.myintthitar.activities.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("customerRegistration.php")
    Call<Customer> performRegistration(@Query("user_name") String userName,@Query("shop_name") String shopName,@Query("phone_number") String phoneNumber,@Query("address") String address,@Query("dob") String dob,@Query("nrc") String nrc,@Query("town_ship") String town);

//    @GET("login.php")
//    Call<User> performUserLogin(@Query("user_name") String UserName,@Query("user_password") String UserPassword);

}
