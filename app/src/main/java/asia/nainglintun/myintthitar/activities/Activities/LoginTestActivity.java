package asia.nainglintun.myintthitar.activities.Activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.Calendar;
import java.util.Date;

import asia.nainglintun.myintthitar.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission_group.CAMERA;

public class LoginTestActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    TextView textViewName;
    private static final int REQUEST_CAMERA = 1;

    ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);


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
        //MainActivity.qrUsername.setText(rawResult.getText());
        //onBackPressed();
        if (rawResult.getText().equals("salesale123")) {
            startActivity(new Intent(LoginTestActivity.this, SalesActivity.class));
        }

        finish();
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