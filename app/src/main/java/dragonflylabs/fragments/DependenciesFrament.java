package dragonflylabs.fragments;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import dragonflylabs.api.Api;
import dragonflylabs.gobmx.R;
import dragonflylabs.models.Dependencia;
import dragonflylabs.models.DependenciaWrapper;
import dragonflylabs.utils.Alerts;
import dragonflylabs.utils.Connection;
import dragonflylabs.utils.GPSTracker;
import dragonflylabs.utils.Urls;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class DependenciesFrament extends Fragment implements Api.OnApiCallDownload<DependenciaWrapper>, GoogleMap.OnMarkerClickListener{

    SupportMapFragment mapFragment;
    GoogleMap googleMap;
    DependenciaWrapper dependenciaWrapper;
    ArrayList<Marker> markers;
    GPSTracker gps;
    @InjectView(R.id.fr_dependencias_txt_name) TextView name;
    @InjectView(R.id.fr_dependencias_txt_address) TextView address;
    @InjectView(R.id.fr_dependencias_txt_select) TextView select;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dependencies, container, false);
        mapFragment = SupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.fr_dependencias_map_container, mapFragment).commit();
        ButterKnife.inject(this, view);
        markers = new ArrayList<Marker>();
        gps = new GPSTracker(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpMapIfNeeded();
        refresh();
    }

    private void refresh() {
        if(Connection.isConnected(getActivity())) {
            Alerts.showProgressDialog(getActivity(), R.string.gob_dialog_loading_dependencies);
            new Api<Void, DependenciaWrapper>(getActivity()).get(DependenciaWrapper.class, Urls.API_DEPENDENCIAS, this);
        }else{
            Crouton.makeText(getActivity(), R.string.gob_error_connection, Style.ALERT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setUpMapIfNeeded() {
        if (googleMap == null) {
            FragmentManager fm = getFragmentManager();
            googleMap = mapFragment.getMap();
            if (googleMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        googleMap.setOnMarkerClickListener(this);
        googleMap.clear();
        if(dependenciaWrapper != null && googleMap != null) {
            ArrayList<Dependencia> dependencias = dependenciaWrapper.getDependencias();
            for (Dependencia dependencia : dependencias) {
                String strLat = dependencia.getLocation();
                if (strLat != null && !strLat.isEmpty()) {
                    double latitude = Double.valueOf(strLat.split(",")[0]);
                    double longitude = Double.valueOf(strLat.split(",")[1]);
                    Marker marker = googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_red))
                            .title(dependencia.getName())
                            .position(new LatLng(latitude, longitude)));
                    markers.add(marker);
                }
            }
        }
        if(gps.canGetLocation()){
            LatLng lat = new LatLng(gps.getLatitude(), gps.getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(lat, 12)));
        }else{
            LatLng lat = new LatLng(19.410375,-99.166738);
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(lat, 12)));
        }
    }

    @Override
    public void onSuccess(DependenciaWrapper object) {
        Alerts.stopProgressDialog();
        dependenciaWrapper = object;
        if(dependenciaWrapper != null) {
            if (dependenciaWrapper.getDependencias() != null) {
                setUpMapIfNeeded();
            } else {
                Crouton.makeText(getActivity(), R.string.gob_empty_dependencias, Style.INFO).show();
            }
        }else{
            Crouton.makeText(getActivity(), R.string.gob_empty_dependencias, Style.INFO).show();
        }
    }

    @Override
    public void onError(String error) {
        Alerts.stopProgressDialog();
        Crouton.makeText(getActivity(), R.string.gob_error_request, Style.ALERT).show();
    }

    Dependencia currentSelected;
    @Override
    public boolean onMarkerClick(Marker marker) {
        select.setVisibility(View.GONE);
        for(int i = 0; i < markers.size(); i++){
            Marker m = markers.get(i);
            if(marker.getTitle().equals(m.getTitle())){
                Dependencia model = dependenciaWrapper.getDependencias().get(i);
                currentSelected = model;
                name.setText(model.getName());
                address.setText(model.getAddress());
                break;
            }
        }
        return false;
    }

    @OnClick(R.id.fr_dependencias_btn_phone)
    public void onPhoneClick(ImageButton img){
        if(currentSelected != null && currentSelected.getPhone() != null){
            String uri = "tel:" + currentSelected.getPhone().trim() ;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(uri));
            startActivity(intent);
        }else{
            Crouton.makeText(getActivity(), R.string.gob_error_phone, Style.INFO).show();
        }
    }

    @OnClick(R.id.fr_dependencias_btn_calendar)
    public void onCalendarClick(ImageButton img){
        Crouton.makeText(getActivity(), "Se agenda la cita", Style.INFO).show();
    }

    @OnClick(R.id.fr_dependencias_btn_web)
    public void onWebClick(ImageButton img){
        if(currentSelected != null && currentSelected.getWebsite() != null) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(currentSelected.getWebsite()));
            startActivity(i);
        }else{
            Crouton.makeText(getActivity(), R.string.gob_error_web_request, Style.INFO).show();
        }
    }
}
