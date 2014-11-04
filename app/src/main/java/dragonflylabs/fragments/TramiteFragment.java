package dragonflylabs.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import dragonflylabs.adapters.TramiteAdapter;
import dragonflylabs.api.Api;
import dragonflylabs.gobmx.R;
import dragonflylabs.gobmx.TramiteDetailActivity;
import dragonflylabs.models.TramiteWrapper;
import dragonflylabs.utils.Alerts;
import dragonflylabs.utils.Connection;
import dragonflylabs.utils.Statics;
import dragonflylabs.utils.Urls;

public class TramiteFragment extends Fragment implements Api.OnApiCallDownload<TramiteWrapper>, AdapterView.OnItemClickListener {

    @InjectView(R.id.fr_main_list)ListView mListView;
    TramiteWrapper tramiteWrapper;
    Activity mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mContext = getActivity();
        ButterKnife.inject(this, v);
        mListView.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refresh();
    }

    public void refresh(){
        if(Connection.isConnected(mContext)) {
            Alerts.showProgressDialog(mContext, R.string.gob_dialog_loading_tramites);
            new Api<Void, TramiteWrapper>(mContext).get(TramiteWrapper.class, Urls.API_TRAMITES, this);
        }else{
            Crouton.makeText(mContext, R.string.gob_error_connection, Style.ALERT).show();
        }
    }

    @Override
    public void onSuccess(TramiteWrapper object) {
        Alerts.stopProgressDialog();
        if(object!= null) {
            tramiteWrapper = object;
            mListView.setAdapter(new TramiteAdapter(getActivity(), R.layout.list_item_tramite, object.getTramites()));
        }else{
            Crouton.makeText(mContext, R.string.gob_empty_tramites, Style.ALERT).show();
        }
    }

    @Override
    public void onError(String error) {
        Alerts.stopProgressDialog();
        Crouton.makeText(mContext, R.string.gob_error_request, Style.ALERT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Statics.tramiteSelected = tramiteWrapper.getTramites().get(i);
        startActivity(new Intent(getActivity(), TramiteDetailActivity.class));
    }
}
