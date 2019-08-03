package DDS_TP2019.Notificaciones;

import DDS_TP2019.Dominio.Persona;

public interface Accion {
	
	public abstract void notificarSugerenciasListas(Persona persona);
	public abstract void notificarAlertaMeteorologica(Persona persona);
}
