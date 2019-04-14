package DDS_TP2019.Dominio;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

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
		List<Prenda> prendasSuperior = (List<Prenda>) prendas.stream().filter(prenda -> prenda.getTipoPrenda().esCategoria("superior"));
		List<Prenda> prendasInferior = (List<Prenda>) prendas.stream().filter(prenda -> prenda.getTipoPrenda().esCategoria("inferior"));
		List<Prenda> prendasCalzado = (List<Prenda>) prendas.stream().filter(prenda -> prenda.getTipoPrenda().esCategoria("calzado"));
		List<Prenda> prendasAccesorio = (List<Prenda>) prendas.stream().filter(prenda -> prenda.getTipoPrenda().esCategoria("accesorio"));
		List<List<Prenda>> atuendosPosibles = Lists.cartesianProduct(prendasSuperior,prendasInferior,prendasCalzado,prendasAccesorio);
		return atuendosPosibles.get(0);
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
