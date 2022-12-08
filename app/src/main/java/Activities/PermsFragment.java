package Activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karenovich.goenglish.R;
import com.karenovich.goenglish.databinding.FragmentPermsBinding;

import java.util.List;

import Adapters.AdminAdapter;
import Data.DBProfile;
import ViewModel.ProfileViewModel;
import ViewModel.TutorialsViewModel;

public class PermsFragment extends Fragment {

    FragmentPermsBinding binding;
    ProfileViewModel model;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPermsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding.recyclerAdmin.setLayoutManager(new LinearLayoutManager(getContext()));
        AdminAdapter adminAdapter = new AdminAdapter();
        binding.recyclerAdmin.setAdapter(adminAdapter);

        model.getAllProfile().observe(getViewLifecycleOwner(), new Observer<List<DBProfile>>() {
            @Override
            public void onChanged(List<DBProfile> dbProfiles) {
                System.out.println(dbProfiles);
                adminAdapter.setProfileList(dbProfiles);
            }
        });

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                long userId = adminAdapter.getUser_id();
                String username = adminAdapter.getUsername();
                String name = adminAdapter.getName();
                String email = adminAdapter.getEmail();
                String password = adminAdapter.getPassword();
                String role = adminAdapter.getRole();
                model.updateProfile(new DBProfile(userId,username,email,password,name,role));
            }
        });

    }
}