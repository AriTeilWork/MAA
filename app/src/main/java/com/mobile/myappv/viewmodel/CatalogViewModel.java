package com.mobile.myappv.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobile.myappv.manager.PhoneCatalog;
import com.mobile.myappv.model.Person;

import java.util.List;

public class CatalogViewModel extends ViewModel {
    private final PhoneCatalog catalog = new PhoneCatalog();
    private final MutableLiveData<List<Person>> peopleLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<String>> searchDataLiveData = new MutableLiveData<>();

    public CatalogViewModel() {
        peopleLiveData.setValue(catalog.getPeople());
        searchDataLiveData.setValue(catalog.generateSearchData());
    }

    public LiveData<List<Person>> getPeople() {
        return peopleLiveData;
    }

    public LiveData<List<String>> getSearchData() {
        return searchDataLiveData;
    }

    public void addPerson(Person person) {
        catalog.addPerson(person);
        peopleLiveData.setValue(catalog.getPeople());
        searchDataLiveData.setValue(catalog.generateSearchData());
    }
}