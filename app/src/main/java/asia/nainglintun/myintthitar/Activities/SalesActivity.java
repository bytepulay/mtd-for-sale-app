package asia.nainglintun.myintthitar.Activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import asia.nainglintun.myintthitar.Activities.Fragments.SaleCustomerFragment;
import asia.nainglintun.myintthitar.Activities.Fragments.SaleHistoryFragment;
import asia.nainglintun.myintthitar.Activities.Fragments.SaleNotificationFragment;
import asia.nainglintun.myintthitar.Activities.Fragments.SaleSettingFragment;
import asia.nainglintun.myintthitar.Activities.Fragments.SalesFragment;
import asia.nainglintun.myintthitar.R;

public class SalesActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    private ActionBar actionBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new SaleCustomerFragment()).addToBackStack(null).commit();
                    actionBar.setTitle("Voucher");
                    return true;
                case R.id.navigation_dashboard:
                    fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new SaleHistoryFragment()).addToBackStack(null).commit();
                    actionBar.setTitle("History");
                    return true;
                case R.id.navigation_notifications:
                  fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new SaleNotificationFragment()).addToBackStack(null).commit();
                  actionBar.setTitle("Notification");
                    return true;

                case R.id.navigation_setting:
                    fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new SaleSettingFragment()).addToBackStack(null).commit();
                    actionBar.setTitle("Setting");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        fragmentManager = getSupportFragmentManager();
        actionBar = getSupportActionBar();
        actionBar.setTitle( "Voucher" );
        actionBar.setDisplayHomeAsUpEnabled(true);
        if(findViewById(R.id.frame_layout_sales)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            fragmentManager.beginTransaction().add(R.id.frame_layout_sales,new SaleCustomerFragment()).commit();
        }
        BottomNavigationView navView = findViewById(R.id.nav_view);
       // mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
