package com.honorsmobileapps.amandajones.fragmentswitharticlelikedislikedemo;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment {

    public static final int YES = 0;
    public static final int NO = 1;
    private static final String KEY_ARTICLE_TITLE = "article_title";


    // Declare the interface
    public interface OnFeedbackGivenListener {
        // This is the method the activity will implement
        // The fragment will call it to communicate with the activity
        // The parameter is if the user said yes or no to liking the article
        public void onFeedbackGiven(int answer);
    }

    // Make a member variable for the class that implements the interface
    // Aka the "listener"
    OnFeedbackGivenListener onFeedbackGivenListener;

    // Make a method to set the member variable
    // The Activity will call this method to set itself as the listener
    public void setOnFeedbackGivenListener(OnFeedbackGivenListener listener) {
        this.onFeedbackGivenListener = listener;
    }

    public FeedbackFragment() {
        // Required empty public constructor
    }

    public static FeedbackFragment newInstance(String articleTitle) {
        // Create an instance of the Fragment
        FeedbackFragment fragment = new FeedbackFragment();
        // Create a Bundle
        Bundle bundle = new Bundle();
        // Put the article title and the key in the bundle
        bundle.putString(KEY_ARTICLE_TITLE, articleTitle);
        // Attach the bundle to the Fragment instance
        fragment.setArguments(bundle);
        // Return the Fragment instance
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);

        String articleTitle = getArguments().getString(KEY_ARTICLE_TITLE);
        if (articleTitle != null) {
            TextView fragmentTitleTextView = rootView.findViewById(R.id.fragment_title_textview);
            fragmentTitleTextView.setText("Current article: " + articleTitle);
        }

        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);
                TextView textView =
                        rootView.findViewById(R.id.fragment_header_textview);
                if (index == YES)
                    textView.setText(R.string.yes_message);
                else if (index == NO) {
                    textView.setText(R.string.no_message);
                }
                onFeedbackGivenListener.onFeedbackGiven(index);
            }
        });
        return rootView;
    }

    public void showToast() {
        Toast.makeText(getActivity(), "CLOSED", Toast.LENGTH_SHORT).show();
    }
}
