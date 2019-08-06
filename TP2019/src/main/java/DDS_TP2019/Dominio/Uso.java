package DDS_TP2019.Dominio;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public class Uso {
	private DateTime fechaInicio;
	private DateTime fechaFin;
	
	public DateTime getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(DateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public DateTime getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(DateTime fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Uso(DateTime fechaInicio, DateTime fechaFin) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	public boolean superponeUsoPrenda(DateTime fechaInicioEvento, DateTime fechaFinEvento) {
		Interval interval = new Interval(fechaInicioEvento, fechaFinEvento);
		Interval interval2 = new Interval(this.getFechaInicio(), this.getFechaFin());
		return interval.overlaps(interval2) ;
	}
	
}
