package DDS_TP2019.Dominio;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

public class Combinar {

	public static Combinar instancia;
	
	public static Combinar getInstance() {
		if(instancia == null) {
			instancia = new Combinar();
		}
		return instancia;
	}
	
	public Set<Atuendo> ejecutar(Set<Prenda> prendas){	
		Set<Set<Prenda>> combinacionesDePrendasPosibles = Sets.powerSet(prendas);
		Set<Atuendo> atuendosPosibles = combinacionesDePrendasPosibles.stream().map(unaCombinacion -> new Atuendo(unaCombinacion)).collect(Collectors.toSet());
		return atuendosPosibles;
	}
	
}
