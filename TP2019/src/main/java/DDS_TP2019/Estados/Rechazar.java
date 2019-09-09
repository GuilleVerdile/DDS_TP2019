package DDS_TP2019.Estados;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import DDS_TP2019.Dominio.Atuendo;

@Entity
@DiscriminatorValue(value="Rechazar")
public class Rechazar extends Estado {  
	
	public Rechazar(String estado) {
		super(estado);
	}
	public Rechazar() {}
	
	public void deshacerDecision(Atuendo atuendo) {
		atuendo.setEstado(new Nuevo("NUEVO"));
	}
	
	public void tomarDecision(int unaCalificacion, Atuendo atuendo) {
		estado = "RECHAZADO";
		atuendo.setEstado(new Rechazar("RECHAZADO"));
	}
}