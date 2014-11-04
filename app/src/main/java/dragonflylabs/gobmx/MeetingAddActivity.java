package dragonflylabs.gobmx;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MeetingAddActivity extends BaseActivity implements CalendarPickerView.OnDateSelectedListener {

    @InjectView(R.id.act_meeting_add_calendar)CalendarPickerView dayPickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_add);
        ButterKnife.inject(this);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        Date today = new Date();
        dayPickerView.init(today, nextYear.getTime()).withSelectedDate(today);
        dayPickerView.setOnDateSelectedListener(this);
    }

    @Override
    public void onDateSelected(Date date) {
        System.out.println(date.toGMTString());
    }

    @Override
    public void onDateUnselected(Date date) {

    }
}
