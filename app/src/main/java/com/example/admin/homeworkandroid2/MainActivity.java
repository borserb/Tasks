package com.example.admin.homeworkandroid2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.example.admin.homeworkandroid2.Adpter.TaskFragment;

public class MainActivity extends AppCompatActivity {

private ViewPager vpTabs;
private TableLayout tlTabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vpTabs = findViewById(R.id.vpTabs);
        tlTabs = findViewById(R.id.tlTabs);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(supportFragmentManager);
        vpTabs.setAdapter(adapter);
        }


public static class TabsFragmentAdapter extends FragmentPagerAdapter {

    public TabsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i==0){
            return TaskFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }
}






    }




