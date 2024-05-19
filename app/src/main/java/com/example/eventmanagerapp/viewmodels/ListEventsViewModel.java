package com.example.eventmanagerapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.eventmanagerapp.data.model.Event;
import com.example.eventmanagerapp.data.repositories.EventManagerRepository;

import java.util.List;

public class ListEventsViewModel extends AndroidViewModel {
    private EventManagerRepository mEventManagerRepository;
    private LiveData<List<Event>> mAllEvents;

    public ListEventsViewModel(@NonNull Application application) {
        super(application);
        mEventManagerRepository = new EventManagerRepository(application);
        mAllEvents = mEventManagerRepository.getAllEvents();
    }

    public LiveData<List<Event>> getAllEvents() {
        return mAllEvents;
    }
}
