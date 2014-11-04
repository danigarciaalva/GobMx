package dragonflylabs.models;

import java.util.ArrayList;

/**
 * Created by caprinet on 10/17/14.
 */
public class TramiteWrapper {

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ArrayList<Tramite> getTramites() {
        return tramites;
    }

    public void setTramites(ArrayList<Tramite> tramites) {
        this.tramites = tramites;
    }

    private Meta meta;
    private ArrayList<Tramite> tramites;
}
