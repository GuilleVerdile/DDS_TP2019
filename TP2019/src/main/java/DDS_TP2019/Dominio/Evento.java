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
	private boolean poseeSugerencias;
	private Set<Atuendo> atuendosSugeridos; // Los atuendos que se sugieren para los eventos proximos se guardaran en el evento. Esto se debe a que en un futuro el programa debe poder tener en cuenta que atuendos acepta y rechaza el usuario para cierto tipo de evento.
	private Set<Atuendo> atuendosAceptados;
	private Set<Atuendo> atuendosRechazados;
	private Set<Atuendo> atuendosCalificados; // Los atuendos calificados van a ir aca para poder acceder a la temperatura del evento
	
	public Evento(String descripcionEvento, DateTime fechaEvento, String ubicacion, String tipoDeEvento) throws Exception {
		this.descripcionEvento = descripcionEvento;
		this.fechaEvento = fechaEvento;
		this.ubicacion = ubicacion;
		this.tipoDeEvento = tipoDeEvento;
		if(this.esFechaPasada()){
			throw new Exception("La fecha introducida ya ha pasado");
		}
	}
	public void mostrarDetalles () {
		System.out.println(this.descripcionEvento +", el " + this.fechaEvento.toString("dd/MM/yyyy hh:mm") + " en " + this.ubicacion + " "  + tipoDeEvento);
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
	public String getDescripcionEvento() {
		return descripcionEvento;
	}
	public void setDescripcionEvento(String descripcionEvento) {
		this.descripcionEvento = descripcionEvento;
	}
	public DateTime getFechaEvento() {
		return fechaEvento;
	}
	public void setFechaEvento(DateTime fechaEvento) {
		this.fechaEvento = fechaEvento;
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
	public Set<Atuendo> getAtuendosAceptados() {
		return atuendosAceptados;
	}
	public void setAtuendosAceptados(Set<Atuendo> atuendosAceptados) {
		this.atuendosAceptados = atuendosAceptados;
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
		this.atuendosAceptados.add(atuendo);
	}

	public void agregarAtuendoRechazado(Atuendo atuendo) {
		this.atuendosRechazados.add(atuendo);
	}
	
	public void agregarAtuendoCalificado(Atuendo atuendo) {
		this.atuendosCalificados.add(atuendo);
	}
	
	public boolean getPoseeSugerencias() {
		return this.atuendosSugeridos != null && !this.atuendosSugeridos.isEmpty();
	}

}
