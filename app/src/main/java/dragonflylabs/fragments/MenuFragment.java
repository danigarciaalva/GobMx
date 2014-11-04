package dragonflylabs.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import dragonflylabs.gobmx.MainActivity;
import dragonflylabs.gobmx.R;

/**
 * Created by caprinet on 10/19/14.
 */
public class MenuFragment extends Fragment{

    @InjectView(R.id.fr_menu_list)ListView mListView;
    MenuAdapter menuAdapter;
    private ArrayList<Fragment> mFragments;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.inject(this, v);
        String[] titles = getResources().getStringArray(R.array.fr_menu_items);
        int icons[] = {
                R.drawable.ic_menu_tramite,
                R.drawable.ic_menu_noticias,
                R.drawable.ic_menu_dependencia,
                R.drawable.ic_menu_historial,
                R.drawable.ic_menu_citas,
                R.drawable.ic_menu_comentarios,
                R.drawable.ic_menu_configuracion,
        };
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        for(int i = 0; i < titles.length; i++){
            items.add(new MenuItem(titles[i], icons[i]));
        }

        menuAdapter = new MenuAdapter(getActivity(), R.layout.list_item_menu, items);
        mListView.setAdapter(menuAdapter);
        return v;
    }

    @OnItemClick(R.id.fr_menu_list)
    public void onMenuItemClick(int position){
        Fragment newContent = null;
        switch (position){
            case 0:
                newContent = new TramiteFragment();
                break;
            case 1:
                newContent = new NoticiasFragment();
                break;
            case 2:
                newContent = new DependenciesFrament();
                break;
            case 3:
                newContent = new HistorialFragment();
            case 4:
                newContent = new PendientMeetingFragment();
                break;
        }
        if (newContent != null)
            switchFragment(newContent);

    }

    class MenuItem{
        public String title;
        public int resId;
        public MenuItem(String title, int resId){ this.title = title; this.resId = resId;}
    }
    class MenuAdapter extends ArrayAdapter<MenuItem> {

        public MenuAdapter(Context context, int resId, ArrayList<MenuItem> objects){
            super(context, resId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_menu, null);
            }
            TextView title = (TextView)convertView.findViewById(R.id.list_item_menu_title);
            ImageView image = (ImageView)convertView.findViewById(R.id.list_item_menu_icon);
            title.setText(getItem(position).title);
            image.setBackgroundResource(getItem(position).resId);
            return convertView;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    private void switchFragment(Fragment fragment) {
        if (getActivity() == null)
            return;
        if (getActivity() instanceof MainActivity) {
            MainActivity fca = (MainActivity) getActivity();
            fca.switchContent(fragment);
        }
    }
}
