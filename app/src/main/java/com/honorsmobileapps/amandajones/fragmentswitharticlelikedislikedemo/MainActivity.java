package com.honorsmobileapps.amandajones.fragmentswitharticlelikedislikedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements FeedbackFragment.OnFeedbackGivenListener{
    private TextView titleTextView;
    private TextView articleBodyTextview;
    private Button feedbackButton;
    private boolean isFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTextView = findViewById(R.id.title);
        articleBodyTextview = findViewById(R.id.article_body);

        feedbackButton = findViewById(R.id.feedback_button);
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }
        });

    }

    public void displayFragment() {
        //Create an instance of your Fragment
        FeedbackFragment feedbackFragment = FeedbackFragment.newInstance(titleTextView.getText().toString());
        // Create a FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Create a FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Add the Fragment and call commit
        fragmentTransaction.add(R.id.fragment_container,
                feedbackFragment).commit();
        // Change text of button to say "close"
        feedbackButton.setText(R.string.close_feedback);
        // Update this helper boolean
        isFragmentDisplayed = true;
    }
//
    public void closeFragment() {
        // Create a FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Find the Fragment you want to remove and cast it
        FeedbackFragment feedbackFragment = (FeedbackFragment)
                fragmentManager.findFragmentById(R.id.fragment_container);
        // If you did find it, do a remove transaction
        if (feedbackFragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(feedbackFragment).commit();
        }
        // Change text of button
        feedbackButton.setText(R.string.give_feedback);
        // Update this helper boolean
        isFragmentDisplayed = false;
    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        // make sure that the Fragment that's being attached is FeedbackFragment
        if(fragment instanceof FeedbackFragment) {
            // Cast it as a FeedbackFragment
            FeedbackFragment feedbackFragment = (FeedbackFragment) fragment;
            // Call Fragment's setOnFeedbackGivenListener method to make this Activity the listener
            feedbackFragment.setOnFeedbackGivenListener(this);
        }
    }

    @Override
    public void onFeedbackGiven(int answer) {
        if(answer == FeedbackFragment.NO) {
            // Show new article
            titleTextView.setText(R.string.title2);
            articleBodyTextview.setText(R.string.article2);
            closeFragment();

            // Find the fragment and call one of its methods to communicate with it
            FragmentManager fragmentManager = getSupportFragmentManager();
            FeedbackFragment feedbackFragment = (FeedbackFragment)
                    fragmentManager.findFragmentById(R.id.fragment_container);
            feedbackFragment.showToast();
        }
    }
}
