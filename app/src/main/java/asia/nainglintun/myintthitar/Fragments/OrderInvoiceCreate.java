package asia.nainglintun.myintthitar.Fragments;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import asia.nainglintun.myintthitar.Activities.InputFilterMinMax;
import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.Activities.OrderScanForVoucherActivity;
import asia.nainglintun.myintthitar.Activities.SalesActivity;
import asia.nainglintun.myintthitar.Activities.ScanForVoucherActivity;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.OrderInoviceData;
import asia.nainglintun.myintthitar.models.SaleInoviceData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderInvoiceCreate extends Fragment implements  View.OnClickListener, AdapterView.OnItemSelectedListener {

//private RadioGroup radioGroup;
private LinearLayout linearLayoutCustomerNew;

public static EditText edShopName,edCustomerName,edCustomerPhone,edDOB,edCustomerAddress,edCustomerID,edCustomerTown,edCustomerNrc;
//private ImageButton searchButton;
    public static EditText edCustomerUserName;
private  EditText saleDate;
final Calendar myCalendar = Calendar.getInstance();
private Button btnCreateInvoiceSave,scanForVoucher;
private LinearLayout linearLayoutRing,linearLayoutBangles,linearLayoutNecklace,linearLayoutEarring;
private Button hideRing,hideBangles,hideNecklace,hideEarring;
private Spinner spinner;
private ProgressDialog progressDialog;
private static final String[] paths = {"Choose Items Type","Ring", "Bangles", "Necklace","Earring"};

private EditText ringTitle,ringNumber,ringPointEight,ringKyat,ringPal,ringYae,banglesTitle,banglesNumber,banglesPointEight,banglesKyat,banglesPal,banglesYae,necklaceTitle,necklaceNumber,necklacePointEight,necklaceKyat,necklacePal,necklaceYae,
        earringTitle,earringNumber,earringPointEight,earringKyat,earringPal,earringYae,voucherNumber,Gram,CuponCode,totalKyat,totalPel,totalYae,totalQualtity,totalPointEight;

    public OrderInvoiceCreate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_create_invoice, container, false);

        ((SalesActivity)getActivity()).setTitle("Create Order Invoice");

        progressDialog = new ProgressDialog(getContext());

        progressDialog.setTitle("Inserting Data");
        progressDialog.setMessage("Please wait..");


        voucherNumber = view.findViewById(R.id.voucherNumber);
         saleDate= view.findViewById(R.id.saleDate);
         Gram = view.findViewById(R.id.gram);
         CuponCode = view.findViewById(R.id.cupon);
         totalQualtity =view.findViewById(R.id.custBuyNumber);
         totalPointEight = view.findViewById(R.id.custDiscountPoint);

         linearLayoutRing = view.findViewById(R.id.ring);
         linearLayoutBangles = view.findViewById(R.id.bangles);
         linearLayoutNecklace = view.findViewById(R.id.necklace);
         linearLayoutEarring  =view.findViewById(R.id.earring);
         hideRing = view.findViewById(R.id.hideRing);
         hideBangles =view.findViewById(R.id.hideBangles);
         hideNecklace = view.findViewById(R.id.hideNecklace);
         hideEarring = view.findViewById(R.id.hideEarring);


        ringTitle = view.findViewById(R.id.ringText);
        ringNumber= view.findViewById(R.id.ringNumber);
        ringPointEight = view.findViewById(R.id.ringPointEight);
        ringKyat = view.findViewById(R.id.ringKyat);
        ringPal = view.findViewById(R.id.ringPal);
        ringYae = view.findViewById(R.id.ringYae);


        banglesTitle = view.findViewById(R.id.banglesText);
        banglesNumber= view.findViewById(R.id.banglesNumber);
        banglesPointEight = view.findViewById(R.id.banglesPointEight);
        banglesKyat = view.findViewById(R.id.banglesKyat);
        banglesPal = view.findViewById(R.id.banglesPal);
        banglesYae = view.findViewById(R.id.banglesYae);

        necklaceTitle = view.findViewById(R.id.necklaceText);
        necklaceNumber= view.findViewById(R.id.necklaceNumber);
        necklacePointEight = view.findViewById(R.id.necklacePointEight);
        necklaceKyat = view.findViewById(R.id.necklaceKyat);
        necklacePal = view.findViewById(R.id.necklacePal);
        necklaceYae = view.findViewById(R.id.necklaceYae);

        earringTitle = view.findViewById(R.id.earringText);
        earringNumber= view.findViewById(R.id.earringNumber);
        earringPointEight = view.findViewById(R.id.earringPointEight);
        earringKyat = view.findViewById(R.id.earringKyat);
        earringPal = view.findViewById(R.id.earringPal);
        earringYae = view.findViewById(R.id.earringYae);



          hideRing.setOnClickListener(this);
          hideBangles.setOnClickListener(this);
          hideNecklace.setOnClickListener(this);
          hideEarring.setOnClickListener(this);


        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }
        };


        saleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




       totalYae = view.findViewById(R.id.yawe);
        totalYae.setFilters(new InputFilter[]{new InputFilterMinMax(1,7)});

        totalPel = view.findViewById(R.id.pel);
        totalPel.setFilters(new InputFilter[]{new InputFilterMinMax(1,15)});

        ringYae.setFilters(new InputFilter[]{new InputFilterMinMax(1,7)});
        banglesYae.setFilters(new InputFilter[]{new InputFilterMinMax(1,7)});
        necklaceYae.setFilters(new InputFilter[]{new InputFilterMinMax(1,7)});
        earringYae.setFilters(new InputFilter[]{new InputFilterMinMax(1,7)});


        ringPal.setFilters(new InputFilter[]{new InputFilterMinMax(1,15)});
        banglesPal.setFilters(new InputFilter[]{new InputFilterMinMax(1,15)});
        necklacePal.setFilters(new InputFilter[]{new InputFilterMinMax(1,15)});
        earringPal.setFilters(new InputFilter[]{new InputFilterMinMax(1,15)});

        edShopName = view.findViewById(R.id.shopName);
        edCustomerName = view.findViewById(R.id.custName);
        edCustomerPhone = view.findViewById(R.id.custPhone);
        edDOB = view.findViewById(R.id.custDOB);
        edCustomerUserName = view.findViewById(R.id.custUserName);
       edCustomerAddress = view.findViewById(R.id.custAddress);
       edCustomerTown = view.findViewById(R.id.custTown);
       edCustomerNrc  = view.findViewById(R.id.custNrc);
       edCustomerID = view.findViewById(R.id.custId);
       // edSarchPhone = view.findViewById(R.id.searchPhone);
//        phoneNumber = edSarchPhone.getText().toString().trim();
//
//        Toast.makeText(getContext(),phoneNumber,Toast.LENGTH_LONG).show();

        totalKyat = view.findViewById(R.id.kyat);
       // searchButton = view.findViewById(R.id.searchButton);
        btnCreateInvoiceSave = view.findViewById(R.id.btnInVoiceSave);
        //searchButton.setOnClickListener(this);
       // searchWithPhoneLayout.setVisibility(View.GONE);

        scanForVoucher = view.findViewById(R.id.scanForvoucher);

        scanForVoucher.setOnClickListener(this);

        btnCreateInvoiceSave.setOnClickListener(this);




        return view;
    }

//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//      switch (checkedId){
//          case R.id.radioNewCustomer:
//             linearLayoutCustomerNew.setVisibility(View.VISIBLE);
//              edShopName.setText("");
//              edCustomerName.setText("");
//              //edSarchPhone.getText().toString();
//              edCustomerPhone.setText("");
//              //Toast.makeText(getContext(), edSarchPhone.getText().toString(),Toast.LENGTH_LONG).show();
//              edDOB.setText("");
//              edCustomerUserName.setText("");
//              edCustomerPassword.setText("");
//              //searchWithPhoneLayout.setVisibility(View.VISIBLE);
//            // searchWithPhoneLayout.setVisibility(View.GONE);
//              break;
//          case R.id.radioExistingCustomer:
//                linearLayoutCustomerNew.setVisibility(View.GONE);
//              //searchWithPhoneLayout.setVisibility(View.VISIBLE);
//
//              break;
//      }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.searchButton:
//
//                linearLayoutCustomerNew.setVisibility(View.VISIBLE);
//                String phoneNumber = edSarchPhone.getText().toString();
//                edShopName.setText("Sein nan Thaw");
//                edCustomerName.setText("Ma Aye Myat Mon");
//                //edSarchPhone.getText().toString();
//                edCustomerPhone.setText(phoneNumber);
//                //Toast.makeText(getContext(), edSarchPhone.getText().toString(),Toast.LENGTH_LONG).show();
//                edDOB.setText("may 25,1990");
//                edCustomerUserName.setText("Aye Myte");
//                edCustomerPassword.setText("ayemyte009");
//                //searchWithPhoneLayout.setVisibility(View.VISIBLE);
//                //searchWithPhoneLayout.setVisibility(View.GONE);
//
//                break;

            case R.id.scanForvoucher:

                startActivity(new Intent(getContext(), OrderScanForVoucherActivity.class));

                break;

            case R.id.btnInVoiceSave:
                saveOrderInvoice();
                //Toast.makeText(getContext(),"To do Invoice Save Operation in SaleInvoiceCreate.java",Toast.LENGTH_LONG).show();

                break;

            case R.id.hideRing:
                linearLayoutRing.setVisibility(View.GONE);
                break;

            case R.id.hideBangles:
                linearLayoutBangles.setVisibility(View.GONE);
                break;

            case R.id.hideNecklace:
                linearLayoutNecklace.setVisibility(View.GONE);

            case R.id.hideEarring:
                linearLayoutEarring.setVisibility(View.GONE);
                break;




        }
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        saleDate.setText(sdf.format(myCalendar.getTime()));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){

            case 1:
                linearLayoutRing.setVisibility(View.VISIBLE);
                break;

            case 2:
                linearLayoutBangles.setVisibility(View.VISIBLE);
                break;

            case 3:
               linearLayoutNecklace.setVisibility(View.VISIBLE);
                break;

            case 4:
                linearLayoutEarring.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void saveOrderInvoice(){
       String voucher_Number,sale_Date,qualtity,pointEight,kyat,pal,yae,gram,cuponCode,CustomerID,ring_Title,ring_Number,ring_Point_Eight,ring_Kyat,ring_Pal,ring_Yae,bangles_Title,bangles_Number,bangles_Point_Eight,bangles_Kyat,bangles_Pal,bangles_Yae,necklace_Title,necklace_Number,necklace_Point_Eight,necklace_Kyat,necklace_Pal,necklace_Yae,
        earring_Title,earring_Number,earring_Point_Eight,earring_Kyat,earring_Pal,earring_Yae;
       voucher_Number = voucherNumber.getText().toString();
        CustomerID = edCustomerID.getText().toString();
       sale_Date = saleDate.getText().toString();
       qualtity = totalQualtity.getText().toString();
       pointEight = totalPointEight.getText().toString();
       kyat = totalKyat.getText().toString();
       pal = totalPel.getText().toString();
       yae = totalYae.getText().toString();
       gram  = Gram.getText().toString();
       cuponCode = CuponCode.getText().toString();
       ring_Title = ringTitle.getText().toString();
       ring_Number = ringNumber.getText().toString();
       ring_Point_Eight = ringPointEight.getText().toString();
       ring_Kyat = ringKyat.getText().toString();
       ring_Pal = ringPal.getText().toString();
       ring_Yae = ringYae.getText().toString();


        bangles_Title = banglesTitle.getText().toString();
        bangles_Number = banglesNumber.getText().toString();
        bangles_Point_Eight = banglesPointEight.getText().toString();
        bangles_Kyat = banglesKyat.getText().toString();
        bangles_Pal = banglesPal.getText().toString();
        bangles_Yae = banglesYae.getText().toString();



        necklace_Title =necklaceTitle.getText().toString();
        necklace_Number =necklaceNumber.getText().toString();
        necklace_Point_Eight =necklacePointEight.getText().toString();
        necklace_Kyat =necklaceKyat.getText().toString();
        necklace_Pal =necklacePal.getText().toString();
        necklace_Yae =necklaceYae.getText().toString();



        earring_Title =earringTitle.getText().toString();
        earring_Number =earringNumber.getText().toString();
        earring_Point_Eight =earringPointEight.getText().toString();
        earring_Kyat =earringKyat.getText().toString();
        earring_Pal =earringPal.getText().toString();
        earring_Yae =earringYae.getText().toString();


       progressDialog.show();

        Call<OrderInoviceData> call = MainActivity.apiInterface.insertOrderInvoice(MainActivity.prefConfig.readName(),voucher_Number,sale_Date,qualtity,pointEight,kyat,pal,yae,gram,cuponCode,CustomerID,ring_Title,ring_Number,ring_Point_Eight,ring_Kyat,ring_Pal,ring_Yae,bangles_Title,bangles_Number,bangles_Point_Eight,bangles_Kyat,bangles_Pal,bangles_Yae,
                necklace_Title,necklace_Number,necklace_Point_Eight,necklace_Kyat,necklace_Pal,necklace_Yae,earring_Title,earring_Number,earring_Point_Eight,earring_Kyat,earring_Pal,earring_Yae);

        call.enqueue(new Callback<OrderInoviceData>() {
            @Override
            public void onResponse(Call<OrderInoviceData> call, Response<OrderInoviceData> response) {
                progressDialog.dismiss();
                if (response.body().getResponse().equals("ok")){
                    Toast.makeText(getContext(),"order Invoice Successfully" + ring_Title + bangles_Title + earring_Title + necklace_Title,Toast.LENGTH_LONG).show();
                } else if (response.body().getResponse().equals("error")){
                    Toast.makeText(getContext(),"order Invoice fail",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<OrderInoviceData> call, Throwable t) {

            }
        });
    }
}
