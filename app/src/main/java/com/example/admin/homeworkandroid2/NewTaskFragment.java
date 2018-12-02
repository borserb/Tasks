package com.example.admin.homeworkandroid2;


import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class NewTaskFragment extends Fragment {

    private EditText etTaskName;
    private ImageButton ibNewItem;
    private TextView tvPriority;
    public static final String TAG = "NewTaskFragment";
    private int priority =0;
    private int priorityColor;
private String taskName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_task, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        etTaskName = view.findViewById(R.id.etTaskName);
        ibNewItem = view.findViewById(R.id.ibNewItem);
        tvPriority = view.findViewById(R.id.tvPriority);

        ibNewItem.setEnabled(false);
        Log.d("NewTaskFR", "Create");
        tvPriority.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Нажали на приоритет", Toast.LENGTH_LONG).show();


            }
        });

        ibNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PriorityDialogFragment priorityDialogFragment = PriorityDialogFragment.newInstance();
                priorityDialogFragment.show(getChildFragmentManager(),PriorityDialogFragment.TAG);

                Toast.makeText(getContext(), "+", Toast.LENGTH_LONG).show();


            }});

        etTaskName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ibNewItem.setEnabled(!TextUtils.isEmpty(s));
            }
        });
        /*Toast.makeText(getContext(), "Priority =" + priority, Toast.LENGTH_SHORT).show();*/
        
    }

    public static NewTaskFragment newInstance() {
        Bundle args = new Bundle();
        NewTaskFragment fragment = new NewTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onPriorityChosen(int priority) {
      this.priority = priority;
        taskName = etTaskName.getText().toString();

        Toast.makeText(getContext(), "Fragment priority =" + priority, Toast.LENGTH_SHORT).show();

        Task task = new Task(taskName, ContextCompat.getColor(getContext(),priority));

        FragmentActivity activity = getActivity();
        if (activity!=null){
            final AppDatabase db = Room.databaseBuilder(activity, AppDatabase.class, "database-name")
                    .allowMainThreadQueries()
                    .build();
            db.taskDao().inser(task);
        }


    }
}

