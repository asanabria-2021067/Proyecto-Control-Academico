package org.in5bm.asanabria.jbeltran.models;

/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:12:25
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class Alumno {

    private String carne;
    private String nombre1;
    private String nombre2;
    private String nombre3;
    private String apellido1;
    private String apellido2;

    public Alumno() {
    }

    public Alumno(String carne) {
        this.carne = carne;
    }

    public Alumno(String carne, String nombre1, String apellido1) {
        this.carne = carne;
        this.nombre1 = nombre1;
        this.apellido1 = apellido1;
    }

    public Alumno(String carne, String nombre1, String nombre2, String nombre3, String apellido1, String apellido2) {
        this.carne = carne;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.nombre3 = nombre3;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getNombre3() {
        return nombre3;
    }

    public void setNombre3(String nombre3) {
        this.nombre3 = nombre3;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    @Override
    public String toString() {
        return carne.toString() + " | " + nombre1.toString() + " " + apellido1.toString();
    }

}
