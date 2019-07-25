package asia.nainglintun.myintthitar.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asia.nainglintun.myintthitar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Order extends Fragment {

    public Order() {
        // Required empty public constructor
    }
private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.sale_order, container, false);

        toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Create Order Invoice");


        return view;
    }

}
