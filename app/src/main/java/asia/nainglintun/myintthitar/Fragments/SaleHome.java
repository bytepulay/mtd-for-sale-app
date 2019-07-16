package asia.nainglintun.myintthitar.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import asia.nainglintun.myintthitar.Activities.SalesActivity;
import asia.nainglintun.myintthitar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleHome extends Fragment implements View.OnClickListener {

private Button btnCreateInvoice,btnCreateOrder,btnAddCustomer,btnSaleIncoice;
   // private ActionBar actionBar;

    private Toolbar toolbar;

    public SaleHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.sale_home, container, false);
        btnCreateInvoice = view.findViewById(R.id.btnCreateInvoice);
        btnCreateOrder = view.findViewById(R.id.btnCreateOrder);
        btnAddCustomer = view.findViewById(R.id.btnAddCustomer);
        btnSaleIncoice = view.findViewById(R.id.btnSaleInvoice);

        toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Home");

//        btnCreateInvoice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new SaleInvoiceCreate()).commit();
//            }
//        });
       btnCreateInvoice.setOnClickListener(this);
        btnCreateOrder.setOnClickListener(this);
        btnAddCustomer.setOnClickListener(this);
        btnSaleIncoice.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()){
           case R.id.btnCreateInvoice:
               SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new asia.nainglintun.myintthitar.Fragments.SaleInvoiceCreate()).commit();
               toolbar.setTitle("Create Invoice");
                break;

            case R.id.btnCreateOrder:
                SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new asia.nainglintun.myintthitar.activities.Fragments.Order()).commit();
               toolbar.setTitle("Create Order");
                break;

            case R.id.btnAddCustomer:
                SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new SaleAddCustomer()).commit();
               toolbar.setTitle("Add Customer");
                break;

            case R.id.btnSaleInvoice:
                SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new asia.nainglintun.myintthitar.Fragments.SaleHistory()).commit();
                toolbar.setTitle("Sale History");
                break;


        }
    }
}
