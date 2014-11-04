package dragonflylabs.models;

import java.util.ArrayList;

/**
 * Created by caprinet on 10/30/14.
 */
public class HistorialWrapper {

    public ArrayList<Historial> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<Historial> historial) {
        this.historial = historial;
    }

    private ArrayList<Historial> historial;
}
