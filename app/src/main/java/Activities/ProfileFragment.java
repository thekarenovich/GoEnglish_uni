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

import java.util.List;

import Data.ProfileDB;
import ViewModel.TutorialsViewModel;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    TutorialsViewModel model;
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
        model = new ViewModelProvider(this).get(TutorialsViewModel.class);

        model.getImage().observe(getViewLifecycleOwner(), new Observer<ProfileDB>() {
            @Override
            public void onChanged(ProfileDB profileDB) {
                binding.avaImg.setImageURI(Uri.parse(profileDB.getImage()));
            }
        });

        ActivityResultLauncher<String[]> getContent = getActivity().getActivityResultRegistry().register("key", new ActivityResultContracts.OpenDocument(), result -> {
            getActivity().getContentResolver().takePersistableUriPermission(result, Intent.FLAG_GRANT_READ_URI_PERMISSION);

            binding.avaImg.setImageURI(result);
            uriImage = result;
        });

        binding.avaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContent.launch(new String[]{"image/*"});
            }
        });

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.insertProfile(new ProfileDB(0, binding.inputUser.getText().toString(),
                        binding.inputEmail.getText().toString(), binding.inputPass.getText().toString(), uriImage.toString()));
            }
        });
    }
}