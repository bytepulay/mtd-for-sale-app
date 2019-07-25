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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.Activities.RecyclerTouchListener;
import asia.nainglintun.myintthitar.Adapters.bindvouchersaleRecyclerAdapter;
import asia.nainglintun.myintthitar.Adapters.saleRecyclerAdapter;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.Salehistory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleListFragment extends Fragment {

   private Toolbar toolbar;
    private RecyclerView recyclerView,recyclerViewDialog;
    private RecyclerView.LayoutManager layoutManager;
    private List<Salehistory> salehistories;
    private saleRecyclerAdapter adapter;
    private bindvouchersaleRecyclerAdapter adapterDialog;
    private ProgressDialog progressDialog;
    public static TextView textDialog,bindName;
    private Dialog dialog;
    private ImageView closeImg;
    private Button dialogEdit;
    private ArrayList<String> dataList;
    private ArrayList<String> nameList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_sale_list, container, false);

       toolbar = view.findViewById(R.id.toolBar);
       toolbar.setTitle("Sale History List ");
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        textDialog = view.findViewById(R.id.dialogText);

        dialog = new Dialog(getContext());
        progressDialog = new ProgressDialog(getContext());

        progressDialog.setMessage("Please Wait...");

        fetchInformation(MainActivity.prefConfig.readName());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String sDate,customerName,shopName,ringDescription,ringNumber,ringPointEight,ringKyat,ringPal,ringYae,
                banglesDescription,banglesNumber,banglesPointEight,banglesKyat,banglesPal,banglesYae;
                String voucher = salehistories.get(position).getVoucherNumber();
                sDate = salehistories.get(position).getSaleDate();
                customerName = salehistories.get(position).getCustomerName();
                shopName = salehistories.get(position).getShopName();
                String town=salehistories.get(position).getCustomerTwon();
                ringDescription = salehistories.get(position).getRingTitle();
                ringNumber = salehistories.get(position).getRingNumber();
                ringPointEight = salehistories.get(position).getRingPointEight();
                ringKyat = salehistories.get(position).getRingKyat();
                ringPal = salehistories.get(position).getRingPal();
                ringYae = salehistories.get(position).getRingYae();
                banglesDescription = salehistories.get(position).getBanglesTitle();
                banglesNumber = salehistories.get(position).getBanglesNumber();
                banglesPointEight = salehistories.get(position).getBanglesPointEight();
                banglesKyat = salehistories.get(position).getBanglesKyat();
                banglesPal = salehistories.get(position).getBanglesPal();
                banglesYae = salehistories.get(position).getBanglesYae();
                Toast.makeText(getContext(),position + town + " is selected! and to do get data from database", Toast.LENGTH_LONG).show();
                Showpopup(voucher,sDate,customerName,shopName,town,ringDescription,ringNumber,ringPointEight,ringKyat,ringPal,ringYae,
                        banglesDescription,banglesNumber,banglesPointEight,banglesKyat,banglesPal,banglesYae);

            }

            @Override
            public void onLongClick(View view, int position) {


            }
        }));

       return view;
    }

    private void Showpopup(String voucher,String sDate,String customerName,String shopName,String town,String ringDescription,String ringNumber,String ringPointEight,String ringKyat,String ringPal,String ringYae,
                           String banglesDescription,String banglesNumber,String banglesPointEight,String banglesKyat,String banglesPal,String banglesYae) {

        dialog.setContentView(R.layout.custom_popup_dialog);
        recyclerView = dialog.findViewById(R.id.recyclerViewDialog);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        closeImg = dialog.findViewById(R.id.closeImage);
//        textDialog = dialog.findViewById(R.id.dialogText);
        //dialogEdit = dialog.findViewById(R.id.edit);
//        bindName = dialog.findViewById(R.id.bindName);

    nameList = new ArrayList<>();
    nameList.add("Voucher No->");
    nameList.add("Sale Date->");
    nameList.add("Customer Name->");
    nameList.add("Shop Name->");
    nameList.add("Township->");

    if (ringDescription!=null){
        nameList.add("Ring Descriptions");
        nameList.add("Ring Number ");
        nameList.add("Ring Point Eight");
        nameList.add("Ring Kyat");
        nameList.add("Ring Pal");
        nameList.add("Ring Yae");

    }


        if (banglesDescription!=null){
            nameList.add("Bangles Descriptions");
            nameList.add("Bangles Number ");
            nameList.add("Bangles Point Eight");
            nameList.add("Bangles Kyat");
            nameList.add("Bangles Pal");
            nameList.add("Bangles Yae");

        }






    dataList = new ArrayList<>();
    dataList.add(voucher);
    dataList.add(sDate);
    dataList.add(customerName);
    dataList.add(shopName);
    dataList.add(town);

    if(ringDescription!=null){
        dataList.add(ringDescription);
        dataList.add(ringNumber);
        dataList.add(ringPointEight);
        dataList.add(ringKyat);
        dataList.add(ringPal);
        dataList.add(ringYae);
    }



        if(banglesDescription!=null){
            dataList.add(banglesDescription);
            dataList.add(banglesNumber);
            dataList.add(banglesPointEight);
            dataList.add(banglesKyat);
            dataList.add(banglesPal);
            dataList.add(banglesYae);
        }

    adapterDialog = new bindvouchersaleRecyclerAdapter(nameList,dataList,getContext());
    recyclerView.setAdapter(adapterDialog);

        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

//        dialogEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               dialog.dismiss();
//               dialog.setContentView(R.layout.sale_create_invoice);
//               dialog.show();
//            }
//        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void fetchInformation(String type){
        progressDialog.show();

        Call<List<Salehistory>> call = MainActivity.apiInterface.getSaleHistoryInfo(type);
        call.enqueue(new Callback<List<Salehistory>>() {
            @Override
            public void onResponse(Call<List<Salehistory>> call, Response<List<Salehistory>> response) {
                progressDialog.dismiss();
                salehistories = response.body();

                adapter = new saleRecyclerAdapter(salehistories,getContext());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Salehistory>> call, Throwable t) {

            }
        });
    }

}
