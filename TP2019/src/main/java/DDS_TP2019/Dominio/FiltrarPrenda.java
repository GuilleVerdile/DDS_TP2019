package DDS_TP2019.Dominio;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

public class FiltrarPrenda {

	public static FiltrarPrenda instancia;
	
	public static FiltrarPrenda getInstance() {
		if(instancia == null) {
			instancia = new FiltrarPrenda();
		}
		return instancia;
	}
	
	public Set<Prenda> ejecutar(Set<Prenda> prendas, String tipoDeEvento){
		return prendas.stream().filter(unaPrenda -> unaPrenda.getTipoPrenda().getTiposDeEvento().contains(tipoDeEvento))
													.collect(Collectors.toSet());
	}
}
