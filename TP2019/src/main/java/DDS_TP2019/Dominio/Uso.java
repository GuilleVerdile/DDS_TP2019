package DDS_TP2019.Dominio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Interval;
@Entity
public class Uso {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}
	public void setId(long _id) {
		this.id=_id;
	}
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime fechaInicio;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime fechaFin;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="prenda_id", nullable=true)
	private Prenda prenda;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="evento_id", nullable=true)
	private Evento evento;
	
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
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
	public Prenda getPrenda() {
		return prenda;
	}
	public void setPrenda(Prenda prenda) {
		this.prenda = prenda;
	}
	public Uso() {
		super();
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
