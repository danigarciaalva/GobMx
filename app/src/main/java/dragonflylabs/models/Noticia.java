package dragonflylabs.models;

/**
 * Created by caprinet on 10/9/14.
 */
public class Noticia {

    private String title;
    private String description;

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    private String fecha_inicio;
    private String fecha_fin;
    public String getImage_portrait() {
        return image_portrait;
    }

    public void setImage_portrait(String image_portrait) {
        this.image_portrait = image_portrait;
    }

    public String getImage_landscape() {
        return image_landscape;
    }

    public void setImage_landscape(String image_landscape) {
        this.image_landscape = image_landscape;
    }

    public String getImage_thumbnail() {
        return image_thumbnail;
    }

    public void setImage_thumbnail(String image_thumbnail) {
        this.image_thumbnail = image_thumbnail;
    }

    private String image_portrait;
    private String image_landscape;
    private String image_thumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
