package DDS_TP2019.Dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UsuarioPremium extends TipoDeUsuario{
	@OneToMany(mappedBy="tipoUsuario")
	private List <Persona> personas;
	
	public boolean permiteAgregarPrendaA(Guardarropa guardarropa) {
		return true;
	}
	public UsuarioPremium() {
		super();
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
