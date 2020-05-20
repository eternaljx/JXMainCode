package com.smartseat.workgroup.common.utils;

import android.os.Handler;
import android.widget.Toast;
import com.smartseat.workgroup.application.App;

public class ToaskUtils {
    private static Toast toast;
    private static Handler mhandler = new Handler();

    private static void shortToask(String info) {
        mhandler.removeCallbacks(r);
        if (toast != null) {
            toast.setText(info);

        } else {
            toast = Toast.makeText(App.context, info, Toast.LENGTH_SHORT);
        }
        mhandler.postDelayed(r, 5000);
        toast.show();
    }

    private static Runnable r = new Runnable() {
        public void run() {
            toast.cancel();
        }

        ;
    };

    public static void showToast(String strId) {
        shortToask(strId);
    }

}
