package asia.nainglintun.myintthitar.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.Adapters.SaleRecyclerAdapter;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.Customer;
import asia.nainglintun.myintthitar.models.Salehistory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaleHistoryListFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Salehistory> salehistoryList;
    private SaleRecyclerAdapter adapter;
    private String rowUser = "";
    String sdate ="";
    String voucher;

    TextView textView;
    public SaleHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_sale_history_list, container, false);

       // textView = view.findViewById(R.id.testing);

        recyclerView = view.findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);


         rowUser =MainActivity.prefConfig.readName();
        Toast.makeText(getContext(), rowUser, Toast.LENGTH_SHORT).show();

         fetchSaleInformation(rowUser);

        // textView.setText("hello");
       // Toast.makeText(getContext(), sdate, Toast.LENGTH_SHORT).show();

        return view;
    }

    public void fetchSaleInformation(String userType){
     Call<List<Salehistory>> call = MainActivity.apiInterface.getSaleHistoryInfo(userType);

     call.enqueue(new Callback<List<Salehistory>>() {
         @Override
         public void onResponse(Call<List<Salehistory>> call, Response<List<Salehistory>> response) {

             salehistoryList = response.body();
             adapter = new SaleRecyclerAdapter(salehistoryList,getContext());
             recyclerView.setAdapter(adapter);


         }

         @Override
         public void onFailure(Call<List<Salehistory>> call, Throwable t) {

         }
     });



    }

}
