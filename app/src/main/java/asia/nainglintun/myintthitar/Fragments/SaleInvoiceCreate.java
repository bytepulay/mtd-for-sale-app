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

import java.math.BigInteger;
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
public class SaleInvoiceCreate extends Fragment implements  View.OnClickListener{

public static EditText edShopName,edCustomerName,edCustomerPhone,edDOB,edCustomerAddress,edCustomerID,edCustomerTown,edCustomerNrc;
public static EditText edCustomerUserName;
private  EditText saleDate;
final Calendar myCalendar = Calendar.getInstance();
private Button btnCreateInvoiceSave;
private ImageButton scanForVoucher;
private Button Bnadd;
private ProgressDialog progressDialog;


private EditText voucherNumber,Gram,CuponCode,totalKyat,totalPal,totalYae,totalQualtity,totalPointEight,Ayot,TotalAyot;
    float Totalkyat, Totalpal,Totalyae;

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
         Ayot = view.findViewById(R.id.aYot);
         TotalAyot = view.findViewById(R.id.totalAyot);

        totalKyat = view.findViewById(R.id.kyat);
        totalYae = view.findViewById(R.id.yae);
        totalPal = view.findViewById(R.id.pal);
        edShopName = view.findViewById(R.id.shopName);
        edCustomerName = view.findViewById(R.id.custName);
        edCustomerPhone = view.findViewById(R.id.custPhone);
        edDOB = view.findViewById(R.id.custDOB);
        edCustomerUserName = view.findViewById(R.id.custUserName);
        edCustomerAddress = view.findViewById(R.id.custAddress);
        edCustomerTown = view.findViewById(R.id.custTown);
        edCustomerNrc  = view.findViewById(R.id.custNrc);
        edCustomerID = view.findViewById(R.id.custId);

        Bnadd = view.findViewById(R.id.Add);
        Bnadd.setOnClickListener(this);



        btnCreateInvoiceSave = view.findViewById(R.id.btnInVoiceSave);
        scanForVoucher = view.findViewById(R.id.scanForvoucher);


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

            case R.id.Add:
                Double editTextGram = Double.parseDouble(Gram.getText().toString());


                double number2 = 16.6;
                int kyat1 =  (int) (editTextGram/number2);

                double beforePal = Math.round((((editTextGram%number2)/number2)*16));

                double afterPal = Math.round((((((editTextGram%number2)/number2)*16)/number2)*8));

                int Yae = (int)afterPal;
                int Pal = (int)beforePal;

                totalKyat.setText(String.valueOf(kyat1));
                totalPal.setText(String.valueOf(Pal));
                totalYae.setText(String.valueOf(Yae));
                break;

        }
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        saleDate.setText(sdf.format(myCalendar.getTime()));
    }


    public void saveSaleInvoice() {

        String voucher_Number, sale_Date, qualtity, pointEight, kyat, pal, yae, gram, cuponCode, CustomerID,totalAyot;
        voucher_Number = voucherNumber.getText().toString();
        gram = Gram.getText().toString();


        CustomerID = edCustomerID.getText().toString();
        sale_Date = saleDate.getText().toString();
        cuponCode = CuponCode.getText().toString();
        qualtity = totalQualtity.getText().toString();
        pointEight = totalPointEight.getText().toString();
//        kyat = totalKyat.getText().toString();
//        pal = totalPal.getText().toString();
//        yae = totalYae.getText().toString();
        int num1= 30;
        int result=(num1%16);

        totalAyot = TotalAyot.getText().toString();


       // Toast.makeText(getContext(), "Kyat " +String.valueOf(kyat1) + "Reminder " + String.valueOf(beforePal)+" After pal"+String.valueOf(afterPal), Toast.LENGTH_SHORT).show();
       // Totalkyat = (Gramfl/(16.6));;



        //Toast.makeText(getContext(), "integer value is" + Value, Toast.LENGTH_SHORT).show();



       // Totalpal = (((Totalkyat-(Gramfl%16.6))*16));

        //Totalyae =(((((Gramfl/16.6)-Totalkyat)*16)-Totalpal)*8);


       // Toast.makeText(getContext(), String.valueOf(Totalkyat), Toast.LENGTH_SHORT).show();







//
//        if (CustomerID.isEmpty()) {
//            edCustomerID.setError("Scan Customer Information");
//            Toast.makeText(getContext(), "Scan Cusotmer Information", Toast.LENGTH_SHORT).show();
//        } else if (!CustomerID.isEmpty()) {
//
//              progressDialog.show();
//
//            Call<SaleInoviceData> call = MainActivity.apiInterface.insertSaleInvoice(MainActivity.prefConfig.readName(), voucher_Number, sale_Date, qualtity, pointEight,totalAyot, kyat, pal, yae, gram, cuponCode, CustomerID);
//
//            call.enqueue(new Callback<SaleInoviceData>() {
//
//
//                @Override
//                public void onResponse(Call<SaleInoviceData> call, Response<SaleInoviceData> response) {
//                    progressDialog.dismiss();
//                    if (response.body().getResponse().equals("ok")) {
//                        Toast.makeText(getContext(), "Sale Invoice Successfully", Toast.LENGTH_LONG).show();
//                    } else if (response.body().getResponse().equals("error")) {
//                        Toast.makeText(getContext(), "sale Invoice fail", Toast.LENGTH_LONG).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<SaleInoviceData> call, Throwable t) {
//                    Toast.makeText(getContext(), "pull fail", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
//}
