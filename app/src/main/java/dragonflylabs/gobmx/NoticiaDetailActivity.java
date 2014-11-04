package dragonflylabs.gobmx;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dragonflylabs.models.Noticia;
import dragonflylabs.ui.BlurredImageView;
import dragonflylabs.utils.Urls;


public class NoticiaDetailActivity extends BaseActivity {

    public static final String NOTICIA_ITEM = "noticia";
    @InjectView(R.id.act_noticia_detail_image) BlurredImageView image;
    @InjectView(R.id.act_noticia_detail_txt_title) TextView title;
    @InjectView(R.id.act_noticia_detail_txt_description) TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_detail);
        ButterKnife.inject(this);
        Noticia model = new Gson().fromJson(getIntent().getStringExtra(NOTICIA_ITEM), Noticia.class);
        setTitle(model.getTitle());
        Picasso.with(this).load(Urls.API_ENDPOINT+model.getImage_portrait()).into(image);
        title.setText(model.getTitle());
        description.setText(model.getDescription());
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.noticia_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if(id == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
