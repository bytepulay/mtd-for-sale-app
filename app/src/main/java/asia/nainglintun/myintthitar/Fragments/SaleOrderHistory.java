package asia.nainglintun.myintthitar.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import asia.nainglintun.myintthitar.Activities.RecyclerTouchListener;
import asia.nainglintun.myintthitar.Activities.VoucherOrderDetailActivity;
import asia.nainglintun.myintthitar.Adapters.SaleOrderHistoryAdapter;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.ToDo.SaleOrderHistoryToDo;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleOrderHistory extends Fragment {

    private View view;
    private ArrayList<SaleOrderHistoryToDo> saleHistoryList;
    private RecyclerView recyclerView;
    private asia.nainglintun.myintthitar.Adapters.SaleOrderHistoryAdapter adapter;

    private Toolbar toolbar;
    public SaleOrderHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.sale_history, container, false);

        toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Order History");

        saleHistoryList = new ArrayList<>();
//        saleHistoryList.add(new SaleHistoryToDo("6/6/2019","type A","20"));
//        saleHistoryList.add(new SaleHistoryToDo("6/6/2019","type B","20"));
//        saleHistoryList.add(new SaleHistoryToDo("6/6/2019","type C","20"));
        saleHistoryList.add(new SaleOrderHistoryToDo("7/6/2019","type D","20","may1000","Sein Nan Thaw","09691234567","9/10/1998","Mg Mg","mgmg@1345","voc001","100","30","100","10","14","7","mgmg1234"));
        saleHistoryList.add(new SaleOrderHistoryToDo("7/6/2019","type D","20","may1000","Sein Nan Thaw","09691234567","9/10/1998","Aung Aung","aungaung@1345","voc001","100","30","100","10","14","7","Aung Aung 22"));
        saleHistoryList.add(new SaleOrderHistoryToDo("7/6/2019","type D","20","may1000","Sein Nan Thaw","09691234567","9/10/1998","kyawKyaw","kyawkyaw@1345","voc001","100","30","100","10","14","7","kyaw Kyaw"));
        saleHistoryList.add(new SaleOrderHistoryToDo("7/6/2019","type D","20","may1000","Sein Nan Thaw","09691234567","9/10/1998","Hla Hla","hlahla@1345","voc001","100","30","100","10","14","7","Hla Hla"));
        saleHistoryList.add(new SaleOrderHistoryToDo("7/6/2019","type D","20","may1000","Sein Nan Thaw","09691234567","9/10/1998","Bo Bo","BoBo@1345","voc001","100","30","100","10","14","7","bo bo"));
        saleHistoryList.add(new SaleOrderHistoryToDo("7/6/2019","type D","20","may1000","Sein Nan Thaw","09691234567","9/10/1998","Aye Aye","AyeAye@1345","voc001","100","30","100","10","14","7","Aye Aye"));
        saleHistoryList.add(new SaleOrderHistoryToDo("7/6/2019","type D","20","may1000","Sein Nan Thaw","09691234567","9/10/1998","Thaw Thaw","ThawThaw@1345","voc001","100","30","100","10","14","7","Thaw Thaw"));
        saleHistoryList.add(new SaleOrderHistoryToDo("7/6/2019","type D","20","may1000","Sein Nan Thaw","09691234567","9/10/1998","zin zin","zinzin@1345","voc001","100","30","100","10","14","7","zin zin"));
        saleHistoryList.add(new SaleOrderHistoryToDo("7/6/2019","type D","20","may1000","Sein Nan Thaw","09691234567","9/10/1998","Thu Thu","thuthu@1345","voc001","100","30","100","10","14","7","thu thu"));
        saleHistoryList.add(new SaleOrderHistoryToDo("7/6/2019","type D","20","may1000","Sein Nan Thaw","09691234567","9/10/1998","zaw zaw","zawzaw@1345","voc001","100","30","100","10","14","7","zaw zaw"));
        saleHistoryList.add(new SaleOrderHistoryToDo("7/6/2019","type D","20","may1000","Sein Nan Thaw","09691234567","9/10/1998","zaw gyi","zawgyi@1345","voc001","100","30","100","10","14","7","zaw gyi"));

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new SaleOrderHistoryAdapter(getContext(), saleHistoryList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(),position + " is selected! and to do get data from database", Toast.LENGTH_LONG).show();

                String sDattes = saleHistoryList.get(position).getTxtDate();
                String sTypes = saleHistoryList.get(position).getTxtType();
                String sAmountt = saleHistoryList.get(position).getTxtAmount();
                String sCuponCode = saleHistoryList.get(position).getCuponCode();
                String sShopName = saleHistoryList.get(position).getShopName();
                String sPhoneNumber = saleHistoryList.get(position).getPhoneNumber();
                String sDateOfBirth = saleHistoryList.get(position).getDateOfBirth();
                String sUserName = saleHistoryList.get(position).getUserName();
                String sPassword = saleHistoryList.get(position).getPassword();
                String sVoucherNumber = saleHistoryList.get(position).getVoucherNumber();
                String sQualtity = saleHistoryList.get(position).getQualtity();
                String sPointEight = saleHistoryList.get(position).getPointEight();
                String sGram = saleHistoryList.get(position).getGram();
                String sKyat = saleHistoryList.get(position).getKyat();
                String sPal = saleHistoryList.get(position).getPal();
                String sYae = saleHistoryList.get(position).getYae();
                String sCustomerUserName = saleHistoryList.get(position).getCustomerName();
                Intent intent = new Intent(getContext(), VoucherOrderDetailActivity.class);
                Bundle bdn = new Bundle();
                bdn.putString("Datte",sDattes);
                bdn.putString("Type",sTypes);
                bdn.putString("Amount",sAmountt);
                bdn.putString("CuponCode",sCuponCode);
                bdn.putString("ShopName",sShopName);
                bdn.putString("PhoneNumber",sPhoneNumber);
                bdn.putString("DateOfBirth",sDateOfBirth);
                bdn.putString("UserName",sUserName);
                bdn.putString("Password",sPassword);
                bdn.putString("Voucher",sVoucherNumber);
                bdn.putString("Qualtity",sQualtity);
                bdn.putString("PointEight",sPointEight);
                bdn.putString("Gram",sGram);
                bdn.putString("Kyat",sKyat);
                bdn.putString("Pal",sPal);
                bdn.putString("Yae",sYae);
                bdn.putString("CustomerUserName",sCustomerUserName);
                intent.putExtras(bdn);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        return view;
    }

}