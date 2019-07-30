package asia.nainglintun.myintthitar.Fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.R;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingCustomerFragment extends Fragment implements View.OnClickListener {

    private Button btnCustomerChangePassword,bnCustometLogout;
    private EditText editTextCustomerChangePassword,getEditTextCustomerComfirmPassword;
    private CircleImageView customerProfile;
    private final int IMG_REQUEST=1;
    private Bitmap bitmap;

    public SettingCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting_customer, container, false);

        btnCustomerChangePassword = view.findViewById(R.id.btnCustomerPasswordSave);
        bnCustometLogout = view.findViewById(R.id.btnCustomerLogout);
        editTextCustomerChangePassword = view.findViewById(R.id.customerChangePassword);
        getEditTextCustomerComfirmPassword = view.findViewById(R.id.customerComfirmPassword);
        customerProfile = view.findViewById(R.id.customerProfile);
        customerProfile.setOnClickListener(this);
        btnCustomerChangePassword.setOnClickListener(this);
        bnCustometLogout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.btnCustomerPasswordSave:
              Toast.makeText(getContext(),"to do Save Password Change Operation",Toast.LENGTH_LONG).show();
                break;
          case R.id.customerProfile:
              selectCustomerImage();
          case R.id.btnCustomerLogout:
              MainActivity.prefConfig.DeleteName(MainActivity.prefConfig.readName());
              startActivity(new Intent(getContext(), MainActivity.class));
              getActivity().finish();
      }
    }

    private void selectCustomerImage() {
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
                customerProfile.setImageBitmap(bitmap);
                //imageView.setVisibility(View.VISIBLE);
//                Attributes.Name.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
