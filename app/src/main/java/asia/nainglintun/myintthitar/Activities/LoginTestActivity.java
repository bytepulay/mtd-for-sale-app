package asia.nainglintun.myintthitar.Activities;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import asia.nainglintun.myintthitar.Fragments.CustomerRankFragment;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.Customer;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static asia.nainglintun.myintthitar.Activities.MainActivity.prefConfig;

public class LoginTestActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    TextView textViewName;
    private static final int REQUEST_CAMERA = 1;


    ZXingScannerView zXingScannerView;
   // ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);

        // progressDialog = new ProgressDialog(this);
        // progressDialog.setMessage("Please wait....");
//        textViewName = findViewById(R.id.loginName);
//
//        Bundle bd = getIntent().getExtras();
//        String login_Name=bd.getString("name");
//
//        textViewName.setText(login_Name);
//
//        Date currentTime = Calendar.getInstance().getTime();
//        Toast.makeText(LoginTestActivity.this,String.valueOf(currentTime),Toast.LENGTH_LONG).show();

        checkPermission();

       // finish();
    }

    @Override
    public void handleResult(Result rawResult) {
       // Comfirm.userName.setText(rawResult.getText());

    Intent intent  = new Intent(LoginTestActivity.this,Comfirm.class);
    intent.putExtra("uname",rawResult.getText());
    startActivity(intent);

        //onBackPressed();
////        if (rawResult.getText().equals("salesale123")) {
////            startActivity(new Intent(LoginTestActivity.this, SalesActivity.class));
////        }
//
//        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_SHORT).show();
//
//        Call<Customer> call = MainActivity.apiInterface.performUserLogin(rawResult.getText());
//        call.enqueue(new Callback<Customer>() {
//            @Override
//            public void onResponse(Call<Customer> call, Response<Customer> response) {
////                progressDialog.dismiss();
//                if (response.body().getResponse().equals("customer")){
//                    String name =response.body().getUserName();
//                    String rowUser = response.body().getResponse();
//                    prefConfig.writeRowUser(rowUser);
//                    prefConfig.writeName(name);
//
//
//
//                    startActivity(new Intent(LoginTestActivity.this,CustomerActivity.class));
//
//
//                }else if (response.body().getResponse().equals("sale")) {
//                    String name =response.body().getUserName();
//                    String rowUser = response.body().getResponse();
//                    prefConfig.writeRowUser(rowUser);
//                    prefConfig.writeName(name);
//                    startActivity(new Intent(LoginTestActivity.this, SalesActivity.class));
//                }else if (response.body().getResponse().equals("new")){
//                    Toast.makeText(LoginTestActivity.this, "please register", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Customer> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private static final int MY_CAMERA_PERMISSION = 1;


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkCallingOrSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION);
            }else{
                zXingScannerView.startCamera();
                zXingScannerView.setResultHandler(this);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_CAMERA_PERMISSION) {
            boolean flag = (grantResults[0] == PackageManager.PERMISSION_GRANTED) && grantResults.length != 0;

            if (!flag) {
                Toast.makeText(this, "No Camera permission", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                zXingScannerView.startCamera();
                zXingScannerView.setResultHandler(this);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}