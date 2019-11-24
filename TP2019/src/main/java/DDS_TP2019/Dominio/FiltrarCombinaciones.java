package DDS_TP2019.Dominio;

import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

public class FiltrarCombinaciones {

	public static FiltrarCombinaciones instancia;
	
	public static FiltrarCombinaciones getInstance() {
		if(instancia == null) {
			instancia = new FiltrarCombinaciones();
		}
		return instancia;
	}
	
	public Set<Atuendo> ejecutar(Set<Atuendo> atuendosPosibles, Double temperatura,DateTime fechaInicioEvento, DateTime fechaFinEvento){	
		
//		Set<Atuendo> atuendosPosibles2 = atuendosPosibles.stream().filter(unAtuendo -> unAtuendo.esTemplado()).collect(Collectors.toSet());
//		System.out.println("Cantidad atuendos posibles..: " + atuendosPosibles.size());
//		atuendosPosibles2.stream().forEach(unAtuendo -> unAtuendo.mostrarPrendas());
		Set<Atuendo> atuendosAEvaluar = atuendosPosibles.stream()
										.filter(unAtuendo -> unAtuendo.cubreTodoElCuerpo())
										.filter(unAtuendo -> unAtuendo.noHayMasDe3DelTipo("parteinferior"))  
										.filter(unAtuendo -> unAtuendo.noHayMasDe3DelTipo("partesuperior"))  
										.filter(unAtuendo -> unAtuendo.noHayMasDe3DelTipo("calzado")) 
										.filter(unAtuendo -> unAtuendo.noHayMasDe3DelTipo("accesorio"))
										.filter(unAtuendo -> unAtuendo.noRepiteNivelPorCategoria())
										.filter(unAtuendo -> unAtuendo.estaDisponible(fechaInicioEvento,fechaFinEvento))
										.collect(Collectors.toSet());
		System.out.println("Cantidad atuendos a evaluar..: " + atuendosAEvaluar.size());
		if(temperatura >= 0 && temperatura < 10) {
			return atuendosAEvaluar.stream().filter(unAtuendo -> unAtuendo.esMuyAbrigado()).collect(Collectors.toSet());
		} else if(temperatura >= 10 && temperatura < 15) {
			return atuendosAEvaluar.stream().filter(unAtuendo -> unAtuendo.esAbrigado()).collect(Collectors.toSet());
		} else if(temperatura >= 15 && temperatura < 20) {
			return atuendosAEvaluar.stream().filter(unAtuendo -> unAtuendo.esChill()).collect(Collectors.toSet());
		} else if(temperatura >= 20 && temperatura < 25) {
			return atuendosAEvaluar.stream().filter(unAtuendo -> unAtuendo.esTemplado()).collect(Collectors.toSet());
		} else if(temperatura >= 25) {
			return atuendosAEvaluar.stream().filter(unAtuendo -> unAtuendo.esFresco()).collect(Collectors.toSet());
		} else {
			return atuendosAEvaluar;  
		}
		
	}
}
