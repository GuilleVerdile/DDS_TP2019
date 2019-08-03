package DDS_TP2019.Decisiones;

import DDS_TP2019.*;
import DDS_TP2019.Dominio.Atuendo;

public class Calificar extends Decision {  
	
	public Calificar(Atuendo atuendo, String estado) {
		super(atuendo, estado);
	}
	
	public void deshacerDecision() {
		estado = "ACEPTADO";
	}
	
	public void tomarDecision(int unaCalificacion) {
		try {
			atuendo.calificar(unaCalificacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Se califica el nivel de abrigo de un atuendo, para tener en cuenta en futuras sugerencias
	//cuan abrigado es el usuario.
	//1-2 le resulto poco abrigado - ABRIGAS MAS para ese rango de temperatura  (Para 1 le sumo 6, para 2 le sumo 3)
	//3 le resulto bien - ABRIGAR IGUAL
	//4-5 le resulto demasiado abrigad - ABRIGAR MENOS para ese rango de temperatura (Para 4 le resto 3, para 5 le resto 6)
}