package dragonflylabs.models;

import java.util.ArrayList;

/**
 * Created by caprinet on 10/6/14.
 */
public class Tramite{

    public Boolean canMakeOnline() {
        return can_make_online;
    }

    public void setCanMakeOnline(Boolean canMakeOnline) {
        this.can_make_online = canMakeOnline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public String getUrl_tramite() {
        return url_tramite;
    }

    public void setUrl_tramite(String url_tramite) {
        this.url_tramite = url_tramite;
    }

    private Boolean can_make_online;
    private String description;
    private Integer id;
    private String name;
    private String resource_uri;
    private String url_tramite;
    private Dependencia dependency;

    public ArrayList<TramiteDetail> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<TramiteDetail> detalles) {
        this.detalles = detalles;
    }

    private ArrayList<TramiteDetail> detalles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dependencia getDependency() {
        return dependency;
    }

    public void setDependency(Dependencia dependency) {
        this.dependency = dependency;
    }
}
