package DDS_TP2019.Dominio;

import java.util.Set;

import org.joda.time.DateTime;

public class GenerarSugerencias {
	
	public static GenerarSugerencias instancia;
	
	public static GenerarSugerencias getInstance() {
		if(instancia == null) {
			instancia = new GenerarSugerencias();
		}
		return instancia;
	}
	
	public Set<Atuendo> ejecutar(Set<Prenda> prendas, Double temperatura, String tipoDeEvento, DateTime fechaInicioEvento, DateTime fechaFinEvento){
		Set<Prenda> prendasAcordesAlEvento = FiltrarPrenda.getInstance().ejecutar(prendas,tipoDeEvento);
		Set<Atuendo> atuendosPosibles =  Combinar.getInstance().ejecutar(prendasAcordesAlEvento);
		Set<Atuendo> atuendosRecomendados = FiltrarCombinaciones.getInstance().ejecutar(atuendosPosibles,temperatura, fechaInicioEvento, fechaFinEvento);
//		System.out.println(atuendosRecomendados);
		return atuendosRecomendados;
	}
	
}



