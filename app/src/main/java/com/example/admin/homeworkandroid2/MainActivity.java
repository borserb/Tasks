package com.example.admin.homeworkandroid2;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.Random;
import com.example.admin.homeworkandroid2.Adpter.TaskAdapter;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private List<Task> tasks = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);


        TaskAdapter taskAdapter = new TaskAdapter(this, tasks, new TaskAdapter.OnTaskClickListner() {
            @Override
            public void onClick(Task task) {
                Toast.makeText(MainActivity.this,task.getName(),Toast.LENGTH_LONG).show();

            }
        });
        rv.setAdapter(taskAdapter);
        createMockDate();
            
        }

    private void createMockDate() {
        Random rand = new Random();
        for (int i = 0; i <50 ; i++) {
            int r = rand.nextInt(254)+1;
            int g = rand.nextInt(254)+1;
            int b = rand.nextInt(254)+1;
            int rgb = Color.rgb(r, g, b);
            tasks.add(new Task("Выполнить задание №" + i, rgb));
        }






    }
}



