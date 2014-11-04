package dragonflylabs.gobmx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dragonflylabs.adapters.WalktroughtAdapter;


public class WalktroughtActivity extends Activity {

    @InjectView(R.id.act_walk_viewpager) ViewPager viewPager;
    @InjectView(R.id.act_walk_indicator) CirclePageIndicator indicator;
    View.OnClickListener callback = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(WalktroughtActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walktrought);
        ButterKnife.inject(this);
        getActionBar().hide();
        viewPager.setAdapter(new WalktroughtAdapter(this, callback));
        indicator.setViewPager(viewPager);
    }
}
