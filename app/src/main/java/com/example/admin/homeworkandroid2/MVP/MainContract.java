package com.example.admin.homeworkandroid2.MVP;

import android.view.View;

public interface MainContract {

    interface ViewShow {
        void showText(View view);
    }

    interface ActivityListner {
        void onSwipWasClicked();
    }

    interface Presenter {
        void onSwipWasClicked();
        /*void onDestroy();*/
    }

    interface Repository {
        int loadMessage();
    }
}
