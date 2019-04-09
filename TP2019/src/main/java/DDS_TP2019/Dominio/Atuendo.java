package DDS_TP2019.Dominio;

import java.util.ArrayList;
import java.util.List;

public class Atuendo {
	
	private List<Prenda> prendas; 
	
	public List<Prenda> getPrendas() {
		return prendas;
	}

	public void setPrendas(List<Prenda> prendas) {
		this.prendas = prendas;
	}

	public Atuendo(List<Prenda> prendas) {
		super();
		this.prendas = new ArrayList<Prenda>();;
	}
	
	
}
