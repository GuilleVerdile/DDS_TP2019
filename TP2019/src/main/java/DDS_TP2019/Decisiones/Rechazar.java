package DDS_TP2019.Decisiones;

import DDS_TP2019.*;
import DDS_TP2019.Dominio.Atuendo;

public class Rechazar extends Decision {  
	
	public Rechazar(Atuendo atuendo, String estado) {
		super(atuendo, estado);
	}
	
	public void deshacerDecision() {
		estado = "NUEVO"; //Si estoy en rechazar, es igual a aceptar (vuelvo a NUEVO)
		atuendo.setEstado("NUEVO");
	}
	
	public void tomarDecision(int unaCalificacion) {
		estado = "RECHAZADO";
		atuendo.setEstado("RECHAZADO");
	}
}