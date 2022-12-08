package Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.karenovich.goenglish.R;
import com.karenovich.goenglish.databinding.ActivityMainBinding;

import Data.DBProfile;
import Data.Tutorials;
import ViewModel.ProfileViewModel;
import ViewModel.TutorialsViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    TutorialsViewModel model;

    ProfileViewModel modelProfile;
    public static long user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        model = new ViewModelProvider(this).get(TutorialsViewModel.class);
        modelProfile = new ViewModelProvider(this).get(ProfileViewModel.class);


        modelProfile.getProfile(user_id).observeForever(new Observer<DBProfile>() {
            @Override
            public void onChanged(DBProfile profile) {
                if(profile.getPermission().equals("user")){
                    binding.bottomNavMenu.setVisibility(View.VISIBLE);
                    binding.bottomNavMenuAdmin.setVisibility(View.GONE);
                    binding.bottomNavMenuModer.setVisibility(View.GONE);
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
                                case R.id.note:
                                    replaceFragment(new NoteFragment());
                                    break;
                                case R.id.phonetic:
                                    replaceFragment(new PhoneticFragment());
                                    break;
                            }
                            return true;
                        }
                    });
                }else if (profile.getPermission().equals("admin")){
                    binding.bottomNavMenu.setVisibility(View.GONE);
                    binding.bottomNavMenuAdmin.setVisibility(View.VISIBLE);
                    binding.bottomNavMenuModer.setVisibility(View.GONE);
                    binding.bottomNavMenuAdmin.setSelectedItemId(R.id.tutorial);
                    binding.bottomNavMenuAdmin.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.tutorial:
                                    replaceFragment(new TutorialsFragment());
                                    break;
                                case R.id.profile:
                                    replaceFragment(new ProfileFragment());
                                    break;
                                case R.id.note:
                                    replaceFragment(new NoteFragment());
                                    break;
                                case R.id.phonetic:
                                    replaceFragment(new PhoneticFragment());
                                    break;
                                case R.id.perms:
                                    replaceFragment(new PermsFragment());
                                    break;
                            }
                            return true;
                        }
                    });
                }else if(profile.getPermission().equals("moder")){
                    binding.bottomNavMenu.setVisibility(View.GONE);
                    binding.bottomNavMenuAdmin.setVisibility(View.GONE);
                    binding.bottomNavMenuModer.setVisibility(View.VISIBLE);
                    binding.bottomNavMenuModer.setSelectedItemId(R.id.tutorial);
                    binding.bottomNavMenuModer.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.tutorial:
                                    replaceFragment(new TutorialsFragment());
                                    break;
                                case R.id.profile:
                                    replaceFragment(new ProfileFragment());
                                    break;
                                case R.id.note:
                                    replaceFragment(new NoteFragment());
                                    break;
                                case R.id.phonetic:
                                    replaceFragment(new PhoneticFragment());
                                    break;
                                case R.id.addtutor:
                                    replaceFragment(new AddTutorialsFragment());
                                    break;
                            }
                            return true;
                        }
                    });
                }
            }
        });

        replaceFragment(new TutorialsFragment());


        //model.insert(new Tutorials(0, "verbs", "thekarenovich"));
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.replace(R.id.mainFrame, fragment);
        ft.commit();
    }
}