package asia.nainglintun.myinthidar.models;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //public static final String BASE_URL = "http://app.myinthidarjewellery.com";

    public static final String BASE_URL = "http://167.71.193.226/subdomain/MTD/public/mtd/";





    private static Retrofit retrofit = null;

    private ApiClient(){}
    public static Retrofit getApiClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
