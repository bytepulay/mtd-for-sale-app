package asia.nainglintun.myintthitar.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.Activities.RecyclerTouchListener;
import asia.nainglintun.myintthitar.Adapters.orderRecyclerAdapter;
import asia.nainglintun.myintthitar.Adapters.saleRecyclerAdapter;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.Orderhistory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderListFragment extends Fragment {

   private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Orderhistory> orderhistories;
    private orderRecyclerAdapter adapter;
    private ProgressDialog progressDialog;
    public OrderListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_order_list, container, false);

       toolbar = view.findViewById(R.id.toolBar);
       toolbar.setTitle("Order History  ");
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        progressDialog = new ProgressDialog(getContext());

        progressDialog.setMessage("Please Wait...");

        fetchInformation(MainActivity.prefConfig.readName());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String address=orderhistories.get(position).getAddress();
                Toast.makeText(getContext(),position + address + " is selected! and to do get data from database", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

       return view;
    }

    public void fetchInformation(String type){
        progressDialog.show();

        Call<List<Orderhistory>> call = MainActivity.apiInterface.getOrderHistoryInfo(type);
        call.enqueue(new Callback<List<Orderhistory>>() {
            @Override
            public void onResponse(Call<List<Orderhistory>> call, Response<List<Orderhistory>> response) {
                progressDialog.dismiss();
                orderhistories = response.body();

                adapter = new orderRecyclerAdapter(orderhistories,getContext());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Orderhistory>> call, Throwable t) {

            }
        });
    }

}
