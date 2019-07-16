package asia.nainglintun.myintthitar.activities.Fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import asia.nainglintun.myintthitar.activities.Activities.AfterScan;
import asia.nainglintun.myintthitar.activities.Activities.InputFilterMinMax;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.activities.Activities.LoginTestActivity;
import asia.nainglintun.myintthitar.activities.Activities.ScanForVoucherActivity;
import me.myatminsoe.mdetect.MMEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleInvoiceCreate extends Fragment implements  View.OnClickListener {

//private RadioGroup radioGroup;
private LinearLayout linearLayoutCustomerNew;
private EditText editTextSearchPhone,editTextKyat,editTextPel,editTextYae;
private EditText edShopName,edCustomerName,edCustomerPhone,edDOB,edCustomerPassword,edSarchPhone;
//private ImageButton searchButton;
    public static EditText edCustomerUserName;
private  EditText saleDate;
private MMEditText voucher;
private Toolbar toolbar;
final Calendar myCalendar = Calendar.getInstance();
private Button btnCreateInvoiceSave,scanForVoucher;

    public SaleInvoiceCreate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.sale_create_invoice, container, false);

        toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Create Invoice");

         saleDate= view.findViewById(R.id.saleDate);

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




        //radioGroup = view.findViewById(R.id.radioGroup);
       // radioGroup.setOnCheckedChangeListener(this);
        //linearLayoutCustomerNew = view.findViewById(R.id.newCustomerLayout);
        //searchWithPhoneLayout = view.findViewById(R.id.searchWithPhoneLayout);
       // editTextSearchPhone= view.findViewById(R.id.searchPhone);
        editTextYae = view.findViewById(R.id.yawe);
        editTextYae.setFilters(new InputFilter[]{new InputFilterMinMax(1,7)});

        editTextPel = view.findViewById(R.id.pel);
        editTextPel.setFilters(new InputFilter[]{new InputFilterMinMax(1,15)});

        edShopName = view.findViewById(R.id.shopName);
        edCustomerName = view.findViewById(R.id.custName);
        edCustomerPhone = view.findViewById(R.id.custPhone);
        edDOB = view.findViewById(R.id.custDOB);
        edCustomerUserName = view.findViewById(R.id.custUserName);
        edCustomerPassword = view.findViewById(R.id.custPassword);
       // edSarchPhone = view.findViewById(R.id.searchPhone);
//        phoneNumber = edSarchPhone.getText().toString().trim();
//
//        Toast.makeText(getContext(),phoneNumber,Toast.LENGTH_LONG).show();

        editTextKyat = view.findViewById(R.id.kyat);
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

                startActivity(new Intent(getContext(), ScanForVoucherActivity.class));

                break;

            case R.id.btnInVoiceSave:

                Toast.makeText(getContext(),"To do Invoice Save Operation in SaleInvoiceCreate.java",Toast.LENGTH_LONG).show();

                break;



        }
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        saleDate.setText(sdf.format(myCalendar.getTime()));
    }


}
