package flujodetrabajo;

import java.util.*;

public class FlujoDeTrabajo {

	private String nombre;
	private Vector<Fase> fases;
	private Vector<Actividad> actividades;
	private Vector<Tarea> tareas;

	public FlujoDeTrabajo(String nombre) {
		this.nombre = nombre;
		this.actividades = new Vector<Actividad>();
		this.fases = new Vector<Fase>();
		this.tareas = new Vector<Tarea>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Vector<Fase> getFases() {
		return fases;
	}

	public void setFases(Vector<Fase> fases) {
		this.fases = fases;
	}

	public Vector<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(Vector<Actividad> actividades) {
		this.actividades = actividades;
	}

	public Vector<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(Vector<Tarea> tareas) {
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