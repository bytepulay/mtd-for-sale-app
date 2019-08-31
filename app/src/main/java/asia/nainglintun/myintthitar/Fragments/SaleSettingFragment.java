package asia.nainglintun.myintthitar.Fragments;


import android.app.ProgressDialog;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import asia.nainglintun.myintthitar.Activities.CustomerActivity;
import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.Activities.SalesActivity;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.ApiClient;
import asia.nainglintun.myintthitar.models.ImageClass;
import asia.nainglintun.myintthitar.models.Sale;
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
    private EditText userName,shopName,phoneNumber,Address,DOB,Nrc,Township,customerName;
    private CircleImageView saleProfile;
    private final int IMG_REQUEST=1;
   private Bitmap bitmap;
   private Toolbar toolbar;


    private ProgressDialog progressDialog;
    public SaleSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale_setting, container, false);

       // ((SalesActivity)getActivity()).setTitle("Setting");
        toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Setting");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales, new FragmentCard()).addToBackStack(null).commit();

            }
        });

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("please wait.....");



        btnSaleChangePassword = view.findViewById(R.id.btnSaleProfile);
        bnLogout = view.findViewById(R.id.btnLogout);
        saleProfile = view.findViewById(R.id.saleProfile);
        bitmap = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.default_profile);
        saleProfile.setImageBitmap(bitmap);

        Call<Sale> saleCall = MainActivity.apiInterface.getSaleProfile(prefConfig.readName());
        saleCall.enqueue(new Callback<Sale>() {
            @Override
            public void onResponse(Call<Sale> call, Response<Sale> response) {
                String Profile=response.body().getProfile();
                //Toast.makeText(getContext(), Profile, Toast.LENGTH_SHORT).show();
                if(!Profile.equals("No")){
                    Glide.with(getContext()).load(ApiClient.BASE_URL +Profile).apply(RequestOptions.skipMemoryCacheOf(true).diskCacheStrategy(DiskCacheStrategy.NONE)).into(saleProfile);

                }
                if(Profile.equals("No")){
                    bitmap = BitmapFactory.decodeResource(getContext().getResources(),
                            R.drawable.default_profile);
                    saleProfile.setImageBitmap(bitmap);
                }
                //Toast.makeText(getContext(), "This is a " +Profile, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Sale> call, Throwable t) {

            }
        });
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
            case R.id.btnSaleProfile:
               uploadImage();
                break;
            case R.id.btnLogout:

                MainActivity.prefConfig.DeleteName(MainActivity.prefConfig.readName());
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();


                break;
        }
    }

    private void uploadImage(){
        progressDialog.show();
        Log.e("work","update method is work");
        String path = imageToString();
        String user_name = MainActivity.prefConfig.readName();
        Call<Sale> call = MainActivity.apiInterface.UpdateSaleProfile(user_name,path);
        call.enqueue(new Callback<Sale>() {
            @Override
            public void onResponse(Call<Sale> call, Response<Sale> response) {
                progressDialog.dismiss();


                Toast.makeText(getContext(), "Success Change profile", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onFailure(Call<Sale> call, Throwable t) {
                progressDialog.dismiss();
               Toast.makeText(getContext(), "Change Fail", Toast.LENGTH_SHORT).show();
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
