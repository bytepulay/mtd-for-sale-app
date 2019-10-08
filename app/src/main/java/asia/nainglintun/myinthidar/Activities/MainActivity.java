package asia.nainglintun.myinthidar.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import asia.nainglintun.myinthidar.R;
import asia.nainglintun.myinthidar.models.ApiClient;
import asia.nainglintun.myinthidar.models.ApiInterface;
import asia.nainglintun.myinthidar.models.Customer;
import asia.nainglintun.myinthidar.models.Sale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static ApiInterface apiInterface;
    public static PrefConfig prefConfig;
    private EditText editTextUserName, editTextPassword;
    private Button btnLogin, btnScan;
    private ProgressDialog progressDialog;
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isConnected(getApplicationContext())) {
            buildDialog(MainActivity.this).show();
        } else {
            setContentView(R.layout.activity_main);
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait....");

            prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        editTextUserName = findViewById(R.id.edUserName);
        editTextPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);

        final Activity activity = this;
                if (!prefConfig.readName().isEmpty() && !prefConfig.readName().equals("User")){
                startActivity(new Intent(MainActivity.this,SalesActivity.class));
                 finish();
            }



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUserName.getText().toString().trim();
                String pass = editTextPassword.getText().toString().trim();
                progressDialog.show();
                Call<Sale> call = MainActivity.apiInterface.performUserLoginSale(username,pass);
                call.enqueue(new Callback<Sale>() {
                    @Override
                    public void onResponse(Call<Sale> call, Response<Sale> response) {
                        progressDialog.dismiss();
                        if (response.body().getResponse().equals("ok")){
                            String name =response.body().getGroup_name();
                            prefConfig.writeName(name);
                            startActivity(new Intent(MainActivity.this,SalesActivity.class));
                        }else if (response.body().getResponse().equals("fail")) {
                            Toast.makeText(MainActivity.this, "please register", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Sale> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Connection Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

        //finish();

    }

    public boolean isConnected(Context context){

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo !=null && netinfo.isConnectedOrConnecting()){
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


            if((mobile !=null && mobile.isConnectedOrConnecting()) || (wifi !=null && wifi.isConnectedOrConnecting()))
                return true;
            else
                return false;
        }else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c){

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or Wifi to access this.Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });




        return builder;
    }


}
