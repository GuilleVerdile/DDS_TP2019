package DDS_TP2019.Dominio;

public class Accesorio extends Categoria {

	public Accesorio(String descripcion) {
		super(descripcion);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean esAccesorio() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected boolean esSuperior() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean esInferior() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean esCalzado() {
		// TODO Auto-generated method stub
		return false;
	}

}
