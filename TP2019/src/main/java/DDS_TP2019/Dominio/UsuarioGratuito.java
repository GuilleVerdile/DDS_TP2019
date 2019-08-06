package DDS_TP2019.Dominio;

public class UsuarioGratuito implements TipoDeUsuario{
	int numeroMaximoPrendas;
	
	public UsuarioGratuito(int numeroMaximoPrendas) {
		this.numeroMaximoPrendas = numeroMaximoPrendas;
	}
	
	public boolean permiteAgregarPrendaA(Guardarropa guardarropa) {
		return numeroMaximoPrendas > guardarropa.cantidadDePrendas();
	}

	@Override
	public boolean esGratuito() {
		return true;
	}

	@Override
	public boolean esPremium() {
		return false;
	}
}
