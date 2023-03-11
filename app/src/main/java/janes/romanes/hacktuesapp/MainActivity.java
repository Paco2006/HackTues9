package janes.romanes.hacktuesapp;

import static androidx.navigation.Navigation.findNavController;
import static androidx.navigation.ui.NavigationUI.setupWithNavController;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawerLayout drawerLayout= findViewById(R.id.drawerLayout);

        findViewById(R.id.ImageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
        navController = findNavController(this, R.id.my_nav_host_fragment);
        setupWithNavController(navigationView, navController);

        TextView textTitle = findViewById(R.id.textTitle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {

                    case R.id.menuCheck_Site_Safety:
                        navController.navigate(R.id.toCheckSiteSafety);
                        drawerLayout.close();

                        break;

                    case R.id.menuPassword_Saver_Generator:
                        navController.navigate(R.id.toPassSavGen);
                        drawerLayout.close();
                        break;

                    case R.id.menuAbout_Cyber_Sec:
                        navController.navigate(R.id.toCyberSec);
                        drawerLayout.close();
                        break;

                    case R.id.menuWhy_Use_Our_App:
                        navController.navigate(R.id.toWhyUseOurApp);
                        drawerLayout.close();
                        break;

                    case R.id.menuAbout_Us:
                        navController.navigate(R.id.toAbouUs);
                        drawerLayout.close();
                        break;

                    case R.id.menuSettings:
                        navController.navigate(R.id.toSettings);
                        drawerLayout.close();
                        break;

                    case R.id.menuMenu:
                        navController.navigate(R.id.toMainPage);
                        drawerLayout.close();
                        break;

                    default:

                }

                return false;
            }
        });
    }

}
