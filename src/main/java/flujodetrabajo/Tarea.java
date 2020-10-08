package flujodetrabajo;

public class Tarea {

	private String nombre;
	private Actividad actividad;
	private Fase fase;
	private FlujoDeTrabajo flujoDeTrabajo;

	public Tarea(String nombre, Actividad actividad, Fase fase, FlujoDeTrabajo flujoDeTrabajo) {
		this.nombre = nombre;
		this.actividad = actividad;
		this.fase = fase;
		this.flujoDeTrabajo = flujoDeTrabajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public FlujoDeTrabajo getFlujoDeTrabajo() {
		return flujoDeTrabajo;
	}

	public void setFlujoDeTrabajo(FlujoDeTrabajo flujoDeTrabajo) {
		this.flujoDeTrabajo = flujoDeTrabajo;
	}

	@Override
	public String toString() {
		return "Tarea{" +
				"nombre='" + nombre + '\'' +
				'}';
	}
}