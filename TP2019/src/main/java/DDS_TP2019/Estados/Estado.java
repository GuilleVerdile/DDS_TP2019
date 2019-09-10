package DDS_TP2019.Estados;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import DDS_TP2019.Dominio.Atuendo;
import DDS_TP2019.Dominio.Prenda;
@Entity
@DiscriminatorColumn(name="tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Estado {
	protected String estado;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@OneToOne(mappedBy = "estado")
	private Atuendo atuendo;
	public long getId() {
		return id;
	}
	public void setId(long _id) {
		this.id=_id;
	}
	
	public Estado(String estado) {
		this.estado = estado;
	}
	
	public Estado() {}

	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}

	public abstract void tomarDecision(int unaCalificacion, Atuendo atuendo);
	
	public abstract void deshacerDecision(Atuendo atuendo); //Me permite ir para atras en la que sea la decision que haya tomado, va a depender de cada decision (si estoy en aceptado voy a volver a nuevo, si estoy en calificado vuelvo a aceptado... etc)
	
}