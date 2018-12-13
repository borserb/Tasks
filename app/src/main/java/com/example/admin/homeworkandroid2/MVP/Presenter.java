package com.example.admin.homeworkandroid2.MVP;

import android.widget.TextView;

import com.example.admin.homeworkandroid2.PersistantStorage;

public class Presenter implements MainContract.Presenter {

    private MainContract.ViewShow viewShow;
    private int messege;
    private MainContract.Repository mRepository;

    public Presenter(MainContract.ViewShow viewShow) {
        this.viewShow = viewShow;
        this.mRepository = new PersistantStorage();
    }


    @Override
    public void onSwipWasClicked() {
       messege= mRepository.loadMessage();
       TextView textView= (TextView)viewShow;
       textView.setText(messege);

    }


}
