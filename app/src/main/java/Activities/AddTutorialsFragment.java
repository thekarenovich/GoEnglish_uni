package Activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karenovich.goenglish.R;
import com.karenovich.goenglish.databinding.FragmentAddTutorialsBinding;

import Data.Tutorials;
import ViewModel.ProfileViewModel;
import ViewModel.TutorialsViewModel;

public class AddTutorialsFragment extends Fragment {

    FragmentAddTutorialsBinding binding;
    TutorialsViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddTutorialsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = new ViewModelProvider(this).get(TutorialsViewModel.class);

        binding.addBtnTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String theme = binding.themeET.getText().toString();
                String material = binding.textET.getText().toString();

                model.insert(new Tutorials(0, theme, material));
            }
        });
    }
}