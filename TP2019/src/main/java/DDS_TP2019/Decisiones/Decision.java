package DDS_TP2019.Decisiones;
import DDS_TP2019.*;
import DDS_TP2019.Dominio.Atuendo;

public abstract class Decision {
	public Atuendo atuendo;
	protected String estado;
	
	public Decision(Atuendo atuendo, String estado) {
		this.atuendo = atuendo;
		this.estado = estado;
	}
	
	public abstract void tomarDecision(int unaCalificacion);
	
	public abstract  void deshacerDecision(); //Me permite ir para atras en la que sea la decision que haya tomado, va a depender de cada decision (si estoy en aceptado voy a volver a nuevo, si estoy en calificado vuelvo a aceptado... etc)
	
}