package DDS_TP2019.Dominio;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class TipoPrenda {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}
	public void setId(long _id) {
		this.id=_id;
	}
	@OneToMany(mappedBy = "tipoPrenda")
	private List<Prenda> prenda;
	private String descripcion;
	private String categoria; 
	@ElementCollection
	private List<String> tiposTelaPosible;
	@ElementCollection
	private List<String> tiposDeEvento;
	private int nivel;
	
	public int getNivel() {
//		System.out.println("nivel tipoPrenda: " + nivel);
//		System.out.println(" tipo DeEvento: "  + tiposDeEvento.get(0));
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public List<String> getTiposTelaPosible() {
		return tiposTelaPosible;
	}
	public void setTiposTelaPosible(List<String> tiposTelaPosible) {
		this.tiposTelaPosible = tiposTelaPosible;
	}
	public List<Prenda> getPrenda() {
		return prenda;
	}
	public void setPrenda(List<Prenda> prenda) {
		this.prenda = prenda;
	}
	public List<String> getTiposDeEvento() {
		return tiposDeEvento;
	}
	public void setTiposDeEvento(List<String> tiposDeEvento) {
		this.tiposDeEvento = tiposDeEvento;
	}
	public TipoPrenda(String descripcion, String categoria, List<String> tiposTelaPosible,List<String> tiposDeEvento,int nivel) {
		super();
		this.descripcion = descripcion;
		this.categoria = categoria;
//		this.tiposTelaPosible = new ArrayList<String>();
//		this.tiposDeEvento = new ArrayList<String>();
		this.tiposTelaPosible = tiposTelaPosible;
		this.tiposDeEvento = tiposDeEvento;
		this.nivel = nivel;
	}
	
	public TipoPrenda() {
	}
	
	public boolean esCategoria(String _categoria) {
		return this.categoria.equals(_categoria);
	}
	public void mostrarDetalles() {
		System.out.println("Detalle Prenda:");
		System.out.println("Categoria: "+ this.categoria);
		System.out.println("Tipo Prenda: "+ this.descripcion);
		System.out.println("Tipos Tela Posible: ") ;
		for (String tipoTela : this.tiposTelaPosible) {
			System.out.println(tipoTela);
		}	    
		System.out.println("Nivel: "+ this.nivel);
	}
	
}
