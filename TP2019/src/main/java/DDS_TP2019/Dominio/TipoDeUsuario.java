package DDS_TP2019.Dominio;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@DiscriminatorColumn(name="tipoUsuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class TipoDeUsuario {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}
	public void setId(long _id) {
		this.id=_id;
	}
	public abstract boolean permiteAgregarPrendaA(Guardarropa guardarropa);
	public abstract boolean esGratuito();
	public abstract boolean esPremium();
}