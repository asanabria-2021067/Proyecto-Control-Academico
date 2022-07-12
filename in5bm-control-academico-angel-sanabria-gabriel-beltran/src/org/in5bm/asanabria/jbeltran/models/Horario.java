package org.in5bm.asanabria.jbeltran.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.*;

/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:12:25
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class Horario {

    IntegerProperty id;
    private ObjectProperty<LocalTime> horarioInicio;
    private ObjectProperty<LocalTime> horarioFinal;
    BooleanProperty lunes;
    BooleanProperty martes;
    BooleanProperty miercoles;
    BooleanProperty jueves;
    BooleanProperty viernes;

    public Horario() {
        this.id = new SimpleIntegerProperty();
        this.horarioInicio = new SimpleObjectProperty();
        this.horarioFinal = new SimpleObjectProperty();
        this.lunes = new SimpleBooleanProperty();
        this.martes = new SimpleBooleanProperty();
        this.miercoles = new SimpleBooleanProperty();
        this.jueves = new SimpleBooleanProperty();
        this.viernes = new SimpleBooleanProperty();

    }

    public Horario(int id, LocalTime horarioInicio, LocalTime horarioFinal, boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes) {
        this.id = new SimpleIntegerProperty(id);
        this.horarioInicio = new SimpleObjectProperty(horarioInicio);
        this.horarioFinal = new SimpleObjectProperty(horarioFinal);
        this.lunes = new SimpleBooleanProperty(lunes);
        this.martes = new SimpleBooleanProperty(martes);
        this.miercoles = new SimpleBooleanProperty(miercoles);
        this.jueves = new SimpleBooleanProperty(jueves);
        this.viernes = new SimpleBooleanProperty(viernes);
    }

    public IntegerProperty id() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public ObjectProperty horarioInicio() {
        return horarioInicio;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio.get();
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio.set(horarioInicio);
    }

    public ObjectProperty horarioFinal() {
        return horarioFinal;
    }

    public LocalTime getHorarioFinal() {
        return horarioFinal.get();
    }

    public void setHorarioFinal(LocalTime horarioFinal) {
        this.horarioFinal.set(horarioFinal);
    }

    public BooleanProperty lunes() {
        return lunes;
    }

    public boolean getLunes() {
        return lunes.get();
    }

    public void setLunes(boolean lunes) {
        this.lunes.set(lunes);
    }

    public BooleanProperty martes() {
        return martes;
    }

    public boolean getMartes() {
        return martes.get();
    }

    public void setMartes(boolean martes) {
        this.martes.set(martes);
    }

    public BooleanProperty miercoles() {
        return miercoles;
    }

    public boolean getMiercoles() {
        return miercoles.get();
    }

    public void setMiercoles(boolean miercoles) {
        this.miercoles.set(miercoles);
    }

    public BooleanProperty jueves() {
        return jueves;
    }

    public boolean getJueves() {
        return jueves.get();
    }

    public void setJueves(boolean jueves) {
        this.jueves.set(jueves);
    }

    public BooleanProperty viernes() {
        return viernes;
    }

    public boolean getViernes() {
        return viernes.get();
    }

    public void setViernes(boolean viernes) {
        this.viernes.set(viernes);
    }

    @Override
    public String toString() {
        return id.get() + " | " + " " + horarioInicio.get() + " -- " + horarioFinal.get();
    }

}
