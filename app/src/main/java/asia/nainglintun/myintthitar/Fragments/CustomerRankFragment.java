package asia.nainglintun.myintthitar.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerRankFragment extends Fragment {

    private Bitmap bitmap;
    private ImageView customerQrCode;
    Toolbar toolbar;
    TextView Customername;

    public CustomerRankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_rank, container, false);
        toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Rank");
        customerQrCode = view.findViewById(R.id.custQrCode);
        Customername = view.findViewById(R.id.customerName);
        Customername.setText(MainActivity.prefConfig.readName());
        bitmap = BitmapFactory.decodeResource(getContext().getResources(),
        R.drawable.qr_code);
        customerQrCode.setImageBitmap(bitmap);
        Toast.makeText(getContext(), MainActivity.prefConfig.readName(), Toast.LENGTH_SHORT).show();

        return view;
    }

}
