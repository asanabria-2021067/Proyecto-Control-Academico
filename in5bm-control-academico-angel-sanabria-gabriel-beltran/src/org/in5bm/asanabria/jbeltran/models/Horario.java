package org.in5bm.asanabria.jbeltran.models;

import java.time.LocalTime;

/**
 *
 * @author informatica
 */
public class Horario {
    int id;
    LocalTime horarioInicio;
    LocalTime horarioFinal;
    boolean lunes;
    boolean martes;
    boolean miercoles;
    boolean jueves;
    boolean viernes;

    public Horario(int id, LocalTime horarioInicio, LocalTime horarioFinal) {
        this.id = id;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
    }

    public Horario(int id, LocalTime horarioInicio, LocalTime horarioFinal, boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes) {
        this.id = id;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
    }

    public Horario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public boolean getLunes() {
        return lunes;
    }

    public void setLunes(boolean lunes) {
        this.lunes = lunes;
    }

    public boolean getMartes() {
        return martes;
    }

    public void setMartes(boolean martes) {
        this.martes = martes;
    }

    public boolean getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(boolean miercoles) {
        this.miercoles = miercoles;
    }

    public boolean getJueves() {
        return jueves;
    }

    public void setJueves(boolean jueves) {
        this.jueves = jueves;
    }

    public boolean getViernes() {
        return viernes;
    }

    public void setViernes(boolean viernes) {
        this.viernes = viernes;
    }
    
    
}
