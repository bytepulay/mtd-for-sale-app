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
import asia.nainglintun.myintthitar.Adapters.CustomerPurchaseRecyclerAdapter;
import asia.nainglintun.myintthitar.Adapters.bindvouchersaleRecyclerAdapter;
import asia.nainglintun.myintthitar.Adapters.editbindvouchersaleRecyclerAdapter;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.OrderInoviceData;
import asia.nainglintun.myintthitar.models.Orderhistory;
import asia.nainglintun.myintthitar.models.Salehistory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderCustomerFragment extends Fragment {

private Toolbar toolbar;
private TextView purchaseDate,cuponCode,purchaseItem,gram;
    private RecyclerView recyclerView,recyclerViewDialog,recyclerViewEdit;
    private RecyclerView.LayoutManager layoutManager;
    private List<Orderhistory> salehistories;
    private CustomerOrderRecyclerAdapter adapter;
    private bindvouchersaleRecyclerAdapter adapterDialog;
    private editbindvouchersaleRecyclerAdapter adapterEditDialog;
    private ProgressDialog progressDialog;
    public static TextView textDialog,bindName;
    private Dialog dialog;
    private ImageView closeImg;
   private ImageButton btnEdit;


    private ArrayList<String> dataList;
    private ArrayList<String> nameList;

    private LinearLayout linearLayoutUpdate;

    public OrderCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_customer, container, false);
        Toast.makeText(getContext(), MainActivity.prefConfig.readName(), Toast.LENGTH_SHORT).show();
        toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Purchase Order List ");
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
                        banglesDescription,banglesNumber,banglesPointEight,banglesKyat,banglesPal,banglesYae,
                        necklaceDescription,necklaceNumber,necklacePointEight,necklaceKyat,necklacePal,necklaceYae,
                        earringDescription,earringNumber,earringPointEight,earringKyat,earringPal,earringYae,
                        totalQty,totalPointEight,totalKyat,totalPal,totalYae,ID,customerPhoneNumber,customerAddress,customerDob,customerNrc,
                        gram,cuponcode;

                ID = String.valueOf(salehistories.get(position).getId());
                String voucher = salehistories.get(position).getVoucherNumber();
                sDate = salehistories.get(position).getOrderDate();
                customerName = salehistories.get(position).getCustomerName();
                shopName = salehistories.get(position).getCustomerShop();
                String town=salehistories.get(position).getCustomerTwon();
                gram = salehistories.get(position).getGram();
                cuponcode = salehistories.get(position).getCuponCode();
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


                necklaceDescription = salehistories.get(position).getNecklaceTitle();
                necklaceNumber = salehistories.get(position).getNecklaceNumber();
                necklacePointEight = salehistories.get(position).getNecklacePointEight();
                necklaceKyat = salehistories.get(position).getNecklaceKyat();
                necklacePal = salehistories.get(position).getNecklacePal();
                necklaceYae = salehistories.get(position).getNecklaceYae();

                earringDescription = salehistories.get(position).getEarringTitle();
                earringNumber = salehistories.get(position).getEarringNumber();
                earringPointEight = salehistories.get(position).getEarringPointEight();
                earringKyat = salehistories.get(position).getEarringKyat();
                earringPal = salehistories.get(position).getEarringPal();
                earringYae = salehistories.get(position).getEarringYae();

                totalQty = salehistories.get(position).getQty();
                totalPointEight = salehistories.get(position).getPointEight();
                totalKyat = salehistories.get(position).getKyat();
                totalPal = salehistories.get(position).getPal();
                totalYae = salehistories.get(position).getYae();


                customerPhoneNumber = salehistories.get(position).getCustomerPhoneNumber();
                customerAddress = salehistories.get(position).getCustomerAddress();
                customerDob = salehistories.get(position).getCustomerDob();

                Toast.makeText(getContext(), "gram is" + salehistories.get(position).getResponse(), Toast.LENGTH_SHORT).show();


                // customerNrc = salehistories.get(position).getCustomerNrc();

                Toast.makeText(getContext(),position + town + ID + ringDescription + banglesDescription + necklaceDescription + earringDescription + " is selected! and to do get data from database", Toast.LENGTH_LONG).show();
                Showpopup(voucher,sDate,cuponcode,customerName,shopName,customerAddress,customerPhoneNumber,customerDob,town,ringDescription,ringNumber,ringPointEight,ringKyat,ringPal,ringYae,
                        banglesDescription,banglesNumber,banglesPointEight,banglesKyat,banglesPal,banglesYae,
                        necklaceDescription,necklaceNumber,necklacePointEight,necklaceKyat,necklacePal,necklaceYae,
                        earringDescription,earringNumber,earringPointEight,earringKyat,earringPal,earringYae,
                        totalQty,totalPointEight,totalKyat,totalPal,totalYae,
                        gram,ID);

            }

            @Override
            public void onLongClick(View view, int position) {


            }
        }));


        return view;
    }

    private void Showpopup(String voucher,String sDate,String cuponCode,String customerName,String shopName, String customerAddress,String customerPhoneNumber,String customerDob,String town,String ringDescription,String ringNumber,String ringPointEight,String ringKyat,String ringPal,String ringYae,
                           String banglesDescription,String banglesNumber,String banglesPointEight,String banglesKyat,String banglesPal,String banglesYae,
                           String necklaceDescription,String necklaceNumber,String necklacePointEight,String necklaceKyat,String necklacePal,String necklaceYae,
                           String earringDescription,String earringNumber,String earringPointEight,String earringKyat,String earringPal,String earringYae,String totalQty,String totalPointEight,String totalKyat,String totalPal,String totalYae,
                           String gram,String ID) {

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
        nameList.add("Voucher No");
        nameList.add("Sale Date");
        nameList.add("Cupon Code");
        nameList.add("Customer Name");
        nameList.add("Shop Name");
        nameList.add("Township");


        if (ringDescription!=null){
            nameList.add("Ring Descriptions");
            nameList.add("Ring Number");
            nameList.add("Ring Point Eight");
            nameList.add("Ring Kyat");
            nameList.add("Ring Pal");
            nameList.add("Ring Yae");

        }


        if (banglesDescription!=null){
            nameList.add("Bangles Descriptions");
            nameList.add("Bangles Number");
            nameList.add("Bangles Point Eight");
            nameList.add("Bangles Kyat");
            nameList.add("Bangles Pal");
            nameList.add("Bangles Yae");

        }




        if (necklaceDescription!=null){
            nameList.add("Necklace Descriptions");
            nameList.add("Necklace Number");
            nameList.add("Necklace Point Eight");
            nameList.add("Necklace Kyat");
            nameList.add("Necklace Pal");
            nameList.add("Necklace Yae");

        }

        if (earringDescription!=null){
            nameList.add("Earring Descriptions");
            nameList.add("Earring Number");
            nameList.add("Earring Point Eight");
            nameList.add("Earring Kyat");
            nameList.add("Earring Pal");
            nameList.add("Earring Yae");

        }








        dataList = new ArrayList<>();
        dataList.add(voucher);
        dataList.add(sDate);
        dataList.add(cuponCode);
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

        if(necklaceDescription!=null){
            dataList.add(necklaceDescription);
            dataList.add(necklaceNumber);
            dataList.add(necklacePointEight);
            dataList.add(necklaceKyat);
            dataList.add(necklacePal);
            dataList.add(necklaceYae);
        }



        if(earringDescription!=null){
            dataList.add(earringDescription);
            dataList.add(earringNumber);
            dataList.add(earringPointEight);
            dataList.add(earringKyat);
            dataList.add(earringPal);
            dataList.add(earringYae);
        }

        if(ringDescription!=null || banglesDescription!=null || necklaceDescription!=null || earringDescription!=null){
            nameList.add("Total Qualtity");
            nameList.add("Total Point Eight");
            nameList.add("Total Kyat");
            nameList.add("Total Pal");
            nameList.add("Total Yae");
            nameList.add("Total Gram");

            dataList.add(totalQty);
            dataList.add(totalPointEight);
            dataList.add(totalKyat);
            dataList.add(totalPal);
            dataList.add(totalYae);
            dataList.add(gram);
        }



        adapterDialog = new bindvouchersaleRecyclerAdapter(nameList,dataList,getContext());
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




    public void fetchInformation(String type){
        progressDialog.setTitle("Loading Data....");
        progressDialog.show();

        Call<List<Orderhistory>> call = MainActivity.apiInterface.getOrderHistoryInfo(type);
        call.enqueue(new Callback<List<Orderhistory>>() {
            @Override
            public void onResponse(Call<List<Orderhistory>> call, Response<List<Orderhistory>> response) {
                progressDialog.dismiss();
                salehistories = response.body();

                adapter = new CustomerOrderRecyclerAdapter(salehistories,getContext());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Orderhistory>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
