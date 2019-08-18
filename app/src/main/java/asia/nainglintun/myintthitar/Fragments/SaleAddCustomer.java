package asia.nainglintun.myintthitar.Fragments;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import asia.nainglintun.myintthitar.Activities.SalesActivity;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.models.Customer;
import asia.nainglintun.myintthitar.models.Customer;
import asia.nainglintun.myintthitar.models.ImageClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleAddCustomer extends Fragment {

    private Toolbar toolbar;

    private EditText userName,shopName,phoneNumber,Address,DOB,Nrc,Township,customerName;
    private Button btnCustomerSave;
    private ProgressDialog progressDialog;
    final Calendar myCalendar = Calendar.getInstance();
    private ImageView imageQrcode;
    Bitmap bitmap;

    public SaleAddCustomer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.sale_add_customer, container, false);

        //((SalesActivity)getActivity()).setTitle("Add New Customer");
        toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Add New Customer");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales, new FragmentCard()).addToBackStack(null).commit();

            }
        });

       imageQrcode = view.findViewById(R.id.customerQrcode);
       userName = view.findViewById(R.id.userName);
       customerName = view.findViewById(R.id.custName);
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
        String username = userName.getText().toString();
        String uniqueUserName=username.concat(currentDateandTime);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(uniqueUserName, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageQrcode.setVisibility(View.VISIBLE);
            imageQrcode.setImageBitmap(bitmap);

        }catch (WriterException e){
            e.printStackTrace();
        }

       // String path = imageToString();

        if(username.isEmpty()){
            userName.setError("Enter User Name");
        }

        String customername =  customerName.getText().toString();
        if(customername.isEmpty()){
            customerName.setError("Enter Customer Name");
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


        if((!username.isEmpty() && !shopname.isEmpty() && !phonenumber.isEmpty() && !address.isEmpty() && !dob.isEmpty() && !nrc.isEmpty() && !township.isEmpty() && !customername.isEmpty())) {
            progressDialog.show();


//            Call<Customer> call = MainActivity.apiInterface.performRegistration(customername,uniqueUserName, shopname, phonenumber, address, dob, nrc, township,Image);
//            call.enqueue(new Callback<Customer>() {
//                @Override
//                public void onResponse(Call<Customer> call, Response<Customer> response) {
//                    progressDialog.dismiss();
//                    if (response.body().getResponse().equals("ok")) {
//                        Toast.makeText(getContext(), "Registration Success...", Toast.LENGTH_LONG).show();
//                    } else if (response.body().getResponse().equals("exist")) {
//                        Toast.makeText(getContext(), "User already exist..", Toast.LENGTH_LONG).show();
//                    } else if (response.body().getResponse().equals("error")) {
//                        Toast.makeText(getContext(), "Something went wrong..", Toast.LENGTH_LONG).show();
//
//
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Customer> call, Throwable t) {
//                    Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
//                }
//            });



            String path = imageToString();
           // String title = editTextSaleChangePassword.getText().toString();
            String user_name = userName.getText().toString();
            String customer_name = customerName.getText().toString();
            String shop_name = shopName.getText().toString();
            String phone = phoneNumber.getText().toString();
            String customer_address =Address.getText().toString();
            String Dob = DOB.getText().toString();
            String NRC = Nrc.getText().toString();
            String town = Township.getText().toString();
            Call<ImageClass> call = MainActivity.apiInterface.uploadImage(customer_name,uniqueUserName,shop_name,phone,customer_address,Dob,NRC,town,path);
            call.enqueue(new Callback<ImageClass>() {
                @Override
                public void onResponse(Call<ImageClass> call, Response<ImageClass> response) {
                    progressDialog.dismiss();
                ImageClass imageClass = response.body();

              Toast.makeText(getContext(),imageClass.getResponse(), Toast.LENGTH_SHORT).show();
//                    String result = response.body().getResponse();
//                    Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();

                }


                @Override
                public void onFailure(Call<ImageClass> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "registation fail", Toast.LENGTH_SHORT).show();
                }
            });



//            userName.setText("");
//            customerName.setText("");
//            shopName.setText("");
//            phoneNumber.setText("");
//            Address.setText("");
//            DOB.setText("");
//            Nrc.setText("");
//            Township.setText("");



        }

    }


    private void uploadImage(){
        Log.e("work","upload method is work");
        String path = imageToString();
       // String title = editTextSaleChangePassword.getText().toString();
        String username = userName.getText().toString();
        String customername = customerName.getText().toString();
        String shopname = shopName.getText().toString();
        String phone = phoneNumber.getText().toString();
        String customeraddress =Address.getText().toString();
        String dob = DOB.getText().toString();
        String nrc = Nrc.getText().toString();
        String town = Township.getText().toString();
        Call<ImageClass> call = MainActivity.apiInterface.uploadImage(username,customername,shopname,phone,customeraddress,dob,nrc,town,path);
        call.enqueue(new Callback<ImageClass>() {
            @Override
            public void onResponse(Call<ImageClass> call, Response<ImageClass> response) {
//                ImageClass imageClass = response.body();n
//
//               Toast.makeText(getContext(), "Server Response:" + imageClass.getResponse(), Toast.LENGTH_SHORT).show();
                String result = response.body().getResponse();
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onFailure(Call<ImageClass> call, Throwable t) {

            }
        });
    }


    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        DOB.setText(sdf.format(myCalendar.getTime()));
    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }


}
