package dragonflylabs.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import dragonflylabs.adapters.NoticiaAdapter;
import dragonflylabs.api.Api;
import dragonflylabs.gobmx.NoticiaDetailActivity;
import dragonflylabs.gobmx.R;
import dragonflylabs.models.Noticia;
import dragonflylabs.models.NoticiaWrapper;
import dragonflylabs.utils.Alerts;
import dragonflylabs.utils.Connection;
import dragonflylabs.utils.Urls;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_UP;

public class NoticiasFragment extends Fragment implements Api.OnApiCallDownload<NoticiaWrapper>, View.OnClickListener {

    @InjectView(R.id.fr_noticias_pager) ViewPager viewPager;
    @InjectView(R.id.fr_noticias_indicator) CirclePageIndicator pageIndicator;
    NoticiaWrapper noticiaWrapper;
    Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rss, container, false);
        ButterKnife.inject(this, v);
        activity = getActivity();
        refresh();
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            float oldX = 0,newX = 0,sens = 5;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case ACTION_DOWN: oldX = event.getX(); break;
                    case ACTION_UP:
                        newX = event.getX();
                        if (Math.abs(oldX - newX) < sens) {
                            itemClicked(viewPager.getCurrentItem());
                            return true;
                        }
                        oldX = 0;
                        newX = 0;
                        break;
                }
                return false;
            }
        });
        return v;
    }

    private void itemClicked(int currentItem) {
        Intent i = new Intent(getActivity(), NoticiaDetailActivity.class);
        i.putExtra(NoticiaDetailActivity.NOTICIA_ITEM, new Gson().toJson(noticiaWrapper.getNoticias().get(currentItem), Noticia.class));
        startActivity(i);
    }

    private void refresh() {
        if(Connection.isConnected(activity)){
            Alerts.showProgressDialog(activity, R.string.gob_dialog_loading_news);
            new Api<Void, NoticiaWrapper>(activity).get(NoticiaWrapper.class, Urls.API_NOTICIAS, this);
        }else{
            Crouton.makeText(activity, R.string.gob_error_connection, Style.ALERT).show();
        }
    }

    @Override
    public void onSuccess(NoticiaWrapper object) {
        Alerts.stopProgressDialog();
        if(object != null){
            noticiaWrapper = object;
            if(noticiaWrapper.getNoticias() != null){
                viewPager.setAdapter(new NoticiaAdapter(getActivity(), object.getNoticias(), this));
                pageIndicator.setViewPager(viewPager);
            }
        }else{
            Crouton.makeText(getActivity(), R.string.gob_empty_noticias, Style.INFO).show();
        }
    }

    @Override
    public void onError(String error) {
        Alerts.stopProgressDialog();
        Crouton.makeText(getActivity(), R.string.gob_error_request, Style.ALERT).show();
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(getActivity(), NoticiaDetailActivity.class);
        i.putExtra(NoticiaDetailActivity.NOTICIA_ITEM, new Gson().toJson(view.getTag(), Noticia.class));
        startActivity(i);
    }
}
