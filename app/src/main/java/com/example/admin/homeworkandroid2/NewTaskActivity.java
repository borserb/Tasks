package com.example.admin.homeworkandroid2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class NewTaskActivity extends AppCompatActivity implements PriorityDialogListner {

public static final String NEW_TASK_KEY = "NEW_TASK_KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteiner);
        Log.d("NewTaskCreateAct", "Create");

        if (savedInstanceState==null){
            Log.d("FragmentCreate", "Create");
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.add(R.id.flContainer, NewTaskFragment.newInstance(), NewTaskFragment.TAG);
            transaction.commit();
        }
    }

    @Override
    public void onPriorityChosen(int priority) {
        NewTaskFragment fragment = ((NewTaskFragment) getSupportFragmentManager().findFragmentByTag(NewTaskFragment.TAG));
        fragment.onPriorityChosen(priority);
        Toast.makeText(this, "Значение приоритета = " + priority, Toast.LENGTH_SHORT).show();

    }
}
