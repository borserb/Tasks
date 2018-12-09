package com.example.admin.homeworkandroid2.MVP;

public interface MainContract {

    interface ViewShow {
        void showText(int count);
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
