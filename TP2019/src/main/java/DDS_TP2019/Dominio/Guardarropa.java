package DDS_TP2019.Dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Guardarropa {

	private Set<Prenda> prendas;

	public Set<Prenda> getPrendas() {
		return prendas;
	}

	public void setPrendas(Set<Prenda> prendas) {
		this.prendas = prendas;
	}

	public void agregarPrenda(Prenda prenda) {
		prendas.add(prenda);
	}
	
	public void recomendarAtuendo() {	//Version Entrega 1 ... no se usa mas	
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
	
	public Set<Atuendo> sugerirAtuendos(Double temperatura, String tipoDeEvento){  // VERSION ENTREGA 2  
		return GenerarSugerencias.getInstance().ejecutar(this.prendas,temperatura,tipoDeEvento);
	} 

	public Guardarropa() {
		super();
		this.prendas = Sets.newHashSet();
	}
	
	public Guardarropa(Set <Prenda> prendas) {
		this.prendas = prendas;
	}

	public void mostrarPrendas() {
		prendas.forEach(prenda -> prenda.mostrarDetalles());
	}

	public int cantidadDePrendas() {
		return prendas.size();
	}

	
	
	
}
