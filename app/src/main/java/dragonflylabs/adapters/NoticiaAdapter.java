package dragonflylabs.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dragonflylabs.gobmx.R;
import dragonflylabs.models.Noticia;
import dragonflylabs.utils.Urls;

/**
 * Created by caprinet on 10/18/14.
 */
public class NoticiaAdapter extends PagerAdapter{
    private ArrayList<View> mViews;
    private Context mContext;


    public NoticiaAdapter(Context context, ArrayList<Noticia> noticias, View.OnClickListener callback){
        mContext = context;
        mViews = new ArrayList<View>();
        for(int i = 0; i < noticias.size(); i++){
            Noticia model = noticias.get(i);
            View container = LayoutInflater.from(context).inflate(R.layout.pager_item_noticia, null);
            TextView title = (TextView)container.findViewById(R.id.pager_item_title);
            TextView description = (TextView)container.findViewById(R.id.pager_item_description);
            ImageView image = (ImageView)container.findViewById(R.id.pager_item_image);
            TextView date = (TextView)container.findViewById(R.id.pager_item_date);
            title.setText(model.getTitle());
            description.setText(model.getDescription());
            Picasso.with(context).load(Urls.API_ENDPOINT+model.getImage_portrait()).into(image);
            Button seemore = (Button)container.findViewById(R.id.pager_item_btn_more);
            seemore.setOnClickListener(callback);
            seemore.setTag(model);
            date.setText(model.getFecha_inicio());
            mViews.add(container);
        }
    }

    @Override
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
