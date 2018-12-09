package com.example.admin.homeworkandroid2.Adpter;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.admin.homeworkandroid2.AppDatabase;
import com.example.admin.homeworkandroid2.NewTaskActivity;
import com.example.admin.homeworkandroid2.R;
import com.example.admin.homeworkandroid2.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends Fragment {
    private RecyclerView rv;
    private List<Task> tasks = new ArrayList<>();
    private FloatingActionButton fabAdd;
    private TaskAdapter taskAdapter;
    public static final int NEW_TASK_ACTIVITY = 101;
    private ConstraintLayout fon;

    public TaskFragment() {
    }

    public static TaskFragment newInstance() {
        return new TaskFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        fabAdd = view.findViewById(R.id.fab_add_task);
        recycleInit(view);
        fon = view.findViewById(R.id.no_tasks_fon);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    startActivityForResult(new Intent(getActivity(), NewTaskActivity.class), NEW_TASK_ACTIVITY);
                }
            }
        });

       /* tasks.add(new Task("первый", 0));*/
        return view;
    }

    private void recycleInit(View view) {
        rv = view.findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        taskAdapter = new TaskAdapter(getContext(), tasks, new TaskAdapter.OnTaskClickListner() {
            @Override
            public void onClick(Task task) {
                Toast.makeText(getContext(), task.getName(), Toast.LENGTH_LONG).show();
            }
        });
        rv.setAdapter(taskAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        FragmentActivity activity = getActivity();
        if (activity!=null){
            final AppDatabase db = Room.databaseBuilder(activity, AppDatabase.class, "database-name").allowMainThreadQueries().build();
            taskAdapter.setTasks(db.taskDao().getAll());
            tasksViInv();
        }

    }

    public void tasksViInv() {
        if (taskAdapter.getItemCount() < 1) {
            fon.setVisibility(View.VISIBLE);
        } else {
            fon.setVisibility(View.INVISIBLE);
        }
    }



}
