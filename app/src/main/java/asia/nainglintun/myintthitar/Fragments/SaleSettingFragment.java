package asia.nainglintun.myintthitar.Fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import asia.nainglintun.myintthitar.Activities.CustomerActivity;
import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.Activities.SalesActivity;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.ImageClass;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static asia.nainglintun.myintthitar.Activities.MainActivity.prefConfig;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleSettingFragment extends Fragment implements View.OnClickListener {

    private Button btnSaleChangePassword,bnLogout;
    private EditText editTextSaleChangePassword,editTextSaleComfirmPassword;
    private CircleImageView saleProfile;
    private final int IMG_REQUEST=1;
   private Bitmap bitmap;

    private Toolbar toolbar;
    public SaleSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale_setting, container, false);

        toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Setting");

        btnSaleChangePassword = view.findViewById(R.id.btnSaleChangePasswordSave);
        bnLogout = view.findViewById(R.id.btnLogout);
        editTextSaleChangePassword = view.findViewById(R.id.saleChangePassword);
        editTextSaleComfirmPassword = view.findViewById(R.id.saleComfirmPassword);
        saleProfile = view.findViewById(R.id.saleProfile);
        bitmap = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.profile);
        saleProfile.setImageBitmap(bitmap);
        saleProfile.setOnClickListener(this);
        btnSaleChangePassword.setOnClickListener(this);
        bnLogout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saleProfile:
                selectSaleImage();
                break;
            case R.id.btnSaleChangePasswordSave:
               uploadImage();
                Toast.makeText(getContext(), "Save Password", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnLogout:

                MainActivity.prefConfig.DeleteName(MainActivity.prefConfig.readName());
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();


                break;
        }
    }


    private void uploadImage(){
        Log.e("work","upload method is work");
        String Image = imageToString();
        String title = editTextSaleChangePassword.getText().toString();
        Call<ImageClass> call = MainActivity.apiInterface.uploadImage(title,Image);
        call.enqueue(new Callback<ImageClass>() {
            @Override
            public void onResponse(Call<ImageClass> call, Response<ImageClass> response) {
//                ImageClass imageClass = response.body();
//
//               Toast.makeText(getContext(), "Server Response:" + imageClass.getResponse(), Toast.LENGTH_SHORT).show();
                String result = response.body().getResponse();
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onFailure(Call<ImageClass> call, Throwable t) {

            }
        });
    }

    private void selectSaleImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                saleProfile.setImageBitmap(bitmap);
                //imageView.setVisibility(View.VISIBLE);
//                Attributes.Name.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }
}
