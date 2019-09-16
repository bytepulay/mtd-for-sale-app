package asia.nainglintun.myintthitar.Fragments;


import android.app.Application;
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
import java.util.Date;
import java.util.Locale;
import java.util.function.DoubleBinaryOperator;

import asia.nainglintun.myintthitar.Activities.CustomRangeInputFilter;
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
private Button Bnadd,BntotalAmount,BntotalRemainAmount,BnSub,BnCalculate,BnItemCalculate,BnayotCalculate,BnReturnGoldPlusAyot;
private ProgressDialog progressDialog;
private Toolbar toolbar;
private double remainGram = 0.0;
private int remainNumber=0;
private int remainPointEight=0;
private double remainAyotYae=0;
private int remainAyotKyat=0,remainAyotPal=0;
    int TOTALKYAT =0;
    int TOTALPAl = 0;
    double TOTALYAE = 0;


private EditText voucherNumber,Gram,CuponCode,totalKyat,totalPal,totalYae,totalQualtity,totalPointEight,TotalAyotKyat,TotalAyotPel,TotalAyotYae,
       buyDebitKyat,buyDebitPal,buyDebitYae,paymentKyat,paymentPal,paymentYae,
        nowRemainKyat,nowRemainPal,nowRemainYae,newTotalKyat,newTotalPal,newTotalYae,edNote,
        edReturnGram,edRemainGram,edRemainKyat,edRemainPal,edRemainYae,edReturnNumber,edReturnPointEight,edRemainNumber,edRemainPointEight,
        edReturnAyoutKyat,edReturnAyotPal,edReturnAyotYae,edRemainAyotKyat,edRemainAyotPal,edRemainAyotYae,edReturnGoldKyat,edReturnGoldPal,edReturnGoldYae,ednowPaymentKyat,ednowPaymentPal,ednowPaymentYae;
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


        edReturnGoldKyat = view.findViewById(R.id.retunGoldKyat);
        edReturnGoldPal = view.findViewById(R.id.retunGoldPal);
        edReturnGoldYae = view.findViewById(R.id.retunGoldYae);

        ednowPaymentKyat = view.findViewById(R.id.netPayGoldKyat);
        ednowPaymentPal = view.findViewById(R.id.netPayGoldPal);
        ednowPaymentYae = view.findViewById(R.id.netPayGoldYae);


        paymentKyat = view.findViewById(R.id.paymentKyat);
        paymentPal = view.findViewById(R.id.paymentPal);
        paymentYae = view.findViewById(R.id.paymentYae);


        nowRemainKyat = view.findViewById(R.id.remainKyat);
        nowRemainPal = view.findViewById(R.id.remainPal);
        nowRemainYae = view.findViewById(R.id.remainYae);

        CuponCode = view.findViewById(R.id.cupon);
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
        edReturnGram = view.findViewById(R.id.returnGram);
        edRemainGram = view.findViewById(R.id.remainGram);

        edRemainKyat =view.findViewById(R.id.nowRmainkyat);
        edRemainPal = view.findViewById(R.id.nowRemainpal);
        edRemainYae = view.findViewById(R.id.nowRemainyae);

        edReturnNumber = view.findViewById(R.id.returnNumber);
        edReturnPointEight = view.findViewById(R.id.returnDiscountPoint);

        edRemainNumber = view.findViewById(R.id.remainNumber);
        edRemainPointEight = view.findViewById(R.id.remainDiscountPoint);
        edReturnAyoutKyat = view.findViewById(R.id.returnAyotKyat);
        edReturnAyotPal = view.findViewById(R.id.returnAyotPal);
        edReturnAyotYae = view.findViewById(R.id.returnAyotYae);

        edRemainAyotKyat = view.findViewById(R.id.remainAyotKyat);
        edRemainAyotPal = view.findViewById(R.id.remainAyotPal);
        edRemainAyotYae = view.findViewById(R.id.remainAyotYae);

        BnSub = view.findViewById(R.id.Sub);
        BnCalculate = view.findViewById(R.id.Calculate);
        BnItemCalculate = view.findViewById(R.id.itemCalculate);
        BnayotCalculate = view.findViewById(R.id.ayotCalculate);



        Bnadd = view.findViewById(R.id.Add);
        BntotalAmount = view.findViewById(R.id.totalAmount);
        BntotalRemainAmount = view.findViewById(R.id.totalRemainAmount);
        BnReturnGoldPlusAyot= view.findViewById(R.id.returnGoldPlusAyot);
        BnReturnGoldPlusAyot.setOnClickListener(this);
        Bnadd.setOnClickListener(this);
        BntotalAmount.setOnClickListener(this);
        BntotalRemainAmount.setOnClickListener(this);
        BnCalculate.setOnClickListener(this);
        BnSub.setOnClickListener(this);
        BnItemCalculate.setOnClickListener(this);
        BnayotCalculate.setOnClickListener(this);





        String date_n = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        saleDate.setText(date_n);




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

        newTotalYae.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 7.9f)});
        newTotalPal.setFilters(new InputFilter[]{new InputFilterMinMax(0,15)});


        edReturnGoldYae.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 7.9f)});
        edReturnGoldPal.setFilters(new InputFilter[]{new InputFilterMinMax(0,15)});

        paymentYae.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 7.9f)});
        paymentPal.setFilters(new InputFilter[]{new InputFilterMinMax(0,15)});


        edReturnAyotYae.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 7.9f)});
        edReturnAyotPal.setFilters(new InputFilter[]{new InputFilterMinMax(0,15)});


        TotalAyotYae.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 7.9f)});
        TotalAyotPel.setFilters(new InputFilter[]{new InputFilterMinMax(0,15)});







        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.scanForvoucher:

                startActivity(new Intent(getContext(), ScanForVoucherActivity.class));

                break;
            case R.id.Sub:
                try {
                    double buyGram = Double.parseDouble(Gram.getText().toString());
                    double returnGram = Double.parseDouble(edReturnGram.getText().toString());
                    remainGram = buyGram - returnGram;
                    edRemainGram.setText(String.valueOf(remainGram));
                    break;
                }catch (Exception e){
                    e.printStackTrace();
                }

            case R.id.Calculate:
                try {
                    //Double editTextGram = Double.parseDouble(.getText().toString());

                    double number2 = 16.6;
                    int kyat1 =  (int) (remainGram/number2);

                    double beforePal = (((remainGram/number2)-kyat1)*16);

                    int PalInt = (int)(beforePal);

                    double beforeYae = (beforePal-PalInt);

                    double RealYae = (beforeYae*8);

                    DecimalFormat form = new DecimalFormat("0.00");


                    double afterPal = (((((remainGram%number2)/number2)*16)/number2)*8);

                    int Yae = (int)afterPal;
                    int Pal = (int)beforePal;

                    edRemainKyat.setText(String.valueOf(kyat1));
                    edRemainPal.setText(String.valueOf(PalInt));
                    edRemainYae.setText(String.valueOf(form.format(RealYae)));
                    break;

                }catch (Exception e){
                    e.printStackTrace();
                }




            case R.id.btnInVoiceSave:
                try {
                    saveSaleInvoice();
                    break;
                }catch (Exception e){
                    e.printStackTrace();
                }

            case R.id.itemCalculate:
                try {
                    int itemNumber = Integer.parseInt(totalQualtity.getText().toString());
                    int itemPointEight = Integer.parseInt(totalPointEight.getText().toString());
                    int returnItemNumber = Integer.parseInt(edReturnNumber.getText().toString());
                    int returnItemPointEight = Integer.parseInt(edReturnPointEight.getText().toString());

                    remainNumber = itemNumber-returnItemNumber;
                    remainPointEight = itemPointEight-returnItemPointEight;

                    edRemainNumber.setText(String.valueOf(remainNumber));
                    edRemainPointEight.setText(String.valueOf(remainPointEight));
                    break;
                }catch (Exception e){
                    e.printStackTrace();
                }


            case R.id.ayotCalculate:
                try{
                    int returnAyotKyat= Integer.parseInt(edReturnAyoutKyat.getText().toString());
                    int returnAyotPal = Integer.parseInt(edReturnAyotPal.getText().toString());
                    double returnAyotYae = Double.parseDouble(edReturnAyotYae.getText().toString());

                    int AyotKyat = Integer.parseInt(TotalAyotKyat.getText().toString());
                    int AyotPal = Integer.parseInt(TotalAyotPel.getText().toString());
                    double AyotYae = Double.parseDouble(TotalAyotYae.getText().toString());

                    if (returnAyotYae > AyotYae)
                    {
                        AyotPal = AyotPal - 1;
                        AyotYae = AyotYae + 8;
                        remainAyotYae = AyotYae - returnAyotYae;
                       // Toast.makeText(getContext(), String.valueOf(remainAyotYae), Toast.LENGTH_SHORT).show();
                    } else if (returnAyotYae < AyotYae)
                    {
                        remainAyotYae = AyotYae - returnAyotYae;
                        //Toast.makeText(getContext(), String.valueOf(remainAyotYae), Toast.LENGTH_SHORT).show();
                    }




                    if (returnAyotPal > AyotPal)
                    {
                        AyotKyat = AyotKyat - 1;
                        AyotPal = AyotPal + 16;

                        remainAyotPal = AyotPal -returnAyotPal;
                    }
                    else if (returnAyotPal < AyotPal)
                    {
                        remainAyotPal = AyotPal - returnAyotPal;
                    }

                    remainAyotKyat = AyotKyat - returnAyotKyat;
                    edRemainAyotKyat.setText(String.valueOf(remainAyotKyat));
                    edRemainAyotPal.setText(String.valueOf(remainAyotPal));
                    DecimalFormat form1 = new DecimalFormat("0.00");
                    edRemainAyotYae.setText(String.valueOf(form1.format(remainAyotYae)));

                    break;

                }catch (Exception e){
                    e.printStackTrace();
                }

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

                        String DebitKyat = String.valueOf(debitKyat);
                        String DebitPal = String.valueOf(debitPal);
                        String DebitYae = String.valueOf(debitYae);

                        if (DebitKyat.contains("-") || DebitPal.contains("-") || DebitYae.contains("-")){
                          //  Toast.makeText(getContext(), "Negative Operation work", Toast.LENGTH_SHORT).show();
                               int nowDebitKyat=Math.abs(debitKyat);
                               int nowDebitPal=Math.abs(debitPal);
                             double nowDebitYae=Math.abs(debitYae);
                             if (nowDebitKyat>totKyat && nowDebitPal >totPal && nowDebitYae>totYae){
                                 TOTALKYAT=nowDebitKyat-totKyat;
                                 TOTALPAl=nowDebitPal-totPal;
                                 TOTALYAE=nowDebitYae-totYae;

                                  buyDebitKyat.setText(String.valueOf(-TOTALKYAT));
                                  buyDebitPal.setText(String.valueOf(-TOTALPAl));
                                  buyDebitYae.setText(String.valueOf(-TOTALYAE));

                             }else  if (nowDebitKyat>totKyat && nowDebitPal>totPal && nowDebitYae==totYae){
                                 TOTALKYAT=nowDebitKyat-totKyat;
                                 TOTALPAl=nowDebitPal-totPal;
                                 TOTALYAE=nowDebitYae-totYae;

                                 buyDebitKyat.setText(String.valueOf(-TOTALKYAT));
                                 buyDebitPal.setText(String.valueOf(-TOTALPAl));
                                 buyDebitYae.setText(String.valueOf(TOTALYAE));

                             }else if (nowDebitKyat>totKyat && nowDebitPal>totPal && nowDebitYae<totYae){
                                 nowDebitPal=nowDebitPal-1;
                                 nowDebitYae=nowDebitYae+8;
                                TOTALKYAT=nowDebitKyat-totKyat;
                                TOTALPAl=nowDebitPal-totPal;
                                TOTALYAE=nowDebitYae-totYae;

                                    DecimalFormat form1 = new DecimalFormat("0.00");
                                    buyDebitYae.setText(String.valueOf(form1.format(-TOTALYAE)));
                                    if(TOTALPAl==0) {
                                        buyDebitPal.setText(String.valueOf(TOTALPAl));
                                    }else if(TOTALPAl!=0){
                                        buyDebitPal.setText(String.valueOf(-TOTALPAl));
                                    }

                                    if (TOTALKYAT==0){
                                        buyDebitKyat.setText(String.valueOf(TOTALKYAT));
                                    }else  if (TOTALKYAT!=0){
                                        buyDebitKyat.setText(String.valueOf(-TOTALKYAT));
                                    }

                            }else if (nowDebitKyat>totKyat && nowDebitPal<totPal && nowDebitYae>=totYae){
                                 TOTALYAE=nowDebitYae-totYae;
                                 nowDebitKyat=nowDebitKyat-1;
                                 nowDebitPal=nowDebitPal+16;
                                 TOTALPAl=nowDebitPal-totPal;
                                 TOTALKYAT=nowDebitKyat-totKyat;

                                 if (TOTALYAE==0){
                                     buyDebitYae.setText("0");
                                 }else if (TOTALYAE!=0){
                                     DecimalFormat form1 = new DecimalFormat("0.00");
                                     buyDebitYae.setText(String.valueOf(form1.format(-TOTALYAE)));
                                 }
                                 buyDebitPal.setText(String.valueOf(-TOTALPAl));
                                 if (TOTALKYAT==0){
                                     buyDebitKyat.setText(String.valueOf(TOTALKYAT));
                                 }else  if (TOTALKYAT!=0){
                                     buyDebitKyat.setText(String.valueOf(-TOTALKYAT));
                                 }

                             }else if (nowDebitKyat>totKyat && nowDebitPal<totPal && nowDebitYae<totYae) {
                                 nowDebitKyat=nowDebitKyat-1;
                                 nowDebitPal = nowDebitPal+15;
                                 nowDebitYae = nowDebitYae + 8;
                                 TOTALYAE = nowDebitYae - totYae;
                                 TOTALPAl = nowDebitPal - totPal;
                                 TOTALKYAT = nowDebitKyat - totKyat;
                                     DecimalFormat form1 = new DecimalFormat("0.00");
                                     buyDebitYae.setText(String.valueOf(form1.format(-TOTALYAE)));
                                 if (TOTALPAl==0){
                                     buyDebitPal.setText("0.0");
                                 }else if (TOTALPAl!=0) {
                                     buyDebitPal.setText(String.valueOf(-TOTALPAl));
                                 }
                                 buyDebitKyat.setText(String.valueOf(-TOTALKYAT));
                             }

                             else if (nowDebitKyat>totKyat && nowDebitPal==totPal && nowDebitYae>=totYae){
                                 TOTALYAE=nowDebitYae-totYae;
                                 TOTALPAl=nowDebitPal-totPal;
                                 TOTALKYAT=nowDebitKyat-totKyat;
                                 if (TOTALYAE==0){
                                     buyDebitYae.setText("0");
                                 }else if (TOTALYAE!=0){
                                     DecimalFormat form1 = new DecimalFormat("0.00");
                                     buyDebitYae.setText(String.valueOf(form1.format(-TOTALYAE)));
                                 }
                                 buyDebitPal.setText(String.valueOf(0));
                                 buyDebitKyat.setText(String.valueOf(-TOTALKYAT));

                             }else if (nowDebitKyat>totKyat && nowDebitPal==totPal && nowDebitYae<totYae){
                                 nowDebitKyat=nowDebitKyat-1;
                                 nowDebitPal=(nowDebitPal+16)-1;
                                 nowDebitYae=nowDebitYae+8;
                                 TOTALYAE=nowDebitYae-totYae;
                                 TOTALPAl=nowDebitPal-totPal;
                                 TOTALKYAT=nowDebitKyat-totKyat;

                                    DecimalFormat form1 = new DecimalFormat("0.00");
                                     buyDebitYae.setText(String.valueOf(form1.format(-TOTALYAE)));
                                     buyDebitPal.setText(String.valueOf(-TOTALPAl));
                                 if (TOTALKYAT==0){
                                     buyDebitKyat.setText(String.valueOf(TOTALKYAT));
                                 }else  if (TOTALKYAT!=0){
                                     buyDebitKyat.setText(String.valueOf(-TOTALKYAT));
                                 }

                             }else if (nowDebitKyat==totKyat && nowDebitPal==totPal && nowDebitYae==totYae){
                                 //for equal
                                 buyDebitKyat.setText("0");
                                 buyDebitPal.setText("0");
                                 buyDebitYae.setText("0.0");

                             }else if (nowDebitKyat==totKyat && nowDebitPal==totPal && nowDebitYae>totYae){
                                 //for equal
                                 TOTALYAE=nowDebitYae-totYae;
                                 DecimalFormat form1 = new DecimalFormat("0.00");
                                 buyDebitYae.setText(String.valueOf(form1.format(-TOTALYAE)));
                                 buyDebitKyat.setText("0");
                                 buyDebitPal.setText("0");

                             }else if (nowDebitKyat==totKyat && nowDebitPal==totPal && nowDebitYae<totYae){
                                 //for equal
                                 TOTALYAE=totYae-nowDebitYae;
                                 DecimalFormat form1 = new DecimalFormat("0.00");
                                 buyDebitYae.setText(String.valueOf(form1.format(TOTALYAE)));
                                 buyDebitKyat.setText("0");
                                 buyDebitPal.setText("0");

                             }else if (nowDebitKyat==totKyat && nowDebitPal>totPal && nowDebitYae==totYae){
                                 //for equal
                                 buyDebitKyat.setText("0");
                                 TOTALPAl=nowDebitPal-totPal;
                                 buyDebitPal.setText(String.valueOf(-TOTALPAl));
                                 buyDebitYae.setText("0.0");

                             }else if (nowDebitKyat==totKyat && nowDebitPal>totPal && nowDebitYae>totYae){
                                 //for equal
                                 TOTALYAE=nowDebitYae-totYae;
                                 TOTALPAl=nowDebitPal-totPal;
                                 DecimalFormat form1 = new DecimalFormat("0.00");
                                 buyDebitYae.setText(String.valueOf(form1.format(-TOTALYAE)));
                                 buyDebitPal.setText(String.valueOf(-TOTALPAl));
                                 buyDebitKyat.setText("0");

                             }else if (nowDebitKyat==totKyat && nowDebitPal>totPal && nowDebitYae<totYae){
                                 //for equal
                                 nowDebitPal=nowDebitPal-1;
                                 nowDebitYae=nowDebitYae+8;
                                 TOTALYAE=nowDebitYae-totYae;
                                 TOTALPAl=nowDebitPal-totPal;
                                 DecimalFormat form1 = new DecimalFormat("0.00");
                                 buyDebitYae.setText(String.valueOf(form1.format(-TOTALYAE)));
                                 if (TOTALPAl==0){
                                     buyDebitPal.setText("0");
                                 }else if (TOTALPAl!=0){
                                     buyDebitPal.setText(String.valueOf(-TOTALPAl));
                                 }
                                 buyDebitKyat.setText("0");

                             }else if (nowDebitKyat==totKyat && nowDebitPal<totPal && nowDebitYae==totYae){
                                 //for equal
                                 TOTALPAl=totPal-nowDebitPal;
                                 buyDebitPal.setText(String.valueOf(TOTALPAl));
                                 buyDebitKyat.setText("0");
                                 buyDebitYae.setText("0");

                             }else if (nowDebitKyat==totKyat && nowDebitPal<totPal && nowDebitYae>totYae){
                                 //for equal
                                 totPal=totPal-1;
                                 totYae=totYae+8;
                                 TOTALYAE=totYae-nowDebitYae;
                                 TOTALPAl=totPal-nowDebitPal;
                                 DecimalFormat form1 = new DecimalFormat("0.00");
                                 buyDebitYae.setText(String.valueOf(form1.format(TOTALYAE)));

                                 if (TOTALPAl==0){
                                     buyDebitPal.setText("0");
                                 }else if (TOTALPAl!=0){
                                     buyDebitPal.setText(String.valueOf(TOTALPAl));
                                 }
                                 buyDebitKyat.setText("0");


                             }else if (nowDebitKyat==totKyat && nowDebitPal<totPal && nowDebitYae<totYae){
                                 //for equal

                                 TOTALYAE=totYae-nowDebitYae;
                                 TOTALPAl=totPal-nowDebitPal;
                                 DecimalFormat form1 = new DecimalFormat("0.00");
                                 buyDebitYae.setText(String.valueOf(form1.format(TOTALYAE)));
                                 buyDebitPal.setText(String.valueOf(TOTALPAl));
                                 buyDebitKyat.setText("0");


                             }else if (nowDebitKyat<totKyat && nowDebitPal>totPal && nowDebitYae>totYae){
                                // for minus < >>
                                 totKyat=totKyat-1;
                                 totPal = (totPal+16)-1;
                                 totYae=totYae+8;

                                 TOTALYAE=totYae-nowDebitYae;
                                 TOTALPAl=totPal-nowDebitPal;
                                 TOTALKYAT=totKyat-nowDebitKyat;

                                 DecimalFormat form1 = new DecimalFormat("0.00");
                                 buyDebitYae.setText(String.valueOf(form1.format(TOTALYAE)));

                                 buyDebitPal.setText(String.valueOf(TOTALPAl));
                                 buyDebitKyat.setText(String.valueOf(TOTALKYAT));
                             }else if (nowDebitKyat<totKyat && nowDebitPal>totPal && nowDebitYae<=totYae){
                                 // for minus < ><=
                                 totKyat=totKyat-1;
                                 totPal = totPal+16;

                                 TOTALYAE=totYae-nowDebitYae;
                                 TOTALPAl=totPal-nowDebitPal;
                                 TOTALKYAT=totKyat-nowDebitKyat;

                                 DecimalFormat form1 = new DecimalFormat("0.00");
                                 buyDebitYae.setText(String.valueOf(form1.format(TOTALYAE)));

                                 buyDebitPal.setText(String.valueOf(TOTALPAl));
                                 buyDebitKyat.setText(String.valueOf(TOTALKYAT));
                             }else if (nowDebitKyat<totKyat && nowDebitPal<totPal && nowDebitYae<=totYae){
                                 // for minus < < <=
                                 TOTALYAE=totYae-nowDebitYae;
                                 TOTALPAl=totPal-nowDebitPal;
                                 TOTALKYAT=totKyat-nowDebitKyat;

                                 DecimalFormat form1 = new DecimalFormat("0.00");
                                 buyDebitYae.setText(String.valueOf(form1.format(TOTALYAE)));

                                 buyDebitPal.setText(String.valueOf(TOTALPAl));
                                 buyDebitKyat.setText(String.valueOf(TOTALKYAT));
                             }else if (nowDebitKyat<totKyat && nowDebitPal<totPal && nowDebitYae>totYae){
                                 // for minus < < >
                                 totPal=totPal-1;
                                 totYae=totYae+8;

                                 TOTALYAE=totYae-nowDebitYae;
                                 TOTALPAl=totPal-nowDebitPal;
                                 TOTALKYAT=totKyat-nowDebitKyat;

                                 DecimalFormat form1 = new DecimalFormat("0.00");
                                 buyDebitYae.setText(String.valueOf(form1.format(TOTALYAE)));

                                 if (TOTALPAl==0){
                                     buyDebitPal.setText("0");
                                 }else if (TOTALPAl!=0){
                                     buyDebitPal.setText(String.valueOf(TOTALPAl));
                                 }

                                 buyDebitKyat.setText(String.valueOf(TOTALKYAT));
                             }else if (nowDebitKyat<totKyat && nowDebitPal==totPal && nowDebitYae<=totYae){
                                 // for minus < == or < = <

                                 TOTALKYAT=totKyat-nowDebitKyat;
                                 TOTALYAE=totYae-nowDebitYae;
                                 DecimalFormat form1 = new DecimalFormat("0.00");
                                 buyDebitYae.setText(String.valueOf(form1.format(TOTALYAE)));
                                 buyDebitPal.setText("0");
                                 buyDebitKyat.setText(String.valueOf(TOTALKYAT));

                             }else if (nowDebitKyat<totKyat && nowDebitPal==totPal && nowDebitYae>totYae){
                                 // for minus < = >
                                 totKyat=totKyat-1;
                                 totPal=totPal+15;
                                 totYae=totYae+8;
                                 TOTALKYAT=totKyat-nowDebitKyat;
                                 TOTALPAl=totPal-nowDebitPal;
                                 TOTALYAE=totYae-nowDebitYae;
                                 DecimalFormat form1 = new DecimalFormat("0.00");
                                 buyDebitYae.setText(String.valueOf(form1.format(TOTALYAE)));
                                 buyDebitPal.setText(String.valueOf(TOTALPAl));
                                 if (TOTALKYAT==0){
                                     buyDebitKyat.setText("0");
                                 }
                                 buyDebitKyat.setText(String.valueOf(TOTALKYAT));

                             }







                            // Toast.makeText(getContext(), String.valueOf(nowDebitKyat) +String.valueOf(nowDebitPal)+String.valueOf(nowDebitYae), Toast.LENGTH_SHORT).show();
//                            }

//                            if (totKyat>debitKyat){
//                                Toast.makeText(getContext(), "plus", Toast.LENGTH_SHORT).show();
//                            }else if (totKyat<debitKyat){
//                                Toast.makeText(getContext(), "minus", Toast.LENGTH_SHORT).show();
//                            }

                            //Toast.makeText(getContext(), "ok minus", Toast.LENGTH_SHORT).show();
                        }else if(!DebitKyat.contains("-") && !DebitPal.contains("-") && !DebitYae.contains("-")){
                          //  Toast.makeText(getContext(), "positive operation is work ", Toast.LENGTH_SHORT).show();
                            TOTALKYAT = totKyat + debitKyat;
                            TOTALPAl = totPal + debitPal;
                            TOTALYAE = totYae + debitYae;


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
                        }


                        break;
                    }catch (Exception e){
                        e.printStackTrace();
                    }



            case R.id.totalRemainAmount:
                try {
                  //  Toast.makeText(getContext(), "Total Remain Amount", Toast.LENGTH_SHORT).show();
                    int getTotalAmountKyat = Integer.parseInt(ednowPaymentKyat.getText().toString());
                    int getTotalAmountPal = Integer.parseInt(ednowPaymentPal.getText().toString());
                    double getTotalAmountYae = Double.parseDouble(ednowPaymentYae.getText().toString());

                    int PayKyat = Integer.parseInt(paymentKyat.getText().toString());
                    int PayPal = Integer.parseInt(paymentPal.getText().toString());
                    double PayYae = Double.parseDouble(paymentYae.getText().toString());

                    int RemainKyat = 0;
                    int RemainPal = 0;
                    double RemainYae = 0.0;

                    //>= && > & > totalRemainAmount
//                    if(PayKyat>=getTotalAmountKyat && PayPal>getTotalAmountPal && PayYae>getTotalAmountYae)
//                    {
//                        nowRemainKyat.setText("");
//                        nowRemainPal.setText("");
//                        nowRemainYae.setText("");
//                        RemainYae = PayYae-getTotalAmountYae;
//                        RemainPal = PayPal-getTotalAmountPal;
//                        RemainKyat=PayKyat-getTotalAmountKyat;
//                        nowRemainKyat.setText(String.valueOf(-RemainKyat));
//                       nowRemainPal.setText(String.valueOf(-RemainPal));
//                        DecimalFormat form1 = new DecimalFormat("0.00");
//                        nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
//                        Toast.makeText(getContext(), nowRemainYae.getText().toString(), Toast.LENGTH_SHORT).show();
//                            break;
//                    }
//                    //>= && < & < totalRemainAmount
//                    else if(PayKyat>getTotalAmountKyat && PayPal<getTotalAmountPal && PayYae<getTotalAmountYae)
//                    {
//                        nowRemainKyat.setText("");
//                        nowRemainPal.setText("");
//                        nowRemainYae.setText("");
//                        Toast.makeText(getContext(), String.valueOf(PayKyat)+String.valueOf(PayPal)+String.valueOf(PayYae), Toast.LENGTH_SHORT).show();
//                        PayPal=PayPal-1;
//                        PayYae = PayYae+8;
//                        RemainYae = PayYae-getTotalAmountYae;
//                        DecimalFormat form1 = new DecimalFormat("0.00");
//                        nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
//
//                        PayKyat=PayKyat-1;
//                        PayPal=PayPal+16;
//                        RemainPal = PayPal-getTotalAmountPal;
//                        if (RemainPal==0) {
//                            nowRemainPal.setText(String.valueOf(RemainPal));
//                        }else if(RemainPal!=0) {
//                            nowRemainPal.setText(String.valueOf(-RemainPal));
//                        }
//
//                        RemainKyat=PayKyat-getTotalAmountKyat;
//                        nowRemainKyat.setText(String.valueOf(-RemainKyat));
//                        if(PayKyat==getTotalAmountKyat){
//                            nowRemainKyat.setText(0);
//                           // Toast.makeText(getContext(), "now remain kyat is " +String.valueOf(PayKyat), Toast.LENGTH_SHORT).show();
//                        }else if(PayKyat>getTotalAmountKyat) {
//                            RemainKyat = PayKyat - getTotalAmountKyat;
//                            Toast.makeText(getContext(), "now remain kyat is " +String.valueOf(RemainKyat), Toast.LENGTH_SHORT).show();
//                            nowRemainKyat.setText(String.valueOf(-RemainKyat));
//                        }
//
//                        break;
//
//                    }
//                    else if (PayKyat>=getTotalAmountKyat && PayPal>getTotalAmountPal && PayYae<getTotalAmountYae)
//                    {
//                        nowRemainKyat.setText("");
//                        nowRemainPal.setText("");
//                        nowRemainYae.setText("");
//                       PayPal=PayPal-1;
//                       PayYae=PayYae+8;
//                       RemainYae=PayYae-getTotalAmountYae;
//                        RemainPal=PayPal-getTotalAmountPal;
//                        RemainKyat = PayKyat -getTotalAmountKyat;
//
//                        if(RemainYae==0){
//                            DecimalFormat form1 = new DecimalFormat("0.00");
//                            nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
//                        }else if(RemainYae!=0) {
//                            DecimalFormat form1 = new DecimalFormat("0.00");
//                            nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
//                        }
//                        if(RemainPal==0){
//                            nowRemainPal.setText(String.valueOf(RemainPal));
//                        }else if(RemainPal!=0) {
//                            nowRemainPal.setText(String.valueOf(-RemainPal));
//                        }
//                         if(RemainKyat==0){
//                            nowRemainKyat.setText(String.valueOf(RemainKyat));
//                        } else if(RemainKyat!=0){
//                            nowRemainKyat.setText(String.valueOf(-RemainKyat));
//                        }
//
//                         break;
//                  }
//                    else if(PayKyat>getTotalAmountKyat && PayPal<getTotalAmountPal && PayYae>getTotalAmountYae)
//
//                    {
//                        nowRemainKyat.setText("");
//                        nowRemainPal.setText("");
//                        nowRemainYae.setText("");
////                        PayKyat=PayKyat-1;
////                        PayPal=PayPal+16;
////                        RemainYae=PayYae-getTotalAmountYae;
////                        RemainPal=PayPal-getTotalAmountPal;
////                        RemainKyat =PayKyat-getTotalAmountKyat;
////                        nowRemainKyat.setText(String.valueOf(-RemainKyat));
////                        nowRemainPal.setText(String.valueOf(-RemainYae));
////                        if (RemainYae==0){
////                            DecimalFormat form1 = new DecimalFormat("0.00");
////                            nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
////                        }else if(RemainYae!=0){
////                            DecimalFormat form1 = new DecimalFormat("0.00");
////                            nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
////                       }
//
//
////                        RemainPal=PayPal-getTotalAmountPal;
////                        if (RemainPal==0){
////                            nowRemainPal.setText(String.valueOf(RemainPal));
////                        }else if(RemainPal!=0){
////                            nowRemainPal.setText(String.valueOf(-RemainPal));
////                        }
////                        RemainKyat = PayKyat-getTotalAmountKyat;
////
//////                        if (RemainKyat==0){
//////                            nowRemainKyat.setText(String.valueOf(RemainKyat));
//////                        }else if(RemainKyat!=0){
//////                            nowRemainKyat.setText(String.valueOf(-RemainKyat));
//////                        }
////
//                       // Toast.makeText(getContext(), "To  do", Toast.LENGTH_SHORT).show();
//                    }
//                    else if(PayKyat>getTotalAmountKyat && PayPal==getTotalAmountPal && PayYae==getTotalAmountYae)
//                    {
//
//                        nowRemainKyat.setText("");
//                        nowRemainPal.setText("");
//                        nowRemainYae.setText("");
//                        RemainYae=PayYae-getTotalAmountYae;
//                        RemainPal=PayPal-getTotalAmountPal;
//                        RemainKyat=PayKyat-getTotalAmountKyat;
//                        DecimalFormat form1 = new DecimalFormat("0.00");
//                        nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
//                        nowRemainPal.setText(String.valueOf(RemainPal));
//                        if (RemainKyat==0){
//                            nowRemainKyat.setText(String.valueOf(RemainKyat));
//                        }else if (RemainKyat!=0){
//
//                            nowRemainKyat.setText(String.valueOf(-RemainKyat));
//                        }
//
//                        break;
//                    }
//
//                    else if(PayKyat>=getTotalAmountKyat && PayPal>getTotalAmountPal && PayYae==getTotalAmountYae)
//                    {
//                        nowRemainKyat.setText("");
//                        nowRemainPal.setText("");
//                        nowRemainYae.setText("");
//
//                        RemainYae=PayYae-getTotalAmountYae;
//                        RemainPal=PayPal-getTotalAmountPal;
//                        RemainKyat=PayKyat-getTotalAmountKyat;
//                        DecimalFormat form1 = new DecimalFormat("0.00");
//                        nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
//                        if(RemainPal==0){
//                            nowRemainPal.setText(String.valueOf(RemainPal));
//                        }else if (RemainPal!=0){
//                            nowRemainPal.setText(String.valueOf(-RemainPal));
//                        }
//                        if (RemainKyat==0){
//                            nowRemainKyat.setText(String.valueOf(RemainKyat));
//                        }else if (RemainKyat!=0){
//
//                            nowRemainKyat.setText(String.valueOf(-RemainKyat));
//                        }
//
//                        break;
//                    }
//
//
//
//                    else if(PayKyat>=getTotalAmountKyat && PayPal<getTotalAmountPal && PayYae==getTotalAmountYae)
//                    {
//                        nowRemainKyat.setText("");
//                        nowRemainPal.setText("");
//                        nowRemainYae.setText("");
//                        RemainYae=PayYae-getTotalAmountYae;
//                        PayKyat=PayKyat-1;
//                        PayPal=PayPal+16;
//                        RemainPal=PayPal-getTotalAmountPal;
//                        RemainKyat=PayKyat-getTotalAmountKyat;
//                        DecimalFormat form1 = new DecimalFormat("0.00");
//                        nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
//                        if(RemainPal==0){
//                            nowRemainPal.setText(String.valueOf(RemainPal));
//                        }else if (RemainPal!=0){
//                            nowRemainPal.setText(String.valueOf(-RemainPal));
//                        }
//                        if (RemainKyat==0){
//                            nowRemainKyat.setText(String.valueOf(RemainKyat));
//                        }else if (RemainKyat!=0){
//
//                            nowRemainKyat.setText(String.valueOf(-RemainKyat));
//                        }
//                        break;
//                    }
//
//                    else if(PayKyat>=getTotalAmountKyat && PayPal==getTotalAmountPal && PayYae>getTotalAmountYae) {
//                        nowRemainKyat.setText("");
//                        nowRemainPal.setText("");
//                        nowRemainYae.setText("");
//                        RemainYae = PayYae - getTotalAmountYae;
//                        RemainPal = PayPal - getTotalAmountPal;
//                        RemainKyat = PayKyat - getTotalAmountKyat;
//                        if(RemainYae==0) {
//                            DecimalFormat form1 = new DecimalFormat("0.00");
//                            nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
//                        }else if(RemainYae!=0){
//                            DecimalFormat form1 = new DecimalFormat("0.00");
//                            nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
//                        }
//
//                        if (RemainPal == 0)
//                        {
//                            nowRemainPal.setText(String.valueOf(RemainPal));
//                        }
//                        else if (RemainPal != 0)
//                        {
//                            nowRemainPal.setText(String.valueOf(-RemainPal));
//                        }else if(RemainKyat == 0)
//                        {
//                            nowRemainKyat.setText(String.valueOf(RemainKyat));
//                        } else if (RemainKyat != 0)
//                        {
//                            nowRemainKyat.setText(String.valueOf(-RemainKyat));
//                        }
//                        break;
//                        }
//                    else if (PayKyat >=getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae < getTotalAmountYae)
//                    {
//                        nowRemainKyat.setText("");
//                        nowRemainPal.setText("");
//                        nowRemainYae.setText("");
//                            PayKyat = PayKyat - 1;
//                            PayPal = (PayPal + 16) - 1;
//                            PayYae = PayYae + 8;
//                            RemainYae = PayYae - getTotalAmountYae;
//                            RemainPal = PayPal - getTotalAmountPal;
//                            RemainKyat = PayKyat - getTotalAmountKyat;
//                            if (RemainYae == 0) {
//                                DecimalFormat form1 = new DecimalFormat("0.00");
//                                nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
//                            } else if(RemainYae!=0) {
//                                DecimalFormat form1 = new DecimalFormat("0.00");
//                                nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
//                            }
//                            if (RemainPal == 0) {
//                                nowRemainPal.setText(String.valueOf(RemainPal));
//                            } else {
//                                nowRemainPal.setText(String.valueOf(-RemainPal));
//                            }
//                            if (RemainKyat == 0) {
//                                nowRemainKyat.setText(String.valueOf(RemainKyat));
//                            } else if (RemainKyat != 0) {
//
//                                nowRemainKyat.setText(String.valueOf(-RemainKyat));
//                            }
//
//                            break;
//                        }else if (PayKyat==getTotalAmountKyat && PayPal==getTotalAmountPal && PayYae==getTotalAmountYae){
//                        nowRemainKyat.setText(0);
//                        nowRemainPal.setText(0);
//                        nowRemainYae.setText(0);
//
//                    }

                    //for greater
                    if (PayKyat<getTotalAmountKyat && PayPal<getTotalAmountPal && PayYae<getTotalAmountYae){
                        RemainKyat=getTotalAmountKyat-PayKyat;
                        RemainPal=getTotalAmountPal-PayPal;
                        RemainYae=getTotalAmountYae-PayYae;
                        nowRemainKyat.setText(String.valueOf(RemainKyat));
                        nowRemainPal.setText(String.valueOf(RemainPal));
                        nowRemainYae.setText(String.valueOf(RemainYae));
                    }else if (PayKyat > getTotalAmountKyat && PayPal < getTotalAmountPal && PayYae == getTotalAmountYae) {

                        PayKyat = PayKyat-1;
                        PayPal=PayPal+16;
                        RemainPal=PayPal-getTotalAmountPal;
                        RemainKyat=PayKyat-getTotalAmountKyat;
                        nowRemainYae.setText("0.0");
                        nowRemainPal.setText(String.valueOf(-RemainPal));
                        if (RemainKyat==0){
                            nowRemainKyat.setText("0"); }else if (RemainKyat!=0){
                            nowRemainKyat.setText(String.valueOf(-RemainKyat));
                        }
                   }else if(PayKyat > getTotalAmountKyat && PayPal < getTotalAmountPal && PayYae > getTotalAmountYae){
                        RemainYae=PayYae-getTotalAmountYae;
                        PayKyat = PayKyat-1;
                        PayPal=PayPal+16;
                        RemainPal=PayPal-getTotalAmountPal;
                        RemainKyat=PayKyat-getTotalAmountKyat;

                        DecimalFormat form1 = new DecimalFormat("0.00");
                       nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                        nowRemainPal.setText(String.valueOf(-RemainPal));
                        if (RemainKyat==0){
                            nowRemainKyat.setText("0");
                        }else if (RemainKyat!=0){
                            nowRemainKyat.setText(String.valueOf(-RemainKyat));
                        }

                   }else if(PayKyat > getTotalAmountKyat && PayPal < getTotalAmountPal && PayYae < getTotalAmountYae){

                        PayKyat = PayKyat-1;
                        PayPal=(PayPal+16)-1;
                        PayYae=PayYae+8;
                        RemainYae=PayYae-getTotalAmountYae;
                        RemainPal=PayPal-getTotalAmountPal;
                        RemainKyat=PayKyat-getTotalAmountKyat;

                        DecimalFormat form1 = new DecimalFormat("0.00");
                        nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                        nowRemainPal.setText(String.valueOf(-RemainPal));
                        if (RemainKyat==0){
                            nowRemainKyat.setText("0");
                        }else if (RemainKyat!=0){
                            nowRemainKyat.setText(String.valueOf(-RemainKyat));
                        }

                    }else if (PayKyat > getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae == getTotalAmountYae) {
                        RemainKyat = PayKyat - getTotalAmountKyat;
                        nowRemainKyat.setText(String.valueOf(-RemainKyat));
                        nowRemainPal.setText("0");
                        nowRemainYae.setText("0");
                    }
                    else if (PayKyat > getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae > getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        nowRemainKyat.setText(String.valueOf(-RemainKyat));
                        nowRemainPal.setText(String.valueOf(RemainPal));
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                    } else if (PayKyat > getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae < getTotalAmountYae) {

                        PayKyat = PayKyat - 1;
                        PayPal = (PayPal + 16) - 1;
                        PayYae = PayYae + 8;
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        if (RemainKyat == 0) {
                            nowRemainKyat.setText(String.valueOf(RemainKyat));
                        } else if (RemainKyat != 0) {
                            nowRemainKyat.setText(String.valueOf(-RemainKyat));
                        }
                        if (RemainPal == 0) {
                            nowRemainPal.setText(String.valueOf(RemainPal));
                        } else if (RemainPal != 0) {
                            nowRemainPal.setText(String.valueOf(-RemainPal));
                        }
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                    } else if (PayKyat > getTotalAmountKyat && PayPal > getTotalAmountYae && PayYae == getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        nowRemainKyat.setText(String.valueOf(-RemainKyat));
                        nowRemainPal.setText(String.valueOf(-RemainPal));
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
                    } else if (PayKyat > getTotalAmountKyat && PayPal > getTotalAmountYae && PayYae > getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        nowRemainKyat.setText(String.valueOf(-RemainKyat));
                        nowRemainPal.setText(String.valueOf(-RemainPal));
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                    } else if (PayKyat > getTotalAmountKyat && PayPal > getTotalAmountYae && PayYae < getTotalAmountYae) {
                        PayPal = PayPal - 1;
                        PayYae = PayYae + 8;
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        nowRemainKyat.setText(String.valueOf(-RemainKyat));
                        if (RemainPal == 0) {
                            nowRemainPal.setText(String.valueOf(RemainPal));
                        } else if (RemainPal != 0) {
                            nowRemainPal.setText(String.valueOf(-RemainPal));
                        }

                        DecimalFormat form1 = new DecimalFormat("0.00");
                        nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                    } else if (PayKyat == getTotalAmountKyat && PayPal > getTotalAmountPal && PayYae > getTotalAmountYae){
                    //for equal
                         RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        DecimalFormat form1 = new DecimalFormat("0.00");
                        nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                        nowRemainPal.setText(String.valueOf(-RemainPal));
                        nowRemainKyat.setText(String.valueOf(RemainKyat));
                    } else if (PayKyat == getTotalAmountKyat && PayPal > getTotalAmountPal && PayYae < getTotalAmountYae) {
                        PayPal = PayPal - 1;
                        PayYae = PayYae + 8;
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        DecimalFormat form1 = new DecimalFormat("0.00");
                        nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                        nowRemainPal.setText(String.valueOf(-RemainPal));
                        nowRemainKyat.setText(String.valueOf(RemainKyat));
                    } else if (PayKyat == getTotalAmountKyat && PayPal > getTotalAmountPal && PayYae == getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        DecimalFormat form1 = new DecimalFormat("0.00");
                        nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
                        nowRemainPal.setText(String.valueOf(-RemainPal));
                        nowRemainKyat.setText(String.valueOf(RemainKyat));
                    } else if (PayKyat == getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae == getTotalAmountYae) { nowRemainKyat.setText("0");
                        nowRemainPal.setText("0");
                        nowRemainYae.setText("0.0");
                    } else if (PayKyat == getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae > getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        nowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                        nowRemainKyat.setText("0");
                        nowRemainPal.setText("0");
                    } else if (PayKyat == getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae < getTotalAmountYae) {
                        RemainYae = getTotalAmountYae - PayYae;
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
                        nowRemainKyat.setText("0");
                        nowRemainPal.setText("0");
                    } else if (PayKyat == getTotalAmountKyat && PayPal < getTotalAmountPal && PayYae == getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = getTotalAmountPal - PayPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;
                        nowRemainYae.setText(String.valueOf(RemainYae));
                        nowRemainPal.setText(String.valueOf(RemainPal));
                        nowRemainKyat.setText(String.valueOf(RemainKyat));
                    } else if (PayKyat == getTotalAmountKyat && PayPal < getTotalAmountPal && PayYae > getTotalAmountYae) {

                        getTotalAmountPal = getTotalAmountPal - 1;
                        getTotalAmountYae = getTotalAmountYae + 8;
                        RemainYae = getTotalAmountYae - PayYae;
                        RemainPal = getTotalAmountPal - PayPal;
                        RemainKyat = getTotalAmountKyat - PayKyat;

                        nowRemainYae.setText(String.valueOf(RemainYae));
                        nowRemainPal.setText(String.valueOf(RemainPal));
                        nowRemainKyat.setText(String.valueOf(RemainKyat));
                    } else if (PayKyat == getTotalAmountKyat && PayPal < getTotalAmountPal && PayYae < getTotalAmountYae) {
                        RemainYae = getTotalAmountYae - PayYae;
                        RemainPal = getTotalAmountPal - PayPal;
                        RemainKyat = getTotalAmountKyat - PayKyat;
                        nowRemainYae.setText(String.valueOf(RemainYae));
                        nowRemainPal.setText(String.valueOf(RemainPal));
                        nowRemainKyat.setText(String.valueOf(RemainKyat));
                    } else if (PayKyat < getTotalAmountKyat) {
                            //for less than
                        if (PayYae > getTotalAmountYae) {
                            getTotalAmountPal = getTotalAmountPal - 1;
                            getTotalAmountYae = getTotalAmountYae + 8;
                            RemainYae = getTotalAmountYae - PayYae;
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
                        } else if (PayYae < getTotalAmountYae) {
                            RemainYae = getTotalAmountYae - PayYae;
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
                        } else if (PayYae == getTotalAmountYae) {
                            RemainYae = getTotalAmountYae - PayYae;
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            nowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
                        }

                        if (PayPal > getTotalAmountPal) {
                            getTotalAmountKyat = getTotalAmountKyat - 1;
                            getTotalAmountPal = getTotalAmountPal + 16;
                            RemainPal = getTotalAmountPal - PayPal;
                            nowRemainPal.setText(String.valueOf(RemainPal));
                        } else if (PayPal < getTotalAmountYae) {
                            RemainPal = getTotalAmountPal - PayPal;
                            nowRemainPal.setText(String.valueOf(RemainPal));

                        } else if (PayPal == getTotalAmountPal) {
                            RemainPal = getTotalAmountPal - PayPal;
                            nowRemainPal.setText(String.valueOf(RemainPal));
                        }

                        RemainKyat = getTotalAmountKyat - PayKyat;
                        nowRemainKyat.setText(String.valueOf(RemainKyat));
                    }

                    break;

                }catch (Exception e){
                    e.printStackTrace();
                }

            case R.id.returnGoldPlusAyot:
                try {


                    int getTotalAmountKyat = Integer.parseInt(buyDebitKyat.getText().toString());
                    int getTotalAmountPal = Integer.parseInt(buyDebitPal.getText().toString());
                    double getTotalAmountYae = Double.parseDouble(buyDebitYae.getText().toString());

                    int PayKyat = Integer.parseInt(edReturnGoldKyat.getText().toString());
                    int PayPal = Integer.parseInt(edReturnGoldPal.getText().toString());
                    double PayYae = Double.parseDouble(edReturnGoldYae.getText().toString());

                    int RemainKyat = 0;
                    int RemainPal = 0;
                    double RemainYae = 0.0;

                    if(PayKyat>getTotalAmountKyat && PayPal>getTotalAmountPal && PayYae>getTotalAmountYae)
                    {
                        RemainYae = PayYae-getTotalAmountYae;
                        RemainPal = PayPal-getTotalAmountPal;
                        RemainKyat=PayKyat-getTotalAmountKyat;
                        ednowPaymentKyat.setText(String.valueOf(-RemainKyat));
                        ednowPaymentPal.setText(String.valueOf(-RemainPal));
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowPaymentYae.setText(String.valueOf(form1.format(-RemainYae)));
                      //
                        // Toast.makeText(getContext(), ednowPaymentYae.getText().toString(), Toast.LENGTH_SHORT).show();

                    }else if(PayKyat>getTotalAmountKyat && PayPal<getTotalAmountPal && PayYae<getTotalAmountYae)
                    {
                        PayPal=PayPal-1;
                        PayYae = PayYae+8;
                        RemainYae = PayYae-getTotalAmountYae;
                        PayKyat=PayKyat-1;
                        PayPal=PayPal+16;
                        RemainPal = PayPal-getTotalAmountPal;
                        RemainKyat=PayKyat-getTotalAmountKyat;
//                        Toast.makeText(getContext(), "Yae is " + String.valueOf(RemainYae), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getContext(), "Pal is" + String.valueOf(RemainPal), Toast.LENGTH_SHORT).show();
                       // Toast.makeText(getContext(),"Kyat is ok" +String.valueOf(RemainKyat), Toast.LENGTH_SHORT).show();
                        ednowPaymentKyat.setText(String.valueOf(RemainKyat));
                        if (RemainPal==0) {
                            ednowPaymentPal.setText(String.valueOf(RemainPal));
                        }else if (RemainPal!=0) {
                            ednowPaymentPal.setText(String.valueOf(-RemainPal));
                        }
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowPaymentYae.setText(String.valueOf(form1.format(-RemainYae)));
                        //Toast.makeText(getContext(), ednowPaymentYae.getText().toString(), Toast.LENGTH_SHORT).show();

                    }
                    else if (PayKyat>getTotalAmountKyat && PayPal>getTotalAmountPal && PayYae<getTotalAmountYae)
                    {
                        PayPal=PayPal-1;
                        PayYae=PayYae+8;
                        RemainYae=PayYae-getTotalAmountYae;
                        RemainPal=PayPal-getTotalAmountPal;
                        RemainKyat = PayKyat -getTotalAmountKyat;

                        if(RemainYae==0){
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        }else if(RemainYae!=0) {
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowPaymentYae.setText(String.valueOf(form1.format(-RemainYae)));
                        }
                        if(RemainPal==0){
                            ednowPaymentPal.setText(String.valueOf(RemainPal));
                        }else if(RemainPal!=0) {
                            ednowPaymentPal.setText(String.valueOf(-RemainPal));
                        }
                        if(RemainKyat==0){
                            ednowPaymentKyat.setText(String.valueOf(RemainKyat));
                        } else if(RemainKyat!=0){
                            ednowPaymentKyat.setText(String.valueOf(-RemainKyat));
                        }

                    }else if (PayKyat>getTotalAmountKyat && PayPal<getTotalAmountPal && PayYae>getTotalAmountYae)

                    {
                        RemainYae=PayYae-getTotalAmountYae;
                        PayKyat=PayKyat-1;
                        PayPal=PayPal+16;
                        RemainPal=PayPal-getTotalAmountPal;
                        RemainKyat = PayKyat-getTotalAmountKyat;
                        if (RemainYae==0){
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        }else if(RemainYae!=0){
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowPaymentYae.setText(String.valueOf(form1.format(-RemainYae)));
                        }

                        if (RemainPal==0){
                            ednowPaymentPal.setText(String.valueOf(RemainPal));
                        }else if(RemainPal!=0){
                            ednowPaymentPal.setText(String.valueOf(-RemainPal));
                        }

                        if (RemainKyat==0){
                            ednowPaymentKyat.setText(String.valueOf(RemainKyat));
                        }else if(RemainKyat!=0){
                            ednowPaymentKyat.setText(String.valueOf(-RemainKyat));
                        }

                    }
                    else if(PayKyat>getTotalAmountKyat && PayPal==getTotalAmountPal && PayYae==getTotalAmountYae)
                    {

                        RemainYae=PayYae-getTotalAmountYae;
                        RemainPal=PayPal-getTotalAmountPal;
                        RemainKyat=PayKyat-getTotalAmountKyat;
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        ednowPaymentPal.setText(String.valueOf(RemainPal));
                        if (RemainKyat==0){
                            ednowPaymentKyat.setText(String.valueOf(RemainKyat));
                        }else if (RemainKyat!=0){

                            ednowPaymentKyat.setText(String.valueOf(-RemainKyat));
                        }
                    }

                    else if(PayKyat>getTotalAmountKyat && PayPal>getTotalAmountPal && PayYae==getTotalAmountYae)
                    {

                        RemainYae=PayYae-getTotalAmountYae;
                        RemainPal=PayPal-getTotalAmountPal;
                        RemainKyat=PayKyat-getTotalAmountKyat;
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        if(RemainPal==0){
                            ednowPaymentPal.setText(String.valueOf(RemainPal));
                        }else if (RemainPal!=0){
                            ednowPaymentPal.setText(String.valueOf(-RemainPal));
                        }
                        if (RemainKyat==0){
                            ednowPaymentKyat.setText(String.valueOf(RemainKyat));
                        }else if (RemainKyat!=0){

                            ednowPaymentKyat.setText(String.valueOf(-RemainKyat));
                        }
                    }



                    else if(PayKyat>getTotalAmountKyat && PayPal<getTotalAmountPal && PayYae==getTotalAmountYae)
                    {

                        RemainYae=PayYae-getTotalAmountYae;
                        PayKyat=PayKyat-1;
                        PayPal=PayPal+16;
                        RemainPal=PayPal-getTotalAmountPal;
                        RemainKyat=PayKyat-getTotalAmountKyat;
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        if(RemainPal==0){
                            ednowPaymentPal.setText(String.valueOf(RemainPal));
                        }else if (RemainPal!=0){
                            ednowPaymentPal.setText(String.valueOf(-RemainPal));
                        }
                        if (RemainKyat==0){
                            ednowPaymentKyat.setText(String.valueOf(RemainKyat));
                        }else if (RemainKyat!=0){

                            ednowPaymentKyat.setText(String.valueOf(-RemainKyat));
                        }
                    }

                    else if(PayKyat>getTotalAmountKyat && PayPal==getTotalAmountPal && PayYae>getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;
                        if(RemainYae==0) {
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        }else if(RemainYae!=0){
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowPaymentYae.setText(String.valueOf(form1.format(-RemainYae)));
                        }

                        if (RemainPal == 0)
                        {
                            ednowPaymentPal.setText(String.valueOf(RemainPal));
                        }
                        else if (RemainPal != 0)
                        {
                            ednowPaymentPal.setText(String.valueOf(-RemainPal));
                        }else if(RemainKyat == 0)
                        {
                            ednowPaymentKyat.setText(String.valueOf(RemainKyat));
                        } else if (RemainKyat != 0)
                        {
                            ednowPaymentKyat.setText(String.valueOf(-RemainKyat));
                        }
                    }
                    else if (PayKyat > getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae < getTotalAmountYae)
                    {
                        PayKyat = PayKyat - 1;
                        PayPal = (PayPal + 16) - 1;
                        PayYae = PayYae + 8;
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;
                        if (RemainYae == 0) {
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        } else if(RemainYae!=0) {
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowPaymentYae.setText(String.valueOf(form1.format(-RemainYae)));
                        }
                        if (RemainPal == 0) {
                            ednowPaymentPal.setText(String.valueOf(RemainPal));
                        } else {
                            ednowPaymentPal.setText(String.valueOf(-RemainPal));
                        }
                        if (RemainKyat == 0) {
                            ednowPaymentKyat.setText(String.valueOf(RemainKyat));
                        } else if (RemainKyat != 0) {

                            ednowPaymentKyat.setText(String.valueOf(-RemainKyat));
                        }
                    }

                    if (PayKyat==0 && PayPal==0 && PayYae==0){
                        ednowPaymentKyat.setText(String.valueOf(getTotalAmountKyat));
                        ednowPaymentPal.setText(String.valueOf(getTotalAmountPal));
                        ednowPaymentYae.setText(String.valueOf(getTotalAmountYae));
                    }





                    if (PayKyat <= getTotalAmountKyat) {

                        if (PayYae > getTotalAmountYae) {
                            getTotalAmountPal = getTotalAmountPal - 1;
                            getTotalAmountYae = getTotalAmountYae + 8;
                            RemainYae = getTotalAmountYae - PayYae;
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        } else if (PayYae < getTotalAmountYae) {
                            RemainYae = getTotalAmountYae - PayYae;
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        } else if (PayYae == getTotalAmountYae) {
                            RemainYae = getTotalAmountYae - PayYae;
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        }

                        if (PayPal > getTotalAmountPal) {
                            getTotalAmountKyat = getTotalAmountKyat - 1;
                            getTotalAmountPal = getTotalAmountPal + 16;
                            RemainPal = getTotalAmountPal - PayPal;
                            ednowPaymentPal.setText(String.valueOf(RemainPal));
                        } else if (PayPal < getTotalAmountPal) {
                            RemainPal = getTotalAmountPal - PayPal;
                            ednowPaymentPal.setText(String.valueOf(RemainPal));

                        } else if (PayPal == getTotalAmountPal) {
                            RemainPal = getTotalAmountPal - PayPal;
                            ednowPaymentPal.setText(String.valueOf(RemainPal));
                        }

                        RemainKyat = getTotalAmountKyat - PayKyat;
                        ednowPaymentKyat.setText(String.valueOf(RemainKyat));

                    }


                    break;
                }catch (Exception e){
                    e.printStackTrace();
                }


        }



    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        saleDate.setText(sdf.format(myCalendar.getTime()));
    }


    public void saveSaleInvoice() {

        String voucher_Number, sale_Date, qualtity, pointEight, kyat, pal, yae, gram, cuponCode, CustomerID,totalAyotkyat,totalAyotPel,totalAyotYae,
        previous_remain_kyat,previous_remain_pal,previous_remain_yae,buy_debit_kyat,buy_debit_pal,buy_debit_yae,payment_kyat,payment_pal,payment_yae,now_remain_kyat,now_remain_pal,now_remain_yae,new_total_kyat,new_total_pal,new_total_yae,note_description,
        return_gram,now_remain_gram,sub_return_kyat,sub_return_pal,sub_return_yae,return_quantity,return_point_eight,now_remain_quantity,now_remain_pointeight,return_ayot_kyat,return_ayot_pal,return_ayot_yae,now_total_ayot_kyat,now_total_ayot_pal,now_total_ayot_yae,
                return_gold_kyat,return_gold_pal,return_gold_yae,net_pay_kayt,net_pay_pal,net_pay_yae;
        voucher_Number = voucherNumber.getText().toString();

        if (voucher_Number.isEmpty()){
            voucherNumber.setError("Enter Voucher No");
        }

        gram = Gram.getText().toString();
        if (gram.isEmpty()){
            Gram.setError("Enter Gram");
        }

        CustomerID = edCustomerID.getText().toString();
        sale_Date = saleDate.getText().toString();
        if (sale_Date.isEmpty()){
            saleDate.setError("Choose Sale Date");
        }
        note_description = edNote.getText().toString();


        new_total_kyat = newTotalKyat.getText().toString();
        if (new_total_kyat.isEmpty()){
            newTotalKyat.setError("Enter Kyat");
        }

        new_total_pal = newTotalPal.getText().toString();
        if (new_total_pal.isEmpty()){
            newTotalPal.setError("Enter Pal");
        }

        new_total_yae = newTotalYae.getText().toString();
        if (new_total_yae.isEmpty()){
            newTotalKyat.setError("Enter Yae");
        }

        cuponCode = CuponCode.getText().toString();

        qualtity = totalQualtity.getText().toString();
        if (qualtity.isEmpty()){
            totalQualtity.setError("Enter Quantity");
        }

        pointEight = totalPointEight.getText().toString();
        if (pointEight.isEmpty()){
            totalPointEight.setError("Enter PointEight");
        }

        kyat = totalKyat.getText().toString();
        if (kyat.isEmpty()){
            totalKyat.setError("Enter Kyat");
        }

        pal = totalPal.getText().toString();
        if (pal.isEmpty()){
            totalPal.setError("Enter pal");
        }
        yae = totalYae.getText().toString();
        if (yae.isEmpty()){
            totalYae.setError("Enter yae");
        }

        totalAyotkyat = TotalAyotKyat.getText().toString();
        if (totalAyotkyat.isEmpty()){
            TotalAyotKyat.setError("Enter Ayotkyat");
        }

        totalAyotPel = TotalAyotPel.getText().toString();
        if (totalAyotPel.isEmpty()){
            TotalAyotPel.setError("Enter AyotPel");
        }


        totalAyotYae = TotalAyotYae.getText().toString();
        if (totalAyotYae.isEmpty()){
            TotalAyotYae.setError("Enter AyotYae");
        }

        previous_remain_kyat = previousRemainKyat.getText().toString();
        if (previous_remain_kyat.isEmpty()){
            previousRemainKyat.setError("Scan kyat");
        }

        previous_remain_pal = previousRemainPal.getText().toString();
        if (previous_remain_pal.isEmpty()){
            previousRemainPal.setError("Scan Yae");
        }

        previous_remain_yae = previousRemainYae.getText().toString();
        if (previous_remain_yae.isEmpty()){
            previousRemainYae.setError("Scan Yae");
        }

        buy_debit_kyat = buyDebitKyat.getText().toString();
        if (buy_debit_kyat.isEmpty()){
            buyDebitKyat.setError("Enter Yae");
        }


        buy_debit_pal = buyDebitPal.getText().toString();
        if (buy_debit_pal.isEmpty()){
            buyDebitPal.setError("Enter Yae");
        }


        buy_debit_yae = buyDebitYae.getText().toString();
        if (buy_debit_yae.isEmpty()){
            buyDebitYae.setError("Enter Yae");
        }


        return_gold_kyat = edReturnGoldKyat.getText().toString();
        if (return_gold_kyat.isEmpty()){
            edReturnGoldKyat.setError("Enter Kyat");
        }

        return_gold_pal = edReturnGoldPal.getText().toString();
        if (return_gold_pal.isEmpty()){
            edReturnGoldPal.setError("Enter Pal");
        }

        return_gold_yae = edReturnGoldYae.getText().toString();
        if (return_gold_yae.isEmpty()){
            edReturnGoldYae.setError("Enter Yae");
        }

        net_pay_kayt = ednowPaymentKyat.getText().toString();
        if (net_pay_kayt.isEmpty()){
            ednowPaymentKyat.setError("Enter Kyat");
        }

        net_pay_pal = ednowPaymentPal.getText().toString();
        if (net_pay_pal.isEmpty()){
            ednowPaymentPal.setError("Enter Pal");
        }

        net_pay_yae = ednowPaymentYae.getText().toString();
        if (net_pay_yae.isEmpty()){
            ednowPaymentYae.setError("Enter Yae");
        }

        payment_kyat = paymentKyat.getText().toString();
        if (payment_kyat.isEmpty()){
            paymentKyat.setError("Enter Kyat");
        }

        payment_pal = paymentPal.getText().toString();
        if (payment_pal.isEmpty()){
            paymentPal.setError("Enter Pal");
        }

        payment_yae = paymentYae.getText().toString();
        if (payment_yae.isEmpty()){
            paymentYae.setError("Enter Yae");
        }



        now_remain_kyat = nowRemainKyat.getText().toString();
        if (now_remain_kyat.isEmpty()){
            nowRemainKyat.setError("Enter Kyat");
        }

        now_remain_pal = nowRemainPal.getText().toString();
        if (now_remain_pal.isEmpty()){
            nowRemainPal.setError("Enter Pal");
        }

        now_remain_yae = nowRemainYae.getText().toString();
        if (now_remain_yae.isEmpty()){
            nowRemainYae.setError("Enter Yae");
        }


        return_gram = edReturnGram.getText().toString();
        if (return_gram.isEmpty()){
            edReturnGram.setError("Enter Gram");
        }

        now_remain_gram = edRemainGram.getText().toString();

        if (now_remain_gram.isEmpty()){
            edRemainGram.setError("Enter Remain Gram");
        }

        sub_return_kyat = edRemainKyat.getText().toString();
        if (sub_return_kyat.isEmpty()){
            edRemainKyat.setError("Enter kyat");
        }

        sub_return_pal = edRemainPal.getText().toString();
        if (sub_return_pal.isEmpty()){
            edRemainPal.setError("Enter Pal");
        }

        sub_return_yae = edRemainYae.getText().toString();
        if (sub_return_yae.isEmpty()){
            edRemainYae.setError("Enter Yae");
        }

        return_quantity = edReturnNumber.getText().toString();
        if (return_quantity.isEmpty()){
            edReturnNumber.setError("Enter quantity");
        }

        return_point_eight = edReturnPointEight.getText().toString();
        if (return_point_eight.isEmpty()){
            edReturnPointEight.setError("Enter point Eight");
        }

        now_remain_quantity = edRemainNumber.getText().toString();
        if (now_remain_quantity.isEmpty()){
            edRemainNumber.setError("Enter Remain Quantity");
        }

        now_remain_pointeight = edRemainPointEight.getText().toString();
        if (now_remain_pointeight.isEmpty()){
            edRemainPointEight.setError("Enter Remain Point Eight");
        }

        return_ayot_kyat = edReturnAyoutKyat.getText().toString();
        if (return_ayot_kyat.isEmpty()){
            edReturnAyoutKyat.setError("Enter Ayot Kyat");
        }
        return_ayot_pal = edReturnAyotPal.getText().toString();
        if (return_ayot_pal.isEmpty()){
            edReturnAyotPal.setError("Enter Ayot Yae");
        }

        return_ayot_yae = edReturnAyotYae.getText().toString();
        if (return_ayot_yae.isEmpty()){
            edReturnAyotYae.setError("Enter Ayot Yae");
        }

        now_total_ayot_kyat =edRemainAyotKyat.getText().toString();
        if (now_total_ayot_kyat.isEmpty()){
            edRemainAyotKyat.setError("Enter Ayot Kyat");
        }


        now_total_ayot_pal = edRemainAyotPal.getText().toString();
        if (now_total_ayot_pal.isEmpty()){
            edRemainAyotPal.setError("Enter Ayot Pal");
        }

        now_total_ayot_yae = edRemainAyotYae.getText().toString();
        if (return_ayot_yae.isEmpty()){
            edReturnAyotYae.setError("Enter Ayot Yae");
        }



        if (!CustomerID.isEmpty() && !voucher_Number.isEmpty() && !gram.isEmpty() &&  !kyat.isEmpty() && !pal.isEmpty() && !yae.isEmpty() && !return_gram.isEmpty() && !now_remain_gram.isEmpty() && !now_remain_kyat.isEmpty() && !now_remain_pal.isEmpty() && !now_remain_yae.isEmpty() && !new_total_kyat.isEmpty() && !new_total_pal.isEmpty() && !new_total_yae.isEmpty() && !previous_remain_kyat.isEmpty() && !previous_remain_pal.isEmpty() && !previous_remain_yae.isEmpty() && !buy_debit_kyat.isEmpty() && !buy_debit_pal.isEmpty() && !buy_debit_yae.isEmpty() && !net_pay_kayt.isEmpty() && !net_pay_pal.isEmpty() && !net_pay_yae.isEmpty() && !payment_kyat.isEmpty() && !payment_pal.isEmpty() && !payment_yae.isEmpty() && !now_remain_kyat.isEmpty() && !now_remain_pal.isEmpty() && !now_remain_yae.isEmpty() &&
                !qualtity.isEmpty() && !pointEight.isEmpty() && !now_remain_quantity.isEmpty() && !now_remain_kyat.isEmpty() && !now_remain_pal.isEmpty() && !now_remain_yae.isEmpty() && !now_total_ayot_kyat.isEmpty() && !now_total_ayot_pal.isEmpty() && !now_total_ayot_yae.isEmpty()) {

              progressDialog.show();

            Call<SaleInoviceData> call = MainActivity.apiInterface.insertSaleInvoice(MainActivity.prefConfig.readName(), voucher_Number, sale_Date,new_total_kyat,new_total_pal,new_total_yae,qualtity, pointEight,totalAyotkyat,totalAyotPel,totalAyotYae,kyat, pal, yae, gram, cuponCode, CustomerID,previous_remain_kyat,previous_remain_pal,previous_remain_yae,buy_debit_kyat,buy_debit_pal,buy_debit_yae,
                    return_gold_kyat,return_gold_pal,return_gold_yae,net_pay_kayt,net_pay_pal,net_pay_yae,
                    payment_kyat,payment_pal,payment_yae,now_remain_kyat,now_remain_pal,now_remain_yae,note_description,return_gram,now_remain_gram,sub_return_kyat,sub_return_pal,sub_return_yae,return_quantity,return_point_eight,now_remain_quantity,now_remain_pointeight,return_ayot_kyat,return_ayot_pal,return_ayot_yae,now_total_ayot_kyat,now_total_ayot_pal,now_total_ayot_yae);

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
        }else {
            Toast.makeText(getContext(), "Please Fill All Information", Toast.LENGTH_SHORT).show();
        }
    }
}
