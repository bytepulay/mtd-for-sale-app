package asia.nainglintun.myintthitar.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import asia.nainglintun.myintthitar.Fragments.CustomerSettingFragment;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.Customer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static asia.nainglintun.myintthitar.Activities.MainActivity.prefConfig;

public class Comfirm extends AppCompatActivity {
    //public static TextView userName ;
   // private TextView userName;
    private Button bnComfirm;
    private EditText edComfirm;
    public String comfirmCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm);
        String text = getIntent().getStringExtra("uname");
        edComfirm = findViewById(R.id.editComfirm);
        bnComfirm = findViewById(R.id.btnComfirm);

       try{
        bnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comfirmCode = edComfirm.getText().toString();
                Call<Customer> call = MainActivity.apiInterface.performUserLogin(text);
                call.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
//                progressDialog.dismiss();
                        if (response.body().getResponse().equals("customer")) {
                            String name = response.body().getUserName();
                            String rowUser = response.body().getResponse();
                            String password = response.body().getPassword();
                            prefConfig.writeRowUser(rowUser);
                            prefConfig.writeName(name);

                            if (password.equals(comfirmCode)) {
                                startActivity(new Intent(Comfirm.this, CustomerActivity.class));
                                finish();
                            } else if (!password.equals(comfirmCode)) {
                                prefConfig.DeleteName(name);
                                startActivity(new Intent(Comfirm.this, MainActivity.class));
                                finish();
                            }

                        } else if (response.body().getResponse().equals("sale")) {
                            String name = response.body().getUserName();
                            String rowUser = response.body().getResponse();
                            prefConfig.writeRowUser(rowUser);
                            prefConfig.writeName(name);
                            startActivity(new Intent(Comfirm.this, SalesActivity.class));
                        } else if (response.body().getResponse().equals("new")) {
                            Toast.makeText(Comfirm.this, "please register", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {

                    }
                });

            }
        });

    }catch (Exception e){
           e.printStackTrace();
       }


    }
}
