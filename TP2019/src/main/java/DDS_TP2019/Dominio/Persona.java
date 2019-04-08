package DDS_TP2019.Dominio;

import java.util.ArrayList;
import java.util.List;

public class Persona {

	private List<Guardarropa> guardarropas;

	public List<Guardarropa> getGuardarropas() {
		return guardarropas;
	}

	public void setGuardarropas(List<Guardarropa> guardarropas) {
		this.guardarropas = guardarropas;
	}

	public void agregarGuardarropa(Guardarropa guardarropa) {
		guardarropas.add(guardarropa);
	}
	
	public Persona() {
		super();
		this.guardarropas =  new ArrayList<Guardarropa>();
	}
	
	
}
