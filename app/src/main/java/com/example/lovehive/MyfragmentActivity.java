// MyFragment.java
package com.example.lovehive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class MyfragmentActivity extends Fragment {

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_fragment_item, container, false);
        textView = view.findViewById(R.id.textViewFragment);

        // Retrieve text from arguments
        String text = getArguments().getString("TEXT_ID");

        // Set the text to the TextView
        textView.setText(text);

        return view;
    }
}
