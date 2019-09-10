package DDS_TP2019.Dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UsuarioGratuito extends TipoDeUsuario{
	int numeroMaximoPrendas;
	@OneToMany(mappedBy="tipoUsuario")
	private List <Persona> personas;
	
	public UsuarioGratuito(int numeroMaximoPrendas) {
		this.numeroMaximoPrendas = numeroMaximoPrendas;
	}
	
	public boolean permiteAgregarPrendaA(Guardarropa guardarropa) {
		return numeroMaximoPrendas > guardarropa.cantidadDePrendas();
	}
	
	public UsuarioGratuito() {
		super();
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
