package Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.karenovich.goenglish.R;
import com.karenovich.goenglish.databinding.ActivityMainBinding;

import Model.Tutorials;
import ViewModel.TutorialsViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    TutorialsViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        model = new ViewModelProvider(this).get(TutorialsViewModel.class);

        replaceFragment(new TutorialsFragment());
        binding.bottomNavMenu.setSelectedItemId(R.id.tutorial);
        binding.bottomNavMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tutorial:
                        replaceFragment(new TutorialsFragment());
                        break;
                    case R.id.profile:
                        replaceFragment(new ProfileFragment());
                        break;
                }
                return false;
            }
        });

//        model.insert(new Tutorials(0, "verbs", "thekarenovich"));
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.replace(R.id.mainFrame, fragment);
        ft.commit();
    }
}