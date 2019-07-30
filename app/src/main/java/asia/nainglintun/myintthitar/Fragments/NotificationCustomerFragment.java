package asia.nainglintun.myintthitar.Fragments;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.Activities.RecyclerTouchListener;
import asia.nainglintun.myintthitar.Adapters.CustomerOrderRecyclerAdapter;
import asia.nainglintun.myintthitar.Adapters.NotificationRecyclerAdapter;
import asia.nainglintun.myintthitar.Adapters.bindvouchersaleRecyclerAdapter;
import asia.nainglintun.myintthitar.Adapters.editbindvouchersaleRecyclerAdapter;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.Customer;
import asia.nainglintun.myintthitar.models.Notification;
import asia.nainglintun.myintthitar.models.Orderhistory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationCustomerFragment extends Fragment {

    private Toolbar toolbar;
    private RecyclerView recyclerView, recyclerViewDialog, recyclerViewEdit;
    private RecyclerView.LayoutManager layoutManager;
    private List<Notification> salehistories;
    private NotificationRecyclerAdapter adapter;
    private bindvouchersaleRecyclerAdapter adapterDialog;
    private editbindvouchersaleRecyclerAdapter adapterEditDialog;
    private ProgressDialog progressDialog;
    public static TextView textDialog, bindName;
    private Dialog dialog;
    private ImageView closeImg;
    private ImageButton btnEdit;
    private String Customer_Id;

    private ArrayList<String> dataList;
    private ArrayList<String> nameList;

    private LinearLayout linearLayoutUpdate;

    public NotificationCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_customer, container, false);
        // Toast.makeText(getContext(), MainActivity.prefConfig.readName(), Toast.LENGTH_SHORT).show();

        toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Notification");


        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        textDialog = view.findViewById(R.id.dialogText);

        dialog = new Dialog(getContext());
        progressDialog = new ProgressDialog(getContext());


        progressDialog.setMessage("Please Wait...");

        Call<Customer> call = MainActivity.apiInterface.getCustomerInfo(MainActivity.prefConfig.readName());
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Customer_Id = String.valueOf(response.body().getId());
                fetchInformation(Customer_Id);
                Toast.makeText(getContext(), Customer_Id, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();

            }
        });




        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String noti_one, noti_group;

                noti_one = salehistories.get(position).getNoti_one();
                noti_group = salehistories.get(position).getNoti_group();

                // customerNrc = salehistories.get(position).getCustomerNrc();

                Showpopup(noti_one, noti_group);


            }

            @Override
            public void onLongClick(View view, int position) {


            }
        }));


        return view;
    }


    private void Showpopup(String noti_one, String noti_group) {

        dialog.setContentView(R.layout.custom_popup_dialog);
        recyclerView = dialog.findViewById(R.id.recyclerViewDialog);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        closeImg = dialog.findViewById(R.id.closeImage);
        btnEdit = dialog.findViewById(R.id.btnVoucherEdit);
        btnEdit.setVisibility(View.GONE);
        //btnSave = dialog.findViewById(R.id.btnVoucherSave);


        nameList = new ArrayList<>();
        nameList.add("noti_one");
        nameList.add("noti_group");


        dataList = new ArrayList<>();
        dataList.add(noti_one);
        dataList.add(noti_group);


        adapterDialog = new bindvouchersaleRecyclerAdapter(nameList, dataList, getContext());
        recyclerView.setAdapter(adapterDialog);

        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


    }

    public void fetchInformation(String Id) {
        progressDialog.setTitle("Loading Data....");
        progressDialog.show();

        Call<List<Notification>> call = MainActivity.apiInterface.readNotification(Id);
        call.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                progressDialog.dismiss();
                salehistories = response.body();

                adapter = new NotificationRecyclerAdapter(salehistories, getContext());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



