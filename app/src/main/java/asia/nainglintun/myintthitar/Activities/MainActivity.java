package asia.nainglintun.myintthitar.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.ApiClient;
import asia.nainglintun.myintthitar.models.ApiInterface;
import asia.nainglintun.myintthitar.models.Customer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static ApiInterface apiInterface;
    public static PrefConfig prefConfig;
    private EditText editTextUserName, editTextPassword;
    private Button btnLogin, btnScan;
    private ProgressDialog progressDialog;



    //public static TextView qrUsername ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        if (!isConnected(getApplicationContext())) {
            buildDialog(MainActivity.this).show();
        } else {
            setContentView(R.layout.activity_main);

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait....");

            prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        editTextUserName = findViewById(R.id.etUserName);
       // editTextPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnScan = findViewById(R.id.btnScan);
        //qrUsername = findViewById(R.id.qrUsername);

        final Activity activity = this;


//            if (!prefConfig.readName().isEmpty() &&  prefConfig.readRowUser().equals("customer") ){
//                startActivity(new Intent(MainActivity.this,CustomerActivity.class));
//                finish();
//            }else
//
                if (!prefConfig.readName().isEmpty() &&  prefConfig.readRowUser().equals("sale") ){

                startActivity(new Intent(MainActivity.this,SalesActivity.class));
                 finish();
            }


        // btnScan.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginTestActivity.class)));
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // progressDialog.show();
                startActivity(new Intent(getApplicationContext(), LoginTestActivity.class));
            }
        });
//                IntentIntegrator integrator = new IntentIntegrator(activity);
//                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
//                integrator.setPrompt("Scan");
//                integrator.setCameraId(0);
//                integrator.setBeepEnabled(true);
//                integrator.setOrientationLocked(true);
//                integrator.setBarcodeImageEnabled(false);
//                integrator.initiateScan();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUserName.getText().toString().trim();

                progressDialog.show();
                Call<Customer> call = MainActivity.apiInterface.performUserLogin(username);
                call.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        progressDialog.dismiss();
                        if (response.body().getResponse().equals("customer")){
                            String name =response.body().getUserName();
                            String rowUser = response.body().getResponse();
                            prefConfig.writeRowUser(rowUser);
                          prefConfig.writeName(name);
                          prefConfig.DeleteName(name);

                            startActivity(new Intent(MainActivity.this,CustomerActivity.class));
                        }else if (response.body().getResponse().equals("sale")) {
                            String name =response.body().getUserName();
                            String rowUser = response.body().getResponse();
                            prefConfig.writeRowUser(rowUser);
                            prefConfig.writeName(name);
                            startActivity(new Intent(MainActivity.this, SalesActivity.class));
                        }else if (response.body().getResponse().equals("new")){
                            Toast.makeText(MainActivity.this, "please register", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
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


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
//
//        if (result!=null){
//            if(result.getContents()==null){
//                Toast.makeText(this,"You cancelled the scanning",Toast.LENGTH_LONG).show();
//            }else {
//                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(MainActivity.this,LoginTestActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("name",result.getContents());
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//
//        }else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//
//    }
//
}
