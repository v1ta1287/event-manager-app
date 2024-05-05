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
import com.example.eventmanagerapp.adapters.EventRecyclerAdapter;
import com.example.eventmanagerapp.data.model.Event;
import com.example.eventmanagerapp.viewmodels.ListEventsViewModel;

import java.util.ArrayList;

/**
 * Controller for event list fragment
 */
public class ListEventsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    ArrayList<Event> listEvents = new ArrayList<>();
    EventRecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ListEventsViewModel mListEventsViewModel;

    public ListEventsFragment() {
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

        mListEventsViewModel = new ViewModelProvider(this).get(ListEventsViewModel.class);
        mListEventsViewModel.getAllEvents().observe(getViewLifecycleOwner(), newData -> {
            recyclerAdapter.setData(newData);
            recyclerAdapter.notifyDataSetChanged();
        });

    }
    @Override
    public void onDetach() {
        super.onDetach();
    }

}