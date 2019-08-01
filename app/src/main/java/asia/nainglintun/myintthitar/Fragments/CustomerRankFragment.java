package asia.nainglintun.myintthitar.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import asia.nainglintun.myintthitar.Activities.CustomerActivity;
import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.ImageClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerRankFragment extends Fragment {

    private Bitmap bitmap;
    private ImageView customerQrCode,Profile;
    //Toolbar toolbar;
    TextView Customername;

    public CustomerRankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_rank, container, false);
//        toolbar = view.findViewById(R.id.toolBar);
//        toolbar.setTitle("Rank");
        ((CustomerActivity)getActivity()).setTitle("Rank");
//        setHasOptionsMenu(true);

        Call<ImageClass> call =MainActivity.apiInterface.getQrImage(MainActivity.prefConfig.readName());
        call.enqueue(new Callback<ImageClass>() {
            @Override
            public void onResponse(Call<ImageClass> call, Response<ImageClass> response) {
                String path = response.body().getImage();
                String name = response.body().getName();
                Toast.makeText(getContext(), path + name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ImageClass> call, Throwable t) {
//                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });


        String name = MainActivity.prefConfig.readName();

        customerQrCode = view.findViewById(R.id.custQrCode);
        Profile = view.findViewById(R.id.profile_image);
        //Glide.get(getContext()).clearDiskCache();
        Glide.with(getContext()).load("https://datacenterasia.000webhostapp.com/mtd/uploads/profile"+name+".jpg").apply(RequestOptions.skipMemoryCacheOf(true).diskCacheStrategy(DiskCacheStrategy.NONE)).into(Profile);

        Customername = view.findViewById(R.id.customerName);
        Customername.setText(MainActivity.prefConfig.readName());
        bitmap = BitmapFactory.decodeResource(getContext().getResources(),
        R.drawable.my_history);
        customerQrCode.setImageBitmap(bitmap);




        Glide.with(getContext()).load("https://datacenterasia.000webhostapp.com/mtd/uploads/"+name+".jpg").into(customerQrCode);

        //Glide.with(getContext()).load(R.drawable.qr_code).into(customerQrCode);

        return view;
    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_items,menu);
//    }
}
