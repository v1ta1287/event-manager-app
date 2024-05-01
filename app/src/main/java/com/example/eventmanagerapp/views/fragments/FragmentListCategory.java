package com.example.eventmanagerapp.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.adapters.CategoryRecyclerAdapter;
import com.example.eventmanagerapp.utilities.SharedPreferencesUtility;
import com.example.eventmanagerapp.data.model.Category;
import com.example.eventmanagerapp.viewmodels.CategoryViewModel;

import java.util.ArrayList;


/**
 * Controller for category list fragment
 */
public class FragmentListCategory extends Fragment {
    ArrayList<Category> listCategories = new ArrayList<>();
    CategoryRecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    CategoryViewModel mCategoryViewModel;

    public FragmentListCategory() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.categoryRecyclerView);

        // A Linear RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        layoutManager = new LinearLayoutManager(getContext());
        // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new CategoryRecyclerAdapter();
        recyclerAdapter.setData(listCategories);
        recyclerView.setAdapter(recyclerAdapter);

        mCategoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        mCategoryViewModel.getAllCategories().observe(getViewLifecycleOwner(), newData -> {
            recyclerAdapter.setData(newData);
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}