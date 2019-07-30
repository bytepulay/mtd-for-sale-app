package asia.nainglintun.myintthitar.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryCustomerFragment extends Fragment {

private Toolbar toolbar;
private TextView purchaseDate,cuponCode,purchaseItem,gram;
    public HistoryCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_customer, container, false);
        Toast.makeText(getContext(), MainActivity.prefConfig.readName(), Toast.LENGTH_SHORT).show();
        toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Purchased History");

        purchaseDate = view.findViewById(R.id.purchaseDate);
        cuponCode = view.findViewById(R.id.cuponCode);
        purchaseItem = view.findViewById(R.id.purchaseItems);
        gram = view.findViewById(R.id.purchaseGram);
        return view;
    }

}
