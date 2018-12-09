package com.example.admin.homeworkandroid2.Adpter;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;
import com.example.admin.homeworkandroid2.AppDatabase;
import com.example.admin.homeworkandroid2.MVP.MainContract;
import com.example.admin.homeworkandroid2.NewTaskActivity;
import com.example.admin.homeworkandroid2.PersistantStorage;
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




        PersistantStorage.init(getContext());

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv);
        return view;

    }

    public ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            Task task = taskAdapter.findTask(viewHolder.getAdapterPosition());
            Toast.makeText(getContext(), viewHolder.getLayoutPosition() +" pos", Toast.LENGTH_LONG).show();
            PersistantStorage.addProperty(1);
            dbDelTask(task);

        }

    };


    private void recycleInit(final View view) {
        rv = view.findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        taskAdapter = new TaskAdapter(getContext(), tasks, new TaskAdapter.OnTaskClickListner() {
            @Override
            public void onClick(Task task) {
               /* Toast.makeText(getContext(), task.getName()+" del", Toast.LENGTH_LONG).show();*/
                /*dbDelTask(task);*/
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
            tasksVinInv();
        }
    }

    public void tasksVinInv() {
        if (taskAdapter.getItemCount() < 1) {
            fon.setVisibility(View.VISIBLE);
        } else {
            fon.setVisibility(View.INVISIBLE);
        }
    }

    private void dbDelTask(Task task){
         FragmentActivity activity = getActivity();
                if (activity!=null) {
                    final AppDatabase db = Room.databaseBuilder(activity, AppDatabase.class, "database-name").allowMainThreadQueries().build();
                    db.taskDao().del(task);
                    taskAdapter.delTask(task);
                    FragmentTransaction ftr = getFragmentManager().beginTransaction();
                    ftr.detach(TaskFragment.this).attach(TaskFragment.this).commit();
                }
    }


}
