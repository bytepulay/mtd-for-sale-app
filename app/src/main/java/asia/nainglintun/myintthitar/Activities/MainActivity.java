package asia.nainglintun.myintthitar.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import asia.nainglintun.myintthitar.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUserName,editTextPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    private String Owner_User = "owner",Owner_Password ="owner123";
    private String Sale_User = "sale",Sale_Password ="sale123";
    private String Customer_User = "customer",Customer_Password ="customer123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUserName = findViewById(R.id.etUserName);
        editTextPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

//        progressDialog = new ProgressDialog(this);
//
//        progressDialog.setMessage("Please wait....");



            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = editTextUserName.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();
                    if(username.equals("owner")&&password.equals("owner123")) {
                        Intent intent = new Intent(MainActivity.this, OwnerActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Sign in Successful",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"User Name Or Password is Incorrect",Toast.LENGTH_SHORT).show();
                    }

                     if(username.equals("customer")&&password.equals("customer123")){
                        Intent in = new Intent(MainActivity.this, CustomerActivity.class);
                        startActivity(in);
                         Toast.makeText(getApplicationContext(),"Sign in Successful",Toast.LENGTH_SHORT).show();
                    }else {
                         Toast.makeText(getApplicationContext(),"User Name Or Password is Incorrect",Toast.LENGTH_SHORT).show();
                     }

                    if(username.equals("sale")&&password.equals("sale123")){
                        Intent ii = new Intent(MainActivity.this, SalesActivity.class);
                        startActivity(ii);
                        Toast.makeText(getApplicationContext(),"Sign in Successful",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"User Name Or Password is Incorrect",Toast.LENGTH_SHORT).show();
                    }
                }
            });




    }

//    private void userLogin() {
//        final String username = editTextUserName.getText().toString().trim();
//        final String password = editTextPassword.getText().toString().trim();
//        progressDialog.show();
//
//    }
}
