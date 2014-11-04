package dragonflylabs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dragonflylabs.gobmx.R;
import dragonflylabs.models.Tramite;

/**
 * Created by caprinet on 10/6/14.
 */
public class TramiteAdapter extends ArrayAdapter<Tramite>{

    private ArrayList<Tramite> tramites;
    public TramiteAdapter(Context context, int resId, ArrayList<Tramite> objects){
        super(context, resId, objects);
        tramites = objects;
    }

    @Override
    public int getCount() {
        return tramites.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_tramite, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Tramite model = getItem(position);
        holder.tramite.setText(model.getName());
        holder.dependencia.setText(model.getDependency().getName());
        holder.online.setAlpha(model.canMakeOnline() ? 1 : 0);
        return  convertView;
    }

    class ViewHolder{
        @InjectView(R.id.item_tramite_txt_tramite)TextView tramite;
        @InjectView(R.id.item_tramite_txt_dependencia)TextView dependencia;
        @InjectView(R.id.item_tramite_container_online) LinearLayout online;
        public ViewHolder(View v){
            ButterKnife.inject(this, v);
        }
    }
}
