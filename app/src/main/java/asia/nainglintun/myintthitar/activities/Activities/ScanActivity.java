package asia.nainglintun.myintthitar.activities.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;

import java.io.Serializable;

import asia.nainglintun.myintthitar.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView zXingScannerView;

    Serializable afterScan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);


        checkPermission();


        zXingScannerView.setResultHandler(this);

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

    @Override
    public void handleResult(Result rawResult) {

//        afterScan = getIntent().getSerializableExtra("afterscan");
//
//        if(afterScan instanceof AfterScan){
//
//            ((AfterScan)afterScan).afterScanJob(this, rawResult.getText());
//        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
