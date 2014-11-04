package dragonflylabs.gobmx;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by caprinet on 10/6/14.
 */
public class BaseActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.general_red)));
        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }

    protected void setSubtitle(int resource_string){
        getActionBar().setSubtitle(getResources().getString(resource_string));
    }

    protected void setSubtitle(String subtitle){
        getActionBar().setSubtitle(subtitle);
    }
}
