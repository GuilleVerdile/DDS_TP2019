package DDS_TP2019.Dominio;

public class UsuarioPremium implements TipoDeUsuario{
	public boolean permiteAgregarPrendaA(Guardarropa guardarropa) {
		return true;
	}

	@Override
	public boolean esGratuito() {
		return false;
	}

	@Override
	public boolean esPremium() {
		return true;
	}
}
