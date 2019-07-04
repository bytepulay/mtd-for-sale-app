package asia.nainglintun.myintthitar.Activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import asia.nainglintun.myintthitar.Activities.Fragments.CustomerFragment;
import asia.nainglintun.myintthitar.Activities.Fragments.OwnerFragment;
import asia.nainglintun.myintthitar.Activities.Fragments.SalesFragment;
import asia.nainglintun.myintthitar.R;

public class OwnerActivity extends AppCompatActivity {
    private TextView mTextMessage;
    public static FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager.beginTransaction().replace(R.id.frame_layout,new OwnerFragment()).addToBackStack(null).commit();

                    return true;
                case R.id.navigation_dashboard:
                    fragmentManager.beginTransaction().replace(R.id.frame_layout,new CustomerFragment()).addToBackStack(null).commit();

                    return true;
                case R.id.navigation_notifications:
                    fragmentManager.beginTransaction().replace(R.id.frame_layout,new SalesFragment()).addToBackStack(null).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.frame_layout)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            fragmentManager.beginTransaction().add(R.id.frame_layout,new OwnerFragment()).commit();
        }
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


//    public void loadFragment(Fragment fragment) {
//        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
//        tx.replace( R.id.frame_layout, fragment );
//        //tx.addToBackStack( null );
//        tx.commitNow();
//
//
//    }

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
