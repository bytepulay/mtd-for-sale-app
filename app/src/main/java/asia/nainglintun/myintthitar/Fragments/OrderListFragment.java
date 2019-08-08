package asia.nainglintun.myintthitar.Fragments;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.Activities.RecyclerTouchListener;
import asia.nainglintun.myintthitar.Activities.SalesActivity;
import asia.nainglintun.myintthitar.Adapters.bindvouchersaleRecyclerAdapter;
import asia.nainglintun.myintthitar.Adapters.editbindvouchersaleRecyclerAdapter;
import asia.nainglintun.myintthitar.Adapters.orderRecyclerAdapter;
import asia.nainglintun.myintthitar.Adapters.saleRecyclerAdapter;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.Orderhistory;
import asia.nainglintun.myintthitar.models.Salehistory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderListFragment extends Fragment implements SearchView.OnQueryTextListener{

    private RecyclerView recyclerView,recyclerViewDialog,recyclerViewEdit;
    private RecyclerView.LayoutManager layoutManager;
    private List<Orderhistory> salehistories;
    private orderRecyclerAdapter adapter;
    private bindvouchersaleRecyclerAdapter adapterDialog;
    private editbindvouchersaleRecyclerAdapter adapterEditDialog;
    private ProgressDialog progressDialog;
    public static TextView textDialog,bindName;
    private Dialog dialog;
    private ImageView closeImg;
    private ImageButton btnEdit;
    private Button dialogEdit,btnSave;

    private EditText edVoucher,edSaleDate,edCustomerName,edShopName,edAddress,edDob,edNrc,edPhoneNumber,edTownShip,edRingDescription,
    edRingNumber,edRingPointEight,edRingKyat,edRingPal,edRingYae,
            edbanglesDescription,
            edbanglesNumber,edbanglesPointEight,edbanglesKyat,edbanglesPal,edbanglesYae,
            edNecklaceDescription,
            edNecklaceNumber,edNecklacePointEight,edNecklaceKyat,edNecklacePal,edNecklaceYae,
            edEarringDescription,
            edEarringNumber,edEarringPointEight,edEarringKyat,edEarringPal,edEarringYae,
            edTotalNumber,edTotalPointEight,edTotalKyat,edTotalPal,edTotalYae,edCuponCode,edGram;
    private ArrayList<String> dataList;
    private ArrayList<String> nameList;

    private LinearLayout linearLayoutUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_order_list, container, false);

        ((SalesActivity)getActivity()).setTitle("Order History Lists");
         setHasOptionsMenu(true);
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

                //Toast.makeText(getContext(), "gram is" + salehistories.get(position).getGram(), Toast.LENGTH_SHORT).show();


              // customerNrc = salehistories.get(position).getCustomerNrc();

                //Toast.makeText(getContext(),position + town + ID + ringDescription + banglesDescription + necklaceDescription + earringDescription + " is selected! and to do get data from database", Toast.LENGTH_LONG).show();
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

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                linearLayoutUpdate = dialog.findViewById(R.id.updateLayout);
                btnSave = dialog.findViewById(R.id.btnUpdateSave);
                btnSave.setVisibility(View.VISIBLE);

                edVoucher = dialog.findViewById(R.id.editVoucher);
                linearLayoutUpdate.setVisibility(View.VISIBLE);
                edSaleDate = dialog.findViewById(R.id.editSaleDate);
                edCustomerName = dialog.findViewById(R.id.editCustomerName);
                edShopName = dialog.findViewById(R.id.editShopName);
                edAddress = dialog.findViewById(R.id.editAddress);
                edPhoneNumber = dialog.findViewById(R.id.editPhoneNumber);
                edDob = dialog.findViewById(R.id.editDOB);
                edTownShip = dialog.findViewById(R.id.editTownShip);
                edRingDescription = dialog.findViewById(R.id.editRingDes);
                edRingNumber = dialog.findViewById(R.id.editRingNumber);
                edRingPointEight = dialog.findViewById(R.id.editRingPointEight);
                edRingKyat = dialog.findViewById(R.id.editRingKyat);
                edRingPal = dialog.findViewById(R.id.editRingPal);
                edRingYae = dialog.findViewById(R.id.editRingYae);


                edbanglesDescription = dialog.findViewById(R.id.editBanglesDes);
                edbanglesNumber = dialog.findViewById(R.id.editBanglesNumber);
                edbanglesPointEight = dialog.findViewById(R.id.editBanglesPointEight);
                edbanglesKyat = dialog.findViewById(R.id.editBanglesKyat);
                edbanglesPal = dialog.findViewById(R.id.editBanglesPal);
                edbanglesYae = dialog.findViewById(R.id.editBanglesYae);




                edNecklaceDescription = dialog.findViewById(R.id.editNecklaceDes);
                edNecklaceNumber = dialog.findViewById(R.id.editNecklaceNumber);
                edNecklacePointEight = dialog.findViewById(R.id.editNecklacePointEight);
                edNecklaceKyat = dialog.findViewById(R.id.editNecklaceKyat);
                edNecklacePal = dialog.findViewById(R.id.editNecklacePal);
                edNecklaceYae = dialog.findViewById(R.id.editNecklaceYae);



                edEarringDescription = dialog.findViewById(R.id.editEarringDes);
                edEarringNumber = dialog.findViewById(R.id.editEarringNumber);
                edEarringPointEight = dialog.findViewById(R.id.editEarringPointEight);
                edEarringKyat = dialog.findViewById(R.id.editEarringKyat);
                edEarringPal = dialog.findViewById(R.id.editEarringPal);
                edEarringYae = dialog.findViewById(R.id.editEarringYae);


                edTotalNumber = dialog.findViewById(R.id.editTotalNumber);
                edTotalPointEight = dialog.findViewById(R.id.editTotalPointEight);
                edTotalKyat = dialog.findViewById(R.id.editTotalKyat);
                edTotalPal= dialog.findViewById(R.id.editTotalPal);
                edTotalYae = dialog.findViewById(R.id.editTotalYae);

                edCuponCode = dialog.findViewById(R.id.editCuponCode);
                edGram = dialog.findViewById(R.id.editTotalGram);




//                edbanglesDescription = dialog.findViewById(R.id.editB)
//






                edVoucher.setText(voucher);
                edSaleDate.setText(sDate);
                edCuponCode.setText(cuponCode);
                edCustomerName.setText(customerName);
                edShopName.setText(shopName);
                edPhoneNumber.setText(customerPhoneNumber);
                edAddress.setText(customerAddress);
                edDob.setText(customerDob);
                edTownShip.setText(town);

                edRingDescription.setText(ringDescription);
                edRingNumber.setText(ringNumber);
                edRingPointEight.setText(ringPointEight);
                edRingKyat.setText(ringKyat);
                edRingPal.setText(ringPal);
                edRingYae.setText(ringYae);

                edbanglesDescription.setText(banglesDescription);
                edbanglesNumber.setText(banglesNumber);
                edbanglesPointEight.setText(banglesPointEight);
                edbanglesKyat.setText(banglesKyat);
                edbanglesPal.setText(banglesPal);
                edbanglesYae.setText(banglesYae);


                edNecklaceDescription.setText(necklaceDescription);
                edNecklaceNumber.setText(necklaceNumber);
                edNecklacePointEight.setText(necklacePointEight);
                edNecklaceKyat.setText(necklaceKyat);
                edNecklacePal.setText(necklacePal);
                edNecklaceYae.setText(necklaceYae);


                edEarringDescription.setText(earringDescription);
                edEarringNumber.setText(earringNumber);
                edEarringPointEight.setText(earringPointEight);
                edEarringKyat.setText(earringKyat);
                edEarringPal.setText(earringPal);
                edEarringYae.setText(earringYae);

                edTotalNumber.setText(totalQty);
                edTotalPointEight.setText(totalPointEight);
                edTotalKyat.setText(totalKyat);
                edTotalYae.setText(totalYae);
                edTotalPal.setText(totalPal);

                edGram.setText(gram);




                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Log.e("Response","doing this methos");
//                        Toast.makeText(getContext(),edVoucher.getText().toString()+ ID
//
//                        , Toast.LENGTH_SHORT).show();
                        progressDialog.setTitle("Update Order Invoice");
                        progressDialog.show();
                      Call<Orderhistory> call = MainActivity.apiInterface.updateOrderInvoice( ID,edVoucher.getText().toString(),
                              edSaleDate.getText().toString(),
                              edCuponCode.getText().toString(),
                              edPhoneNumber.getText().toString(),
                              edCustomerName.getText().toString(),
                              edShopName.getText().toString(),
                              edAddress.getText().toString(),
                              edDob.getText().toString(),
                              edTownShip.getText().toString(),
                              edRingDescription.getText().toString(),
                              edRingNumber.getText().toString(),
                              edRingPointEight.getText().toString(),
                              edRingKyat.getText().toString(),
                              edRingKyat.getText().toString(),
                              edRingYae.getText().toString(),

                              edbanglesDescription.getText().toString(),
                              edbanglesNumber.getText().toString(),
                              edbanglesPointEight.getText().toString(),
                              edbanglesKyat.getText().toString(),
                              edbanglesKyat.getText().toString(),
                              edbanglesYae.getText().toString(),


                              edNecklaceDescription.getText().toString(),
                              edNecklaceNumber.getText().toString(),
                              edNecklacePointEight.getText().toString(),
                              edNecklaceKyat.getText().toString(),
                              edNecklaceKyat.getText().toString(),
                              edNecklaceYae.getText().toString(),


                              edEarringDescription.getText().toString(),
                              edEarringNumber.getText().toString(),
                              edEarringPointEight.getText().toString(),
                              edEarringKyat.getText().toString(),
                              edEarringKyat.getText().toString(),
                              edEarringYae.getText().toString(),
                              edTotalNumber.getText().toString(),
                              edTotalPointEight.getText().toString(),
                              edTotalKyat.getText().toString(),
                              edTotalPal.getText().toString(),
                              edTotalYae.getText().toString(),
                              edGram.getText().toString()
                              );


//                        Log.e("Response","after doing this update ");

                      call.enqueue(new Callback<Orderhistory>() {
                          @Override
                          public void onResponse(Call<Orderhistory> call, Response<Orderhistory> response) {
                              progressDialog.dismiss();

                                  if(response.body().getResponse().equals("ok")){
                                      Toast.makeText(getContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                                  }




                          }

                          @Override
                          public void onFailure(Call<Orderhistory> call, Throwable t) {

                              Toast.makeText(getContext(), "Updat Fail", Toast.LENGTH_SHORT).show();
                          }
                      });
                    }
                });







//
//                recyclerViewEdit = dialog.findViewById(R.id.recyclerViewEdit);
//                recyclerViewEdit.setVisibility(View.VISIBLE);
//                layoutManager = new LinearLayoutManager(getContext());
//                recyclerViewEdit.setLayoutManager(layoutManager);
//                recyclerViewEdit.setHasFixedSize(true);
//                adapterEditDialog = new editbindvouchersaleRecyclerAdapter(nameList,dataList,getContext());
//                recyclerViewEdit.setAdapter(adapterEditDialog);


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

        Call<List<Orderhistory>> call = MainActivity.apiInterface.getOrderHistoryInfo(type);
        call.enqueue(new Callback<List<Orderhistory>>() {
            @Override
            public void onResponse(Call<List<Orderhistory>> call, Response<List<Orderhistory>> response) {
                progressDialog.dismiss();
                salehistories = response.body();

                adapter = new orderRecyclerAdapter(salehistories,getContext());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Orderhistory>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "No Order History", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_items,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        s = s.toLowerCase();
        ArrayList<Orderhistory> newList = new ArrayList<>();

        for (Orderhistory orderhistory : salehistories)
        {
            String sDate = orderhistory.getOrderDate().toLowerCase();
            String shopName = orderhistory.getCustomerShop().toLowerCase();
            String town = orderhistory.getCustomerTwon().toLowerCase();
            if (sDate.contains(s) || shopName.contains(s) || town.contains(s)){
                newList.add(orderhistory);
            }
        }

        adapter.setFilter(newList);

        return true;

    }


    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
//        return super.onCreateOptionsMenu(getActivity(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == android.R.id.home){
//            getActivity().finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}

}
