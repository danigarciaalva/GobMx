package dragonflylabs.utils;

import android.app.ProgressDialog;
import android.content.Context;

import dragonflylabs.gobmx.R;

/**
 * Created by caprinet on 10/13/14.
 */
public abstract class Alerts {

    static ProgressDialog dialog;

    public static void showProgressDialog(Context context, int title, int message){
        if(dialog != null && dialog.isShowing())
            dialog.dismiss();
        dialog = new ProgressDialog(context);
        dialog.setTitle(context.getString(title));
        dialog.setMessage(context.getString(message));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void showProgressDialog(Context context, int message){
        if(dialog != null && dialog.isShowing())
            dialog.dismiss();
        dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(message));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void showProgressDialog(Context context){
        if(dialog != null && dialog.isShowing())
            dialog.dismiss();
        dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.dialog_loading));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void stopProgressDialog(){
        if(dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

}
