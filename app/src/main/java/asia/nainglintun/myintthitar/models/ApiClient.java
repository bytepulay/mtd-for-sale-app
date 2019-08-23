package asia.nainglintun.myintthitar.models;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

   // private static final String BASE_URL = "http://mtdatabase.com/mtd/";

    private static final String BASE_URL = "http://167.71.193.226/mtd/";





    private static Retrofit retrofit = null;

    private ApiClient(){}
    public static Retrofit getApiClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
