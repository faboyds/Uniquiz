package phoenix.uniquizandroid.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import phoenix.uniquizandroid.R;

public class MainActivity extends AppCompatActivity {

    ExploreFragment explore = new ExploreFragment();
    SearchFragment search = new SearchFragment();
    ProfileFragment profile = new ProfileFragment();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.app.FragmentManager manager = getFragmentManager();

            switch (item.getItemId()) {
                case R.id.navigation_explore:
                    manager.beginTransaction().replace(R.id.content, explore).commit();
                    return true;
                case R.id.navigation_search:
                    manager.beginTransaction().replace(R.id.content, search).commit();
                    return true;

                case R.id.navigation_profile:
                    manager.beginTransaction().replace(R.id.content, profile).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        android.app.FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.content, explore).commit();
    }

}
