package DDS_TP2019.Dominio;

import java.util.ArrayList;
import java.util.List;

public class Sistema {

	private List<Persona> personas;
	private List<TipoTela> tiposTelas;
	private List<String> colores;
	
	public List<String> getColores() {
		return colores;
	}
	public void setColores(List<String> colores) {
		this.colores = colores;
	}
	
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	public List<TipoTela> getTiposTelas() {
		return tiposTelas;
	}
	public void setTiposTelas(List<TipoTela> tiposTelas) {
		this.tiposTelas = tiposTelas;
	}
	
	public void agregarPersona(Persona persona) {
		personas.add(persona);
	}
	
	public Sistema() {
		super();
		this.personas = new ArrayList<Persona>();
		this.tiposTelas = new ArrayList<TipoTela>();
		this.colores =  new ArrayList<String>();
	}
	
	
	
}
