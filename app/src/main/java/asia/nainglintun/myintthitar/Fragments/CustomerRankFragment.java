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
import asia.nainglintun.myintthitar.models.Customer;
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
    private String Customer_Id,Customer_name,paths;
    private TextView textViewPoint;

    public CustomerRankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_rank, container, false);
        ((CustomerActivity)getActivity()).setTitle("Rank");
//      setHasOptionsMenu(true);
        Customername = view.findViewById(R.id.customerName);
       // Customername.setText(MainActivity.prefConfig.readName());
        textViewPoint = view.findViewById(R.id.pointNumber);
        Profile = view.findViewById(R.id.profile_image);
        bitmap = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.default_profile);
        Profile.setImageBitmap(bitmap);

        Call<Customer> call1 = MainActivity.apiInterface.getCustomerInfo(MainActivity.prefConfig.readName());
        call1.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Customer_Id = String.valueOf(response.body().getId());
                Customer_name =response.body().getName();
                paths = response.body().getProfile();

                Customername.setText(Customer_name);
                Toast.makeText(getContext(), paths, Toast.LENGTH_SHORT).show();

                if(paths==""){
                    bitmap = BitmapFactory.decodeResource(getContext().getResources(),
                            R.drawable.default_profile);
                    Profile.setImageBitmap(bitmap);
                }else if(paths!="") {
                    Glide.with(getContext()).load("http://mtdatabase.com/mtd/"+paths).apply(RequestOptions.skipMemoryCacheOf(true).diskCacheStrategy(DiskCacheStrategy.NONE)).into(Profile);

                }





                Toast.makeText(getContext(), Customer_Id +Customer_name + paths, Toast.LENGTH_SHORT).show();
                getPoint(Customer_Id);

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();

            }
        });


        Call<ImageClass> call =MainActivity.apiInterface.getQrImage(MainActivity.prefConfig.readName());
        call.enqueue(new Callback<ImageClass>() {
            @Override
            public void onResponse(Call<ImageClass> call, Response<ImageClass> response) {
                String path = response.body().getImage();
                Toast.makeText(getContext(), path , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ImageClass> call, Throwable t) {
            }
        });


        String name = MainActivity.prefConfig.readName();

        customerQrCode = view.findViewById(R.id.custQrCode);

        //Glide.get(getContext()).clearDiskCache();
       // Glide.with(getContext()).load("http://mtdatabase.com/mtd/uploads/profile"+name+".jpg").apply(RequestOptions.skipMemoryCacheOf(true).diskCacheStrategy(DiskCacheStrategy.NONE)).into(Profile);


        bitmap = BitmapFactory.decodeResource(getContext().getResources(),
        R.drawable.my_history);
        customerQrCode.setImageBitmap(bitmap);




        Glide.with(getContext()).load("http://mtdatabase.com/mtd/uploads/"+name+".jpg").into(customerQrCode);

        //Glide.with(getContext()).load(R.drawable.qr_code).into(customerQrCode);

        return view;
    }


    public void getPoint(String Id){

        Call<Customer> customerCall = MainActivity.apiInterface.getTotalPoint(Customer_Id);
        customerCall.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                String Points = response.body().getResponse();
                textViewPoint.setText(Points);

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });
    }

}
