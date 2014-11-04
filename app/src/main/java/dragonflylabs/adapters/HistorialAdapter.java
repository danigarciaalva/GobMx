package dragonflylabs.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dragonflylabs.gobmx.R;
import dragonflylabs.models.Historial;
import dragonflylabs.models.Tramite;

/**
 * Created by caprinet on 10/30/14.
 */
public class HistorialAdapter extends ArrayAdapter<Historial> {

    View.OnClickListener callback;

    public HistorialAdapter(Context context, int resId, ArrayList<Historial> objectTramites, View.OnClickListener callback) {
        super(context, resId, objectTramites);
        this.callback = callback;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder;
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_item_historial, null);
            holder = new ViewHolder(v);
            v.setTag(holder);
        }else{
            holder = (ViewHolder)v.getTag();
        }
        Historial model = getItem(position);
        holder.tramite.setText(model.getTramite().getName());
        holder.dependencia.setText(model.getTramite().getDependency().getName());
        holder.fecha.setText(model.getDate());
        holder.menu.setOnClickListener(callback);
        holder.menu.setTag(model);
        return v;
    }

    class ViewHolder{
        @InjectView(R.id.list_item_historial_tramite)TextView tramite;
        @InjectView(R.id.list_item_historial_dependencia)TextView dependencia;
        @InjectView(R.id.list_item_historial_fecha)TextView fecha;
        @InjectView(R.id.list_item_historial_menu)ImageButton menu;
        public ViewHolder(View v){
            ButterKnife.inject(this, v);
        }
    }
}
