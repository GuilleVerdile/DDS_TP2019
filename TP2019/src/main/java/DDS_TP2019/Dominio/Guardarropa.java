package DDS_TP2019.Dominio;

import java.util.ArrayList;
import java.util.List;

public class Guardarropa {

	private List<Prenda> prendas;

	public List<Prenda> getPrendas() {
		return prendas;
	}

	public void setPrendas(List<Prenda> prendas) {
		this.prendas = prendas;
	}

	public void agregarPrenda(Prenda prenda) {
		prendas.add(prenda);
	}
	
//	public Atuendo recomendarAtuendo() {
//		return 
//	}
	
	public Guardarropa() {
		super();
		this.prendas =  new ArrayList<Prenda>();
	}
	
	
	
}
