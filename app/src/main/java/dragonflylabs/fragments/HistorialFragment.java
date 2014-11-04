package dragonflylabs.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupMenu;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import dragonflylabs.adapters.HistorialAdapter;
import dragonflylabs.adapters.TramiteAdapter;
import dragonflylabs.api.Api;
import dragonflylabs.gobmx.DependencyDetailActivity;
import dragonflylabs.gobmx.R;
import dragonflylabs.gobmx.TramiteDetailActivity;
import dragonflylabs.models.Historial;
import dragonflylabs.models.HistorialWrapper;
import dragonflylabs.models.TramiteWrapper;
import dragonflylabs.utils.Alerts;
import dragonflylabs.utils.Connection;
import dragonflylabs.utils.Statics;
import dragonflylabs.utils.Urls;

/**
 * Created by caprinet on 10/30/14.
 */
public class HistorialFragment extends Fragment implements Api.OnApiCallDownload<HistorialWrapper>, PopupMenu.OnMenuItemClickListener, View.OnClickListener{

    @InjectView(R.id.fr_historial_list) ListView mListView;
    Activity mContext;
    HistorialWrapper tramiteWrapper;
    Historial currentElementSelected;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_historial, container, false);
        ButterKnife.inject(this, v);
        mContext = getActivity();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refresh();
    }

    public void refresh(){
        if(Connection.isConnected(mContext)) {
            Alerts.showProgressDialog(mContext, R.string.gob_dialog_loading_historial);
            new Api<Void, HistorialWrapper>(mContext).get(HistorialWrapper.class, Urls.API_TRAMITES_HISTORIAL, this);
        }else{
            Crouton.makeText(mContext, R.string.gob_error_connection, Style.ALERT).show();
        }
    }

    @Override
    public void onSuccess(HistorialWrapper object) {
        Alerts.stopProgressDialog();
        if(object!= null) {
            tramiteWrapper = object;
            mListView.setAdapter(new HistorialAdapter(getActivity(), R.layout.list_item_historial, object.getHistorial(), this));
        }else{
            Crouton.makeText(mContext, R.string.gob_empty_historial, Style.ALERT).show();
        }
    }

    @Override
    public void onError(String error) {
        Alerts.stopProgressDialog();
        Crouton.makeText(mContext, R.string.gob_error_request, Style.ALERT).show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_see_dependency:
                if(currentElementSelected != null){
                    Intent i = new Intent(mContext, DependencyDetailActivity.class);
                    Statics.dependenciaSelected = currentElementSelected.getTramite().getDependency();
                    startActivity(i);
                }
                break;
            case R.id.action_see_tramit:
                if(currentElementSelected != null) {
                    Intent i = new Intent(mContext, TramiteDetailActivity.class);
                    Statics.tramiteSelected = currentElementSelected.getTramite();
                    startActivity(i);
                }
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        currentElementSelected = (Historial)view.getTag();
        PopupMenu popupMenu = new PopupMenu(mContext, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.historial_item_menu);
        popupMenu.show();
    }
}
