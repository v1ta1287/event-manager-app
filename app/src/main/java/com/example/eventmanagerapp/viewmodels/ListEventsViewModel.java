package com.example.eventmanagerapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.eventmanagerapp.data.model.Event;
import com.example.eventmanagerapp.data.repositories.CategoryRepository;
import com.example.eventmanagerapp.data.repositories.EventRepository;

import java.util.List;

public class ListEventsViewModel extends AndroidViewModel {
    private EventRepository mEventRepository;
    private LiveData<List<Event>> mAllEvents;

    public ListEventsViewModel(@NonNull Application application) {
        super(application);
        mEventRepository = new EventRepository(application);
        mAllEvents = mEventRepository.getAllEvents();
    }

    public LiveData<List<Event>> getAllEvents() {
        return mAllEvents;
    }
}
