package asia.nainglintun.myinthidar.Fragments;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abdeveloper.library.MultiSelectDialog;
import com.abdeveloper.library.MultiSelectModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import asia.nainglintun.myinthidar.Activities.CustomRangeInputFilter;
import asia.nainglintun.myinthidar.Activities.InputFilterMinMax;
import asia.nainglintun.myinthidar.Activities.MainActivity;
import asia.nainglintun.myinthidar.Activities.OrderScanForVoucherActivity;
import asia.nainglintun.myinthidar.Activities.SalesActivity;
import asia.nainglintun.myinthidar.R;
import asia.nainglintun.myinthidar.models.OrderInoviceData;
import asia.nainglintun.myinthidar.models.Sale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderInvoiceCreate extends Fragment implements  View.OnClickListener {

//private RadioGroup radioGroup;
private LinearLayout linearLayoutCustomerNew;

public static EditText edShopName,edCustomerName,edCustomerPhone,edDOB,edCustomerAddress,edCustomerID,edCustomerTown,edCustomerNrc;
public static EditText edCustomerUserName,previousRemainKyat,previousRemainPal,previousRemainYae;
private  EditText saleDate;
public static TextView updateVoucher,updateSaleDate,updateSaleName;
final Calendar myCalendar = Calendar.getInstance();
private Button btnCreateInvoiceSave,Bnadd,BnCalculate,BntotalAmount,BntotalRemainAmount;;
private ImageButton scanForVoucher;
private Button hideRing,hideBangles,hideNecklace,hideEarring;
private ProgressDialog progressDialog;
private Toolbar toolbar;
private TextView textViews1,textViews2,textViews3,textViews4,textViews5,textViews6;


    private EditText voucherNumber,Gram,CuponCode,totalKyat,totalPal,totalYae,totalQualtity,totalPointEight,
            TotalAyotKyat,TotalAyotPel,TotalAyotYae,
            buyDebitKyat,buyDebitPal,buyDebitYae,paymentKyat,paymentPal,paymentYae,
            nowRemainKyat,nowRemainPal,nowRemainYae,newTotalKyat,newTotalPal,newTotalYae,edNote;

    float Totalkyat, Totalpal,Totalyae;
    int TOTALKYAT =0;
    int TOTALPAl = 0;
    double TOTALYAE = 0;

    public  String sale0,sale1,sale2,sale3,sale4,sale5,sale;
    public String s1,s2,s3,s4,s5,s6;
    LinearLayout selectedSaleLayout;


    List<Sale> saleList;
    List<String> filterSaleList = new ArrayList<>();

    ArrayList<MultiSelectModel> listOfCountries,listOfSale;

    private String TAG = "Cancel";

    Button show_dialog_btn;

    MultiSelectDialog multiSelectDialog;


    public OrderInvoiceCreate() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_create_invoice, container, false);

        //((SalesActivity)getActivity()).setTitle("Create Order Invoice");
        toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Order Invoice");
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


        selectedSaleLayout = view.findViewById(R.id.saleInfoRoot);
        textViews1 = view.findViewById(R.id.saleOne);
        textViews2 = view.findViewById(R.id.saleTwo);
        textViews3 = view.findViewById(R.id.saleThree);
        textViews4 = view.findViewById(R.id.saleFour);
        textViews5 = view.findViewById(R.id.saleFive);
        textViews6 = view.findViewById(R.id.saleSix);


        show_dialog_btn = view.findViewById(R.id.btnChooseSaleMember);
        show_dialog_btn.setOnClickListener(this);


        totalKyat = view.findViewById(R.id.kyat);
        totalYae = view.findViewById(R.id.yawe);
        totalPal = view.findViewById(R.id.pel);


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



        Bnadd = view.findViewById(R.id.Add);


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

        btnCreateInvoiceSave = view.findViewById(R.id.btnInVoiceSave);


        Bnadd = view.findViewById(R.id.Add);
        BntotalAmount = view.findViewById(R.id.totalAmount);
        BntotalRemainAmount = view.findViewById(R.id.totalRemainAmount);
        Bnadd.setOnClickListener(this);
        BntotalAmount.setOnClickListener(this);
        BntotalRemainAmount.setOnClickListener(this);



        String date_n = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        saleDate.setText(date_n);



        scanForVoucher = view.findViewById(R.id.scanForvoucher);

        progressDialog.show();
        Call<List<Sale>> call = MainActivity.apiInterface.sale_info();
        call.enqueue(new Callback<List<Sale>>() {

            @Override
            public void onResponse(Call<List<Sale>> call, Response<List<Sale>> response) {
                progressDialog.dismiss();
                saleList = response.body();
                insertSaleinfo(saleList);
                show_dialog_btn.setEnabled(true);

            }

            @Override
            public void onFailure(Call<List<Sale>> call, Throwable t) {

            }
        });




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





        newTotalYae.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 7.9f)});
        newTotalPal.setFilters(new InputFilter[]{new InputFilterMinMax(0,15)});

        paymentYae.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 7.9f)});
        paymentPal.setFilters(new InputFilter[]{new InputFilterMinMax(0,15)});



        TotalAyotYae.setFilters(new InputFilter[]{new CustomRangeInputFilter(0f, 7.9f)});
        TotalAyotPel.setFilters(new InputFilter[]{new InputFilterMinMax(0,15)});




        scanForVoucher.setOnClickListener(this);

        btnCreateInvoiceSave.setOnClickListener(this);




        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnChooseSaleMember:
                multiSelectDialog.show(getActivity().getSupportFragmentManager(), "multiSelectDialog");

                break;

            case R.id.scanForvoucher:

                startActivity(new Intent(getContext(), OrderScanForVoucherActivity.class));

                break;

            case R.id.btnInVoiceSave:
                saveOrderInvoice();
                break;

            case R.id.Add:
                try {
                    Double editTextGram = Double.parseDouble(Gram.getText().toString());


                    double number2 = 16.6;
                    int kyat1 = (int) (editTextGram / number2);

                    double beforePal = (((editTextGram / number2) - kyat1) * 16);

                    int PalInt = (int) (beforePal);

                    double beforeYae = (beforePal - PalInt);

                    double RealYae = (beforeYae * 8);

                    DecimalFormat form = new DecimalFormat("0.00");

                    double afterPal = (((((editTextGram % number2) / number2) * 16) / number2) * 8);

                    int Yae = (int) afterPal;
                    int Pal = (int) beforePal;

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
                       // Toast.makeText(getContext(), "Negative Operation work", Toast.LENGTH_SHORT).show();
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
                        } else if (nowDebitKyat>totKyat && nowDebitPal==totPal && nowDebitYae>=totYae){
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

                    }else if(!DebitKyat.contains("-") && !DebitPal.contains("-") && !DebitYae.contains("-")){
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
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        saleDate.setText(sdf.format(myCalendar.getTime()));
    }


    public void saveOrderInvoice(){
       String voucher_Number,sale_Date,qualtity,pointEight,kyat,pal,yae,gram,cuponCode,CustomerID,totalAyotkyat,totalAyotPel,totalAyotYae,
               previous_remain_kyat,previous_remain_pal,previous_remain_yae,buy_debit_kyat,buy_debit_pal,buy_debit_yae,payment_kyat,payment_pal,payment_yae,now_remain_kyat,now_remain_pal,now_remain_yae,
                new_total_kayt,new_total_pal,new_total_yae,note_description;

       voucher_Number = voucherNumber.getText().toString();
       if (voucher_Number.isEmpty()){
           voucherNumber.setError("Enter Voucher No");
       }
        CustomerID = edCustomerID.getText().toString();
       sale_Date = saleDate.getText().toString();
       new_total_kayt = newTotalKyat.getText().toString();
        if (new_total_kayt.isEmpty()){
            newTotalKyat.setError("Enter Kyat");
        }
       new_total_pal = newTotalPal.getText().toString();
        if (new_total_pal.isEmpty()){
            newTotalPal.setError("Enter Pal");
        }
       new_total_yae = newTotalYae.getText().toString();
        if (new_total_yae.isEmpty()){
            newTotalYae.setError("Enter Yae");
        }

       qualtity = totalQualtity.getText().toString();
        if (qualtity.isEmpty()){
            totalQualtity.setError("Enter Quantity");
        }
       pointEight = totalPointEight.getText().toString();
        if (pointEight.isEmpty()){
            totalPointEight.setError("Enter Point Eight");
        }

       kyat = totalKyat.getText().toString();
        if (pointEight.isEmpty()){
            totalPointEight.setError("Enter Kyat");
        }

       pal = totalPal.getText().toString();
        if (pointEight.isEmpty()){
            totalPal.setError("Enter Pal");
        }

       yae = totalYae.getText().toString();
        if (yae.isEmpty()){
            totalYae.setError("Enter Yae");
        }

        gram  = Gram.getText().toString();
        if (gram.isEmpty()){
            Gram.setError("Enter Gram");
        }

       cuponCode = CuponCode.getText().toString();



        totalAyotkyat = TotalAyotKyat.getText().toString();
        if (totalAyotkyat.isEmpty()){
            TotalAyotKyat.setError("Enter Ayot Kyat");
        }
        totalAyotPel = TotalAyotPel.getText().toString();
        if (totalAyotPel.isEmpty()){
            TotalAyotPel.setError("Enter Ayot Yae");
        }
        totalAyotYae = TotalAyotYae.getText().toString();
        if (totalAyotYae.isEmpty()){
            TotalAyotYae.setError("Enter Ayot Kyat");
        }

        previous_remain_kyat = previousRemainKyat.getText().toString();
        previous_remain_pal = previousRemainPal.getText().toString();
        previous_remain_yae = previousRemainYae.getText().toString();

        buy_debit_kyat = buyDebitKyat.getText().toString();
        buy_debit_pal = buyDebitPal.getText().toString();
        buy_debit_yae = buyDebitYae.getText().toString();

        payment_kyat = paymentKyat.getText().toString();
        if (payment_kyat.isEmpty()){
            paymentKyat.setError("Enter Payment Kyat");
        }

        payment_pal = paymentPal.getText().toString();
        if (payment_pal.isEmpty()){
            paymentPal.setError("Enter Payment Pal");
        }

        payment_yae = paymentYae.getText().toString();
        if (payment_yae.isEmpty()){
            paymentYae.setError("Enter Payment Yae");
        }



        now_remain_kyat = nowRemainKyat.getText().toString();
        if (now_remain_kyat.isEmpty()){
            nowRemainKyat.setError("Enter Remain Kyat");
        }

        now_remain_pal = nowRemainPal.getText().toString();
        if (now_remain_pal.isEmpty()){
            nowRemainPal.setError("Enter Remain Yae");
        }
        now_remain_yae = nowRemainYae.getText().toString();
        if (now_remain_yae.isEmpty()){
            nowRemainYae.setError("Enter Remain Yae");
        }

        note_description = edNote.getText().toString();

        s1 = filterSaleList.get(0);
        s2 = filterSaleList.get(1);
        s3 = filterSaleList.get(2);
        s4 = filterSaleList.get(3);
        s5 = filterSaleList.get(4);
        s6 = filterSaleList.get(5);

        Log.d("MYTEST",s1 + s2 + s3 + s4 + s5 + s6 );



        if (!CustomerID.isEmpty() && !voucher_Number.isEmpty() && !new_total_kayt.isEmpty() && !new_total_pal.isEmpty() && !new_total_yae.isEmpty() && !qualtity.isEmpty() && !pointEight.isEmpty() && !kyat.isEmpty() && !pal.isEmpty() && !yae.isEmpty() && !gram.isEmpty() && !now_remain_kyat.isEmpty() && !now_remain_pal.isEmpty() && !now_remain_yae.isEmpty() && !payment_kyat.isEmpty() && !payment_pal.isEmpty() && !payment_yae.isEmpty()) {

            progressDialog.show();

        Call<OrderInoviceData> call = MainActivity.apiInterface.insertOrderInvoice(MainActivity.prefConfig.readName(),s1,s2,s3,s4,s5,s6,voucher_Number,sale_Date,new_total_kayt,new_total_pal,new_total_yae,qualtity,pointEight,totalAyotkyat,totalAyotPel,totalAyotYae,kyat,pal,yae,gram,cuponCode,CustomerID,previous_remain_kyat,previous_remain_pal,previous_remain_yae,buy_debit_kyat,buy_debit_pal,buy_debit_yae,
                payment_kyat,payment_pal,payment_yae,now_remain_kyat,now_remain_pal,now_remain_yae,note_description);

        call.enqueue(new Callback<OrderInoviceData>() {
            @Override
            public void onResponse(Call<OrderInoviceData> call, Response<OrderInoviceData> response) {
                progressDialog.dismiss();
                if (response.body().getResponse().equals("ok")){
                    Toast.makeText(getContext(),"order Invoice Successfully" ,Toast.LENGTH_LONG).show();
                    ///SalesActivity.fragmentManager.beginTransaction().add(R.id.frame_layout_sales,new FragmentCard()).commit();
                } else if (response.body().getResponse().equals("error")){
                    Toast.makeText(getContext(),"order Invoice fail",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<OrderInoviceData> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }else {
         Toast.makeText(getContext(), "Please Fill All Information", Toast.LENGTH_SHORT).show();
     }
        }


    public void insertSaleinfo(List<Sale> list) {
        listOfSale = new ArrayList<>();
        for (int i = 0; i < saleList.size(); i++) {
            listOfSale.add(new MultiSelectModel(saleList.get(i).getId(), saleList.get(i).getName()));

        }
        multiSelectDialog = new MultiSelectDialog()
                .title(getResources().getString(R.string.multi_select_dialog_title)) //setting title for dialog
                .titleSize(25)
                .positiveText("Done")
                .negativeText("Cancel")
                .setMinSelectionLimit(1)
                .setMaxSelectionLimit(6)
                // .preSelectIDsList(alreadySelectedCountries) //List of ids that you need to be selected
                .multiSelectList(listOfSale) // the multi select model list with ids and name
                .onSubmit(new MultiSelectDialog.SubmitCallbackListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSelected(ArrayList<Integer> selectedIds, ArrayList<String> selectedNames, String dataString) {
                        //will return list of selected IDS

                        for (int i = 0; i < selectedIds.size(); i++) {
                            Toast.makeText(getContext(), "Selected Ids : " + selectedIds.get(i) + "\n" +
                                    "Selected Names : " + selectedNames.get(i) + "\n" +
                                    "DataString : " + dataString, Toast.LENGTH_SHORT).show();
                            filterSaleList.clear();
                            if (selectedNames.size() == 6) {
                                sale0 = selectedNames.get(0);
                                sale1 = selectedNames.get(1);
                                sale2 = selectedNames.get(2);
                                sale3 = selectedNames.get(3);
                                sale4 = selectedNames.get(4);
                                sale5 = selectedNames.get(5);
                                filterSaleList.clear();
                                filterSaleList.add(sale0);
                                filterSaleList.add(sale1);
                                filterSaleList.add(sale2);
                                filterSaleList.add(sale3);
                                filterSaleList.add(sale4);
                                filterSaleList.add(sale5);

                                textViews1.setText("");
                                textViews2.setText("");
                                textViews3.setText("");
                                textViews4.setText("");
                                textViews5.setText("");
                                textViews6.setText("");

                                textViews1.setText(sale0);
                                textViews2.setText(sale1);
                                textViews3.setText(sale2);
                                textViews4.setText(sale3);
                                textViews5.setText(sale4);
                                textViews6.setText(sale5);
                                Toast.makeText(getContext(), sale0 + sale1 + sale2 + sale3 + sale4 + sale5, Toast.LENGTH_SHORT).show();
                            } else if (selectedNames.size() == 5) {
                                sale0 = selectedNames.get(0);
                                sale1 = selectedNames.get(1);
                                sale2 = selectedNames.get(2);
                                sale3 = selectedNames.get(3);
                                sale4 = selectedNames.get(4);
                                filterSaleList.clear();
                                filterSaleList.add(sale0);
                                filterSaleList.add(sale1);
                                filterSaleList.add(sale2);
                                filterSaleList.add(sale3);
                                filterSaleList.add(sale4);
                                filterSaleList.add(null);

                                textViews1.setText("");
                                textViews2.setText("");
                                textViews3.setText("");
                                textViews4.setText("");
                                textViews5.setText("");
                                textViews6.setText("");

                                textViews1.setText(sale0);
                                textViews2.setText(sale1);
                                textViews3.setText(sale2);
                                textViews4.setText(sale3);
                                textViews5.setText(sale4);
                                Toast.makeText(getContext(), sale0 + sale1 + sale2 + sale3 + sale4, Toast.LENGTH_SHORT).show();
                            } else if (selectedNames.size() == 4) {
                                sale0 = selectedNames.get(0);
                                sale1 = selectedNames.get(1);
                                sale2 = selectedNames.get(2);
                                sale3 = selectedNames.get(3);
                                filterSaleList.clear();
                                filterSaleList.add(sale0);
                                filterSaleList.add(sale1);
                                filterSaleList.add(sale2);
                                filterSaleList.add(sale3);
                                filterSaleList.add(null);
                                filterSaleList.add(null);

                                textViews1.setText("");
                                textViews2.setText("");
                                textViews3.setText("");
                                textViews4.setText("");
                                textViews5.setText("");
                                textViews6.setText("");

                                textViews1.setText(sale0);
                                textViews2.setText(sale1);
                                textViews3.setText(sale2);
                                textViews4.setText(sale3);
                                Toast.makeText(getContext(), sale0 + sale1 + sale2 + sale3, Toast.LENGTH_SHORT).show();
                            } else if (selectedNames.size() == 3) {
                                sale0 = selectedNames.get(0);
                                sale1 = selectedNames.get(1);
                                sale2 = selectedNames.get(2);
                                filterSaleList.clear();
                                filterSaleList.add(sale0);
                                filterSaleList.add(sale1);
                                filterSaleList.add(sale2);
                                filterSaleList.add(null);
                                filterSaleList.add(null);
                                filterSaleList.add(null);

                                textViews1.setText("");
                                textViews2.setText("");
                                textViews3.setText("");
                                textViews4.setText("");
                                textViews5.setText("");
                                textViews6.setText("");

                                textViews1.setText(sale0);
                                textViews2.setText(sale1);
                                textViews3.setText(sale2);
                                Toast.makeText(getContext(), sale0 + sale1 + sale2, Toast.LENGTH_SHORT).show();
                            } else if (selectedNames.size() == 2) {
                                sale0 = selectedNames.get(0);
                                sale1 = selectedNames.get(1);
                                filterSaleList.clear();
                                filterSaleList.add(sale0);
                                filterSaleList.add(sale1);
                                filterSaleList.add(null);
                                filterSaleList.add(null);
                                filterSaleList.add(null);
                                filterSaleList.add(null);

                                textViews1.setText("");
                                textViews2.setText("");
                                textViews3.setText("");
                                textViews4.setText("");
                                textViews5.setText("");
                                textViews6.setText("");

                                textViews1.setText(sale0);
                                textViews2.setText(sale1);
                                Toast.makeText(getContext(), sale0 + sale1, Toast.LENGTH_SHORT).show();
                            } else if (selectedNames.size() == 1) {
                                sale0 = selectedNames.get(0);
                                filterSaleList.clear();
                                filterSaleList.add(sale0);
                                filterSaleList.add(null);
                                filterSaleList.add(null);
                                filterSaleList.add(null);
                                filterSaleList.add(null);
                                filterSaleList.add(null);

                                textViews1.setText("");
                                textViews2.setText("");
                                textViews3.setText("");
                                textViews4.setText("");
                                textViews5.setText("");
                                textViews6.setText("");

                                textViews1.setText(sale0);
                                Toast.makeText(getContext(), sale0, Toast.LENGTH_SHORT).show();
                            }


                        }


                    }

                    @Override
                    public void onCancel() {


                    }
                });
    }
}
