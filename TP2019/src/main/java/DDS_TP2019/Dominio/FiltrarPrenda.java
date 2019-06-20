package DDS_TP2019.Dominio;

import java.util.Set;
import java.util.stream.Collectors;

public class FiltrarPrenda {

	public static FiltrarPrenda instancia;
	
	public static FiltrarPrenda getInstance() {
		if(instancia == null) {
			instancia = new FiltrarPrenda();
		}
		return instancia;
	}
	
	public Set<Prenda> ejecutar(Set<Prenda> prendas, String tipoDeEvento){
		Set<Prenda> prendasAcordesAlEvento = null;
		if(tipoDeEvento == "FORMAL") {
			prendasAcordesAlEvento = prendas.stream()
													.filter(unaPrenda -> unaPrenda.esFormal())
													.collect(Collectors.toSet());
		} else if(tipoDeEvento == "INFORMAL"){
			prendasAcordesAlEvento = prendas.stream()
													.filter(unaPrenda -> unaPrenda.esInformal())
													.collect(Collectors.toSet());
		} 
		return prendasAcordesAlEvento;
	}
}
