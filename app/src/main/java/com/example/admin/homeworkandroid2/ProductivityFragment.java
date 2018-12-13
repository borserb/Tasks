package com.example.admin.homeworkandroid2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.homeworkandroid2.MVP.MainContract;

public class ProductivityFragment extends Fragment {
    TextView textView;
    private MainContract.Presenter mPresenter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = (View) inflater.inflate(R.layout.product_fragmen, container, false);
        textView = inflate.findViewById(R.id.productivity_quantity);
        textView.setText("Кол-во "+PersistantStorage.getProperty());

        return  inflate;
    }





    public static ProductivityFragment newInstance() {
        Bundle args = new Bundle();
        ProductivityFragment fragment = new ProductivityFragment();
        fragment.setArguments(args);
        return fragment;
    }



}
