package org.in5bm.asanabria.jbeltran.models;
/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:10:17
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class Roles {
    int id;
    String descripcion;

    public Roles() {
    }

    public Roles(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
