package com.example.sangameswaran.wonderwoman.Constants;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Sangameswaran on 06-01-2018.
 */

public class CommonFunctions {
    public static void toastString(String message, Context context){
        Toast t=Toast.makeText(context,message,Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }
}
