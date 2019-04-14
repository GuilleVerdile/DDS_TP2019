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
	
	public List<Prenda> recomendarAtuendo() {		
		List<Prenda> prendasSuperior = (List<Prenda>) prendas.stream().filter(prenda -> prenda.getTipoPrenda().esSuperior());
		List<Prenda> prendasInferior = (List<Prenda>) prendas.stream().filter(prenda -> prenda.getTipoPrenda().esInferior());
		List<Prenda> prendasCalzado = (List<Prenda>) prendas.stream().filter(prenda -> prenda.getTipoPrenda().esCalzado());
		List<Prenda> prendasAccesorio = (List<Prenda>) prendas.stream().filter(prenda -> prenda.getTipoPrenda().esAccesorio());
		return prendasAccesorio;
	}
	
	public Guardarropa() {
		super();
		this.prendas =  new ArrayList<Prenda>();
	}
	
	public Guardarropa(List <Prenda> prendas) {
		this.prendas =  prendas;
	}

	public void mostrarPrendas() {
		// TODO Auto-generated method stub
		prendas.forEach(prenda -> prenda.mostrarDetalles());
	}

	
	
	
}
