package DDS_TP2019.Dominio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import DDS_TP2019.Clima.ServicioMeteorologico;
import DDS_TP2019.Decisiones.Aceptar;
import DDS_TP2019.Decisiones.Calificar;
import DDS_TP2019.Decisiones.Decision;
import DDS_TP2019.Decisiones.Rechazar;
import DDS_TP2019.Notificaciones.Accion;

public class Persona {
	
	private String nombre;
	public TipoDeUsuario tipoDeUsuario;
	private List<Guardarropa> guardarropas;
	private List<Evento> eventos;
	private String mail;
	private String password;
	private List<Accion> acciones;
	public List<Decision> decisiones;
	
	public TipoDeUsuario getTipoDeUsuario() {
		return tipoDeUsuario;
	}

	public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Guardarropa> getGuardarropas() {
		return guardarropas;
	}
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<Accion> acciones) {
		this.acciones = acciones;
	}

	public List<Decision> getDecisiones() {
		return decisiones;
	}

	public void setDecisiones(List<Decision> decisiones) {
		this.decisiones = decisiones;
	}

	public void setGuardarropas(List<Guardarropa> guardarropas) {
		this.guardarropas = guardarropas;
	}

	public void agregarGuardarropa(Guardarropa guardarropa) {
		guardarropas.add(guardarropa);
	}
	
	public Persona(String nombre,TipoDeUsuario tipoDeUsuario) {
		super();
		this.nombre = nombre;
		this.guardarropas =  new ArrayList<Guardarropa>();
		this.eventos =  new ArrayList<Evento>();
		this.tipoDeUsuario = tipoDeUsuario;
		this.decisiones = new ArrayList<Decision>();
		this.acciones = new ArrayList<Accion>();
	}
	
	public Persona() {
		this.guardarropas =  new ArrayList<Guardarropa>();
		this.eventos = new ArrayList<Evento>();
	}

	public void agregarPrendaAguardarropa(int posGuardarropa, Prenda prenda) {
		this.guardarropas.get(posGuardarropa).agregarPrenda(prenda);		
	}
	
	public void agregarPrendaAGuardarropa(int posGuardarropa, Prenda prenda) throws Exception {	
		if (this.poseePrendaEnOtroGuardarropa(prenda)) {
			throw new Exception("La prenda a agregar pertenece a un guardarropa del usuario");
		}
		if(!this.tipoDeUsuario.permiteAgregarPrendaA(this.guardarropas.get(posGuardarropa))){
			throw new Exception("El guardarropas alcanzo el maximo de prendas permitido");
		}
		this.guardarropas.get(posGuardarropa).agregarPrenda(prenda);
	}
	
	public void agregarEvento(String descripcion, DateTime fecha, String ubicacion, String tipoDeEvento) throws Exception{
		Evento evento = new Evento(descripcion, fecha, ubicacion, tipoDeEvento);
		evento.mostrarDetalles();
		this.eventos.add(evento);
	}
	
	public List<Evento> eventosProximos() {
		return eventos.stream().filter(unEvento -> unEvento.estaProximo()).collect(Collectors.toList());
	}
	
	public boolean poseePrendaCompartidaConOtroGuardarropa(Guardarropa _guardarropa) {
		return guardarropas.stream().anyMatch(guardarropa -> guardarropa.poseePrendaCompartida(_guardarropa));
	}

	public boolean poseePrendaEnOtroGuardarropa(Prenda prenda) {
		return guardarropas.stream().anyMatch(guardarropa -> guardarropa.contienePrenda(prenda));
	}

	public boolean poseeGuardarropa(Guardarropa guardarropa) {
		return guardarropas.contains(guardarropa);
	}
	
	public Set<Set<Atuendo>> sugerirATodosLosGuardarropas(Double temperatura, String evento) {  //Si son sugerencias random, le mando la temperatura actual (servicioMeteorologico.obtenerTemperatura())
		return guardarropas.stream().map(guardarropa -> guardarropa.sugerirAtuendos(temperatura, evento)).collect(Collectors.toSet());
	}
	
	public void sugerirAtuendosParaEventosProximos(ServicioMeteorologico servicioMeteorologico){
		List<Evento> eventosProximos = this.eventosProximos();
		eventosProximos.stream().forEach(evento -> {
			Set<Set<Atuendo>> atuendosSugeridosPorDiferentesGuardarropas = null;
			try {
				atuendosSugeridosPorDiferentesGuardarropas = this.sugerirATodosLosGuardarropas(evento.temperatura(servicioMeteorologico),evento.getTipoEvento());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Set<Atuendo>atuendosSugeridosParaEvento = atuendosSugeridosPorDiferentesGuardarropas.stream().flatMap(atuendos -> atuendos.stream()).collect(Collectors.toSet());
			evento.setAtuendosSugeridos(atuendosSugeridosParaEvento);
		});
	}
	
	public void calificarAtuendo(Atuendo atuendo, Evento evento, int calificacion) {
		Calificar calificado = new Calificar(atuendo, "CALIFICADO");
		calificado.tomarDecision(calificacion);
		decisiones.add(calificado);
		evento.getAtuendosCalificados().add(atuendo);
	}
	
	public void aceptarAtuendo(Evento evento, Atuendo atuendo) {
		Aceptar aceptado = new Aceptar(atuendo, "ACEPTADO");
		aceptado.tomarDecision(0); // Esto está bien? Pongo 0 porque todavía no lo clasifiqué
		decisiones.add(aceptado);
		evento.agregarAtuendoAceptado(atuendo);
	}
	
	public void rechazarAtuendo(Evento evento, Atuendo atuendo) {
		Rechazar rechazado = new Rechazar(atuendo, "RECHAZADO");
		rechazado.tomarDecision(0); // Esto está bien? Pongo 0 porque todavía no lo clasifiqué
		decisiones.add(rechazado);
		evento.agregarAtuendoRechazado(atuendo);
	}
	
	public void deshacerDecision() {
		Decision ultimaDecision = decisiones.get(0);
		ultimaDecision.deshacerDecision();
		decisiones.remove(ultimaDecision);
	}
	
	public void agregarAccion(Accion accion) {
		this.acciones.add(accion);
	}
	
	public void quitarAccion(Accion accion) {
		this.acciones.remove(accion);
	}
	
	public void recibirAlerta() {
		this.acciones.stream().forEach(accion -> {
			accion.notificarSugerenciasListas(this);
			//accion.notificarAlertaMeteorologica(this);
		});
	}
	
}
