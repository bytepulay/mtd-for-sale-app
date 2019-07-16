package asia.nainglintun.myintthitar.Activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import asia.nainglintun.myintthitar.activities.Fragments.CustomerRankFragment;
import asia.nainglintun.myintthitar.activities.Fragments.HistoryCustomerFragment;
import asia.nainglintun.myintthitar.activities.Fragments.NotificationCustomerFragment;
import asia.nainglintun.myintthitar.activities.Fragments.SettingCustomerFragment;
import asia.nainglintun.myintthitar.R;

public class CustomerActivity extends AppCompatActivity {
    private TextView mTextMessage;
    public static FragmentManager fragmentManager;
    private ActionBar actionBar;
    BottomNavigationView navView;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager.beginTransaction().replace(R.id.frame_layout_customer,new CustomerRankFragment()).commit();
                    actionBar.setTitle("Rank");
                    return true;
                case R.id.navigation_dashboard:
                    fragmentManager.beginTransaction().replace(R.id.frame_layout_customer,new HistoryCustomerFragment()).commit();
                    actionBar.setTitle("History");

                    return true;
                case R.id.navigation_notifications:
                    fragmentManager.beginTransaction().replace(R.id.frame_layout_customer,new NotificationCustomerFragment()).commit();
                    actionBar.setTitle("Notification");

                    return true;
                case R.id.navigation_setting:
                    fragmentManager.beginTransaction().replace(R.id.frame_layout_customer,new SettingCustomerFragment()).commit();

                    actionBar.setTitle("Setting");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        actionBar = getSupportActionBar();
        actionBar.setTitle( "Rank" );
        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.frame_layout_customer)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            fragmentManager.beginTransaction().add(R.id.frame_layout_customer,new CustomerRankFragment()).commit();
        }
        navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
       //onBackPressed();
    }

    @Override
    public void onBackPressed() {
        BottomNavigationView mBottomNavigationView = findViewById(R.id.nav_view);
        if (mBottomNavigationView.getSelectedItemId() == R.id.navigation_home)
        {
            super.onBackPressed();
            finish();
        }
        else
        {
            mBottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }
    }
}
