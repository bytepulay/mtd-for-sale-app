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
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import asia.nainglintun.myintthitar.Activities.InputFilterMinMax;
import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.Activities.SalesActivity;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.Activities.ScanForVoucherActivity;
import asia.nainglintun.myintthitar.models.SaleInoviceData;
import me.myatminsoe.mdetect.MMEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleInvoiceCreate extends Fragment implements  View.OnClickListener, AdapterView.OnItemSelectedListener {

//private RadioGroup radioGroup;
private LinearLayout linearLayoutCustomerNew;

public static EditText edShopName,edCustomerName,edCustomerPhone,edDOB,edCustomerAddress,edCustomerID,edCustomerTown,edCustomerNrc;
//private ImageButton searchButton;
    public static EditText edCustomerUserName;
private  EditText saleDate;
final Calendar myCalendar = Calendar.getInstance();
private Button btnCreateInvoiceSave;
private ImageButton scanForVoucher;
private LinearLayout linearLayoutRing,linearLayoutBangles,linearLayoutNecklace,linearLayoutEarring;
private Button hideRing,hideBangles,hideNecklace,hideEarring;
private Spinner spinner;
private static final String[] paths = {"Choose Items Type","Ring", "Bangles", "Necklace","Earring"};
private ProgressDialog progressDialog;
int totalRingNumber=0,totalBangesNumber=0,totalNecklaceNumber =0,totalEarringNumber=0,totalAllNumber=0,totalAllPointEight=0,totalAllKyat=0,totalAllPal=0,totalAllYae=0,
        totalRingPointEight=0,totalBangesPointEight=0,totalNecklacePointEight=0,totalEarringPointEight=0,
        totalRingKyat=0,totalBangesKyat=0,totalNecklaceKyat=0,totalEarringKyat=0,
        totalRingPal=0,totalBangesPal=0,totalNecklacePal=0,totalEarringPal=0,
        totalRingYae=0,totalBangesYae=0,totalNecklaceYae=0,totalEarringYae=0,totalPoint=0;


private EditText ringTitle,ringNumber,ringPointEight,ringKyat,ringPal,ringYae,banglesTitle,banglesNumber,banglesPointEight,banglesKyat,banglesPal,banglesYae,necklaceTitle,necklaceNumber,necklacePointEight,necklaceKyat,necklacePal,necklaceYae,
        earringTitle,earringNumber,earringPointEight,earringKyat,earringPal,earringYae,voucherNumber,Gram,CuponCode,totalKyat,totalPal,totalYae,totalQualtity,totalPointEight;

    public SaleInvoiceCreate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sale_create_invoice, container, false);

        ((SalesActivity)getActivity()).setTitle("Create Sale Invoice");


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
        ringPal = view.findViewById(R.id.rPal);
        ringYae = view.findViewById(R.id.rYae);


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




       totalYae = view.findViewById(R.id.yae);
        //totalYae.setFilters(new InputFilter[]{new InputFilterMinMax(0,7)});

        totalPal = view.findViewById(R.id.pal);
        //totalPal.setFilters(new InputFilter[]{new InputFilterMinMax(0,15)});


        ringYae.setFilters(new InputFilter[]{new InputFilterMinMax(0,7)});
        banglesYae.setFilters(new InputFilter[]{new InputFilterMinMax(0,7)});
        necklaceYae.setFilters(new InputFilter[]{new InputFilterMinMax(0,7)});
        earringYae.setFilters(new InputFilter[]{new InputFilterMinMax(0,7)});


        ringPal.setFilters(new InputFilter[]{new InputFilterMinMax(0,15)});
        banglesPal.setFilters(new InputFilter[]{new InputFilterMinMax(0,15)});
        necklacePal.setFilters(new InputFilter[]{new InputFilterMinMax(0,15)});
        earringPal.setFilters(new InputFilter[]{new InputFilterMinMax(0,15)});

        edShopName = view.findViewById(R.id.shopName);
        edCustomerName = view.findViewById(R.id.custName);
        edCustomerPhone = view.findViewById(R.id.custPhone);
        edDOB = view.findViewById(R.id.custDOB);
        edCustomerUserName = view.findViewById(R.id.custUserName);
       edCustomerAddress = view.findViewById(R.id.custAddress);
       edCustomerTown = view.findViewById(R.id.custTown);
       edCustomerNrc  = view.findViewById(R.id.custNrc);
       edCustomerID = view.findViewById(R.id.custId);

        totalKyat = view.findViewById(R.id.kyat);
        btnCreateInvoiceSave = view.findViewById(R.id.btnInVoiceSave);
        scanForVoucher = view.findViewById(R.id.scanForvoucher);

        scanForVoucher.setOnClickListener(this);

        btnCreateInvoiceSave.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.scanForvoucher:

                startActivity(new Intent(getContext(), ScanForVoucherActivity.class));

                break;

            case R.id.btnInVoiceSave:
                saveSaleInvoice();
                break;

            case R.id.hideRing:
                linearLayoutRing.setVisibility(View.GONE);
                ringTitle.setText("");
                ringNumber.setText("0");
                ringPointEight.setText("0");
                ringKyat.setText("0");
                ringPal.setText("0");
                ringYae.setText("0");
                break;

            case R.id.hideBangles:
                linearLayoutBangles.setVisibility(View.GONE);
                banglesTitle.setText("");
                banglesNumber.setText("0");
                banglesPointEight.setText("0");
                banglesKyat.setText("0");
                banglesPal.setText("0");
                banglesYae.setText("0");
                break;

            case R.id.hideNecklace:
                linearLayoutNecklace.setVisibility(View.GONE);
                necklaceTitle.setText("");
                necklaceNumber.setText("0");
                necklacePointEight.setText("0");
                necklaceKyat.setText("0");
                necklacePal.setText("0");
                necklaceYae.setText("0");

            case R.id.hideEarring:
                linearLayoutEarring.setVisibility(View.GONE);
                earringTitle.setText("");
                earringNumber.setText("0");
                earringPointEight.setText("0");
                earringKyat.setText("0");
                earringPal.setText("0");
                earringYae.setText("0");
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


    public void saveSaleInvoice() {
        String voucher_Number, sale_Date, qualtity, pointEight, kyat, pal, yae, gram, cuponCode, CustomerID, ring_Title, ring_Number, ring_Point_Eight, ring_Kyat, ring_Pal, ring_Yae, bangles_Title, bangles_Number, bangles_Point_Eight, bangles_Kyat, bangles_Pal, bangles_Yae, necklace_Title, necklace_Number, necklace_Point_Eight, necklace_Kyat, necklace_Pal, necklace_Yae,
                earring_Title, earring_Number, earring_Point_Eight, earring_Kyat, earring_Pal, earring_Yae;
        voucher_Number = voucherNumber.getText().toString();
        CustomerID = edCustomerID.getText().toString();
        sale_Date = saleDate.getText().toString();

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


        necklace_Title = necklaceTitle.getText().toString();
        necklace_Number = necklaceNumber.getText().toString();
        necklace_Point_Eight = necklacePointEight.getText().toString();
        necklace_Kyat = necklaceKyat.getText().toString();
        necklace_Pal = necklacePal.getText().toString();
        necklace_Yae = necklaceYae.getText().toString();


        earring_Title = earringTitle.getText().toString();
        earring_Number = earringNumber.getText().toString();
        earring_Point_Eight = earringPointEight.getText().toString();
        earring_Kyat = earringKyat.getText().toString();
        earring_Pal = earringPal.getText().toString();
        earring_Yae = earringYae.getText().toString();





        try {
            totalRingNumber = Integer.parseInt(ring_Number);
            totalBangesNumber = Integer.parseInt(bangles_Number);
            totalNecklaceNumber = Integer.parseInt(necklace_Number);
            totalEarringNumber = Integer.parseInt(earring_Number);


        } catch (NumberFormatException e) {
                e.printStackTrace();
        }

        try {
            totalRingPointEight = Integer.parseInt(ring_Point_Eight);
            totalBangesPointEight = Integer.parseInt(bangles_Point_Eight);
            totalNecklacePointEight = Integer.parseInt(necklace_Point_Eight);
            totalEarringPointEight = Integer.parseInt(earring_Point_Eight);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        try {
            totalRingKyat = Integer.parseInt(ring_Kyat);
            totalBangesKyat = Integer.parseInt(bangles_Kyat);
            totalNecklaceKyat = Integer.parseInt(necklace_Kyat);
            totalEarringKyat = Integer.parseInt(earring_Kyat);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        try {

            totalRingPal = Integer.parseInt(ring_Pal);
            totalBangesPal = Integer.parseInt(bangles_Pal);
            totalNecklacePal = Integer.parseInt(necklace_Pal);
            totalEarringPal = Integer.parseInt(earring_Pal);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {

            totalRingYae = Integer.parseInt(ring_Yae);
            totalBangesYae = Integer.parseInt(bangles_Yae);
            totalNecklaceYae = Integer.parseInt(necklace_Yae);
            totalEarringYae = Integer.parseInt(earring_Yae);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }




        totalAllNumber = totalRingNumber+totalBangesNumber+totalNecklaceNumber+totalEarringNumber;
        totalAllPointEight = totalRingPointEight+totalBangesPointEight+totalNecklacePointEight+totalEarringPointEight;
        totalAllKyat = totalRingKyat + totalBangesKyat+totalNecklaceKyat+totalEarringKyat;
        totalAllPal = totalRingPal + totalBangesPal+totalNecklacePal+totalEarringPal;
        totalAllYae = totalRingYae + totalBangesYae+totalNecklaceYae+totalEarringYae;


       totalPoint= totalAllNumber-(totalAllPointEight/2);
       String stotalPoint = String.valueOf(totalPoint);
        Toast.makeText(getContext(), String.valueOf(totalPoint), Toast.LENGTH_SHORT).show();

        totalQualtity.setText(String.valueOf(totalAllNumber));
        totalPointEight.setText(String.valueOf(totalAllPointEight));
        totalKyat.setText(String.valueOf(totalAllKyat));
        totalPal.setText(String.valueOf(totalAllPal));
        totalYae.setText(String.valueOf(totalAllYae));

        qualtity = totalQualtity.getText().toString();
        pointEight = totalPointEight.getText().toString();
        kyat = totalKyat.getText().toString();
        pal = totalPal.getText().toString();
        yae = totalYae.getText().toString();
        gram = Gram.getText().toString();

        Toast.makeText(getContext(), "qualtity: " +qualtity +"pointeight : " + pointEight+ "Kyat :" + kyat + "Pal :"+ pal + "Yae :" +yae, Toast.LENGTH_SHORT).show();


        if (CustomerID.isEmpty()) {
            edCustomerID.setError("Scan Customer Information");
            Toast.makeText(getContext(), "Scan Cusotmer Information", Toast.LENGTH_SHORT).show();
        } else if (!CustomerID.isEmpty()) {

              progressDialog.show();

            Call<SaleInoviceData> call = MainActivity.apiInterface.insertSaleInvoice(MainActivity.prefConfig.readName(), voucher_Number, sale_Date, qualtity, pointEight,stotalPoint, kyat, pal, yae, gram, cuponCode, CustomerID, ring_Title, ring_Number, ring_Point_Eight, ring_Kyat, ring_Pal, ring_Yae, bangles_Title, bangles_Number, bangles_Point_Eight, bangles_Kyat, bangles_Pal, bangles_Yae,
                    necklace_Title, necklace_Number, necklace_Point_Eight, necklace_Kyat, necklace_Pal, necklace_Yae, earring_Title, earring_Number, earring_Point_Eight, earring_Kyat, earring_Pal, earring_Yae);

            call.enqueue(new Callback<SaleInoviceData>() {


                @Override
                public void onResponse(Call<SaleInoviceData> call, Response<SaleInoviceData> response) {
                    progressDialog.dismiss();
                    if (response.body().getResponse().equals("ok")) {
                        Toast.makeText(getContext(), "Sale Invoice Successfully", Toast.LENGTH_LONG).show();
                    } else if (response.body().getResponse().equals("error")) {
                        Toast.makeText(getContext(), "sale Invoice fail", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<SaleInoviceData> call, Throwable t) {
                    Toast.makeText(getContext(), "pull fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
