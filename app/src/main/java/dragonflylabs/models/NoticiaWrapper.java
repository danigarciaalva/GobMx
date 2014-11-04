package dragonflylabs.models;

import java.util.ArrayList;

/**
 * Created by caprinet on 10/18/14.
 */
public class NoticiaWrapper {

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ArrayList<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(ArrayList<Noticia> noticias) {
        this.noticias = noticias;
    }

    private Meta meta;
    private ArrayList<Noticia> noticias;
}
