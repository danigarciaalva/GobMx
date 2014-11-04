package dragonflylabs.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dragonflylabs.gobmx.R;
import dragonflylabs.models.Comentario;

/**
 * Created by caprinet on 11/3/14.
 */
public class ComentariosAdapter extends ArrayAdapter<Comentario> {

    public ComentariosAdapter(Context context, int resId, ArrayList<Comentario> comentarios) {
        super(context, resId, comentarios);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder;
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_item_comentario, null);
            holder = new ViewHolder(v);
            v.setTag(holder);
        }else{
            holder = (ViewHolder)v.getTag();
        }
        Comentario model = getItem(position);
        holder.comennt.setText(model.getComment());
        holder.user.setText(model.getUser());
        if(model.isCommented()){
            holder.icon_voted.setImageResource(R.drawable.ic_voted);
            holder.voted.setText("Votado");
        }else{
            holder.icon_voted.setImageResource(R.drawable.ic_no_voted);
            holder.voted.setText("Votar");
        }
        return v;
    }

    class ViewHolder{
        @InjectView(R.id.list_item_comentario_comment)TextView comennt;
        @InjectView(R.id.list_item_comentario_user)TextView user;
        @InjectView(R.id.list_item_comentario_voted)TextView voted;
        @InjectView(R.id.list_item_comentario_icon_voted)ImageView icon_voted;

        public ViewHolder(View v){
            ButterKnife.inject(this, v);
        }
    }
}
