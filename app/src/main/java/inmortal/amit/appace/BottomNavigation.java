package inmortal.amit.appace;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BottomNavigation extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame,new RadioFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectFragment=null;

            switch (item.getItemId())
            {
                case R.id.nav_radio:
                    selectFragment=new RadioFragment();
                    break;

                case R.id.nav_tv:
                    selectFragment=new TvFragment();
                  break;
                case R.id.nav_settings:
                    selectFragment=new SettingsFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                    selectFragment).commit();

            return true;
        }
    };

}
