package com.example.eventmanagerapp.viewmodels;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.eventmanagerapp.data.model.Event;
import com.example.eventmanagerapp.data.repositories.EventRepository;

import java.util.List;

public class EventViewModel extends AndroidViewModel {
    private EventRepository mRepository;
    private LiveData<List<Event>> mAllEvents;

    public EventViewModel(@NonNull Application application) {
        super(application);
        mRepository = new EventRepository(application);
        mAllEvents = mRepository.getAllEvents();
    }

    public LiveData<List<Event>> getAllEvents() {
        return mAllEvents;
    }

    public void insert(Event event) {
        mRepository.insert(event);
    }
    public void deleteAll(){
        mRepository.deleteAll();
    }
    public void deleteByName(String name){mRepository.deleteByName(name);}

}
