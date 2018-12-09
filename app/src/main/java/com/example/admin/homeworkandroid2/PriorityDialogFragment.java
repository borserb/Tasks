package com.example.admin.homeworkandroid2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class PriorityDialogFragment extends DialogFragment {
    public static final String TAG = "PriorityDialogFragment";
    private PriorityDialogListner priorityDialogListner;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            if (context instanceof PriorityDialogListner){
                priorityDialogListner = (PriorityDialogListner) context;
            } else {
                throw new UnsupportedOperationException("Активити должно реализововать интерфейс PriorityDialogListner");
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_priority, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        View btnOk1 =view.findViewById(R.id.btnOk1);
        View btnOk2 = view.findViewById(R.id.btnOk2);
        View btnOk3 = view.findViewById(R.id.btnOk3);
        View btnOk4 = view.findViewById(R.id.btnOk4);
        View cancel = view.findViewById(R.id.cancel_priority);

        btnOk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priorityDialogListner.onPriorityChosen(R.color.redPoint);
            }});
        btnOk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priorityDialogListner.onPriorityChosen(R.color.priority2);
            }});
        btnOk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priorityDialogListner.onPriorityChosen(R.color.priprity3);
            }});
        btnOk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Toast.makeText(getContext(), "Fragment priority =" + R.color.priority4, Toast.LENGTH_SHORT).show();*/
                priorityDialogListner.onPriorityChosen(R.color.priority4);
            }});
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PriorityDialogFragment.super.dismiss();

            }});

        super.onViewCreated(view, savedInstanceState);
    }

    public static PriorityDialogFragment newInstance() {
        PriorityDialogFragment fragment = new PriorityDialogFragment();
        return fragment;
    }

}
