package DDS_TP2019.Dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public void recomendarAtuendo() {		
		List<Prenda> prendasSuperior = prendas.stream().filter(prenda -> prenda.getTipoPrenda().esCategoria("partesuperior")).collect(Collectors.toList());;
		List<Prenda> prendasInferior = prendas.stream().filter(prenda -> prenda.getTipoPrenda().esCategoria("parteinferior")).collect(Collectors.toList());;
		List<Prenda> prendasCalzado = prendas.stream().filter(prenda -> prenda.getTipoPrenda().esCategoria("calzado")).collect(Collectors.toList());;
		List<Prenda> prendasAccesorio = prendas.stream().filter(prenda -> prenda.getTipoPrenda().esCategoria("accesorio")).collect(Collectors.toList());
		
		List<List<Prenda>> atuendosCompletos = Lists.cartesianProduct(prendasSuperior,prendasInferior,prendasCalzado,prendasAccesorio);
		List<List<Prenda>> atuendosIncompletos = Lists.cartesianProduct(prendasSuperior,prendasInferior,prendasCalzado);
		List<List<Prenda>> atuendosPosibles = new ArrayList<List<Prenda>>();
		atuendosPosibles.addAll(atuendosCompletos);
		atuendosPosibles.addAll(atuendosIncompletos);
		for(List<Prenda> atuendo : atuendosPosibles) {
		System.out.println("Detalles de atuendo: \n");
		atuendo.forEach(prenda -> prenda.mostrarDetalles());
		}
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
