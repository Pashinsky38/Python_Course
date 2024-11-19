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
            String textToSpeak = "Python is a high-level, general-purpose programming language known for its simplicity and readability. It is widely used in various fields like web development, data analysis, artificial intelligence, scientific computing, and automation. Python's design emphasizes code readability, using indentation to define blocks of code, which makes it easier to learn and write. Key features of Python: Easy to learn and use: Its syntax is clear and concise, making it beginner-friendly. Interpreted language: Python executes code line by line, which makes it easier to debug. Extensive libraries: It has a vast collection of libraries (like NumPy, Pandas, TensorFlow) that help with tasks ranging from math operations to machine learning. Cross-platform: It runs on various platforms like Windows, macOS, and Linux. Dynamic typing: You don't need to specify data types when declaring variables, and they can change type dynamically. It's commonly used by developers, scientists, and analysts for tasks ranging from simple scripting to large-scale applications.";
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