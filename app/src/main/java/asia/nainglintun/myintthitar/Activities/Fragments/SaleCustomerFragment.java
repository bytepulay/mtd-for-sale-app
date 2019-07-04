package asia.nainglintun.myintthitar.Activities.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import asia.nainglintun.myintthitar.Activities.SalesActivity;
import asia.nainglintun.myintthitar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleCustomerFragment extends Fragment {

private Button btnCreateNewCustomer;
    public SaleCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale_customer, container, false);
        btnCreateNewCustomer = view.findViewById(R.id.btnCreateNewCustomer);
        btnCreateNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new SalesReportFragment()).commit();
            }
        });
        return view;
    }

}
