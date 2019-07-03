package DDS_TP2019.Dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

public class Persona {
	
	private String nombre;
	public TipoDeUsuario tipoDeUsuario;
	private List<Guardarropa> guardarropas;
	private List<Evento> eventos;

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
	}
	
	public Persona() {
		this.guardarropas =  new ArrayList<Guardarropa>();
		this.eventos = new ArrayList<Evento>();
	}

	public void agregarPrendaAguardarropa(int posGuardarropa, Prenda prenda) {
		this.guardarropas.get(posGuardarropa).agregarPrenda(prenda);		
	}
	
	public void agregarPrendaAGuardarropa(int posGuardarropa, Prenda prenda) throws Exception {	
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
	
}
