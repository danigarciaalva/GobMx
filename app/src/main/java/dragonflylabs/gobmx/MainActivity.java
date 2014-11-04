package dragonflylabs.gobmx;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dragonflylabs.fragments.DependenciesFrament;
import dragonflylabs.fragments.ComunityFragment;
import dragonflylabs.fragments.MenuFragment;
import dragonflylabs.fragments.TramiteFragment;
import dragonflylabs.fragments.NoticiasFragment;
import dragonflylabs.ui.CustomViewPager;

/**
 * Created by caprinet on 9/29/14.
 */
public class MainActivity extends SlidingFragmentActivity{

    protected MenuFragment menuFragment;
    private Fragment mContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ab = getActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.general_red)));
        ab.setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        ab.setIcon(getResources().getDrawable(R.drawable.ic_menu));
        ab.setHomeButtonEnabled(true);

        ButterKnife.inject(this);
        if (savedInstanceState != null)
            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
        if (mContent == null)
            mContent = new Fragment();

        setBehindContentView(R.layout.menu_frame_layout);
        if(savedInstanceState == null){
            FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
            menuFragment = new MenuFragment();
            t.replace(R.id.menu_frame_container, menuFragment);
            t.commit();
        }else{
            menuFragment = (MenuFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame_container);
        }

        setContentView(R.layout.content_frame_layout);


        SlidingMenu sm = getSlidingMenu();
        sm.setMode(SlidingMenu.LEFT);
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        setSlidingActionBarEnabled(false);

        switchContent(new TramiteFragment());
    }

    public void switchContent(Fragment fragment) {
        mContent = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame_container, fragment)
                .commit();
        getSlidingMenu().showContent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                toggle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
