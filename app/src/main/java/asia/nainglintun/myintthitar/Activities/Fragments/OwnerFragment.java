package asia.nainglintun.myintthitar.Activities.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import asia.nainglintun.myintthitar.Activities.OwnerActivity;
import asia.nainglintun.myintthitar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OwnerFragment extends Fragment {

    private Button btnCreateCustomer,btnCreateSalesPerson;
    OwnerActivity activitySpecification = new OwnerActivity();

    public OwnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_owner, container, false);

        btnCreateCustomer = view.findViewById(R.id.createCustomer);
        btnCreateSalesPerson = view.findViewById(R.id.createSales);

        btnCreateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OwnerActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout,new CustomerFragment()).addToBackStack(null).commit();
            }
        });


        btnCreateSalesPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            OwnerActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout,new SalesFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }



}
