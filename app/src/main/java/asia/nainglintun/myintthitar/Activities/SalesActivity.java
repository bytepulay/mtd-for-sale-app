package asia.nainglintun.myintthitar.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import asia.nainglintun.myintthitar.Fragments.FragmentCard;
import asia.nainglintun.myintthitar.Fragments.OrderListFragment;
import asia.nainglintun.myintthitar.Fragments.SaleListFragment;
import asia.nainglintun.myintthitar.Fragments.SaleSettingFragment;
import asia.nainglintun.myintthitar.R;

;

public class SalesActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
   // private ActionBar actionBar;
    private Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new FragmentCard()).addToBackStack(null).commit();
                   toolbar.setTitle("Home");
                    return true;
                case R.id.navigation_dashboard:
                    fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new SaleListFragment()).addToBackStack(null).commit();
                    toolbar.setTitle("Sale History");
                    return true;
                case R.id.navigation_notifications:
                  fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new OrderListFragment()).addToBackStack(null).commit();
                  toolbar.setTitle(" Order history ");
                    return true;

                case R.id.navigation_setting:
                    fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new SaleSettingFragment()).addToBackStack(null).commit();
                   toolbar.setTitle("Setting");
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
//        actionBar = getSupportActionBar();
//        actionBar.setTitle( "Home" );

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");

        Toast.makeText(this, MainActivity.prefConfig.readName(), Toast.LENGTH_SHORT).show();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        BottomNavigationView navView = findViewById(R.id.nav_view);
       // mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        if(findViewById(R.id.frame_layout_sales)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            fragmentManager.beginTransaction().add(R.id.frame_layout_sales,new FragmentCard()).commit();
        }
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



        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new FragmentCard()).addToBackStack(null).commit();
            //finish();
        }else {
            fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new FragmentCard()).addToBackStack(null).commit();

        }

        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


}
