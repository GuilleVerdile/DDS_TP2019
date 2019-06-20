package DDS_TP2019.Dominio;
import java.io.IOException;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Days;

import DDS_TP2019.Clima.ServicioMeteorologico;

public class Evento {	
	private String descripcionEvento; 
	public DateTime fechaEvento;
	private String ubicacion;
	private String tipoDeEvento;
		
	public Evento(String descripcionEvento, DateTime fechaEvento, String ubicacion, String tipoDeEvento) throws Exception {
		this.descripcionEvento = descripcionEvento;
		this.fechaEvento = fechaEvento;
		this.ubicacion = ubicacion;
		this.tipoDeEvento = tipoDeEvento;
		if(this.esFechaPasada()){
			throw new Exception("La fecha introducida ya ha pasado");
		}
	}

	public boolean estaProximo(){
		return this.diferenciaConHoy() < 3;
	}

	public boolean esFechaPasada(){
		return this.diferenciaConHoy() < 0;
	}

	public int diferenciaConHoy(){
		DateTime fechaActual = new DateTime();
		int dias = Days.daysBetween(fechaActual,this.fechaEvento).getDays();
		return dias;
	}
	
	public double temperatura(ServicioMeteorologico servicioMeteorologico) throws IOException{
		return servicioMeteorologico.obtenerTemperaturaFutura(this.fechaEvento);
	}

	public String getTipoEvento(){
		return this.tipoDeEvento;
	}
	
}
