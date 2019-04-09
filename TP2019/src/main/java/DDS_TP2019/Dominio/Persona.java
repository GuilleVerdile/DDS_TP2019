package DDS_TP2019.Dominio;

import java.util.ArrayList;
import java.util.List;

public class Persona {

	private String nombre;
	private List<Guardarropa> guardarropas;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Guardarropa> getGuardarropas() {
		return guardarropas;
	}

	public void setGuardarropas(List<Guardarropa> guardarropas) {
		this.guardarropas = guardarropas;
	}

	public void agregarGuardarropa(Guardarropa guardarropa) {
		guardarropas.add(guardarropa);
	}
	
	public Persona(String nombre) {
		super();
		this.nombre = nombre;
		this.guardarropas =  new ArrayList<Guardarropa>();
	}
	
	
}
