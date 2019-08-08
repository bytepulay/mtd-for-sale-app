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

import asia.nainglintun.myintthitar.Activities.CustomerActivity;
import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.Activities.RecyclerTouchListener;
import asia.nainglintun.myintthitar.Adapters.CustomerOrderRecyclerAdapter;
import asia.nainglintun.myintthitar.Adapters.NotificationGroupRecyclerAdapter;
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
    private RecyclerView recyclerView, recyclerViewDialog, recyclerViewGroup;
    private RecyclerView.LayoutManager layoutManager,layoutManagerGroup;
    private List<Notification> salehistories,notifications;
    private NotificationRecyclerAdapter adapter;
    private NotificationGroupRecyclerAdapter notiAdapter;
    private bindvouchersaleRecyclerAdapter adapterDialog;
    private editbindvouchersaleRecyclerAdapter adapterEditDialog;
    private ProgressDialog progressDialog;
    public static TextView textDialog, bindName;
    private Dialog dialog;
    private ImageView closeImg;
    private ImageButton btnEdit;
    private String Customer_Id,profile;
    private TextView notiOneTitle,notiOneDescription;

    private ArrayList<String> dataList;
    private ArrayList<String> nameList;

    private LinearLayout linearLayoutUpdate,linearLayoutNotiOne;

    public NotificationCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_customer, container, false);
        // Toast.makeText(getContext(), MainActivity.prefConfig.readName(), Toast.LENGTH_SHORT).show();

        ((CustomerActivity)getActivity()).setTitle("Notification");


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerViewGroup = view.findViewById(R.id.recyclerViewGroup);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManagerGroup = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewGroup.setLayoutManager(layoutManagerGroup);
        recyclerView.setHasFixedSize(true);
        recyclerViewGroup.setHasFixedSize(true);

        //linearLayoutNotiOne = view.findViewById(R.id.linearNotiOne);


        textDialog = view.findViewById(R.id.dialogText);

        dialog = new Dialog(getContext());
        progressDialog = new ProgressDialog(getContext());


        progressDialog.setMessage("Please Wait...");

        Call<Customer> call = MainActivity.apiInterface.getCustomerInfo(MainActivity.prefConfig.readName());
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Customer_Id = String.valueOf(response.body().getId());
                profile = response.body().getProfile();

                fetchInformation(Customer_Id);

//                Toast.makeText(getContext(), Customer_Id + profile, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();

            }
        });

         fetchNotificationGroup();




        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String noti_one_title,noti_one_description;

                noti_one_title = salehistories.get(position).getTitle_one();
                noti_one_description = salehistories.get(position).getNoti_one();

                Showpopup(noti_one_title,noti_one_description);


            }

            @Override
            public void onLongClick(View view, int position) {


            }
        }));


        recyclerViewGroup.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerViewGroup, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String noti_one_title,noti_one_description;

                noti_one_title = notifications.get(position).getTitle_group();
                noti_one_description = notifications.get(position).getNoti_group();

                Showpopup(noti_one_title,noti_one_description);


            }

            @Override
            public void onLongClick(View view, int position) {


            }
        }));



        return view;
    }


    private void Showpopup(String noti_one_title,String noti_one_description) {

        dialog.setContentView(R.layout.custom_noti_one_popup_dialog);
        notiOneTitle = dialog.findViewById(R.id.notiOneDetialTitle);
        notiOneDescription = dialog.findViewById(R.id.notiOneDescription);
//        if (!noti_one_title.isEmpty() && !noti_one_description.isEmpty()){
//            notiOneTitle.setText("");
//            notiOneDescription.setText("");
//        }
        notiOneTitle.setText(noti_one_title);
        notiOneDescription.setText(noti_one_description);


        closeImg = dialog.findViewById(R.id.closeImage);
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


    }


    private void ShowpopupGroup(String noti_group_title,String noti_group_description) {

        dialog.setContentView(R.layout.custom_noti_group_popup_dialog);
        notiOneTitle = dialog.findViewById(R.id.notiOneDetialTitle);
        notiOneDescription = dialog.findViewById(R.id.notiOneDescription);
//        if (!noti_one_title.isEmpty() && !noti_one_description.isEmpty()){
//            notiOneTitle.setText("");
//            notiOneDescription.setText("");
//        }
        notiOneTitle.setText(noti_group_title);
        notiOneDescription.setText(noti_group_description);


        closeImg = dialog.findViewById(R.id.closeImage);
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
                Toast.makeText(getContext(), "Notification Not Found", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void fetchNotificationGroup() {
        Call<List<Notification>> notificationCall = MainActivity.apiInterface.readGroupNotification();
        notificationCall.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
               notifications = response.body();
               String group = response.body().get(0).getTitle_group();
                Toast.makeText(getContext(), group, Toast.LENGTH_SHORT).show();
               notiAdapter = new NotificationGroupRecyclerAdapter(notifications,getContext());
               recyclerViewGroup.setAdapter(notiAdapter);
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Notification Not Found", Toast.LENGTH_SHORT).show();
            }
        });

    }
}



