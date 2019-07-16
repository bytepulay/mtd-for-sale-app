package asia.nainglintun.myintthitar.activities.Fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import asia.nainglintun.myintthitar.R;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleSettingFragment extends Fragment implements View.OnClickListener {

    private Button btnSaleChangePassword;
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
        editTextSaleChangePassword = view.findViewById(R.id.saleChangePassword);
        editTextSaleComfirmPassword = view.findViewById(R.id.saleComfirmPassword);
        saleProfile = view.findViewById(R.id.saleProfile);
        saleProfile.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saleProfile:
                selectSaleImage();
        }
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
}
