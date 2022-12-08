package Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karenovich.goenglish.R;
import com.karenovich.goenglish.databinding.FragmentProfileBinding;

import Data.DBProfile;
import ViewModel.ProfileViewModel;
import ViewModel.TutorialsViewModel;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    ProfileViewModel model;
    Uri uriImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = new ViewModelProvider(this).get(ProfileViewModel.class);

        model.getProfile(MainActivity.user_id).observe(getViewLifecycleOwner(), new Observer<DBProfile>() {
            @Override
            public void onChanged(DBProfile profile) {
                binding.nameProfile.setText(profile.getName());
                binding.usernameProfile.setText(profile.getUsername());
                binding.emailProfile.setText(profile.getEmail());
                binding.permProfile.setText(profile.getPermission());
            }
        });

        binding.signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AuthActivity.class);
                MainActivity.user_id = Long.parseLong(null);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}