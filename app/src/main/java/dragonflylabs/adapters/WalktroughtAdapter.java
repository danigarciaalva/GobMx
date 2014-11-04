package dragonflylabs.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dragonflylabs.gobmx.R;
import dragonflylabs.gobmx.WalktroughtActivity;

/**
 * Created by caprinet on 10/19/14.
 */
public class WalktroughtAdapter extends PagerAdapter {

    private ArrayList<View> mViews;
    private android.content.Context mContext;

    public WalktroughtAdapter(Context context, View.OnClickListener callback) {
        this.mContext = context;
        mViews = new ArrayList<View>();
        String[] descs = context.getResources().getStringArray(R.array.act_walk_desc_texts);
        TypedArray icons = context.getResources().obtainTypedArray(R.array.act_walk_icons);
        for(int i = 0; i < descs.length; i++){
            View container = LayoutInflater.from(context).inflate(R.layout.fragment_walktrought, null);
            TextView description = (TextView) container.findViewById(R.id.act_walk_txt_description);
            ImageView imageIV = (ImageView) container.findViewById(R.id.act_walk_image);
            description.setText(descs[i]);
            imageIV.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), icons.getResourceId(i, -1)));
            Button entrar = (Button)container.findViewById(R.id.act_walk_btn_enter);
            entrar.setVisibility((i == (descs.length -1)) ? View.VISIBLE : View.GONE);
            description.setVisibility((i == (descs.length -1)) ? View.GONE : View.VISIBLE);
            entrar.setOnClickListener(callback);
            mViews.add(container);
        }
    }

    public int getCount() {
        return mViews.size();
    }

    @Override
    public Object instantiateItem(View container, int position){
        View view = new View(mContext);
        view = mViews.get(position);
        ((ViewPager) container).addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(View container, int position, Object obj) {
        ((ViewPager) container).removeView((View) obj);
    }
    @Override
    public boolean isViewFromObject(View container, Object obj) {
        return container == obj;
    }
}
