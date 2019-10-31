package DDS_TP2019.Dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Entity
public class Guardarropa {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}
	public void setId(long _id) {
		this.id=_id;
	}
	@OneToMany(mappedBy="guardarropa")
	private Set<Prenda> prendas;

	public Set<Prenda> getPrendas() {
		return prendas;
	}

	public void setPrendas(Set<Prenda> prendas) {
		this.prendas = prendas;
	}

	public void agregarPrenda(Prenda prenda) {
		prendas.add(prenda);
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	 private List<Persona> personas;
	 
	 public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
	public void agregarPersona(Persona p) {
		this.personas.add(p);
	}
	
	public void recomendarAtuendo() {	//Version Entrega 1 ... no se usa mas	
		List<Prenda> prendasSuperior = prendas.stream().filter(prenda -> prenda.getTipoPrenda().esCategoria("partesuperior")).collect(Collectors.toList());;
		List<Prenda> prendasInferior = prendas.stream().filter(prenda -> prenda.getTipoPrenda().esCategoria("parteinferior")).collect(Collectors.toList());;
		List<Prenda> prendasCalzado = prendas.stream().filter(prenda -> prenda.getTipoPrenda().esCategoria("calzado")).collect(Collectors.toList());;
		List<Prenda> prendasAccesorio = prendas.stream().filter(prenda -> prenda.getTipoPrenda().esCategoria("accesorio")).collect(Collectors.toList());
		
		List<List<Prenda>> atuendosCompletos = Lists.cartesianProduct(prendasSuperior,prendasInferior,prendasCalzado,prendasAccesorio);
		List<List<Prenda>> atuendosIncompletos = Lists.cartesianProduct(prendasSuperior,prendasInferior,prendasCalzado);
		List<List<Prenda>> atuendosPosibles = new ArrayList<List<Prenda>>();
		atuendosPosibles.addAll(atuendosCompletos);
		atuendosPosibles.addAll(atuendosIncompletos);
		for(List<Prenda> atuendo : atuendosPosibles) {
		System.out.println("Detalles de atuendo: \n");
		atuendo.forEach(prenda -> prenda.mostrarDetalles());
		}
	}
	
	public Set<Atuendo> sugerirAtuendos(Double temperatura, String tipoDeEvento, DateTime fechaInicioEvento, DateTime fechaFinEvento){  // VERSION ENTREGA 2  
		return GenerarSugerencias.getInstance().ejecutar(this.prendas,temperatura,tipoDeEvento,fechaInicioEvento,fechaFinEvento);
	} 

	public Guardarropa() {
		super();
		this.prendas = Sets.newHashSet();
		this.personas = new ArrayList<Persona>();
	}
	
	public Guardarropa(Set <Prenda> prendas) {
		this.prendas = prendas;
	}

	public void mostrarPrendas() {
		prendas.forEach(prenda -> prenda.mostrarDetalles());
	}

	public int cantidadDePrendas() {
		return prendas.size();
	}

	public boolean contienePrenda(Prenda prenda) {
		return this.prendas.contains(prenda);
	}

	public boolean poseePrendaCompartida(Guardarropa guardarropa) {
		return this.prendas.stream().anyMatch(unaPrenda -> guardarropa.getPrendas().contains(unaPrenda));
	}
	
	public boolean estaCompartido() throws Exception {
		Sistema s = new Sistema();
		return s.esGuardarropasCompartido(this);
	}
	
}
