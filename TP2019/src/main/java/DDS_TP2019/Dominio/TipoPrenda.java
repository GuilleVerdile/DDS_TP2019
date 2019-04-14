package DDS_TP2019.Dominio;

import java.util.ArrayList;
import java.util.List;

public class TipoPrenda {

	private String descripcion;
	private String categoria; 
	private List<String> tiposTelaPosible;
	
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
	
	public TipoPrenda(String descripcion, String categoria, List<String> tiposTelaPosible) {
		super();
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.tiposTelaPosible = new ArrayList<String>();
	}
	
	public TipoPrenda() {
	}
	
	public boolean esCategoria(String _categoria) {
		return this.categoria == _categoria;
	}
	public void mostrarDetalles() {
		System.out.println("Detalle Prenda:");
		System.out.println("Categoria: "+ this.categoria);
		System.out.println("Tipo Prenda: "+ this.descripcion);
		System.out.println("Tipos Tela Posible: ") ;
		for (String tipoTela : this.tiposTelaPosible) {
			System.out.println(tipoTela);
		}	    
		
	}
	
}
