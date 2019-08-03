package DDS_TP2019.Dominio;

import java.util.Set;
import java.util.stream.Collectors;

public class FiltrarCombinaciones {

	public static FiltrarCombinaciones instancia;
	
	public static FiltrarCombinaciones getInstance() {
		if(instancia == null) {
			instancia = new FiltrarCombinaciones();
		}
		return instancia;
	}
	
	public Set<Atuendo> ejecutar(Set<Atuendo> atuendosPosibles, Double temperatura){	
		Set<Atuendo> atuendosAEvaluar = atuendosPosibles.stream()
										.filter(unAtuendo -> unAtuendo.cubreTodoElCuerpo())
										.filter(unAtuendo -> unAtuendo.noHayMasDe3DelTipo("parteinferior"))  
										.filter(unAtuendo -> unAtuendo.noHayMasDe3DelTipo("partesuperior"))  
										.filter(unAtuendo -> unAtuendo.noHayMasDe3DelTipo("calzado")) 
										.filter(unAtuendo -> unAtuendo.noHayMasDe3DelTipo("accesorio"))
										.filter(unAtuendo -> unAtuendo.noRepiteNivelPorCategoria())
										.filter(unAtuendo -> unAtuendo.estaDisponible())
										.collect(Collectors.toSet());
				
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
