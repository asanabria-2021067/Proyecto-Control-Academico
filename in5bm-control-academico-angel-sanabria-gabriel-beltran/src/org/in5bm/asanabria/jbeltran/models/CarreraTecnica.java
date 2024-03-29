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
public class CarreraTecnica {

    private String codigo;
    private String carrera;
    private String grado;
    private String jornada;
    private Character seccion;

    public CarreraTecnica() {
    }

    public CarreraTecnica(String codigo) {
        this.codigo = codigo;
    }

    public CarreraTecnica(String codigo, String carrera, String grado, String jornada, Character seccion) {
        this.codigo = codigo;
        this.carrera = carrera;
        this.grado = grado;
        this.jornada = jornada;
        this.seccion = seccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public Character getSeccion() {
        return seccion;
    }

    public void setSeccion(Character seccion) {
        this.seccion = seccion;
    }

    @Override
    public String toString() {
        return codigo + " | " + " " + carrera;
    }

}
