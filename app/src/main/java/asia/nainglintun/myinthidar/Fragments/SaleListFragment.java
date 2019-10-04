package asia.nainglintun.myinthidar.Fragments;


import android.app.DatePickerDialog;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import asia.nainglintun.myinthidar.Activities.MainActivity;
import asia.nainglintun.myinthidar.Activities.RecyclerTouchListener;
import asia.nainglintun.myinthidar.Activities.SalesActivity;
import asia.nainglintun.myinthidar.Adapters.bindvouchersaleRecyclerAdapter;
import asia.nainglintun.myinthidar.Adapters.editbindvouchersaleRecyclerAdapter;
import asia.nainglintun.myinthidar.Adapters.saleRecyclerAdapter;
import asia.nainglintun.myinthidar.R;
import asia.nainglintun.myinthidar.models.Sale;
import asia.nainglintun.myinthidar.models.Salehistory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static asia.nainglintun.myinthidar.Activities.MainActivity.prefConfig;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleListFragment extends Fragment implements SearchView.OnQueryTextListener {

   private Toolbar toolbar;
    private RecyclerView recyclerView,recyclerViewDialog,recyclerViewEdit;
    private RecyclerView.LayoutManager layoutManager;
    private List<Salehistory> salehistories;
    private saleRecyclerAdapter adapter;
    private bindvouchersaleRecyclerAdapter adapterDialog;
    private editbindvouchersaleRecyclerAdapter adapterEditDialog;
    private ProgressDialog progressDialog;
    public static TextView textDialog,bindName;
    private Dialog dialog;
    private ImageView closeImg;
    private ImageButton btnEdit;
    private Button dialogEdit,BnUpdateInVoiceSave,btnAdd,btnSub,btnCalculateRemainGramTOKyatPalYae,btnCalculateTotalAmount,btnreturnGoldPlusAyot,btntotalRemainAmount,btnitemCalculate,btnayotCalculate;
    final Calendar myCalendar = Calendar.getInstance();
    private double remainAyotYae=0;
    private int remainAyotKyat=0,remainAyotPal=0;
    public static String Status;

    private EditText edVoucher,edSaleDate,edCustomerName,edShopName,edAddress,edDob,edNrc,edPhoneNumber,edTownShip,edCustomerId,edCustomerUserName,edNote,
           edTotalKyat,edTotalPal,edTotalYae,edCuponCode,edGram,edviewVoucher,edviewDate,edviewGram,
            edGramToKyat,edGramToPal,edGramToYae,edReturnGram,edRemainGram,edGramRemainKyat,edGramRemainPal,edGramRemainYae;
    private ArrayList<String> dataList;
    private ArrayList<String> nameList;

  private double RemainGram=0;


    private EditText ednowPurchaseKyat,ednowPurchasePal,ednowPurchaseYae,edPreviousRemainKyat,edPreviousRemainPal,edPreviousRemainYae,edBuyDebitKyat,edBuyDebitPal,edBuyDebitYae,edReturnGoldKyat,edReturnGoldPal,edReturnGoldYae,ednowPaymentKyat,
            ednowPaymentYae,ednowPaymentPal, ednetPaymentKyat, ednetPaymentPal,ednetPaymentYae,ednowRemainKyat,ednowRemainPal,ednowRemainYae,edTotalNumber,edTotalPointEight,
            edReturnQualtity,edReturnPointEight,edRemainQualtity,edRemainPointEight,edAyotkyat,edAyotYae,edAyotPal,edReturnAyotKyat,edReturnAyotPal,edReturnAyotYae,
    edNowRemainAyotKyat,edNowRemainAyotPal,edNowRemainAyotYae;
  private TextView textViewDebitVoucherNo,textViewDebitSaleDate,textViewDebitSaleName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_sale_list, container, false);
//       toolbar = view.findViewById(R.id.toolBar);
//       toolbar.setTitle("Sale History Lists");

       ((SalesActivity)getActivity()).setTitle("Sale History Lists");

        SalesActivity.toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);

        SalesActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // SalesActivity.fragmentManager.beginTransaction().remove(new SaleListFragment()).commit();
                SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales, new FragmentCard()).addToBackStack(null).commit();
            }
        });
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
                String sDate,customerName,shopName,
                totalQty,totalPointEight,totalKyat,totalPal,totalYae,ID,customer_id,customerPhoneNumber,customerAddress,customerDob,customerNrc,remark,customerUserName,
                gram,cuponcode,totalAyotKyat,totalAyotPal,totalAyotYae,previous_kyat,previous_yae,previous_pal,buydebitkyat,buydebitpal,buydebityae,
                paymentkyat,paymentpal,paymentyae,remainkyat,remainpal,remainyae,newTotalKyat,newTotalPal,newTotalYae,
                returnGram,returnRemainGram,sub_return_kyat,sub_return_pal,sub_return_yae,return_quantity,return_pointeight,
                now_remain_quantity,now_remain_pointeight,return_ayot_kyat,return_ayot_pal,return_ayot_yae,now_total_ayot_kyat,now_total_ayot_pal,now_total_ayot_yae,
               debitVoucherNo,debitSaleDate,debitSaleName,returnGoldkyat,returnGoldPal,returnGoldYae,netPaymentKyat,netPaymentYae,netPaymentPal,  previous_debit_kyat,
                previous_debit_pal,
                previous_debit_yae;

                 ID = String.valueOf(salehistories.get(position).getId());
                String voucher = salehistories.get(position).getVoucherNumber();
                sDate = salehistories.get(position).getSaleDate();
                customerName = salehistories.get(position).getCustomerName();
                customer_id = salehistories.get(position).getCustomerId();
                shopName = salehistories.get(position).getCustomerShop();
                String town=salehistories.get(position).getCustomerTwon();
                gram = salehistories.get(position).getGram();
                cuponcode = salehistories.get(position).getCuponCode();
                customerNrc = salehistories.get(position).getCustomerNrc();
                customerUserName = salehistories.get(position).getCustomerUserName();
                remark = salehistories.get(position).getRemark();



                returnGram = salehistories.get(position).getReturnGram();
                returnRemainGram = salehistories.get(position).getRemainGram();
                sub_return_kyat=salehistories.get(position).getSubReturnKyat();
                sub_return_pal = salehistories.get(position).getReturnAyotPal();
                sub_return_yae = salehistories.get(position).getSubReturnYae();

                return_quantity = salehistories.get(position).getReturnQuantity();
                return_pointeight = salehistories.get(position).getReturnPointEight();

                now_remain_quantity = salehistories.get(position).getRemainQuantity();
                now_remain_pointeight = salehistories.get(position).getRemainPointEight();

                return_ayot_kyat = salehistories.get(position).getReturnAyotKyat();
                return_ayot_pal = salehistories.get(position).getReturnAyotPal();
                return_ayot_yae = salehistories.get(position).getReturnAyotYae();

                now_total_ayot_kyat = salehistories.get(position).getRemainAyotKyat();
                now_total_ayot_pal = salehistories.get(position).getRemainAyotPal();
                now_total_ayot_yae = salehistories.get(position).getReturnAyotYae();


                totalQty = salehistories.get(position).getQty();
               totalPointEight = salehistories.get(position).getPointEight();
               totalKyat = salehistories.get(position).getKyat();
               totalPal = salehistories.get(position).getPal();
               totalYae = salehistories.get(position).getYae();

               totalAyotKyat = salehistories.get(position).getTotal_ayot_kyat();
               totalAyotPal = salehistories.get(position).getTotal_ayot_pal();
               totalAyotYae = salehistories.get(position).getTotal_ayot_yae();

               previous_kyat = salehistories.get(position).getDebitKyat();
               previous_pal = salehistories.get(position).getDebitPal();
               previous_yae =salehistories.get(position).getDebitYae();

                previous_debit_kyat = salehistories.get(position).getPreviousRemainKyat();
                previous_debit_pal = salehistories.get(position).getPreviousRemainPal();
                previous_debit_yae =salehistories.get(position).getPreviousRemainYae();

                buydebitkyat = salehistories.get(position).getBuyDebitKyat();
               buydebitpal = salehistories.get(position).getBuyDebitPal();
               buydebityae =salehistories.get(position).getBuyDebitYae();

               paymentkyat = salehistories.get(position).getPaymentKyat();
               paymentpal = salehistories.get(position).getPaymentPal();
               paymentyae = salehistories.get(position).getPaymentYae();

               remainkyat = salehistories.get(position).getNowRemainKyat();
               remainpal = salehistories.get(position).getNowRemainPal();
               remainyae = salehistories.get(position).getNowRemainYae();

               newTotalKyat = salehistories.get(position).getTotal_kyat();
               newTotalPal = salehistories.get(position).getTotal_pal();
               newTotalYae = salehistories.get(position).getTotal_yae();

               returnGoldkyat = salehistories.get(position).getReturn_gold_kyat();
                returnGoldPal = salehistories.get(position).getReturn_gold_pal();
                returnGoldYae = salehistories.get(position).getReturn_gold_yae();



               customerPhoneNumber = salehistories.get(position).getCustomerPhoneNumber();
               customerAddress = salehistories.get(position).getCustomerAddress();
               customerDob = salehistories.get(position).getCustomerDob();

               debitVoucherNo = salehistories.get(position).getDebitVoucher();
               debitSaleDate = salehistories.get(position).getDebitSaleDate();
               debitSaleName = salehistories.get(position).getDebitSaleName();


               netPaymentKyat = salehistories.get(position).getNet_pay_kayt();
               netPaymentPal = salehistories.get(position).getNet_pay_pal();
               netPaymentYae = salehistories.get(position).getNet_pay_yae();







                Showpopup(voucher,sDate,cuponcode,totalAyotKyat,totalAyotPal,totalAyotYae,customerName,shopName,customerAddress,customerPhoneNumber,customerDob,town,
                        totalQty,totalPointEight,totalKyat,totalPal,totalYae,
                        gram,ID,previous_kyat,previous_pal,previous_yae,buydebitkyat,buydebitpal,buydebityae,paymentkyat,paymentpal,paymentyae,remainkyat,remainpal,remainyae,newTotalKyat,newTotalPal,newTotalYae,
                        returnGram,returnRemainGram,sub_return_kyat,sub_return_pal,sub_return_yae,debitVoucherNo,debitSaleDate,debitSaleName,returnGoldkyat,returnGoldPal,returnGoldYae,
                        netPaymentKyat,netPaymentPal,netPaymentYae,return_quantity,return_pointeight,now_remain_quantity,now_remain_pointeight,return_ayot_kyat,return_ayot_pal,return_ayot_yae,
                        now_total_ayot_kyat,now_total_ayot_pal,now_total_ayot_yae,customerNrc,customerUserName,remark,customer_id);

            }

            @Override
            public void onLongClick(View view, int position) {


            }
        }));

       return view;
    }

    private void Showpopup(String voucher,String sDate,String cuponCode,String total_ayot_kyat,String total_ayot_pal,String total_ayo_yae,String customerName,String shopName, String customerAddress,String customerPhoneNumber,String customerDob,String town,
                         String totalQty,String totalPointEight,String totalKyat,String totalPal,String totalYae,
                          String gram,String ID,String previous_kyat,String previous_pal,String previous_yae,String buydebitkyat,String buydebitpal,String buydebityae,String paymentkyat,String paymentpal,String paymentyae,String remainkyat,String remainpal,String remainyae,String newTotalKyat,String newTotalPal,String newTotalYae,
                           String returnGram,String returnRemainGram,String SubReturnKyat,
                           String SubReturnPal,String SubReturnYae,String debitVoucherNo,String debitSaleDate,String debitSaleName,String returnGoldkyat,String returnGoldPal,String returnGoldYae,
                           String netPaymentKyat,String netPaymentPal,String netPaymentYae,String returnQuantity,String returnPointEight,String nowRemainQuantity,String nowRemainPointEight,
                           String return_ayot_kyat,String return_ayot_pal,String return_ayot_yae,
                           String now_total_ayot_kyat,String now_total_ayot_pal,String now_total_ayot_yae,String customerNrc,String customerUserName,String remark,String customer_id) {

        dialog.setContentView(R.layout.custom_popup_dialog);
        //recyclerView = dialog.findViewById(R.id.recyclerViewDialog);
//        layoutManag,er = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);

        closeImg = dialog.findViewById(R.id.closeImage);
       // btnEdit = dialog.findViewById(R.id.btnVoucherEdit);
        edviewVoucher = dialog.findViewById(R.id.voucherNumber);
        edviewVoucher.setText(voucher);

        edSaleDate=dialog.findViewById(R.id.saleDate);
        edSaleDate.setText(sDate);

        BnUpdateInVoiceSave = dialog.findViewById(R.id.btnUpdateInVoiceSave);



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }
        };


        edSaleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        edGram = dialog.findViewById(R.id.gram);
        edGram.setText(gram);

        btnAdd = dialog.findViewById(R.id.Add);
        edTotalKyat=dialog.findViewById(R.id.kyat);
        edTotalPal=dialog.findViewById(R.id.pal);
        edTotalYae=dialog.findViewById(R.id.yae);

        edTotalKyat.setText(totalKyat);
        edTotalPal.setText(totalPal);
        edTotalYae.setText(totalYae);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Double editTextGram = Double.parseDouble(edGram.getText().toString());


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

                    edTotalKyat.setText(String.valueOf(kyat1));
                    edTotalPal.setText(String.valueOf(PalInt));
                    edTotalYae.setText(String.valueOf(form.format(RealYae)));


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



        edReturnGram = dialog.findViewById(R.id.returnGram);
        edRemainGram = dialog.findViewById(R.id.remainGram);
        edGramRemainKyat = dialog.findViewById(R.id.nowRmainkyat);
        edGramRemainPal = dialog.findViewById(R.id.nowRemainpal);
        edGramRemainYae = dialog.findViewById(R.id.nowRemainyae);


        edReturnGram.setText(returnGram);
        btnSub=dialog.findViewById(R.id.Sub);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double buyGram = Double.parseDouble(edGram.getText().toString());
                double returnGram = Double.parseDouble(edReturnGram.getText().toString());
                 RemainGram= buyGram - returnGram;
                edRemainGram.setText(String.valueOf(RemainGram));
            }
        });

        edRemainGram.setText(returnRemainGram);
        edGramRemainKyat.setText(SubReturnKyat);
        edGramRemainPal.setText(SubReturnPal);
        edGramRemainYae.setText(SubReturnYae);
        btnCalculateRemainGramTOKyatPalYae=dialog.findViewById(R.id.Calculate);
        btnCalculateRemainGramTOKyatPalYae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double number2 = 16.6;
                int kyat1 =  (int) (RemainGram/number2);

                double beforePal = (((RemainGram/number2)-kyat1)*16);

                int PalInt = (int)(beforePal);

                double beforeYae = (beforePal-PalInt);

                double RealYae = (beforeYae*8);

                DecimalFormat form = new DecimalFormat("0.00");


                double afterPal = (((((RemainGram%number2)/number2)*16)/number2)*8);

                int Yae = (int)afterPal;
                int Pal = (int)beforePal;

                edGramRemainKyat.setText(String.valueOf(kyat1));
                edGramRemainPal.setText(String.valueOf(PalInt));
                edGramRemainYae.setText(String.valueOf(form.format(RealYae)));
            }
        });

        ednowPurchaseKyat = dialog.findViewById(R.id.totalkyat);
        ednowPurchasePal = dialog.findViewById(R.id.totalpal);
        ednowPurchaseYae = dialog.findViewById(R.id.totalyae);

        ednowPurchaseKyat.setText(newTotalKyat);
        ednowPurchasePal.setText(newTotalPal);
        ednowPurchaseYae.setText(newTotalYae);

        textViewDebitVoucherNo=dialog.findViewById(R.id.updatevoucher);
        textViewDebitSaleDate=dialog.findViewById(R.id.updateSaleDate);
        textViewDebitSaleName=dialog.findViewById(R.id.updatSaleName);

        textViewDebitVoucherNo.setText(debitVoucherNo);
        textViewDebitSaleDate.setText(debitSaleDate);
        textViewDebitSaleName.setText(debitSaleName);

        edPreviousRemainKyat=dialog.findViewById(R.id.debitKyat);
        edPreviousRemainPal=dialog.findViewById(R.id.debitPal);
        edPreviousRemainYae=dialog.findViewById(R.id.debitYae);

        edPreviousRemainKyat.setText(previous_kyat);
        edPreviousRemainPal.setText(previous_pal);
        edPreviousRemainYae.setText(previous_yae);

        btnCalculateTotalAmount = dialog.findViewById(R.id.totalAmount);
        btnCalculateTotalAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int totKyat = Integer.parseInt(ednowPurchaseKyat.getText().toString());
                int totPal = Integer.parseInt(ednowPurchasePal.getText().toString());
                double totYae = Double.parseDouble(ednowPurchaseYae.getText().toString());
                double plusPal;
                int plusKyat;
                double plusYaeOne = 0;
                int realResultPal = 0;
                int plusForKyat = 0;
                int resultpal = 0;

                int debitKyat = Integer.parseInt(edPreviousRemainKyat.getText().toString());
                int debitPal = Integer.parseInt(edPreviousRemainPal.getText().toString());
                double debitYae = Double.parseDouble(edPreviousRemainYae.getText().toString());

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
                        edBuyDebitKyat.setText(String.valueOf(TOTALKYAT));
                        edBuyDebitPal.setText(String.valueOf(resultpal));
                    } else if (TOTALYAE < 8) {
                        edBuyDebitKyat.setText(String.valueOf(TOTALKYAT));
                        edBuyDebitPal.setText(String.valueOf(TOTALPAl));
                    }
                } else if (TOTALPAl >= 16) {
                    if (TOTALYAE >= 8) {
                        plusYaeOne = TOTALYAE / 8;
                        int plusYaeOneInt = (int) plusYaeOne;
                        TOTALPAl = TOTALPAl + plusYaeOneInt;
                        plusKyat = TOTALPAl / 16;
                        resultpal = TOTALPAl % 16;

                        TOTALKYAT = TOTALKYAT + plusKyat;
                        edBuyDebitKyat.setText(String.valueOf(TOTALKYAT));
                        edBuyDebitPal.setText(String.valueOf(resultpal));
                    } else if (TOTALYAE < 8) {
                        plusKyat = TOTALPAl / 16;
                        resultpal = TOTALPAl % 16;
                        TOTALKYAT = TOTALKYAT + plusKyat;
                        edBuyDebitKyat.setText(String.valueOf(TOTALKYAT));
                        edBuyDebitPal.setText(String.valueOf(resultpal));
                    }
                }
                if (TOTALYAE < 8) {
                    DecimalFormat form1 = new DecimalFormat("0.00");
                    edBuyDebitYae.setText(String.valueOf(form1.format(TOTALYAE)));
                } else if (TOTALYAE >= 8) {
                    double resultYae;
                    plusPal = TOTALYAE / 8;
                    resultYae = TOTALYAE % 8;
                    double totalPals = (double) resultpal;

                    double resultPal1 = totalPals + plusPal;
                    int resultPalInt = (int) resultPal1;


                    DecimalFormat form1 = new DecimalFormat("0.00");
                    edBuyDebitYae.setText(String.valueOf(form1.format(resultYae)));

                }




            }
        });
        edBuyDebitKyat = dialog.findViewById(R.id.buyDebitKyat);
        edBuyDebitPal=dialog.findViewById(R.id.buyDebitPal);
        edBuyDebitYae = dialog.findViewById(R.id.buyDebitYae);

        edBuyDebitKyat.setText(buydebitkyat);
        edBuyDebitPal.setText(buydebitpal);
        edBuyDebitYae.setText(buydebityae);

        edReturnGoldKyat=dialog.findViewById(R.id.retunGoldKyat);
        edReturnGoldPal=dialog.findViewById(R.id.retunGoldPal);
        edReturnGoldYae = dialog.findViewById(R.id.retunGoldYae);

     edReturnGoldKyat.setText(returnGoldkyat);
     edReturnGoldPal.setText(returnGoldPal);
     edReturnGoldYae.setText(returnGoldYae);

     ednowPaymentKyat = dialog.findViewById(R.id.paymentKyat);
     ednowPaymentYae = dialog.findViewById(R.id.paymentYae);
     ednowPaymentPal = dialog.findViewById(R.id.paymentPal);

     ednetPaymentKyat = dialog.findViewById(R.id.netPayGoldKyat);
     ednetPaymentPal = dialog.findViewById(R.id.netPayGoldPal);
     ednetPaymentYae = dialog.findViewById(R.id.netPayGoldYae);

     ednetPaymentKyat.setText(netPaymentKyat);
     ednetPaymentPal.setText(netPaymentPal);
     ednetPaymentYae.setText(netPaymentYae);

     ednowPaymentKyat.setText(paymentkyat);
     ednowPaymentPal.setText(paymentpal);
     ednowPaymentYae.setText(paymentyae);

        btnreturnGoldPlusAyot=dialog.findViewById(R.id.returnGoldPlusAyot);
        btnreturnGoldPlusAyot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int getTotalAmountKyat = Integer.parseInt(edBuyDebitKyat.getText().toString());
                    int getTotalAmountPal = Integer.parseInt(edBuyDebitPal.getText().toString());
                    double getTotalAmountYae = Double.parseDouble(edBuyDebitYae.getText().toString());

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
                        ednetPaymentKyat.setText(String.valueOf(-RemainKyat));
                        ednetPaymentPal.setText(String.valueOf(-RemainPal));
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednetPaymentYae.setText(String.valueOf(form1.format(-RemainYae)));
                        Toast.makeText(getContext(), ednetPaymentYae.getText().toString(), Toast.LENGTH_SHORT).show();

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
                        ednetPaymentKyat.setText(String.valueOf(RemainKyat));
                        if (RemainPal==0) {
                            ednetPaymentPal.setText(String.valueOf(RemainPal));
                        }else if (RemainPal!=0) {
                            ednetPaymentPal.setText(String.valueOf(-RemainPal));
                        }
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednetPaymentYae.setText(String.valueOf(form1.format(-RemainYae)));
                        //Toast.makeText(getContext(), ednetPaymentYae.getText().toString(), Toast.LENGTH_SHORT).show();

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
                            ednetPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        }else if(RemainYae!=0) {
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednetPaymentYae.setText(String.valueOf(form1.format(-RemainYae)));
                        }
                        if(RemainPal==0){
                            ednetPaymentPal.setText(String.valueOf(RemainPal));
                        }else if(RemainPal!=0) {
                            ednetPaymentPal.setText(String.valueOf(-RemainPal));
                        }
                        if(RemainKyat==0){
                            ednetPaymentKyat.setText(String.valueOf(RemainKyat));
                        } else if(RemainKyat!=0){
                            ednetPaymentKyat.setText(String.valueOf(-RemainKyat));
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
                            ednetPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        }else if(RemainYae!=0){
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednetPaymentYae.setText(String.valueOf(form1.format(-RemainYae)));
                        }

                        if (RemainPal==0){
                            ednetPaymentPal.setText(String.valueOf(RemainPal));
                        }else if(RemainPal!=0){
                            ednetPaymentPal.setText(String.valueOf(-RemainPal));
                        }

                        if (RemainKyat==0){
                            ednetPaymentKyat.setText(String.valueOf(RemainKyat));
                        }else if(RemainKyat!=0){
                            ednetPaymentKyat.setText(String.valueOf(-RemainKyat));
                        }

                    }
                    else if(PayKyat>getTotalAmountKyat && PayPal==getTotalAmountPal && PayYae==getTotalAmountYae)
                    {

                        RemainYae=PayYae-getTotalAmountYae;
                        RemainPal=PayPal-getTotalAmountPal;
                        RemainKyat=PayKyat-getTotalAmountKyat;
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednetPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        ednetPaymentPal.setText(String.valueOf(RemainPal));
                        if (RemainKyat==0){
                            ednetPaymentKyat.setText(String.valueOf(RemainKyat));
                        }else if (RemainKyat!=0){

                            ednetPaymentKyat.setText(String.valueOf(-RemainKyat));
                        }
                    }

                    else if(PayKyat>getTotalAmountKyat && PayPal>getTotalAmountPal && PayYae==getTotalAmountYae)
                    {

                        RemainYae=PayYae-getTotalAmountYae;
                        RemainPal=PayPal-getTotalAmountPal;
                        RemainKyat=PayKyat-getTotalAmountKyat;
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednetPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        if(RemainPal==0){
                            ednetPaymentPal.setText(String.valueOf(RemainPal));
                        }else if (RemainPal!=0){
                            ednetPaymentPal.setText(String.valueOf(-RemainPal));
                        }
                        if (RemainKyat==0){
                            ednetPaymentKyat.setText(String.valueOf(RemainKyat));
                        }else if (RemainKyat!=0){

                            ednetPaymentKyat.setText(String.valueOf(-RemainKyat));
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
                        ednetPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        if(RemainPal==0){
                            ednetPaymentPal.setText(String.valueOf(RemainPal));
                        }else if (RemainPal!=0){
                            ednetPaymentPal.setText(String.valueOf(-RemainPal));
                        }
                        if (RemainKyat==0){
                            ednetPaymentKyat.setText(String.valueOf(RemainKyat));
                        }else if (RemainKyat!=0){

                            ednetPaymentKyat.setText(String.valueOf(-RemainKyat));
                        }
                    }

                    else if(PayKyat>getTotalAmountKyat && PayPal==getTotalAmountPal && PayYae>getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;
                        if(RemainYae==0) {
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednetPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        }else if(RemainYae!=0){
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednetPaymentYae.setText(String.valueOf(form1.format(-RemainYae)));
                        }

                        if (RemainPal == 0)
                        {
                            ednetPaymentPal.setText(String.valueOf(RemainPal));
                        }
                        else if (RemainPal != 0)
                        {
                            ednetPaymentPal.setText(String.valueOf(-RemainPal));
                        }else if(RemainKyat == 0)
                        {
                            ednetPaymentKyat.setText(String.valueOf(RemainKyat));
                        } else if (RemainKyat != 0)
                        {
                            ednetPaymentKyat.setText(String.valueOf(-RemainKyat));
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
                            ednetPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        } else if(RemainYae!=0) {
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednetPaymentYae.setText(String.valueOf(form1.format(-RemainYae)));
                        }
                        if (RemainPal == 0) {
                            ednetPaymentPal.setText(String.valueOf(RemainPal));
                        } else {
                            ednetPaymentPal.setText(String.valueOf(-RemainPal));
                        }
                        if (RemainKyat == 0) {
                            ednetPaymentKyat.setText(String.valueOf(RemainKyat));
                        } else if (RemainKyat != 0) {

                            ednetPaymentKyat.setText(String.valueOf(-RemainKyat));
                        }
                    }

                    if (PayKyat==0 && PayPal==0 && PayYae==0){
                        ednetPaymentKyat.setText(String.valueOf(getTotalAmountKyat));
                        ednetPaymentPal.setText(String.valueOf(getTotalAmountPal));
                        ednetPaymentYae.setText(String.valueOf(getTotalAmountYae));
                    }





                    if (PayKyat <= getTotalAmountKyat) {

                        if (PayYae > getTotalAmountYae) {
                            getTotalAmountPal = getTotalAmountPal - 1;
                            getTotalAmountYae = getTotalAmountYae + 8;
                            RemainYae = getTotalAmountYae - PayYae;
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednetPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        } else if (PayYae < getTotalAmountYae) {
                            RemainYae = getTotalAmountYae - PayYae;
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednetPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        } else if (PayYae == getTotalAmountYae) {
                            RemainYae = getTotalAmountYae - PayYae;
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednetPaymentYae.setText(String.valueOf(form1.format(RemainYae)));
                        }

                        if (PayPal > getTotalAmountPal) {
                            getTotalAmountKyat = getTotalAmountKyat - 1;
                            getTotalAmountPal = getTotalAmountPal + 16;
                            RemainPal = getTotalAmountPal - PayPal;
                            ednetPaymentPal.setText(String.valueOf(RemainPal));
                        } else if (PayPal < getTotalAmountPal) {
                            RemainPal = getTotalAmountPal - PayPal;
                            ednetPaymentPal.setText(String.valueOf(RemainPal));

                        } else if (PayPal == getTotalAmountPal) {
                            RemainPal = getTotalAmountPal - PayPal;
                            ednetPaymentPal.setText(String.valueOf(RemainPal));
                        }

                        RemainKyat = getTotalAmountKyat - PayKyat;
                        ednetPaymentKyat.setText(String.valueOf(RemainKyat));

                    }


                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });



        ednowRemainKyat = dialog.findViewById(R.id.remainKyat);
        ednowRemainPal = dialog.findViewById(R.id.remainPal);
        ednowRemainYae = dialog.findViewById(R.id.remainYae);

        ednowRemainKyat.setText(remainkyat);
        ednowRemainYae.setText(remainyae);
        ednowRemainPal.setText(remainpal);
        btntotalRemainAmount=dialog.findViewById(R.id.totalRemainAmount);
        btntotalRemainAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //  Toast.makeText(getContext(), "Total Remain Amount", Toast.LENGTH_SHORT).show();
                    int getTotalAmountKyat = Integer.parseInt(ednetPaymentKyat.getText().toString());
                    int getTotalAmountPal = Integer.parseInt(ednetPaymentPal.getText().toString());
                    double getTotalAmountYae = Double.parseDouble(ednetPaymentYae.getText().toString());

                    int PayKyat = Integer.parseInt(ednowPaymentKyat.getText().toString());
                    int PayPal = Integer.parseInt(ednowPaymentPal.getText().toString());
                    double PayYae = Double.parseDouble(ednowPaymentYae.getText().toString());

                    int RemainKyat = 0;
                    int RemainPal = 0;
                    double RemainYae = 0.0;


                    if (PayKyat > getTotalAmountKyat && PayPal < getTotalAmountPal && PayYae == getTotalAmountYae) {

                        PayKyat = PayKyat-1;
                        PayPal=PayPal+16;
                        RemainPal=PayPal-getTotalAmountPal;
                        RemainKyat=PayKyat-getTotalAmountKyat;
                        ednowRemainYae.setText("0.0");
                        ednowRemainPal.setText(String.valueOf(-RemainPal));
                        if (RemainKyat==0){
                            ednowRemainKyat.setText("0");
                        }else if (RemainKyat!=0){
                            ednowRemainKyat.setText(String.valueOf(-RemainKyat));
                        }
                    }else if(PayKyat > getTotalAmountKyat && PayPal < getTotalAmountPal && PayYae > getTotalAmountYae){
                        RemainYae=PayYae-getTotalAmountYae;
                        PayKyat = PayKyat-1;
                        PayPal=PayPal+16;
                        RemainPal=PayPal-getTotalAmountPal;
                        RemainKyat=PayKyat-getTotalAmountKyat;

                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                        ednowRemainPal.setText(String.valueOf(-RemainPal));
                        if (RemainKyat==0){
                            ednowRemainKyat.setText("0");
                        }else if (RemainKyat!=0){
                            ednowRemainKyat.setText(String.valueOf(-RemainKyat));
                        }

                    }else if(PayKyat > getTotalAmountKyat && PayPal < getTotalAmountPal && PayYae < getTotalAmountYae){

                        PayKyat = PayKyat-1;
                        PayPal=(PayPal+16)-1;
                        PayYae=PayYae+8;
                        RemainYae=PayYae-getTotalAmountYae;
                        RemainPal=PayPal-getTotalAmountPal;
                        RemainKyat=PayKyat-getTotalAmountKyat;

                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                        ednowRemainPal.setText(String.valueOf(-RemainPal));
                        if (RemainKyat==0){
                            ednowRemainKyat.setText("0");
                        }else if (RemainKyat!=0){
                            ednowRemainKyat.setText(String.valueOf(-RemainKyat));
                        }

                    }else if (PayKyat > getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae == getTotalAmountYae) {
                        RemainKyat = PayKyat - getTotalAmountKyat;
                        ednowRemainKyat.setText(String.valueOf(-RemainKyat));
                        ednowRemainPal.setText("0");
                        ednowRemainYae.setText("0");
                    }
                    else if (PayKyat > getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae > getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        ednowRemainKyat.setText(String.valueOf(-RemainKyat));
                        ednowRemainPal.setText(String.valueOf(RemainPal));
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                    } else if (PayKyat > getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae < getTotalAmountYae) {

                        PayKyat = PayKyat - 1;
                        PayPal = (PayPal + 16) - 1;
                        PayYae = PayYae + 8;
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        if (RemainKyat == 0) {
                            ednowRemainKyat.setText(String.valueOf(RemainKyat));
                        } else if (RemainKyat != 0) {
                            ednowRemainKyat.setText(String.valueOf(-RemainKyat));
                        }
                        if (RemainPal == 0) {
                            ednowRemainPal.setText(String.valueOf(RemainPal));
                        } else if (RemainPal != 0) {
                            ednowRemainPal.setText(String.valueOf(-RemainPal));
                        }
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                    } else if (PayKyat > getTotalAmountKyat && PayPal > getTotalAmountYae && PayYae == getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        ednowRemainKyat.setText(String.valueOf(-RemainKyat));
                        ednowRemainPal.setText(String.valueOf(-RemainPal));
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
                    } else if (PayKyat > getTotalAmountKyat && PayPal > getTotalAmountYae && PayYae > getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        ednowRemainKyat.setText(String.valueOf(-RemainKyat));
                        ednowRemainPal.setText(String.valueOf(-RemainPal));
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                    } else if (PayKyat > getTotalAmountKyat && PayPal > getTotalAmountYae && PayYae < getTotalAmountYae) {
                        PayPal = PayPal - 1;
                        PayYae = PayYae + 8;
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        ednowRemainKyat.setText(String.valueOf(-RemainKyat));
                        if (RemainPal == 0) {
                            ednowRemainPal.setText(String.valueOf(RemainPal));
                        } else if (RemainPal != 0) {
                            ednowRemainPal.setText(String.valueOf(-RemainPal));
                        }

                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                    } else if (PayKyat == getTotalAmountKyat && PayPal > getTotalAmountPal && PayYae > getTotalAmountYae){
                        //for equal
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                        ednowRemainPal.setText(String.valueOf(-RemainPal));
                        ednowRemainKyat.setText(String.valueOf(RemainKyat));
                    } else if (PayKyat == getTotalAmountKyat && PayPal > getTotalAmountPal && PayYae < getTotalAmountYae) {
                        PayPal = PayPal - 1;
                        PayYae = PayYae + 8;
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                        ednowRemainPal.setText(String.valueOf(-RemainPal));
                        ednowRemainKyat.setText(String.valueOf(RemainKyat));
                    } else if (PayKyat == getTotalAmountKyat && PayPal > getTotalAmountPal && PayYae == getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = PayPal - getTotalAmountPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;

                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
                        ednowRemainPal.setText(String.valueOf(-RemainPal));
                        ednowRemainKyat.setText(String.valueOf(RemainKyat));
                    } else if (PayKyat == getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae == getTotalAmountYae) {
                        ednowRemainKyat.setText("0");
                        ednowRemainPal.setText("0");
                        ednowRemainYae.setText("0.0");
                    } else if (PayKyat == getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae > getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowRemainYae.setText(String.valueOf(form1.format(-RemainYae)));
                        ednowRemainKyat.setText("0");
                        ednowRemainPal.setText("0");
                    } else if (PayKyat == getTotalAmountKyat && PayPal == getTotalAmountPal && PayYae < getTotalAmountYae) {
                        RemainYae = getTotalAmountYae - PayYae;
                        DecimalFormat form1 = new DecimalFormat("0.00");
                        ednowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
                        ednowRemainKyat.setText("0");
                        ednowRemainPal.setText("0");
                    } else if (PayKyat == getTotalAmountKyat && PayPal < getTotalAmountPal && PayYae == getTotalAmountYae) {
                        RemainYae = PayYae - getTotalAmountYae;
                        RemainPal = getTotalAmountPal - PayPal;
                        RemainKyat = PayKyat - getTotalAmountKyat;
                        ednowRemainYae.setText(String.valueOf(RemainYae));
                        ednowRemainPal.setText(String.valueOf(RemainPal));
                        ednowRemainKyat.setText(String.valueOf(RemainKyat));
                    } else if (PayKyat == getTotalAmountKyat && PayPal < getTotalAmountPal && PayYae > getTotalAmountYae) {

                        getTotalAmountPal = getTotalAmountPal - 1;
                        getTotalAmountYae = getTotalAmountYae + 8;
                        RemainYae = getTotalAmountYae - PayYae;
                        RemainPal = getTotalAmountPal - PayPal;
                        RemainKyat = getTotalAmountKyat - PayKyat;

                        ednowRemainYae.setText(String.valueOf(RemainYae));
                        ednowRemainPal.setText(String.valueOf(RemainPal));
                        ednowRemainKyat.setText(String.valueOf(RemainKyat));
                    } else if (PayKyat == getTotalAmountKyat && PayPal < getTotalAmountPal && PayYae < getTotalAmountYae) {
                        RemainYae = getTotalAmountYae - PayYae;
                        RemainPal = getTotalAmountPal - PayPal;
                        RemainKyat = getTotalAmountKyat - PayKyat;
                        ednowRemainYae.setText(String.valueOf(RemainYae));
                        ednowRemainPal.setText(String.valueOf(RemainPal));
                        ednowRemainKyat.setText(String.valueOf(RemainKyat));
                    } else if (PayKyat < getTotalAmountKyat) {
                        //for less than
                        if (PayYae > getTotalAmountYae) {
                            getTotalAmountPal = getTotalAmountPal - 1;
                            getTotalAmountYae = getTotalAmountYae + 8;
                            RemainYae = getTotalAmountYae - PayYae;
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
                        } else if (PayYae < getTotalAmountYae) {
                            RemainYae = getTotalAmountYae - PayYae;
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
                        } else if (PayYae == getTotalAmountYae) {
                            RemainYae = getTotalAmountYae - PayYae;
                            DecimalFormat form1 = new DecimalFormat("0.00");
                            ednowRemainYae.setText(String.valueOf(form1.format(RemainYae)));
                        }

                        if (PayPal > getTotalAmountPal) {
                            getTotalAmountKyat = getTotalAmountKyat - 1;
                            getTotalAmountPal = getTotalAmountPal + 16;
                            RemainPal = getTotalAmountPal - PayPal;
                            ednowRemainPal.setText(String.valueOf(RemainPal));
                        } else if (PayPal < getTotalAmountYae) {
                            RemainPal = getTotalAmountPal - PayPal;
                            ednowRemainPal.setText(String.valueOf(RemainPal));

                        } else if (PayPal == getTotalAmountPal) {
                            RemainPal = getTotalAmountPal - PayPal;
                            ednowRemainPal.setText(String.valueOf(RemainPal));
                        }

                        RemainKyat = getTotalAmountKyat - PayKyat;
                        ednowRemainKyat.setText(String.valueOf(RemainKyat));
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


       edTotalNumber = dialog.findViewById(R.id.custBuyNumber);
       edTotalPointEight = dialog.findViewById(R.id.custDiscountPoint);

       edTotalNumber.setText(totalQty);
       edTotalPointEight.setText(totalPointEight);

       edReturnQualtity = dialog.findViewById(R.id.returnNumber);
       edReturnPointEight = dialog.findViewById(R.id.returnDiscountPoint);
       edRemainQualtity = dialog.findViewById(R.id.remainNumber);
       edRemainPointEight = dialog.findViewById(R.id.remainDiscountPoint);

       edReturnQualtity.setText(returnQuantity);
       edReturnPointEight.setText(returnPointEight);
       edRemainQualtity.setText(nowRemainQuantity);
       edRemainPointEight.setText(nowRemainPointEight);



       btnitemCalculate = dialog.findViewById(R.id.itemCalculate);
       btnitemCalculate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {
                   int remainNumber, remainPointEight;
                   int itemNumber = Integer.parseInt(edTotalNumber.getText().toString());
                   int itemPointEight = Integer.parseInt(edTotalPointEight.getText().toString());
                   int returnItemNumber = Integer.parseInt(edReturnQualtity.getText().toString());
                   int returnItemPointEight = Integer.parseInt(edReturnPointEight.getText().toString());

                   remainNumber = itemNumber - returnItemNumber;
                   remainPointEight = itemPointEight - returnItemPointEight;

                   edRemainQualtity.setText(String.valueOf(remainNumber));
                   edRemainPointEight.setText(String.valueOf(remainPointEight));

               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       });


        edAyotkyat = dialog.findViewById(R.id.ayotKyat);
        edAyotPal= dialog.findViewById(R.id.ayotPal);
        edAyotYae = dialog.findViewById(R.id.ayotYae);
        edReturnAyotKyat = dialog.findViewById(R.id.returnAyotKyat);
        edReturnAyotPal= dialog.findViewById(R.id.returnAyotPal);
        edReturnAyotYae = dialog.findViewById(R.id.returnAyotYae);
        edNowRemainAyotKyat = dialog.findViewById(R.id.remainAyotKyat);
        edNowRemainAyotPal= dialog.findViewById(R.id.remainAyotPal);
        edNowRemainAyotYae = dialog.findViewById(R.id.remainAyotYae);


        edAyotkyat.setText(total_ayot_kyat);
        edAyotPal.setText(total_ayot_pal);
        edAyotYae.setText(total_ayo_yae);
        edReturnAyotKyat.setText(return_ayot_kyat);
        edReturnAyotPal.setText(return_ayot_pal);
        edReturnAyotYae.setText(return_ayot_yae);
        edNowRemainAyotKyat.setText(now_total_ayot_kyat);
        edNowRemainAyotPal.setText(now_total_ayot_pal);
        edNowRemainAyotYae.setText(now_total_ayot_yae);



        btnayotCalculate = dialog.findViewById(R.id.ayotCalculate);
        btnayotCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int returnAyotKyat= Integer.parseInt(edReturnAyotKyat.getText().toString());
                    int returnAyotPal = Integer.parseInt(edReturnAyotPal.getText().toString());
                    double returnAyotYae = Double.parseDouble(edReturnAyotYae.getText().toString());

                    int AyotKyat = Integer.parseInt(edAyotkyat.getText().toString());
                    int AyotPal = Integer.parseInt(edAyotPal.getText().toString());
                    double AyotYae = Double.parseDouble(edAyotYae.getText().toString());

                    if (returnAyotYae > AyotYae)
                    {
                        AyotPal = AyotPal - 1;
                        AyotYae = AyotYae + 8;
                        remainAyotYae = AyotYae - returnAyotYae;
                        Toast.makeText(getContext(), String.valueOf(remainAyotYae), Toast.LENGTH_SHORT).show();
                    } else if (returnAyotYae < AyotYae)
                    {
                        remainAyotYae = AyotYae - returnAyotYae;
                        Toast.makeText(getContext(), String.valueOf(remainAyotYae), Toast.LENGTH_SHORT).show();
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
                    edNowRemainAyotKyat.setText(String.valueOf(remainAyotKyat));
                    edNowRemainAyotPal.setText(String.valueOf(remainAyotPal));
                    DecimalFormat form1 = new DecimalFormat("0.00");
                    edNowRemainAyotYae.setText(String.valueOf(form1.format(remainAyotYae)));



                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        edCuponCode = dialog.findViewById(R.id.cupon);
        edShopName = dialog.findViewById(R.id.shopName);
        edCustomerName = dialog.findViewById(R.id.custName);
        edPhoneNumber = dialog.findViewById(R.id.custPhone);
        edDob = dialog.findViewById(R.id.custDOB);
        edCustomerUserName = dialog.findViewById(R.id.custUserName);
        edAddress = dialog.findViewById(R.id.custAddress);
        edTownShip = dialog.findViewById(R.id.custTown);
        edNrc  = dialog.findViewById(R.id.custNrc);
        edCustomerId = dialog.findViewById(R.id.custId);

        edNote = dialog.findViewById(R.id.note);


        edCuponCode.setText(cuponCode);
        edShopName.setText(shopName);
        edCustomerName.setText(customerName);
        edPhoneNumber.setText(customerPhoneNumber);
        edDob.setText(customerDob);
        edAddress.setText(customerAddress);
        edTownShip.setText(town);
       edCustomerId.setText(customer_id);
       edNote.setText(remark);
       edCustomerUserName.setText(customerUserName);
       edNrc.setText(customerNrc);
        










//    nameList = new ArrayList<>();
//    nameList.add("Voucher No");
//    nameList.add("Sale Date");
//    nameList.add("Customer Name");
//    nameList.add("Shop Name");
//    nameList.add("Township");
//    nameList.add("Qualtity");
//    nameList.add("Point eight");
//    nameList.add("Cupon Code");
//    nameList.add("စုစုေပါင္း gram");
//    nameList.add("က်ပ္");
//    nameList.add("ပဲ");
//    nameList.add("ေရြး");
//    nameList.add("ယခုဝယ္သည့္ က်ပ္");
//    nameList.add("ယခုဝယ္သည့္ ပဲ");
//    nameList.add("ယခုဝယ္သည့္ ေရြး");
//    nameList.add(" အေလ်ာ့ က်ပ္");
//    nameList.add(" အေလ်ာ့ ပဲ");
//    nameList.add(" အေလ်ာ့ ေရြး");
//    nameList.add("ယခင္အေျကြး က်ပ္");
//    nameList.add("ယခင္အေျကြး ပဲ");
//    nameList.add("ယခင္အေျကြး ေရြး");
//    nameList.add("စုစုေပါင္း က်ပ္");
//    nameList.add("စုစုေပါင္း ပဲ");
//    nameList.add("စုစုေပါင္း ေရြး");
//    nameList.add("ယခုေပးေခ်မည့္ က်ပ္");
//    nameList.add("ယခုေပးေခ်မည့္ ပဲ");
//    nameList.add("ယခုေပးေခ်မည့္ ေရြး");
//    nameList.add("ယခုလက္က်န္ က်ပ္");
//    nameList.add("ယခုလက္က်န္ ပဲ");
//    nameList.add("ယခုလက္က်န္ ေရြး");











//    dataList = new ArrayList<>();
//    dataList.add(voucher);
//    dataList.add(sDate);
//    dataList.add(customerName);
//    dataList.add(shopName);
//    dataList.add(town);
//    dataList.add(totalQty);
//    dataList.add(totalPointEight);
//    dataList.add(cuponCode);
//    dataList.add(gram);
//    dataList.add(totalKyat);
//    dataList.add(totalPal);
//    dataList.add(totalYae);
//    dataList.add(newTotalKyat);
//    dataList.add(newTotalPal);
//    dataList.add(newTotalYae);
//    dataList.add(total_ayot_kyat);
//    dataList.add(total_ayot_pal);
//    dataList.add(total_ayo_yae);
//    dataList.add(previous_kyat);
//    dataList.add(previous_pal);
//    dataList.add(previous_yae);
//    dataList.add(buydebitkyat);
//    dataList.add(buydebitpal);
//    dataList.add(buydebityae);
//    dataList.add(paymentkyat);
//    dataList.add(paymentpal);
//    dataList.add(paymentyae);
//    dataList.add(remainkyat);
//    dataList.add(remainpal);
//    dataList.add(remainyae);





    //adapterDialog = new bindvouchersaleRecyclerAdapter(nameList,dataList,getContext());
   // recyclerView.setAdapter(adapterDialog);

        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        Call<Sale> saleCall = MainActivity.apiInterface.getSaleProfile(prefConfig.readName());
        saleCall.enqueue(new Callback<Sale>() {
            @Override
            public void onResponse(Call<Sale> call, Response<Sale> response) {
                 Status=response.body().getStatus();

            }

            @Override
            public void onFailure(Call<Sale> call, Throwable t) {

            }
        });


        BnUpdateInVoiceSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Status.equals("No")) {
                            Toast.makeText(getContext(), "You Need Owner Approve", Toast.LENGTH_SHORT).show();
                        } else if (Status.equals("Yes")) {


                            progressDialog.setTitle("Update Sale Invoice");
                            progressDialog.show();
                            Call<Salehistory> call = MainActivity.apiInterface.updateSaleInvoice(ID, MainActivity.prefConfig.readName(), edviewVoucher.getText().toString(),
                                    edSaleDate.getText().toString(),
                                    ednowPurchaseKyat.getText().toString(),
                                    ednowPurchasePal.getText().toString(),
                                    ednowPurchaseYae.getText().toString(),
                                    edTotalNumber.getText().toString(),
                                    edTotalPointEight.getText().toString(),
                                    edAyotkyat.getText().toString(),
                                    edAyotPal.getText().toString(),
                                    edAyotYae.getText().toString(),
                                    edTotalKyat.getText().toString(),
                                    edTotalPal.getText().toString(),
                                    edTotalYae.getText().toString(),
                                    edGram.getText().toString(),
                                    edCuponCode.getText().toString(),
                                    edCustomerId.getText().toString(),
                                    edPreviousRemainKyat.getText().toString(),
                                    edPreviousRemainPal.getText().toString(),
                                    edPreviousRemainYae.getText().toString(),
                                    edBuyDebitKyat.getText().toString(),
                                    edBuyDebitPal.getText().toString(),
                                    edBuyDebitYae.getText().toString(),
                                    edReturnGoldKyat.getText().toString(),
                                    edReturnGoldPal.getText().toString(),
                                    edReturnGoldYae.getText().toString(),
                                    ednetPaymentKyat.getText().toString(),
                                    ednetPaymentPal.getText().toString(),
                                    ednetPaymentYae.getText().toString(),
                                    ednowPaymentKyat.getText().toString(),
                                    ednowPaymentPal.getText().toString(),
                                    ednowPaymentYae.getText().toString(),
                                    ednowRemainKyat.getText().toString(),
                                    ednowRemainPal.getText().toString(),
                                    ednowRemainYae.getText().toString(),
                                    edNote.getText().toString(),
                                    edReturnGram.getText().toString(),
                                    edRemainGram.getText().toString(),
                                    edGramRemainKyat.getText().toString(),
                                    edGramRemainPal.getText().toString(),
                                    edGramRemainYae.getText().toString(),
                                    edReturnQualtity.getText().toString(),
                                    edReturnPointEight.getText().toString(),
                                    edRemainQualtity.getText().toString(),
                                    edRemainPointEight.getText().toString(),
                                    edReturnAyotKyat.getText().toString(),
                                    edReturnAyotPal.getText().toString(),
                                    edReturnAyotYae.getText().toString(),
                                    edNowRemainAyotKyat.getText().toString(),
                                    edNowRemainAyotPal.getText().toString(),
                                    edNowRemainAyotYae.getText().toString(),
                                    edShopName.getText().toString(),
                                    edCustomerName.getText().toString(),
                                    edPhoneNumber.getText().toString(),
                                    edDob.getText().toString(),
                                    edNrc.getText().toString(),
                                    edAddress.getText().toString(),
                                    edTownShip.getText().toString(),
                                    edCustomerUserName.getText().toString()
                            );


                            Log.e("Response", "after doing this update ");

                            call.enqueue(new Callback<Salehistory>() {
                                @Override
                                public void onResponse(Call<Salehistory> call, Response<Salehistory> response) {
                                    progressDialog.dismiss();
                                    if (response.body().getResponse().equals("ok")) {
                                        Toast.makeText(getContext(), "Update successfully", Toast.LENGTH_SHORT).show();
                                    }

                                    // Toast.makeText(getContext(), "do response" + ID, Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onFailure(Call<Salehistory> call, Throwable t) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Update Fail Please Check Your Connection ",  Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });




//        BnUpdateInVoiceSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //Log.e("Response","doing this methos");
////                        Toast.makeText(getContext(),edVoucher.getText().toString()+ ID
////
////                        , Toast.LENGTH_SHORT).show();
//                progressDialog.setTitle("Update Sale Invoice");
//                progressDialog.show();
//                Call<Salehistory> call = MainActivity.apiInterface.updateSaleInvoice(ID,MainActivity.prefConfig.readName(),edviewVoucher.getText().toString(),
//                        edSaleDate.getText().toString()
//                );
//                Toast.makeText(getContext(), ID, Toast.LENGTH_SHORT).show();
//
//                Log.e("Response","after doing this update ");
//
//                call.enqueue(new Callback<Salehistory>() {
//                    @Override
//                    public void onResponse(Call<Salehistory> call, Response<Salehistory> response) {
//                        progressDialog.dismiss();
//                        if (response.body().getResponse().equals("ok")){
//
//                            Toast.makeText(getContext(), "Update successfully", Toast.LENGTH_SHORT).show();
//                        }
//                        String voc = response.body().getVoucherNumber();
//                        String sDate = response.body().getSaleDate();
//                        Toast.makeText(getContext(), "do response" + voc + sDate, Toast.LENGTH_LONG).show();
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Salehistory> call, Throwable t) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getContext(), "update Fail" + t.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//            }
//        });
//

//
//
//
//
//
//
////
////                recyclerViewEdit = dialog.findViewById(R.id.recyclerViewEdit);
////                recyclerViewEdit.setVisibility(View.VISIBLE);
////                layoutManager = new LinearLayoutManager(getContext());
////                recyclerViewEdit.setLayoutManager(layoutManager);
////                recyclerViewEdit.setHasFixedSize(true);
////                adapterEditDialog = new editbindvouchersaleRecyclerAdapter(nameList,dataList,getContext());
////                recyclerViewEdit.setAdapter(adapterEditDialog);
//
//
//            }
//        });
//
//
//
////        dialogEdit.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////               dialog.dismiss();
////               dialog.setContentView(R.layout.sale_create_invoice);
////               dialog.show();
////            }
////        });

//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    edGram.setEnabled(true);
//                    edviewVoucher.setEnabled(true);
//                    edviewDate.setEnabled(true);
//                    edGramToKyat.setEnabled(true);
//                    edGramToPal.setEnabled(true);
//                    edGramToYae.setEnabled(true);
//
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//
//                Toast.makeText(getContext(), "Need Owner Approve", Toast.LENGTH_SHORT).show();
//            }
//        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void fetchInformation(String type){
        progressDialog.setTitle("Loading Data....");
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
                progressDialog.dismiss();
                Toast.makeText(getContext(), "History Not Found", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_items,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        //((SalesActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        s = s.toLowerCase();
        ArrayList<Salehistory> newList = new ArrayList<>();

        for (Salehistory salehistory : salehistories)
        {
            String sDate = salehistory.getSaleDate().toLowerCase();
            String shopName = salehistory.getCustomerShop().toLowerCase();
            String town = salehistory.getCustomerTwon();
            String voucher = salehistory.getVoucherNumber();
            if (sDate.contains(s) || shopName.contains(s) || town.contains(s) || voucher.contains(s)){
                newList.add(salehistory);
            }
        }

        adapter.setFilter(newList);

        return true;
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edSaleDate.setText(sdf.format(myCalendar.getTime()));
    }



}


