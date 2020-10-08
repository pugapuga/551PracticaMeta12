package flujodetrabajo;

import java.util.*;

public class Actividad {

	private String nombre;
	private FlujoDeTrabajo flujoDeTrabajo;
	private ArrayList<Tarea> tareas;

	public Actividad(String nombre, FlujoDeTrabajo flujoDeTrabajo) {
		this.nombre = nombre;
		this.flujoDeTrabajo = flujoDeTrabajo;
		this.tareas = new ArrayList<Tarea>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public FlujoDeTrabajo getFlujoDeTrabajo() {
		return flujoDeTrabajo;
	}

	public void setFlujoDeTrabajo(FlujoDeTrabajo flujoDeTrabajo) {
		this.flujoDeTrabajo = flujoDeTrabajo;
	}

	public ArrayList<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(ArrayList<Tarea> tareas) {
		this.tareas = tareas;
	}

	@Override
	public String toString() {
		return "Actividad{" +
				"nombre='" + nombre + '\'' +
				'}';
	}
}