package asia.nainglintun.myintthitar.Activities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefManager
{

    private static SharePrefManager instance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME = "myshareprefq12";
    private static final String KEY_USERNAME="username";
    private static final String KEY_USER_PASSWORD="useremail";
    //private static final String KEY_USER_ID="userid";

    private SharePrefManager(Context context) {
        mCtx = context;
    }



    public static synchronized SharePrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharePrefManager(context);
        }
        return instance;
    }

    public boolean userLogin(String username, String password){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //editor.putInt(KEY_USER_ID,id);
        editor.putString(KEY_USER_PASSWORD,password);
        editor.putString(KEY_USERNAME,username);

        editor.apply();

        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME,null)!=null){
            return true;
        }

        return false;
    }

    public boolean Logout(){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public String getUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME,null);
    }

    public String getKeyUserPassword(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_PASSWORD,null);
    }
}
