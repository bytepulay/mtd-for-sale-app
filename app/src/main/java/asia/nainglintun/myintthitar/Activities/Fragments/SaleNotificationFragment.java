package asia.nainglintun.myintthitar.Activities.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asia.nainglintun.myintthitar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleNotificationFragment extends Fragment {


    public SaleNotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale_notification, container, false);
        return view;
    }

}
