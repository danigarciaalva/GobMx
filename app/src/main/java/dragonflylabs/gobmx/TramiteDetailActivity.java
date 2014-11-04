package dragonflylabs.gobmx;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import dragonflylabs.adapters.TramiteDetailAdapter;
import dragonflylabs.models.Tramite;
import dragonflylabs.models.TramiteDetail;
import dragonflylabs.utils.Statics;
import it.sephiroth.android.library.floatingmenu.FloatingActionItem;
import it.sephiroth.android.library.floatingmenu.FloatingActionMenu;


public class TramiteDetailActivity extends BaseActivity implements View.OnClickListener, FloatingActionMenu.OnItemClickListener {

    @InjectView(R.id.act_tramite_detail_dependency_address) TextView dependency_address;
    @InjectView(R.id.act_tramite_detail_dependency_name) TextView dependency_name;
    @InjectView(R.id.act_tramite_detail_list) ListView listView;
    GoogleMap googleMap;
    Tramite tramite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tramite_detail);
        ButterKnife.inject(this);
        tramite = Statics.tramiteSelected;
        setTitle(tramite.getName());
        dependency_address.setText(tramite.getDependency().getAddress());
        dependency_name.setText(tramite.getDependency().getName());
        listView.setAdapter(new TramiteDetailAdapter(this, R.layout.card_tramite_detail, tramite.getDetalles(), this));

        int action_item_padding = 18;
        FloatingActionItem item1 = new FloatingActionItem.Builder(0)
                .withResId(R.drawable.ic_phone)
                .withDelay(0)
                .withPadding(action_item_padding)
                .build();

        FloatingActionItem item2 = new FloatingActionItem.Builder(1)
                .withResId(R.drawable.ic_calendar)
                .withDelay(50)
                .withPadding(action_item_padding)
                .build();

        FloatingActionItem item3 = new FloatingActionItem.Builder(2)
                .withResId(R.drawable.ic_web)
                .withDelay(100)
                .withPadding(action_item_padding)
                .build();

        FloatingActionMenu mFloatingMenu = new FloatingActionMenu
                .Builder(this)
                .addItem(item1)
                .addItem(item2)
                .addItem(item3)
                .withScrollDelegate(new FloatingActionMenu.AbsListViewScrollDelegate(listView))
                .withThreshold(R.dimen.float_action_threshold)
                .withGap(R.dimen.float_action_item_gap)
                .withHorizontalPadding(R.dimen.float_action_h_padding)
                .withVerticalPadding(R.dimen.float_action_v_padding)
                .withGravity(FloatingActionMenu.Gravity.RIGHT | FloatingActionMenu.Gravity.BOTTOM)
                .withDirection(FloatingActionMenu.Direction.Horizontal)
                .animationDuration(300)
                .animationInterpolator(new OvershootInterpolator())
                .visible(true)
                .build();

        mFloatingMenu.setOnItemClickListener(this);
        setUpMapIfNeeded();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (googleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.act_tramite_detail_map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (googleMap != null) {
                double lat = Double.parseDouble(tramite.getDependency().getLocation().split(",")[0]);
                double lon = Double.parseDouble(tramite.getDependency().getLocation().split(",")[1]);
                LatLng latlon = new LatLng(lat, lon);
                googleMap.addMarker(new MarkerOptions().position(latlon).title(tramite.getDependency().getName()));
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(new LatLng(latlon.latitude, latlon.longitude+0.002111), 15)));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tramite_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.cart_tramite_detail_btn_more){
            TramiteDetail tramite = (TramiteDetail)view.getTag();
            new AlertDialog.Builder(this).setTitle(tramite.getApartado().getName()).setMessage(tramite.getDescription()).setPositiveButton("Aceptar", null).show();
        }
    }

    @Override
    public void onItemClick(FloatingActionMenu floatingActionMenu, int i) {
        switch (i){
            case 0:
                makePhoneCall();
                break;
            case 1:
                makeDate();
                break;
            case 2:
                makeWebRequest();
                break;
        }
    }

    private void makeWebRequest() {
        if(tramite.getDependency() != null && tramite.getDependency().getWebsite() != null) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(tramite.getDependency().getWebsite()));
            startActivity(i);
        }else{
            Crouton.makeText(this, R.string.gob_error_web_request, Style.INFO).show();
        }
    }

    private void makeDate() {
        Intent i = new Intent(this, MeetingAddActivity.class);
        startActivity(i);
    }

    private void makePhoneCall() {
        if(tramite.getDependency() != null && tramite.getDependency().getPhone() != null){
            String uri = "tel:" + tramite.getDependency().getPhone().trim() ;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(uri));
            startActivity(intent);
        }else{
            Crouton.makeText(this, R.string.gob_error_phone, Style.INFO).show();
        }
    }
}
