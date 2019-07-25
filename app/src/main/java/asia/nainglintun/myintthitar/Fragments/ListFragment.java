package asia.nainglintun.myintthitar.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.Activities.RecyclerTouchListener;
import asia.nainglintun.myintthitar.Adapters.RecyclerAdapter;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.ApiInterface;
import asia.nainglintun.myintthitar.models.Calories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Calories> calories;
    private RecyclerAdapter adapter;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;
    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        progressDialog = new ProgressDialog(getContext());

        progressDialog.setMessage("Please Wait...");

        fetchInformation("fruits");

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String address=calories.get(position).getAddress();
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

        Call<List<Calories>> call = MainActivity.apiInterface.getCaloriesInfo(type);
        call.enqueue(new Callback<List<Calories>>() {
            @Override
            public void onResponse(Call<List<Calories>> call, Response<List<Calories>> response) {
                progressDialog.dismiss();
                calories = response.body();

                adapter = new RecyclerAdapter(calories,getContext());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Calories>> call, Throwable t) {

            }
        });
    }

}
