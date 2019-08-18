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
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.text.DecimalFormat;
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
public static EditText edCustomerUserName, previousRemainKyat,previousRemainPal,previousRemainYae;
public static TextView updateVoucher,updateSaleDate,updateSaleName;
private  EditText saleDate;
final Calendar myCalendar = Calendar.getInstance();
private Button btnCreateInvoiceSave;
private ImageButton scanForVoucher;
private Button Bnadd,BnCalculate,BntotalAmount,BntotalRemainAmount;
private ProgressDialog progressDialog;
private Toolbar toolbar;


private EditText voucherNumber,Gram,CuponCode,totalKyat,totalPal,totalYae,totalQualtity,totalPointEight,TotalAyotKyat,TotalAyotPel,TotalAyotYae,
       buyDebitKyat,buyDebitPal,buyDebitYae,paymentKyat,paymentPal,paymentYae,
        nowRemainKyat,nowRemainPal,nowRemainYae,newTotalKyat,newTotalPal,newTotalYae,edNote;
    float Totalkyat, Totalpal,Totalyae;

    public SaleInvoiceCreate() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sale_create_invoice, container, false);

        //((SalesActivity)getActivity()).setTitle("Create Sale Invoice");
        toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Sale Inovice");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales, new FragmentCard()).addToBackStack(null).commit();

            }
        });

        progressDialog = new ProgressDialog(getContext());
       progressDialog.setTitle("Inserting Data");
        progressDialog.setMessage("Please wait..");
         voucherNumber = view.findViewById(R.id.voucherNumber);
         saleDate= view.findViewById(R.id.saleDate);


         newTotalKyat = view.findViewById(R.id.totalkyat);
         newTotalPal = view.findViewById(R.id.totalpal);
         newTotalYae = view.findViewById(R.id.totalyae);
         Gram = view.findViewById(R.id.gram);
         CuponCode = view.findViewById(R.id.cupon);
         totalQualtity =view.findViewById(R.id.custBuyNumber);
         totalPointEight = view.findViewById(R.id.custDiscountPoint);
         TotalAyotKyat = view.findViewById(R.id.ayotKyat);
         TotalAyotPel = view.findViewById(R.id.ayotPal);
         TotalAyotYae = view.findViewById(R.id.ayotYae);

        totalKyat = view.findViewById(R.id.kyat);
        totalYae = view.findViewById(R.id.yae);
        totalPal = view.findViewById(R.id.pal);

        previousRemainKyat = view.findViewById(R.id.debitKyat);
        previousRemainPal = view.findViewById(R.id.debitPal);
        previousRemainYae = view.findViewById(R.id.debitYae);

        updateVoucher = view.findViewById(R.id.updatevoucher);
        updateSaleDate = view.findViewById(R.id.updateSaleDate);
        updateSaleName = view.findViewById(R.id.updatSaleName);

        buyDebitKyat = view.findViewById(R.id.buyDebitKyat);
        buyDebitPal = view.findViewById(R.id.buyDebitPal);
        buyDebitYae = view.findViewById(R.id.buyDebitYae);

        paymentKyat = view.findViewById(R.id.paymentKyat);
        paymentPal = view.findViewById(R.id.paymentPal);
        paymentYae = view.findViewById(R.id.paymentYae);


        nowRemainKyat = view.findViewById(R.id.remainKyat);
        nowRemainPal = view.findViewById(R.id.remainPal);
        nowRemainYae = view.findViewById(R.id.remainYae);


        edShopName = view.findViewById(R.id.shopName);
        edCustomerName = view.findViewById(R.id.custName);
        edCustomerPhone = view.findViewById(R.id.custPhone);
        edDOB = view.findViewById(R.id.custDOB);
        edCustomerUserName = view.findViewById(R.id.custUserName);
        edCustomerAddress = view.findViewById(R.id.custAddress);
        edCustomerTown = view.findViewById(R.id.custTown);
        edCustomerNrc  = view.findViewById(R.id.custNrc);
        edCustomerID = view.findViewById(R.id.custId);

        edNote = view.findViewById(R.id.note);

        Bnadd = view.findViewById(R.id.Add);
        BntotalAmount = view.findViewById(R.id.totalAmount);
        BntotalRemainAmount = view.findViewById(R.id.totalRemainAmount);
        Bnadd.setOnClickListener(this);
        BntotalAmount.setOnClickListener(this);
        BntotalRemainAmount.setOnClickListener(this);




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
                try {
                    Double editTextGram = Double.parseDouble(Gram.getText().toString());


                    double number2 = 16.6;
                    int kyat1 =  (int) (editTextGram/number2);

                    double beforePal = (((editTextGram/number2)-kyat1)*16);

                    int PalInt = (int)(beforePal);

                    double beforeYae = (beforePal-PalInt);

                    double RealYae = (beforeYae*8);

                    DecimalFormat form = new DecimalFormat("0.00");


                    double afterPal = (((((editTextGram%number2)/number2)*16)/number2)*8);

                    int Yae = (int)afterPal;
                    int Pal = (int)beforePal;

                    totalKyat.setText(String.valueOf(kyat1));
                    totalPal.setText(String.valueOf(PalInt));
                    totalYae.setText(String.valueOf(form.format(RealYae)));
                    break;

                }catch (Exception e){
                    e.printStackTrace();
                }

            case R.id.totalAmount:
                try {
                    int totKyat = Integer.parseInt(newTotalKyat.getText().toString());
                    int totPal = Integer.parseInt(newTotalPal.getText().toString());
                    double totYae = Double.parseDouble(newTotalYae.getText().toString());
                    double plusPal;
                    int plusKyat;
                    double plusYaeOne = 0;
                    int realResultPal = 0;
                    int plusForKyat = 0;
                    int resultpal = 0;

                    int debitKyat = Integer.parseInt(previousRemainKyat.getText().toString());
                    int debitPal = Integer.parseInt(previousRemainPal.getText().toString());
                    double debitYae = Double.parseDouble(previousRemainYae.getText().toString());

                    int TOTALKYAT = totKyat + debitKyat;
                    int TOTALPAl = totPal + debitPal;
                    double TOTALYAE = totYae + debitYae;

                    if (TOTALPAl < 16) {

                        if (TOTALYAE >= 8) {
                            plusYaeOne = TOTALYAE / 8;
                            int plusYaeOneInt = (int) plusYaeOne;
                            TOTALPAl = TOTALPAl + plusYaeOneInt;
                            plusKyat = TOTALPAl / 16;
                            resultpal = TOTALPAl % 16;

                            TOTALKYAT = TOTALKYAT + plusKyat;
                            buyDebitKyat.setText(String.valueOf(TOTALKYAT));
                            buyDebitPal.setText(String.valueOf(resultpal));
                        } else if (TOTALYAE < 8) {
                            buyDebitKyat.setText(String.valueOf(TOTALKYAT));
                            buyDebitPal.setText(String.valueOf(TOTALPAl));
                        }
                    } else if (TOTALPAl >= 16) {
                        if (TOTALYAE >= 8) {
                            plusYaeOne = TOTALYAE / 8;
                            int plusYaeOneInt = (int) plusYaeOne;
                            TOTALPAl = TOTALPAl + plusYaeOneInt;
                            plusKyat = TOTALPAl / 16;
                            resultpal = TOTALPAl % 16;

                            TOTALKYAT = TOTALKYAT + plusKyat;
                            buyDebitKyat.setText(String.valueOf(TOTALKYAT));
                            buyDebitPal.setText(String.valueOf(resultpal));
                        } else if (TOTALYAE < 8) {
                            plusKyat = TOTALPAl / 16;
                            resultpal = TOTALPAl % 16;
                            TOTALKYAT = TOTALKYAT + plusKyat;
                            buyDebitKyat.setText(String.valueOf(TOTALKYAT));
                            buyDebitPal.setText(String.valueOf(resultpal));
                        }
                    }
                    if (TOTALYAE < 8) {
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        buyDebitYae.setText(String.valueOf(form1.format(TOTALYAE)));
                    } else if (TOTALYAE >= 8) {
                        double resultYae;
                        plusPal = TOTALYAE / 8;
                        resultYae = TOTALYAE % 8;
                        double totalPals = (double) resultpal;

                        double resultPal1 = totalPals + plusPal;
                        int resultPalInt = (int) resultPal1;
//
//                    if(resultPalInt>=16){
//                        plusForKyat=resultPalInt/16;
//                        TOTALKYAT = TOTALKYAT+plusForKyat;
//                        buyDebitKyat.setText(String.valueOf(TOTALKYAT));
//                        realResultPal= resultPalInt%16;
//                        buyDebitPal.setText(String.valueOf(realResultPal));
//                    }else if(resultPalInt<16){
//                        buyDebitPal.setText(String.valueOf(resultPalInt));
//                    }


                        DecimalFormat form1 = new DecimalFormat("0.00");
                        buyDebitYae.setText(String.valueOf(form1.format(resultYae)));

                    }


                    break;
                }catch (Exception e){
                    e.printStackTrace();
                }

            case R.id.totalRemainAmount:
                try {


                    int getTotalAmountKyat = Integer.parseInt(buyDebitKyat.getText().toString());
                    int getTotalAmountPal = Integer.parseInt(buyDebitPal.getText().toString());
                    double getTotalAmountYae = Double.parseDouble(buyDebitYae.getText().toString());

                    int PayKyat = Integer.parseInt(paymentKyat.getText().toString());
                    int PayPal = Integer.parseInt(paymentPal.getText().toString());
                    double PayYae = Double.parseDouble(paymentYae.getText().toString());

                    int RemainKyat = 0;
                    int RemainPal = 0;
                    double RemainYae = 0.0;


                    if (PayPal > getTotalAmountPal) {
                        getTotalAmountKyat = getTotalAmountKyat - 1;
                        getTotalAmountPal = getTotalAmountPal + 16;
                        if (PayYae > getTotalAmountYae) {
                            getTotalAmountPal = getTotalAmountPal - 1;
                        }
                        RemainPal = getTotalAmountPal - PayPal;
                    } else if (PayPal < getTotalAmountPal) {
                        RemainPal = getTotalAmountPal - PayPal;
                    }
                    if (PayYae > getTotalAmountYae) {
                        getTotalAmountPal = getTotalAmountPal - 1;
                        Toast.makeText(getContext(), String.valueOf(getTotalAmountPal), Toast.LENGTH_SHORT).show();
                        getTotalAmountYae = getTotalAmountYae + 8;
                        RemainYae = getTotalAmountYae - PayYae;
                    } else if (PayYae < getTotalAmountYae) {
                        RemainYae = getTotalAmountYae - PayYae;
                    }

                    RemainKyat = getTotalAmountKyat - PayKyat;


                    nowRemainKyat.setText(String.valueOf(RemainKyat));
                    nowRemainPal.setText(String.valueOf(RemainPal));
                    DecimalFormat form1 = new DecimalFormat("0.00");
                    nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));


                    break;

                }catch (Exception e){
                    e.printStackTrace();
                }

        }
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        saleDate.setText(sdf.format(myCalendar.getTime()));
    }


    public void saveSaleInvoice() {

        String voucher_Number, sale_Date, qualtity, pointEight, kyat, pal, yae, gram, cuponCode, CustomerID,totalAyotkyat,totalAyotPel,totalAyotYae,
        previous_remain_kyat,previous_remain_pal,previous_remain_yae,buy_debit_kyat,buy_debit_pal,buy_debit_yae,payment_kyat,payment_pal,payment_yae,now_remain_kyat,now_remain_pal,now_remain_yae,new_total_kyat,new_total_pal,new_total_yae,note_description;
        voucher_Number = voucherNumber.getText().toString();
        gram = Gram.getText().toString();


        CustomerID = edCustomerID.getText().toString();
        sale_Date = saleDate.getText().toString();
        note_description = edNote.getText().toString();

        new_total_kyat = newTotalKyat.getText().toString();
        new_total_pal = newTotalPal.getText().toString();
        new_total_yae = newTotalYae.getText().toString();

        cuponCode = CuponCode.getText().toString();
        qualtity = totalQualtity.getText().toString();
        pointEight = totalPointEight.getText().toString();
        kyat = totalKyat.getText().toString();
        pal = totalPal.getText().toString();
        yae = totalYae.getText().toString();

        totalAyotkyat = TotalAyotKyat.getText().toString();
        totalAyotPel = TotalAyotPel.getText().toString();
        totalAyotYae = TotalAyotYae.getText().toString();

        previous_remain_kyat = previousRemainKyat.getText().toString();
        previous_remain_pal = previousRemainPal.getText().toString();
        previous_remain_yae = previousRemainYae.getText().toString();

        buy_debit_kyat = buyDebitKyat.getText().toString();
        buy_debit_pal = buyDebitPal.getText().toString();
        buy_debit_yae = buyDebitYae.getText().toString();

        payment_kyat = paymentKyat.getText().toString();
        payment_pal = paymentPal.getText().toString();
        payment_yae = paymentYae.getText().toString();


        now_remain_kyat = nowRemainKyat.getText().toString();
        now_remain_pal = nowRemainPal.getText().toString();
        now_remain_yae = nowRemainYae.getText().toString();











        if (CustomerID.isEmpty()) {
            edCustomerID.setError("Scan Customer Information");
            Toast.makeText(getContext(), "Scan Cusotmer Information", Toast.LENGTH_SHORT).show();
        } else if (!CustomerID.isEmpty()) {

              progressDialog.show();

            Call<SaleInoviceData> call = MainActivity.apiInterface.insertSaleInvoice(MainActivity.prefConfig.readName(), voucher_Number, sale_Date,new_total_kyat,new_total_pal,new_total_yae,qualtity, pointEight,totalAyotkyat,totalAyotPel,totalAyotYae,kyat, pal, yae, gram, cuponCode, CustomerID,previous_remain_kyat,previous_remain_pal,previous_remain_yae,buy_debit_kyat,buy_debit_pal,buy_debit_yae,
                    payment_kyat,payment_pal,payment_yae,now_remain_kyat,now_remain_pal,now_remain_yae,note_description);

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
                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
