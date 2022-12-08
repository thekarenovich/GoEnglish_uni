package Activities;

import android.content.Intent;
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
import com.karenovich.goenglish.databinding.FragmentSignInBinding;

import Data.DBProfile;
import ViewModel.ProfileViewModel;

public class SignInFragment extends Fragment {

    FragmentSignInBinding binding;
    ProfileViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        View view  = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBProfile profile;

                if(model.getUsername(binding.userNameSignIn.getText().toString()) != null){
                    String user = binding.userNameSignIn.getText().toString();
                    profile = model.getUsername(user);

                    if (binding.pswSignIn.getText().toString().equals(profile.getPassword())){
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        MainActivity.user_id = profile.getId();
                        startActivity(intent);
                    }
                }else {
                    Toast.makeText(getContext(), "Такого юзера нет", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.noAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SignUpFragment());
            }
        });

        binding.signInBtnVk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new VkFragment());
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