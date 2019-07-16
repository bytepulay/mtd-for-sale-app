package asia.nainglintun.myintthitar.activities.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.activities.Fragments.VoucherEdit;
import asia.nainglintun.myintthitar.activities.Fragments.VoucherOrderEdit;

public class VoucherOrderDetailActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
   // private ActionBar actionBar;
    private Toolbar toolbar;

    private TextView textViewDate,textViewType,textViewAmount,textViewCouponCode,textViewShopName,textViewPhoneNumber,
            textViewDateOfBirth,textViewUserName,textViewPassword,textViewVoucher,textViewQualtity,textViewPointEight,
            textViewGram,textViewKyate,textViewPal,textViewYae,textViewCustomerUserName;
    private Button btnVoucherEdit;
    private LinearLayout linearLayout,linearLayoutEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voucher_order_detail);
        fragmentManager = getSupportFragmentManager();
        //actionBar = getSupportActionBar();

        toolbar = findViewById(R.id.toolBar);
        toolbar.setTitle("Order Voucher Detail");

        final VoucherOrderEdit voucherOrderEdit = new VoucherOrderEdit();

        textViewDate = findViewById(R.id.txtDate);
        textViewType = findViewById(R.id.txtType);
        textViewAmount = findViewById(R.id.txtAmount);
        textViewCouponCode = findViewById(R.id.txtCuponCode);
        textViewShopName = findViewById(R.id.txtShopName);
        textViewPhoneNumber = findViewById(R.id.txtPhoneNumber);
        textViewDateOfBirth = findViewById(R.id.txtDateOfBirth);
        textViewUserName = findViewById(R.id.txtUserName);
        textViewPassword = findViewById(R.id.txtPassword);
        textViewVoucher = findViewById(R.id.txtVoucherNumber);
        textViewQualtity = findViewById(R.id.txtQualitity);
        textViewPointEight = findViewById(R.id.txtPointEight);
        textViewGram = findViewById(R.id.txtGram);
        textViewKyate = findViewById(R.id.txtKyat);
        textViewPal = findViewById(R.id.txtPal);
        textViewYae = findViewById(R.id.txtYae);
        btnVoucherEdit = findViewById(R.id.voucherEdit);
        textViewCustomerUserName = findViewById(R.id.txtCustomerUserName);
        linearLayout = findViewById(R.id.voucherDetail);
       // linearLayoutEdit = findViewById(R.id.editVoucher);





        Bundle bb = getIntent().getExtras();
        final String textDate = bb.getString("Datte");
        String textType = bb.getString("Type");
        final String textAmount = bb.getString("Amount");
        final String textCuponCode = bb.getString("CuponCode");
        final String textShopName = bb.getString("ShopName");
        final String textPhoneNumber = bb.getString("PhoneNumber");
        final String textDateOfBirth = bb.getString("DateOfBirth");
        final String textUserName = bb.getString("UserName");
        final String textPassword = bb.getString("Password");
        final String textVoucher = bb.getString("Voucher");
        String textQualitity = bb.getString("Qualtity");
        final String textPointEight = bb.getString("PointEight");
        final String textGram = bb.getString("Gram");
        final String textKyat = bb.getString("Kyat");
        final String textPal = bb.getString("Pal");
        final String textYae = bb.getString("Yae");
        final String textCustomerUserName =bb.getString("CustomerUserName");

        textViewDate.setText("Date :" +textDate);
        textViewType.setText("Type :" +textType);
        textViewAmount.setText("Amount :" +textAmount);
        textViewCouponCode.setText("CuponCode :" + textCuponCode);
        textViewShopName.setText("ShopName :" + textShopName);
        textViewPhoneNumber.setText("Phone Number :" + textPhoneNumber);
        textViewDateOfBirth.setText("Date Of Birth :" + textDateOfBirth);
        textViewUserName.setText("User Name :" +textUserName);
        textViewPassword.setText("Password :" + textPassword);
        textViewVoucher.setText("Voucher Number :" + textVoucher);
        textViewQualtity.setText("Qualtity :" + textVoucher);
        textViewPointEight.setText("Point Eight :" + textPointEight);
        textViewGram.setText("Gram : " + textGram);
        textViewKyate.setText("Kyat :" + textKyat);
        textViewPal.setText("Pal :" + textPal);
        textViewYae.setText("Yae :" + textYae);
        textViewCustomerUserName.setText("Customer User Name : " + textCustomerUserName );




        btnVoucherEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setTitle( "Order Voucher Edit" );
               linearLayout.setVisibility(View.GONE);
               //linearLayoutEdit.setVisibility(View.VISIBLE);
                Bundle b = new Bundle();
                b.putString("Voucher",textVoucher);
                b.putString("Date",textDate);
                b.putString("Qualtity",textAmount);
                b.putString("PointEight",textPointEight);
                b.putString("Kyat",textKyat);
                b.putString("Pal",textPal);
                b.putString("Yae",textYae);
                b.putString("Gram",textGram);
                b.putString("CuponCode",textCuponCode);
                b.putString("ShopName",textShopName);
                b.putString("CustomerName",textUserName);
                b.putString("PhoneNumber",textPhoneNumber);
                b.putString("DateOfBirth",textDateOfBirth);
                b.putString("CustomerUserName",textCustomerUserName);
                b.putString("Password",textPassword);

               voucherOrderEdit.setArguments(b);

                fragmentManager.beginTransaction().replace(R.id.frame_layout_sales_edit,voucherOrderEdit).addToBackStack(null).commit();



            }
        });
       // Toast.makeText(getApplicationContext(),textDate + textType + textAmount,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        toolbar.setTitle( "Order Voucher Detail" );
        linearLayout.setVisibility(View.VISIBLE);
        //linearLayoutEdit.setVisibility(View.GONE);
    }
}
