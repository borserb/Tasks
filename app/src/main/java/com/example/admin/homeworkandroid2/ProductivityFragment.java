package com.example.admin.homeworkandroid2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProductivityFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (View) inflater.inflate(R.layout.product_fragmen, container, false);
    }

    public static ProductivityFragment newInstance() {
        Bundle args = new Bundle();
        ProductivityFragment fragment = new ProductivityFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
