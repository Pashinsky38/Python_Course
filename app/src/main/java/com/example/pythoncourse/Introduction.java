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
    private TextToSpeechHelper tts; // Instance of TextToSpeechHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = IntroductionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set click listener for introductionToBasics button to navigate to BasicsFragment
        binding.introductionToBasics.setOnClickListener(view -> navigateToBasicsFragment());

        // Initialize TextToSpeechHelper
        tts = new TextToSpeechHelper(this);

        // Set click listener for ttsButton to speak the introduction text
        binding.ttsButton.setOnClickListener(view -> {
            String textToSpeak = getString(R.string.python_description);
            tts.speak(textToSpeak);
        });
    }

    // Method to navigate to the BasicsFragment and hide current UI elements
    private void navigateToBasicsFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BasicsFragment basicsFragment = new BasicsFragment();

        fragmentTransaction.replace(R.id.fragment_basics, basicsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        // Hide UI elements after navigation
        binding.textView.setVisibility(View.GONE);
        binding.textView2.setVisibility(View.GONE);
        binding.introductionToBasics.setVisibility(View.GONE);
        binding.ttsButton.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        // Stop and shutdown TextToSpeech when the activity is destroyed
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}