package asia.nainglintun.myintthitar.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.activities.models.ApiClient;
import asia.nainglintun.myintthitar.activities.models.ApiInterface;

public class MainActivity extends AppCompatActivity {

    public static ApiInterface apiInterface;
    private EditText editTextUserName, editTextPassword;
    private Button btnLogin, btnScan;
    private ProgressDialog progressDialog;
    private String Owner_User = "owner", Owner_Password = "owner123";
    private String Sale_User = "sale", Sale_Password = "sale123";
    private String Customer_User = "customer", Customer_Password = "customer123";

    //public static TextView qrUsername ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        editTextUserName = findViewById(R.id.etUserName);
        editTextPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnScan = findViewById(R.id.btnScan);
        //qrUsername = findViewById(R.id.qrUsername);

        final Activity activity = this;


         btnScan.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), LoginTestActivity.class)));

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
                String password = editTextPassword.getText().toString().trim();

                if (username.equals("customer") && password.equals("customer123")) {
                    Intent in = new Intent(MainActivity.this, CustomerActivity.class);
                    startActivity(in);
                    Toast.makeText(getApplicationContext(), "Sign in Successful", Toast.LENGTH_SHORT).show();
                } else if (username.equals("sale") && password.equals("sale123")) {
                    Intent ii = new Intent(MainActivity.this, SalesActivity.class);
                    startActivity(ii);
                    Toast.makeText(getApplicationContext(), "Sign in Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });


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
