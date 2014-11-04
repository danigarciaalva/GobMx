package dragonflylabs.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dragonflylabs.adapters.ComentariosAdapter;
import dragonflylabs.gobmx.R;
import dragonflylabs.models.Comentario;

/**
 * Created by caprinet on 11/3/14.
 */
public class ComentariosFragment extends android.support.v4.app.Fragment {

    @InjectView(R.id.fr_comentarios_list)ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comentarios, container, false);
        ButterKnife.inject(this, v);
        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
        Comentario comment = new Comentario();
        comment.setComment("Me gustaría que abrieran una nueva oficina del DIF cerca de mi casa en Perisur, ya que la más cercana se encuentra a 20 kilómetros.");
        comment.setUser("Joel Humberto Gómez Paredes");
        comment.setCommented(true);
        Comentario comment2 = new Comentario();
        comment2.setComment("En SEDESOL aún no hacen trámites digitales, estaría bien que pudieran hacerse desde la app de Gob.Mx\n.");
        comment2.setUser("Daniel García Alvarado");
        comment2.setCommented(false);
        comentarios.add(comment);
        comentarios.add(comment2);
        mListView.setAdapter(new ComentariosAdapter(getActivity(), R.layout.list_item_comentario, comentarios));
        return v;
    }
}
