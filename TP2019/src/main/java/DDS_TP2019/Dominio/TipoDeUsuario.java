package DDS_TP2019.Dominio;
public interface TipoDeUsuario {
	public boolean permiteAgregarPrendaA(Guardarropa guardarropa);
	public boolean esGratuito();
	public boolean esPremium();
}