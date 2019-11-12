package DDS_TP2019.Dominio;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.joda.time.DateTime;

import DDS_TP2019.Estados.Calificar;
import DDS_TP2019.Estados.Estado;
@Entity
public class Atuendo {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}
	public void setId(long _id) {
		this.id=_id;
	}
	@ManyToMany
	private Set<Prenda> prendas;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
	private Estado estado;
	private int calificacion;
	@ManyToOne
	@JoinColumn(name="persona_id", nullable=true)
	private Persona persona;
	
	public Atuendo(Set<Prenda> prendas) {
		this.prendas = prendas;	
//		this.estado = new Nuevo("NUEVO");
	}
	
	@OneToOne(mappedBy = "atuendoAceptado")
    @JoinColumn(name = "eventoAceptado_id", nullable=true)
	private Evento eventoAceptado;
	
	@ManyToOne
	@JoinColumn(name="eventoRechazado_id", nullable=true)
	private Evento eventoRechazado;
	
	@ManyToOne
	@JoinColumn(name="eventoCalificado_id", nullable=true)
	private Evento eventoCalificado;
	
	@ManyToOne
	@JoinColumn(name="eventoSugerido_id", nullable=true)
	private Evento eventoSugerido;
	
	public Atuendo() {}
	
	/*
	public boolean esAtuendoPosible() {
		return this.prendas.size() >= 3;
	}*/
	
	public boolean cubreTodoElCuerpo() {
		return this.contieneDeCategoria("partesuperior") &&
				this.contieneDeCategoria("parteinferior") &&
				this.contieneDeCategoria("calzado");
	}
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Evento getEventoAceptado() {
		return eventoAceptado;
	}
	public void setEventoAceptado(Evento eventoAceptado) {
		this.eventoAceptado = eventoAceptado;
	}
	public Evento getEventoRechazado() {
		return eventoRechazado;
	}
	public void setEventoRechazado(Evento eventoRechazado) {
		this.eventoRechazado = eventoRechazado;
	}
	public Evento getEventoCalificado() {
		return eventoCalificado;
	}
	public void setEventoCalificado(Evento eventoCalificado) {
		this.eventoCalificado = eventoCalificado;
	}
	public Evento getEventoSugerido() {
		return eventoSugerido;
	}
	public void setEventoSugerido(Evento eventoSugerido) {
		this.eventoSugerido = eventoSugerido;
	}
	public boolean contieneDeCategoria(String unaCategoria) {
		return this.prendas.stream().anyMatch(unaPrenda -> unaPrenda.getTipoPrenda().getCategoria() == unaCategoria);
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public void setPrendas(Set<Prenda> prendas) {
		this.prendas = prendas;
	}


	public void calificar(int unaCalificacion) throws Exception{
		if(unaCalificacion < 0 || unaCalificacion > 5){   //No es una calificacion valida, usamos rango de 1 a 5 
			throw new Exception("La calificacion no esta dentro del rango solicitado");
		}
		if(this.estado.getEstado() == "ACEPTADO"){
			this.calificacion = unaCalificacion;
			this.setEstado(new Calificar("CALIFICADO"));
		}
	}
	
	public boolean esMuyAbrigado() {
		return this.sumaNivelDeAbrigo(90,100);
	}
	
	public boolean esAbrigado() {
		return this.sumaNivelDeAbrigo(75,90);
	}
	
	public boolean esChill() {
		return this.sumaNivelDeAbrigo(50,75);
	}
	
	public boolean esTemplado() {
		return this.sumaNivelDeAbrigo(35,50);
	}
	
	public boolean esFresco() {
		return this.sumaNivelDeAbrigo(10,35);
	}
	
	public boolean sumaNivelDeAbrigo(int unMinimo,int unMaximo) {
		int nivelDeAbrigo = this.prendas.stream().mapToInt(unaPrenda -> unaPrenda.getCalorias()).sum();
		return nivelDeAbrigo >= unMinimo && nivelDeAbrigo <= unMaximo;
	}
	
	public Set<Prenda> filtrarPorCategoria(String unaCategoria) {
		return this.prendas.stream().filter(unaPrenda -> unaPrenda.getTipoPrenda().getCategoria() == unaCategoria).collect(Collectors.toSet());
	}
	
	public boolean noHayMasDe3DelTipo(String categoria) {
		return this.filtrarPorCategoria(categoria).size() <= 3;
	}
		
	public boolean esAtuendoFormal() {
		return this.prendas.stream().allMatch(unaPrenda -> unaPrenda.getTipoPrenda().getTiposDeEvento().contains("FORMAL"));
	}
	
	public boolean esAtuendoInformal() {
		return this.prendas.stream().allMatch(unaPrenda -> unaPrenda.getTipoPrenda().getTiposDeEvento().contains("INFORMAL"));
	}
	
	public boolean esAtuendoDiario() {
		return this.prendas.stream().allMatch(unaPrenda -> unaPrenda.getTipoPrenda().getTiposDeEvento().contains("DIARIO"));
	}
	
	public boolean noRepiteNivelPorCategoria() {
		if(this.contieneDeCategoria("accesorio")) {     // Si el atuendo contiene algun accesorio, se fija tambien que cumpla la regla de las capas
			return (noRepiteNivel(filtrarPrendasCategoria("partesuperior"))&&
					noRepiteNivel(filtrarPrendasCategoria("parteinferior"))&&
					noRepiteNivel(filtrarPrendasCategoria("calzado"))&&
					noRepiteNivel(filtrarPrendasCategoria("accesorio")));  
		}
		else {
			return (noRepiteNivel(filtrarPrendasCategoria("partesuperior"))&&
					noRepiteNivel(filtrarPrendasCategoria("parteinferior"))&&
					noRepiteNivel(filtrarPrendasCategoria("calzado"))); 
		}
	}
	private Set<Prenda> filtrarPrendasCategoria(String categoria) {
		return this.prendas.stream().filter(prenda -> prenda.getTipoPrenda().getCategoria() == categoria).collect(Collectors.toSet());
	}
	private boolean noRepiteNivel(Set<Prenda> prendaXCategoria) {    // en ninguna parte del cuerpo puede haber dos prendas del mismo nivel
		return ((prendaXCategoria.stream().filter(prenda -> prenda.getTipoPrenda().getNivel() == 0).collect(Collectors.toSet()).size()  <= 1) &&    //prenda nivel 0 es optativa          
		(prendaXCategoria.stream().filter(prenda -> prenda.getTipoPrenda().getNivel() == 1).collect(Collectors.toSet()).size() == 1) &&   // prenda nivel 1 es obligatoria
		(prendaXCategoria.stream().filter(prenda -> prenda.getTipoPrenda().getNivel() == 2).collect(Collectors.toSet()).size()  == 1) &&  // prenda nivel 2 es obligatoria
		(prendaXCategoria.stream().filter(prenda -> prenda.getTipoPrenda().getNivel() == 3).collect(Collectors.toSet()).size()  <= 1) &&  //prenda nivel 3 es optativa 
		(prendaXCategoria.stream().filter(prenda -> prenda.getTipoPrenda().getNivel() == 4).collect(Collectors.toSet()).size()  <= 1));  //prenda nivel 4 es optativa 
	}
	public Set<Prenda> getPrendas() {
		return this.prendas;
	}
	public void mostrarPrendas() {
		this.prendas.stream().forEach(p -> p.mostrarDetalles());
	}
	
	public void agregarUso(DateTime fechaInicioEvento, DateTime fechaFinEvento) {
		this.prendas.forEach(unaPrenda -> unaPrenda.agregarUso(fechaInicioEvento,fechaFinEvento));
	}
	
	public boolean estaDisponible(DateTime fechaInicioEvento, DateTime fechaFinEvento) {
		return this.prendas.stream().allMatch(unaPrenda -> unaPrenda.isEstaDisponible(fechaInicioEvento,fechaFinEvento));
	}
}