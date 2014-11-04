package dragonflylabs.gobmx;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
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
import dragonflylabs.models.Dependencia;
import dragonflylabs.utils.GPSTracker;
import dragonflylabs.utils.Statics;

/**
 * Created by caprinet on 10/30/14.
 */
public class DependencyDetailActivity extends FragmentActivity{

    SupportMapFragment mapFragment;
    GoogleMap googleMap;
    @InjectView(R.id.fr_dependencias_txt_name) TextView name;
    @InjectView(R.id.fr_dependencias_txt_address) TextView address;
    GPSTracker gps;
    Dependencia dependencia;
    @InjectView(R.id.fr_dependencias_txt_select)TextView select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dependencies);
        ActionBar ab = getActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.general_red)));
        ab.setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        mapFragment = SupportMapFragment.newInstance();
        dependencia = Statics.dependenciaSelected;
        getSupportFragmentManager().beginTransaction().replace(R.id.fr_dependencias_map_container, mapFragment).commit();
        ButterKnife.inject(this);
        gps = new GPSTracker(this);
        setTitle(dependencia.getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (googleMap == null) {
            FragmentManager fm = getSupportFragmentManager();
            googleMap = mapFragment.getMap();
            if (googleMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        googleMap.clear();
        String strLat = dependencia.getLocation();
        double latitude = Double.valueOf(strLat.split(",")[0]);
        double longitude = Double.valueOf(strLat.split(",")[1]);
        googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_red))
                .title(dependencia.getName())
                .position(new LatLng(latitude, longitude)));
        if(gps.canGetLocation()){
            LatLng lat = new LatLng(gps.getLatitude(), gps.getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(lat, 12)));
        }else{
            LatLng lat = new LatLng(19.410375,-99.166738);
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(lat, 12)));
        }
        select.setVisibility(View.GONE);
        name.setText(dependencia.getName());
        address.setText(dependencia.getAddress());
    }

    @OnClick(R.id.fr_dependencias_btn_phone)
    public void onPhoneClick(ImageButton img){
        if(dependencia != null && dependencia.getPhone() != null){
            String uri = "tel:" + dependencia.getPhone().trim() ;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(uri));
            startActivity(intent);
        }else{
            Crouton.makeText(this, R.string.gob_error_phone, Style.INFO).show();
        }
    }

    @OnClick(R.id.fr_dependencias_btn_calendar)
    public void onCalendarClick(ImageButton img){
        Intent i = new Intent(this, MeetingAddActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.fr_dependencias_btn_web)
    public void onWebClick(ImageButton img){
        if(dependencia != null && dependencia.getWebsite() != null) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(dependencia.getWebsite()));
            startActivity(i);
        }else{
            Crouton.makeText(this, R.string.gob_error_web_request, Style.INFO).show();
        }
    }
}
