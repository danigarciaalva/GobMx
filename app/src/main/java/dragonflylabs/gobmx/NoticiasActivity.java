package dragonflylabs.gobmx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by caprinet on 10/9/14.
 */
public class NoticiasActivity extends Activity{

    Button btnRefresh;
    ListView listView;
    TextView txtLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        btnRefresh = (Button)findViewById(R.id.act_noticias_btn_refresh);
        listView = (ListView)findViewById(R.id.act_noticias_list);
        txtLabel = (TextView)findViewById(R.id.act_noticias_txt);
    }

}
