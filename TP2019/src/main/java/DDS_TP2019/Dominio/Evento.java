package DDS_TP2019.Dominio;
import java.io.IOException;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Days;

import com.google.maps.errors.ApiException;

import DDS_TP2019.Clima.ServicioMeteorologico;
@Entity
public class Evento {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}
	public void setId(long _id) {
		this.id=_id;
	}
	private String descripcionEvento; 
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime fechaInicioEvento;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime fechaFinEvento;
	private String ubicacion;
	private String tipoDeEvento;
	private boolean poseeSugerencias;
	@OneToMany(fetch = FetchType.LAZY, mappedBy="eventoSugerido",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Atuendo> atuendosSugeridos; // Los atuendos que se sugieren para los eventos proximos se guardaran en el evento. Esto se debe a que en un futuro el programa debe poder tener en cuenta que atuendos acepta y rechaza el usuario para cierto tipo de evento.
	@OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Atuendo atuendoAceptado;
	@OneToMany(fetch = FetchType.LAZY, mappedBy="eventoRechazado",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Atuendo> atuendosRechazados;
	@OneToMany(fetch = FetchType.LAZY, mappedBy="eventoCalificado",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Atuendo> atuendosCalificados; // Los atuendos calificados van a ir aca para poder acceder a la temperatura del evento
	@ManyToOne
	@JoinColumn(name="persona_id", nullable=false)
	private Persona persona;
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Evento() {};
	public Evento(String descripcionEvento, DateTime fechaInicioEvento,DateTime fechaFinEvento, String ubicacion, String tipoDeEvento) throws Exception {
		this.descripcionEvento = descripcionEvento;
		this.fechaInicioEvento = fechaInicioEvento;
		this.fechaFinEvento = fechaFinEvento;
		this.ubicacion = ubicacion;
		this.tipoDeEvento = tipoDeEvento;
		if(this.esFechaPasada()){
			throw new Exception("La fecha introducida ya ha pasado");
		}
	}
	public void mostrarDetalles () {
		System.out.println(this.descripcionEvento +", desde " + this.fechaInicioEvento.toString("dd/MM/yyyy hh:mm") +", hasta " + this.fechaFinEvento.toString("dd/MM/yyyy hh:mm") + " en " + this.ubicacion + " "  + tipoDeEvento);
	}
	
	public boolean estaProximo(){
		return this.diferenciaConHoy() < 3;
	}

	public boolean esFechaPasada(){
		
		return this.diferenciaConHoy() < 0;
	}

	public int diferenciaConHoy(){
		DateTime fechaActual = new DateTime();
		int dias = Days.daysBetween(fechaActual,this.fechaInicioEvento).getDays();
		return dias;
	}
	
	public double temperatura(ServicioMeteorologico servicioMeteorologico) throws IOException, ApiException, InterruptedException{
		return servicioMeteorologico.obtenerTemperaturaFutura(this.fechaInicioEvento, this.ubicacion);
	}

	public String getTipoEvento(){
		return this.tipoDeEvento;
	}
	public String getDescripcionEvento() {
		return descripcionEvento;
	}
	public void setDescripcionEvento(String descripcionEvento) {
		this.descripcionEvento = descripcionEvento;
	}
	public DateTime getFechaInicioEvento() {
		return fechaInicioEvento;
	}
	public void setFechaInicioEvento(DateTime fechaInicioEvento) {
		this.fechaInicioEvento = fechaInicioEvento;
	}
	public DateTime getFechaFinEvento() {
		return fechaFinEvento;
	}
	public void setFechaFinEvento(DateTime fechaFinEvento) {
		this.fechaFinEvento = fechaFinEvento;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getTipoDeEvento() {
		return tipoDeEvento;
	}
	public void setTipoDeEvento(String tipoDeEvento) {
		this.tipoDeEvento = tipoDeEvento;
	}
	public boolean isPoseeSugerencias() {
		return poseeSugerencias;
	}
	public void setPoseeSugerencias(boolean poseeSugerencias) {
		this.poseeSugerencias = poseeSugerencias;
	}
	public Set<Atuendo> getAtuendosSugeridos() {
		return atuendosSugeridos;
	}
	public void setAtuendosSugeridos(Set<Atuendo> atuendosSugeridos) {
		this.atuendosSugeridos = atuendosSugeridos;
	}
	public Atuendo getAtuendoAceptado() {
		return atuendoAceptado;
	}
	public void setAtuendoAceptado(Atuendo atuendoAceptado) {
		this.atuendoAceptado = atuendoAceptado;
	}
	public Set<Atuendo> getAtuendosRechazados() {
		return atuendosRechazados;
	}
	public void setAtuendosRechazados(Set<Atuendo> atuendosRechazados) {
		this.atuendosRechazados = atuendosRechazados;
	}
	public Set<Atuendo> getAtuendosCalificados() {
		return atuendosCalificados;
	}
	public void setAtuendosCalificados(Set<Atuendo> atuendosCalificados) {
		this.atuendosCalificados = atuendosCalificados;
	}
	public void agregarAtuendoAceptado(Atuendo atuendo) {
		this.atuendoAceptado = atuendo;
	}

	public void agregarAtuendoRechazado(Atuendo atuendo) {
		this.atuendosRechazados.add(atuendo);
	}
	
	public void agregarAtuendoCalificado(Atuendo atuendo) {
		this.atuendosCalificados.add(atuendo);
	}
	
	public void agregarAtuendoSugerido(Atuendo atuendo) {
		this.atuendosSugeridos.add(atuendo);
	}
	
	public boolean getPoseeSugerencias() {
		return this.atuendosSugeridos != null && !this.atuendosSugeridos.isEmpty();
	}

}
