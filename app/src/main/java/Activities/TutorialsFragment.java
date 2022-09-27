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
import com.karenovich.goenglish.databinding.FragmentTutorialsBinding;

import java.util.List;

import Adapters.TutorialsAdapter;
import Model.Tutorials;
import ViewModel.TutorialsViewModel;


public class TutorialsFragment extends Fragment {

    FragmentTutorialsBinding binding;
    TutorialsViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTutorialsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = new ViewModelProvider(this).get(TutorialsViewModel.class);
        binding.tutorialRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.tutorialRecycler.setHasFixedSize(true);
        TutorialsAdapter tutorialsAdapter = new TutorialsAdapter();
        binding.tutorialRecycler.setAdapter(tutorialsAdapter);

        model.getAllTutorials().observe(getViewLifecycleOwner(), new Observer<List<Tutorials>>() {
            @Override
            public void onChanged(List<Tutorials> tutorials) {
                tutorialsAdapter.setTutorials(tutorials);
            }
        });

    }


}



