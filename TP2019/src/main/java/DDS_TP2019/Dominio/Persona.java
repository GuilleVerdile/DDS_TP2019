package DDS_TP2019.Dominio;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


public class Persona {
	
	private String nombre;
	
//	@JsonIgnoreProperties
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
	
	public Persona() {
		this.guardarropas =  new ArrayList<Guardarropa>();
	}

	public void agregarPrendaAguardarropa(int posGuardarropa, Prenda prenda) {
		this.guardarropas.get(posGuardarropa).agregarPrenda(prenda);		
	}
	
}
