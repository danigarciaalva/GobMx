package dragonflylabs.models;

import java.util.ArrayList;

/**
 * Created by caprinet on 10/18/14.
 */
public class DependenciaWrapper {

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ArrayList<Dependencia> getDependencias() {
        return dependencias;
    }

    public void setDependencias(ArrayList<Dependencia> dependencias) {
        this.dependencias = dependencias;
    }

    private Meta meta;
    private ArrayList<Dependencia> dependencias;
}
