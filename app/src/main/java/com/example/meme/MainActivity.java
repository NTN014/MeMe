    package com.example.meme;

    import static com.google.android.material.internal.ContextUtils.getActivity;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentManager;
    import androidx.fragment.app.FragmentTransaction;

    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.view.MenuItem;
    import android.widget.ViewFlipper;

    import com.google.android.material.bottomnavigation.BottomNavigationView;
    import com.google.android.material.navigation.NavigationBarView;

    import java.util.Calendar;

    public class MainActivity extends AppCompatActivity {

        BottomNavigationView bottomNavigationView;
        HomeFragment homeFragment = new HomeFragment();
        GiftFragment giftFragment = new GiftFragment();
        ProfileFragment profileFragment = new ProfileFragment();
        SettingFragment settingFragment = new SettingFragment();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            bottomNavigationView = findViewById(R.id.bottomNav);

            replaceFragment(homeFragment);

            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.home) {
                        replaceFragment(homeFragment);
                        return true;
                    } else if (itemId == R.id.gift) {
                        replaceFragment(giftFragment);
                        return true;
                    } else if (itemId == R.id.profile) {
                        replaceFragment(profileFragment);
                        return true;
                    } else if (itemId == R.id.setting) {
                        replaceFragment(settingFragment);
                        return true;
                    }
                    return false;
                }
            });
        }

        public void replaceFragment(Fragment fragment) {
            FragmentManager fragmentManager =getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();
        }


    }