package dragonflylabs.models;

/**
 * Created by caprinet on 10/19/14.
 */
public class TramiteDetail {

    public Apartado getApartado() {
        return apartado;
    }

    public void setApartado(Apartado apartado) {
        this.apartado = apartado;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Apartado apartado;
    private String description;
    private int id;
}
