package DDS_TP2019.Estados;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import DDS_TP2019.Dominio.Atuendo;

@Entity
@DiscriminatorValue(value="Calificar")
public class Calificar extends Estado {  
	
	public Calificar(String estado) {
		super(estado);
	}
	public Calificar() {}
	public void deshacerDecision(Atuendo atuendo) {
		atuendo.setEstado(new Nuevo("NUEVO"));
	}
	
	public void tomarDecision(int unaCalificacion, Atuendo atuendo) {
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