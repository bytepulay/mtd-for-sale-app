package asia.nainglintun.myintthitar.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("customerRegistration.php")
    Call<Customer> performRegistration(@Query("name") String name,@Query("user_name") String userName, @Query("shop_name") String shopName, @Query("phone_number") String phoneNumber, @Query("address") String address, @Query("dob") String dob, @Query("nrc") String nrc, @Query("town_ship") String town);

    @GET("slae_invoice_userData.php")
    Call<Customer> getCustomerInfo(@Query("user_name") String userName);


        @GET("logintest.php")
    Call<Customer> performUserLogin(@Query("user_name") String UserName);

    @GET("sale_invoice_insert_data.php")
    Call<SaleInoviceData> insertSaleInvoice(@Query("voucher_number") String voucherNumber,@Query("sdate") String saleDate,@Query("qty") String qty,@Query("point_eight") String pointEight,@Query("kyat") String Kyat,@Query("pal") String Pal,@Query("yae") String Yae,@Query("gram") String Gram,@Query("cupon_code") String cuponCode,@Query("customer_id") String customerId,@Query("ring_title") String ringTitle,@Query("ring_number") String ringNumber,@Query("ring_point_eight") String ringPointEight,@Query("ring_kyat") String ringKyat,@Query("ring_pal") String ringPal,@Query("ring_yae") String ringYae,
                                            @Query("bangles_title") String banglesTitle,@Query("bangles_number") String banglesNumber,@Query("bangles_point_eight") String banglesPointEight,@Query("bangles_kyat") String banglesKyat,@Query("bangles_pal") String banglesPal,@Query("bangles_yae") String banglesYae,
                                            @Query("necklace_title") String necklaceTitle,@Query("necklace_number") String necklaceNumber,@Query("necklace_point_eight") String necklacePointEight,@Query("necklace_kyat") String necklaceKyat,@Query("necklace_pal") String necklacePal,@Query("necklace_yae") String necklaceYae,
                                            @Query("earring_title") String earringTitle,@Query("earring_number") String earringNumber,@Query("earring_point_eight") String earringPointEight,@Query("earring_kyat") String earringKyat,@Query("earring_pal") String earringPal,@Query("earring_yae") String earringYae);


    @GET("order_invoice_insert_data.php")
    Call<OrderInoviceData> insertOrderInvoice(@Query("voucher_number") String voucherNumber,@Query("order_date") String saleDate,@Query("qty") String qty,@Query("point_eight") String pointEight,@Query("kyat") String Kyat,@Query("pal") String Pal,@Query("yae") String Yae,@Query("gram") String Gram,@Query("cupon_code") String cuponCode,@Query("customer_id") String customerId,@Query("ring_title") String ringTitle,@Query("ring_number") String ringNumber,@Query("ring_point_eight") String ringPointEight,@Query("ring_kyat") String ringKyat,@Query("ring_pal") String ringPal,@Query("ring_yae") String ringYae,
                                            @Query("bangles_title") String banglesTitle,@Query("bangles_number") String banglesNumber,@Query("bangles_point_eight") String banglesPointEight,@Query("bangles_kyat") String banglesKyat,@Query("bangles_pal") String banglesPal,@Query("bangles_yae") String banglesYae,
                                            @Query("necklace_title") String necklaceTitle,@Query("necklace_number") String necklaceNumber,@Query("necklace_point_eight") String necklacePointEight,@Query("necklace_kyat") String necklaceKyat,@Query("necklace_pal") String necklacePal,@Query("necklace_yae") String necklaceYae,
                                            @Query("earring_title") String earringTitle,@Query("earring_number") String earringNumber,@Query("earring_point_eight") String earringPointEight,@Query("earring_kyat") String earringKyat,@Query("earring_pal") String earringPal,@Query("earring_yae") String earringYae);

    @GET("salehistory.php")
    Call<List<Salehistory>> getSaleHistoryInfo(@Query("user_name") String userName);



    @GET("orderhistory.php")
    Call<List<Orderhistory>> getOrderHistoryInfo(@Query("user_name") String userName);

    @GET("getCalories.php")
    Call<List<Calories>> getCaloriesInfo(@Query("item_type") String item_type);


}