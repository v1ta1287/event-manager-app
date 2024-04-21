package com.example.eventmanagerapp.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmanagerapp.R;
import com.example.eventmanagerapp.controller.handler.EventRecyclerAdapter;
import com.example.eventmanagerapp.controller.util.SharedPreferencesUtility;
import com.example.eventmanagerapp.model.Event;

import java.util.ArrayList;

public class FragmentListEvent extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    ArrayList<Event> listEvents = new ArrayList<>();
    EventRecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    public FragmentListEvent() {
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
        return inflater.inflate(R.layout.fragment_list_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.eventRecylerView);

// A Linear RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        layoutManager = new LinearLayoutManager(getContext());
// Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new EventRecyclerAdapter();
        recyclerAdapter.setData(listEvents);
        recyclerView.setAdapter(recyclerAdapter);

        ArrayList<Event> categoryListFromSharedPreference = SharedPreferencesUtility.getEventsFromSharedPreferences(requireActivity());
        listEvents.addAll(categoryListFromSharedPreference);

    }
    @Override
    public void onDetach() {
        super.onDetach();
    }

}