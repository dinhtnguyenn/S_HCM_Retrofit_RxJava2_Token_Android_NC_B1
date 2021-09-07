package com.example.myapplication.others;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.myapplication.R;

public class ShowNotifyUser {
    private static ProgressDialog mProgressDialog;

    public static void showProgressDialog(Context context, String message) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }


    public static void dismissProgressDialog() {
        mProgressDialog.dismiss();
    }
}
