package ar.com.ada.api.noaa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "boya")
public class Boya {

    @Column(name = "boya_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "color_luz")
    private String colorLuz;

    @Column(name = "longitud_instalacion")
    private double longitudInstalacion;

    @Column(name = "latitud_instalacion")
    private double latitudInstalacion;

    @JsonIgnore
    @OneToMany(mappedBy = "boya", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Muestra> muestras = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColorLuz() {
        return colorLuz;
    }

    public void setColorLuz(String colorLuz) {
        this.colorLuz = colorLuz;
    }

    public double getLongitudInstalacion() {
        return longitudInstalacion;
    }

    public void setLongitudInstalacion(double longitudInstalacion) {
        this.longitudInstalacion = longitudInstalacion;
    }

    public double getLatitudInstalacion() {
        return latitudInstalacion;
    }

    public void setLatitudInstalacion(double latitudInstalacion) {
        this.latitudInstalacion = latitudInstalacion;
    }

    public List<Muestra> getMuestras() {
        return muestras;
    }

    public void setMuestras(List<Muestra> muestras) {
        this.muestras = muestras;
    }

    // rel bidirct
    public void agregar(Muestra muestra) {
        this.muestras.add(muestra);
        // muestra.setBoya(this);
    }

}
