package Activities;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.karenovich.goenglish.databinding.FragmentTutorialsBinding;


public class TutorialsFragment extends Fragment {

    FragmentTutorialsBinding binding;

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
}