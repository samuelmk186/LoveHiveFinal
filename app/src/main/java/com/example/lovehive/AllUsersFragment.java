// AllUsersFragment.java
package com.example.lovehive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class AllUsersFragment extends Fragment {

    private ArrayList<GridViewPojo> arrayList;
    private GridView gridView;
    private AdapterGridView adapterGridView;

    public AllUsersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_all_users_fragment, container, false);

        gridView = view.findViewById(R.id.grid_list);

        // Populate data
        gridItemShow();

        // Set up the adapter
        adapterGridView = new AdapterGridView(requireContext(), arrayList);
        gridView.setAdapter(adapterGridView);

        return view;
    }

    private void gridItemShow() {
        arrayList = new ArrayList<>();
        arrayList.add(new GridViewPojo(R.drawable.ic_smartphone, "Mobile"));
        arrayList.add(new GridViewPojo(R.drawable.ic_internet, "Internet"));
        // Add more items as needed
    }
}
