package com.example.admin.homeworkandroid2.Adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.homeworkandroid2.R;
import com.example.admin.homeworkandroid2.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    @NonNull
    private final Context context;
    private List<Task> tasks;
    private final OnTaskClickListner onTaskClickListner;

    public TaskAdapter(@NonNull Context context, List<Task> tasks, OnTaskClickListner onTaskClickListner) {
        this.context = context;
        this.tasks = tasks;
        this.onTaskClickListner = onTaskClickListner;
    }

    public interface OnTaskClickListner {
        void onClick(Task task);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.task_item, parent, false);

        final TaskViewHolder taskViewHolder = new TaskViewHolder(view);

        taskViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OnTaskClickListner onTaskClickListner = TaskAdapter.this.onTaskClickListner;
                if (taskViewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onTaskClickListner.onClick(tasks.get(taskViewHolder.getAdapterPosition()));
                }
            }
        });

        return taskViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int i) {
        holder.SetData(tasks.get(i));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


    public void taskAdd(Task task) {
        tasks.add(task);
        notifyItemInserted(tasks.size() - 1);
    }


    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }


}


class TaskViewHolder extends RecyclerView.ViewHolder {
    private TextView tvMarker;
    private TextView tvTask;
    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        tvMarker = itemView.findViewById(R.id.tvMarker);
        tvTask = itemView.findViewById(R.id.tvTask);
    }
    public void SetData(Task task) {
        tvTask.setText(task.getName());
        tvMarker.setTextColor(task.getPriority());
        tvMarker.setText("●");


    }


}