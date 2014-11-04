package dragonflylabs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dragonflylabs.gobmx.R;
import dragonflylabs.models.TramiteDetail;

/**
 * Created by caprinet on 10/22/14.
 */
public class TramiteDetailAdapter extends ArrayAdapter<TramiteDetail>{


    private Context mContext;
    private ArrayList<TramiteDetail> mItems;
    private View.OnClickListener callback;
    public TramiteDetailAdapter(Context context, int resId, ArrayList<TramiteDetail> objects, View.OnClickListener callback){
        super(context, resId, objects);
        this.mContext = context;
        this.mItems = objects;
        this.callback = callback;
    }

    @Override
    public int getCount() {
        return this.mItems.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.card_tramite_detail, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        TramiteDetail detail = getItem(position);
        String des = null;
        if(detail.getDescription().length() > 200) {
            des = detail.getDescription().substring(0, 200);
            holder.seemore.setTag(detail);
            holder.seemore.setOnClickListener(callback);
            holder.seemore.setVisibility(View.VISIBLE);
            holder.separator.setVisibility(View.VISIBLE);
        }else{
            des = detail.getDescription();
            holder.seemore.setVisibility(View.GONE);
            holder.separator.setVisibility(View.GONE);
        }
        holder.title.setText(detail.getApartado().getName());
        holder.description.setText(des);
        return convertView;
    }

    class ViewHolder {

        @InjectView(R.id.cart_tramite_detail_title) TextView title;
        @InjectView(R.id.cart_tramite_detail_description) TextView description;
        @InjectView(R.id.cart_tramite_detail_view_separator) View separator;
        @InjectView(R.id.cart_tramite_detail_btn_more) Button seemore;
        public ViewHolder(View view){
            ButterKnife.inject(this, view);
        }
    }
}
