package DDS_TP2019.Estados;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import DDS_TP2019.Dominio.Atuendo;

@Entity
@DiscriminatorValue(value="Aceptar")
public class Aceptar extends Estado {  
	
	public Aceptar(String estado) {
		super(estado);
	}
	public Aceptar() {}
	public void deshacerDecision(Atuendo atuendo) {
		atuendo.setEstado(new Nuevo("NUEVO"));
		}
	
	public void tomarDecision(int unaCalificacion, Atuendo atuendo) {
		atuendo.setEstado(new Aceptar("ACEPTADO"));
	}
}