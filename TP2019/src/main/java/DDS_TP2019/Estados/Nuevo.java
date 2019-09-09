package DDS_TP2019.Estados;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import DDS_TP2019.Dominio.Atuendo;

@Entity
@DiscriminatorValue(value="Nuevo")
public class Nuevo extends Estado {  
	
	public Nuevo(String estado) {
		super(estado);
	}
	public Nuevo() {}
	public void deshacerDecision(Atuendo atuendo) {
		atuendo.setEstado(new Nuevo("NUEVO"));
	}
	
	public void tomarDecision(int unaCalificacion, Atuendo atuendo) {
		atuendo.setEstado(new Nuevo("NUEVO"));
	}
}