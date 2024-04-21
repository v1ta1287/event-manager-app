package com.example.eventmanagerapp.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.controller.handler.CategoryRecyclerAdapter;
import com.example.eventmanagerapp.controller.util.SharedPreferencesUtility;
import com.example.eventmanagerapp.model.Category;

import java.util.ArrayList;


public class FragmentListCategory extends Fragment {
    ArrayList<Category> listCategories = new ArrayList<>();
    CategoryRecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.categoryRecyclerView);

// A Linear RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        layoutManager = new LinearLayoutManager(getContext());
// Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new CategoryRecyclerAdapter();
        recyclerAdapter.setData(listCategories);
        recyclerView.setAdapter(recyclerAdapter);

        ArrayList<Category> categoryListFromSharedPreference = SharedPreferencesUtility.getCategoriesFromSharedPreferences(requireActivity());
        listCategories.addAll(categoryListFromSharedPreference);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}