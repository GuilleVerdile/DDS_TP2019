package DDS_TP2019.Dominio;

public class Prenda {

	private String colorPrimario;
	private String colorSecundario;
	private TipoPrenda tipoPrenda;
	private TipoTela tipoTela;
	
	public String getColorPrimario() {
		return colorPrimario;
	}
	public void setColorPrimario(String colorPrimario) {
		this.colorPrimario = colorPrimario;
	}
	public String getColorSecundario() {
		return colorSecundario;
	}
	public void setColorSecundario(String colorSecundario) {
		this.colorSecundario = colorSecundario;
	}
	public TipoPrenda getTipoPrenda() {
		return tipoPrenda;
	}
	public void setTipoPrenda(TipoPrenda tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}
	public TipoTela getTipoTela() {
		return tipoTela;
	}
	public void setTipoTela(TipoTela tipoTela) {
		this.tipoTela = tipoTela;
	}
	
	public Prenda(String colorPrimario, String colorSecundario, TipoPrenda tipoPrenda, TipoTela tipoTela) {
		super();
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
		this.tipoPrenda = tipoPrenda;
		this.tipoTela = tipoTela;
	}
	
	public void mostrarDetalles() {
		// TODO Auto-generated method stub
		System.out.println("Detalle Prenda:");
		System.out.println("Categoria: "+ tipoPrenda.getCategoria().getDescripcion());
		System.out.println("Tipo Prenda: "+ tipoPrenda.getDescripcion());
		System.out.println("Tipo Tela: "+ tipoTela.getDescripcion());
		System.out.println("Color Primario: " + colorPrimario);
		System.out.println("Color Secundario: " + colorSecundario);
		return;
	}
	
	
	
	
}
