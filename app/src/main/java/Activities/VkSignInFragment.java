package Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.karenovich.goenglish.R;
import com.karenovich.goenglish.databinding.FragmentVkSignInBinding;

import Data.ConfigUser;
import Data.DBProfile;
import ViewModel.ProfileViewModel;
import ViewModel.VkViewModel;

public class VkSignInFragment extends Fragment {

    FragmentVkSignInBinding binding;
    VkViewModel model;
    ProfileViewModel pModel;

    DBProfile profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVkSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = new ViewModelProvider(this).get(VkViewModel.class);
        pModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        model.getUsernameVk(ConfigUser.getInstance().access_token, ConfigUser.getInstance().user_id).observe(getViewLifecycleOwner(), new Observer<DBProfile>() {
            @Override
            public void onChanged(DBProfile dbProfile) {
                profile = pModel.getUsername(dbProfile.getUsername());

                if (profile == null){
                    Toast.makeText(getContext(), "Такого юзера нет", Toast.LENGTH_SHORT).show();
                    replaceFragment(new SignUpFragment());
                }else {
                    long id = profile.getId();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    MainActivity.user_id = id;
                    startActivity(intent);
                }
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