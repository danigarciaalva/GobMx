package dragonflylabs.models;

import java.util.ArrayList;

/**
 * Created by caprinet on 10/30/14.
 */
public class Historial {

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    private Tramite tramite;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private String date;
    private int rating;

}
