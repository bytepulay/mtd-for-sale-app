package asia.nainglintun.myintthitar.activities.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import asia.nainglintun.myintthitar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VoucherOrderEdit extends Fragment {

private EditText voucherNumber,saleDate,qualtity,pointEight,kyat,pal,yae,gram,cuponCode,shopName,customerName,phoneNumber,
    dateOfBirth,customerUserName,customerPassword;
private Button btnEditSave;
private View view;

//private Toolbar toolbar;
    public VoucherOrderEdit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.voucher_order_edit, container, false);

//        toolbar = view.findViewById(R.id.toolBar);
//        toolbar.setTitle(" Order Voucher Edit");

        Bundle bbdd = getArguments();

        String testName =bbdd.getString("Voucher");
        String testDate =bbdd.getString("Date");
        String testQualtity = bbdd.getString("Qualtity");
        String testPointEight = bbdd.getString("PointEight");
        String testKyat = bbdd.getString("Kyat");
        String testPal =bbdd.getString("Pal");
        String testYae = bbdd.getString("Yae");
        String testGram = bbdd.getString("Gram");
        String testCuponCode =bbdd.getString("CuponCode");
        String testShopName = bbdd.getString("ShopName");
        String testCustomerName = bbdd.getString("CustomerName");
        String testPhoneNumber = bbdd.getString("PhoneNumber");
        String testDateOfBirth = bbdd.getString("DateOfBirth");
        String testCustomerUsernName = bbdd.getString("CustomerUserName");
        String testPassword = bbdd.getString("Password");

      Toast.makeText(getContext(),testName,Toast.LENGTH_LONG).show();



      voucherNumber = view.findViewById(R.id.voucherNumber);
      saleDate = view.findViewById(R.id.saleDate);
      qualtity =view.findViewById(R.id.custBuyNumber);
      pointEight = view.findViewById(R.id.custDiscountPoint);
      kyat = view.findViewById(R.id.kyat);
      pal = view.findViewById(R.id.pel);
      yae = view.findViewById(R.id.yae);
      gram = view.findViewById(R.id.gram);
      cuponCode = view.findViewById(R.id.cupon);
      shopName = view.findViewById(R.id.shopName);
      customerName = view .findViewById(R.id.custName);
      phoneNumber = view.findViewById(R.id.custPhone);
      dateOfBirth = view.findViewById(R.id.custDOB);
      customerUserName = view.findViewById(R.id.custUserName);
      customerPassword = view.findViewById(R.id.custPassword);
      btnEditSave = view.findViewById(R.id.btnEditSave);


      voucherNumber.setText(testName);
      saleDate.setText(testDate);
      qualtity.setText(testQualtity);
      pointEight.setText(testPointEight);
      kyat.setText(testKyat);
      pal.setText(testPal);
      yae.setText(testYae);
      gram.setText(testGram);
      cuponCode.setText(testCuponCode);
      shopName.setText(testShopName);
      customerName.setText(testCustomerName);
      phoneNumber.setText(testPhoneNumber);
      dateOfBirth.setText(testDateOfBirth);
      customerUserName.setText(testCustomerUsernName);
      customerPassword.setText(testPassword);

      btnEditSave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(getContext(),"To do Save Operation in VoucherOrderEdit.java",Toast.LENGTH_LONG).show();
          }
      });


        return view;
    }

}
