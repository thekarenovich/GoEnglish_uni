package Activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.karenovich.goenglish.R;
import com.karenovich.goenglish.databinding.FragmentSignUpBinding;

import Data.DBProfile;
import ViewModel.ProfileViewModel;
import ViewModel.TutorialsViewModel;

public class SignUpFragment extends Fragment {

    FragmentSignUpBinding binding;
    ProfileViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.insertProfile(new DBProfile(0,
                        binding.userNameSignUp.getText().toString(),
                        binding.emailSignUp.getText().toString(),
                        binding.pswSignUp.getText().toString(),
                        binding.nameSignUp.getText().toString(), "user"));
                Toast.makeText(getContext(), "Профиль создан!", Toast.LENGTH_SHORT).show();
                replaceFragment(new SignInFragment());
            }
        });

        binding.yesAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SignInFragment());
            }
        });

        binding.signUpBtnVk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new VkFragmentUp());
            }
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.authFrame, fragment);
        ft.commit();
    }
}