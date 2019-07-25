package asia.nainglintun.myintthitar.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationCustomerFragment extends Fragment {


    public NotificationCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_customer, container, false);
        Toast.makeText(getContext(), MainActivity.prefConfig.readName(), Toast.LENGTH_SHORT).show();

        return view;
    }

}
