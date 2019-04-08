package DDS_TP2019.Dominio;

import java.util.ArrayList;
import java.util.List;

public class TipoPrenda {

	private String descripcion;
	private Categoria categoria; 
	private List<TipoTela> tiposTelaPosible;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<TipoTela> getTiposTelaPosible() {
		return tiposTelaPosible;
	}
	public void setTiposTelaPosible(List<TipoTela> tiposTelaPosible) {
		this.tiposTelaPosible = tiposTelaPosible;
	}
	
	public TipoPrenda(String descripcion, Categoria categoria, List<TipoTela> tiposTelaPosible) {
		super();
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.tiposTelaPosible = new ArrayList<TipoTela>();
	}
	
	
	
}
