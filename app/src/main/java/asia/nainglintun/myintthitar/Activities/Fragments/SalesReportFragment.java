package asia.nainglintun.myintthitar.Activities.Fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import asia.nainglintun.myintthitar.Activities.InputFilterMinMax;
import asia.nainglintun.myintthitar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesReportFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

private RadioGroup radioGroup;
private LinearLayout linearLayoutCustomerNew,searchWithPhoneLayout;
private EditText editTextSearchPhone,editTextKyat,editTextPel,editTextYae;
private EditText edShopName,edCustomerName,edCustomerPhone,edDOB,edCustomerUserName,edCustomerPassword,edSarchPhone;
private ImageButton searchButton;
private  EditText saleDate;
final Calendar myCalendar = Calendar.getInstance();

    public SalesReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sales_report, container, false);



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




        radioGroup = view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        linearLayoutCustomerNew = view.findViewById(R.id.newCustomerLayout);
        searchWithPhoneLayout = view.findViewById(R.id.searchWithPhoneLayout);
        editTextSearchPhone= view.findViewById(R.id.searchPhone);
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
        edSarchPhone = view.findViewById(R.id.searchPhone);
//        phoneNumber = edSarchPhone.getText().toString().trim();
//
//        Toast.makeText(getContext(),phoneNumber,Toast.LENGTH_LONG).show();

        editTextKyat = view.findViewById(R.id.kyat);
        searchButton = view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);
        searchWithPhoneLayout.setVisibility(View.GONE);




        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
      switch (checkedId){
          case R.id.radioNewCustomer:
             linearLayoutCustomerNew.setVisibility(View.VISIBLE);
              edShopName.setText("");
              edCustomerName.setText("");
              //edSarchPhone.getText().toString();
              edCustomerPhone.setText("");
              //Toast.makeText(getContext(), edSarchPhone.getText().toString(),Toast.LENGTH_LONG).show();
              edDOB.setText("");
              edCustomerUserName.setText("");
              edCustomerPassword.setText("");
              //searchWithPhoneLayout.setVisibility(View.VISIBLE);
             searchWithPhoneLayout.setVisibility(View.GONE);
              break;
          case R.id.radioExistingCustomer:
                linearLayoutCustomerNew.setVisibility(View.GONE);
              searchWithPhoneLayout.setVisibility(View.VISIBLE);

              break;
      }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchButton:

                linearLayoutCustomerNew.setVisibility(View.VISIBLE);
                String phoneNumber = edSarchPhone.getText().toString();
                edShopName.setText("Sein nan Thaw");
                edCustomerName.setText("Ma Aye Myat Mon");
                //edSarchPhone.getText().toString();
                edCustomerPhone.setText(phoneNumber);
                //Toast.makeText(getContext(), edSarchPhone.getText().toString(),Toast.LENGTH_LONG).show();
                edDOB.setText("may 25,1990");
                edCustomerUserName.setText("Aye Myte");
                edCustomerPassword.setText("ayemyte009");
                //searchWithPhoneLayout.setVisibility(View.VISIBLE);
                searchWithPhoneLayout.setVisibility(View.GONE);

        }
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        saleDate.setText(sdf.format(myCalendar.getTime()));
    }
}
