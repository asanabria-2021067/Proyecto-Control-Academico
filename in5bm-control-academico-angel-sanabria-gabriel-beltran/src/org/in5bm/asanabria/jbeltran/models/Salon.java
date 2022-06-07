package org.in5bm.asanabria.jbeltran.models;

/**
 *
 * @author Angel Sanabria y Gabriel Beltran
 * @date 29/04/2022
 * @time 16:05:06
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067, 2021022
 */
public class Salon {

    private String codigo;
    private String descripcion;
    private String edificio;
    private int nivel;
    private int capacidadMax;

    public Salon() {
        
    }

    public Salon(String codigo, int capacidadMax) {
        this.codigo = codigo;
        this.capacidadMax = capacidadMax;
    }

    public Salon(String codigo, String descripcion, String edificio, int nivel, int capacidadMax) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.edificio = edificio;
        this.nivel = nivel;
        this.capacidadMax = capacidadMax;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    @Override
    public String toString() {
        return "Salon{" + "codigo=" + codigo + ", descripcion=" + descripcion + ", edificio=" + edificio + ", nivel=" + nivel + ", capacidadMax=" + capacidadMax + '}';
    }
    
    
    
}
