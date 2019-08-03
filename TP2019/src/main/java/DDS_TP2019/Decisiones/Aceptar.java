package DDS_TP2019.Decisiones;

import DDS_TP2019.*;
import DDS_TP2019.Dominio.Atuendo;

public class Aceptar extends Decision {  
	
	public Aceptar(Atuendo atuendo, String estado) {
		super(atuendo, estado);
	}
	
	public void deshacerDecision() {
		estado = "NUEVO"; //Si estoy en aceptar el atuendo esta como aceptado - vuelvo a NUEVO que es el estado anterior correspondiente
		atuendo.setEstado("NUEVO");
	}
	
	public void tomarDecision(int unaCalificacion) {
		atuendo.setEstado("ACEPTADO");
		atuendo.setPrendasDisponibles(false);
	}
}