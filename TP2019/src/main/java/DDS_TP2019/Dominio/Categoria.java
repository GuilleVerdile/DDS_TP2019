package DDS_TP2019.Dominio;

public abstract class Categoria {

	private String descripcion;
	
	public Categoria(String descripcion) {
		super();
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	protected abstract boolean esAccesorio();
	protected abstract boolean esSuperior();
	protected abstract boolean esInferior();
	protected abstract boolean esCalzado();
	
	
}
