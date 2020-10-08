package flujodetrabajo;

import java.util.*;

public class FlujoDeTrabajo {

	private String nombre;
	private ArrayList<Fase> fases;
	private ArrayList<Actividad> actividades;
	private ArrayList<Tarea> tareas;

	public FlujoDeTrabajo(String nombre) {
		this.nombre = nombre;
		this.actividades = new ArrayList<Actividad>();
		this.fases = new ArrayList<Fase>();
		this.tareas = new ArrayList<Tarea>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Fase> getFases() {
		return fases;
	}

	public void setFases(ArrayList<Fase> fases) {
		this.fases = fases;
	}

	public ArrayList<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(ArrayList<Actividad> actividades) {
		this.actividades = actividades;
	}

	public ArrayList<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(ArrayList<Tarea> tareas) {
		this.tareas = tareas;
	}

	@Override
	public String toString() {
		return "FlujoDeTrabajo{" +
				"nombre='" + nombre + '\'' +
				", fases=" + fases +
				", actividades=" + actividades +
				", tareas=" + tareas +
				'}';
	}
}