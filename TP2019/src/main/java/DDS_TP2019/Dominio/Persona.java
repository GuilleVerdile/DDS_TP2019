package DDS_TP2019.Dominio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import com.google.common.collect.Sets;
import com.google.maps.errors.ApiException;

import DDS_2019.DAOs.AtuendoDAO;
import DDS_2019.DAOs.EventoDAO;
import DDS_TP2019.Clima.ServicioMeteorologico;
import DDS_TP2019.Estados.Aceptar;
import DDS_TP2019.Estados.Calificar;
import DDS_TP2019.Estados.Rechazar;
import DDS_TP2019.Notificaciones.Accion;
import db.EntityManagerHelper;

@Entity
public class Persona {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}
	public void setId(long _id) {
		this.id=_id;
	}
	private String nombre;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="tipoDeUsuario_id", nullable=false)
	private TipoDeUsuario tipoUsuario;
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="personas",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Guardarropa> guardarropas;
	@OneToMany(fetch = FetchType.LAZY, mappedBy="persona",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Evento> eventos;
	private String mail;
	private String password;
	@Transient
	private List<Accion> acciones;
	@OneToMany(mappedBy="persona")
	private List<Atuendo> historialAtuendos;
	
	public TipoDeUsuario getTipoDeUsuario() {
		return tipoUsuario;
	}

	public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {
		this.tipoUsuario = tipoDeUsuario;
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

	public List<Atuendo> getHistorialAtuendos() {
		return historialAtuendos;
	}

	public void setHistorialAtuendos(List<Atuendo> historialAtuendos) {
		this.historialAtuendos = historialAtuendos;
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
		this.tipoUsuario = tipoDeUsuario;
		this.historialAtuendos = new ArrayList<Atuendo>();
		this.acciones = new ArrayList<Accion>();
	}
	
	public Persona(String nombre, TipoDeUsuario tipoUsuario, String mail, String password) {
		super();
		this.nombre = nombre;
		this.tipoUsuario = tipoUsuario;
		this.mail = mail;
		this.password = password;
		this.guardarropas =  new ArrayList<Guardarropa>();
		this.eventos =  new ArrayList<Evento>();
		this.historialAtuendos = new ArrayList<Atuendo>();
		this.acciones = new ArrayList<Accion>();
	}
	public Persona() {
		this.guardarropas =  new ArrayList<Guardarropa>();
		this.eventos = new ArrayList<Evento>();
	}

	public void agregarPrendaAGuardarropa(int posGuardarropa, Prenda prenda) throws Exception {	
		if (this.poseePrendaEnOtroGuardarropa(prenda)) {
			throw new Exception("La prenda a agregar pertenece a un guardarropa del usuario");
		}
		if(!this.tipoUsuario.permiteAgregarPrendaA(this.guardarropas.get(posGuardarropa))){
			throw new Exception("El guardarropas alcanzo el maximo de prendas permitido");
		}
		this.guardarropas.get(posGuardarropa).agregarPrenda(prenda);
	}
	
	public void agregarEvento(String descripcion, DateTime fechaInicio, DateTime fechaFin, String ubicacion, String tipoDeEvento) throws Exception{
		Evento evento = new Evento(descripcion, fechaInicio, fechaFin, ubicacion, tipoDeEvento);
		evento.mostrarDetalles();
		this.eventos.add(evento);
	}
	
	public void agregarEvento(Evento evento) {
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
	
	public Set<Set<Atuendo>> sugerirATodosLosGuardarropas(Double temperatura, String tipoEvento, DateTime fechaInicioEvento,DateTime fechaFinEvento){  //Si son sugerencias random, le mando la temperatura actual (servicioMeteorologico.obtenerTemperatura())
		return guardarropas.stream().map(guardarropa -> guardarropa.sugerirAtuendos(temperatura, tipoEvento, fechaInicioEvento, fechaFinEvento)).collect(Collectors.toSet());
	}
	
	public void sugerirAtuendosParaEventosProximos(ServicioMeteorologico servicioMeteorologico){
		List<Evento> eventosProximos = this.eventosProximos();
		eventosProximos.stream().forEach(evento -> 
			obtenerAtuendosParaEventoProximo(evento,servicioMeteorologico));
	}
	
	public void obtenerAtuendosParaEventoProximo(Evento evento, ServicioMeteorologico servicioMeteorologico) {
		Set<Set<Atuendo>> atuendosSugeridosPorDiferentesGuardarropas = null ;
		try {
			atuendosSugeridosPorDiferentesGuardarropas = this.sugerirATodosLosGuardarropas(evento.temperatura(servicioMeteorologico),evento.getTipoEvento(),evento.getFechaInicioEvento(),evento.getFechaFinEvento());
//			atuendosSugeridosPorDiferentesGuardarropas = this.sugerirATodosLosGuardarropas(21.0,evento.getTipoEvento(),evento.getFechaInicioEvento(),evento.getFechaFinEvento());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApiException ex) {
                    Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
                }
		Set<Atuendo>atuendosSugeridosParaEvento = Sets.newHashSet();
		atuendosSugeridosParaEvento = atuendosSugeridosPorDiferentesGuardarropas.stream().flatMap(atuendos -> atuendos.stream()).collect(Collectors.toSet());
		System.out.println("Cantidad atuendos sugeridos..: " + atuendosSugeridosParaEvento.size());
		
//		 AtuendoDAO atuendoDAO = new AtuendoDAO(EntityManagerHelper.getEntityManager());
//		 Atuendo atuendo = new Atuendo(atuendoDAO.obtenerAtuendo(5).getPrendas());
//		 atuendosSugeridosParaEvento.add(atuendo);
//		 System.out.println("Cantidad atuendos sugeridos..: " + atuendosSugeridosParaEvento.size());
		persistirAtuendosDelEvento(evento,atuendosSugeridosParaEvento);
	}
	
	
	public static void persistirAtuendosDelEvento(Evento eventoT, Set<Atuendo> atuendosSugeridosParaEvento) {
	    EventoDAO eventoDAO = new EventoDAO(EntityManagerHelper.getEntityManager());
	    Evento evento = eventoDAO.obtenerEvento(eventoT.getId());
	    System.out.println(evento.getId());
	    evento.setAtuendosSugeridos(Sets.newHashSet());
	    AtuendoDAO atuendoDAO = new AtuendoDAO(EntityManagerHelper.getEntityManager());
	    System.out.println("Arranco a persistir..");
	    atuendosSugeridosParaEvento.stream().forEach(atuendoSugerido -> {
	    	atuendoSugerido.mostrarPrendas();
	    	System.out.println(" agregarAtuendoSugerido..");
	    	evento.agregarAtuendoSugerido(atuendoSugerido);
	    	System.out.println(" setEventoSugerido..");
		   	atuendoSugerido.setEventoSugerido(evento);
		 	System.out.println("Se agrego la nueva prenda al guardarropa en memoria. " );
		 	eventoDAO.actualizarEvento(evento);
			Long idAtuendo = atuendoDAO.obtenerUltimoIDAtuendoInsertado();
			atuendoSugerido.setId(idAtuendo);
			System.out.println("id nuevo atuendo: " + atuendoSugerido.getId());
	    });
	}
	
	public void calificarAtuendo(Atuendo atuendo, Evento evento, int calificacion) throws Exception {
		atuendo.setEstado(new Calificar("CALIFICADO"));
		atuendo.calificar(calificacion);
		historialAtuendos.add(atuendo);
		evento.getAtuendosCalificados().add(atuendo);
	}
	
	public void aceptarAtuendo(Evento evento, Atuendo atuendo) {
		atuendo.setEstado(new Aceptar("ACEPTADO"));
		historialAtuendos.add(atuendo);
		evento.agregarAtuendoAceptado(atuendo);
		atuendo.agregarUso(evento);
	}
	
	public void rechazarAtuendo(Evento evento, Atuendo atuendo) {
		atuendo.setEstado(new Rechazar("RECHAZADO"));
		historialAtuendos.add(atuendo);
		evento.agregarAtuendoRechazado(atuendo);
	}
	
	public void deshacerDecision() {
		Atuendo atuendo = historialAtuendos.get(0);
		atuendo.getEstado().deshacerDecision(atuendo);
		historialAtuendos.remove(atuendo);
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
	public void eliminarGuardarropaConId(Long idGuardarropaAeliminar) {
		// TODO Auto-generated method stub
		this.guardarropas.removeIf(g -> g.getId() == idGuardarropaAeliminar);
	}
	public boolean esUsuarioPremium() {
		// TODO Auto-generated method stub
		return this.tipoUsuario.esPremium();
	}
	public boolean esUsuarioGratuito() {
		// TODO Auto-generated method stub
		return this.tipoUsuario.esGratuito();
	}
	public void eliminarEventoConId(Long idEventoAeliminar) {
		// TODO Auto-generated method stub
		this.eventos.removeIf(e -> e.getId() == idEventoAeliminar);
	}
	
	
	
}
