package com.example.pythoncourse;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pythoncourse.databinding.IntroductionBinding;

public class Introduction extends AppCompatActivity {

    private IntroductionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = IntroductionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.introductionToBasics.setOnClickListener(view -> navigateToBasicsFragment());
    }

    private void navigateToBasicsFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BasicsFragment basicsFragment = new BasicsFragment();

        fragmentTransaction.replace(R.id.fragment_basics, basicsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        binding.textView.setVisibility(View.GONE);
        binding.textView2.setVisibility(View.GONE);
        binding.introductionToBasics.setVisibility(View.GONE);
    }


}// ---------------------------------- End of Introduction ----------------------------------