package asia.nainglintun.myintthitar.activities.Fragments;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.activities.Activities.MainActivity;
import asia.nainglintun.myintthitar.activities.models.Customer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleAddCustomer extends Fragment {

    private Toolbar toolbar;

    private EditText userName,shopName,phoneNumber,Address,DOB,Nrc,Township;
    private Button btnCustomerSave;
    private ProgressDialog progressDialog;
    final Calendar myCalendar = Calendar.getInstance();

    public SaleAddCustomer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.sale_add_customer, container, false);

       toolbar = view.findViewById(R.id.toolBar);
       toolbar.setTitle("Create New Customer");

       userName = view.findViewById(R.id.userName);
       shopName = view.findViewById(R.id.shopName);
       phoneNumber = view.findViewById(R.id.phoneNumber);
       Address = view.findViewById(R.id.address);
       DOB = view.findViewById(R.id.dateOfBirth);
       Nrc = view.findViewById(R.id.nrc);
       Township = view.findViewById(R.id.township);

       btnCustomerSave = view.findViewById(R.id.customerSave);
        progressDialog = new ProgressDialog(getContext());

        progressDialog.setMessage("Please wait....");





        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }
        };

        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        btnCustomerSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             performRegistration();
           }
       });
       return view;
    }


    public void performRegistration(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());

        Toast.makeText(getContext(),currentDateandTime,Toast.LENGTH_LONG).show();
        String username = userName.getText().toString();
        String uniqueUserName=username.concat(currentDateandTime);
        if(username.isEmpty()){
            userName.setError("Enter User Name");
        }


        String shopname = shopName.getText().toString();
        if(shopname.isEmpty()){
            shopName.setError("Enter Shop Name");
        }

        String phonenumber = phoneNumber.getText().toString();
        if(phonenumber.isEmpty()){
            phoneNumber.setError("Enter Phone Number");
        }

        String address = Address.getText().toString();

        if(address.isEmpty()){
            Address.setError("Enter Address ");
        }


        String dob = DOB.getText().toString();
        if(dob.isEmpty()){
            DOB.setError("Enter Date Of Birth");
        }


        String nrc= Nrc.getText().toString();
        if(nrc.isEmpty()){
            Nrc.setError("Enter NRC");
        }


        String township =Township.getText().toString();
        if(township.isEmpty()){
            Township.setError("Enter TownShip");
        }


        if((!username.isEmpty() && !shopname.isEmpty() && !phonenumber.isEmpty() && !address.isEmpty() && !dob.isEmpty() && !nrc.isEmpty() && !township.isEmpty())) {
            progressDialog.show();


            Call<Customer> call = MainActivity.apiInterface.performRegistration(uniqueUserName, shopname, phonenumber, address, dob, nrc, township);
            call.enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Call<Customer> call, Response<Customer> response) {
                    progressDialog.dismiss();
                    if (response.body().getResponse().equals("ok")) {
                        Toast.makeText(getContext(), "Registration Success...", Toast.LENGTH_LONG).show();
                    } else if (response.body().getResponse().equals("exist")) {
                        Toast.makeText(getContext(), "User already exist..", Toast.LENGTH_LONG).show();
                    } else if (response.body().getResponse().equals("error")) {
                        Toast.makeText(getContext(), "Something went wrong..", Toast.LENGTH_LONG).show();


                    }
                }

                @Override
                public void onFailure(Call<Customer> call, Throwable t) {

                }
            });


            userName.setText("");
            shopName.setText("");
            phoneNumber.setText("");
            Address.setText("");
            DOB.setText("");
            Nrc.setText("");
            Township.setText("");

        }

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        DOB.setText(sdf.format(myCalendar.getTime()));
    }


}
