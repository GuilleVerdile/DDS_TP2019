package DDS_2019.DAOs;

import javax.persistence.EntityManager;

import DDS_TP2019.Dominio.Persona;
import db.EntityManagerHelper;

public class PersonaDAO {
	
	private EntityManager entityManager;
	
	public PersonaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;		
	}

	public Persona obtenerPersona(long idUsuario) {
		
		Persona persona = entityManager.find(Persona.class, idUsuario);
		
		if(persona == null) {
			try {
				throw new Exception("No existe una persona con el id: " + idUsuario);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return persona;
	}
	
	public long guardarPersona(Persona persona) {
		
		long idUsuario;
		
		if(persona.getId() != 0) {
			try {
				throw new Exception("La persona no es nueva");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		EntityManagerHelper.beginTransaction();
    	EntityManagerHelper.getEntityManager().persist(persona);
    	EntityManagerHelper.getEntityManager().getTransaction().commit();
//		entityManager.persist(persona);
//		entityManager.getTransaction().commit();
		idUsuario = persona.getId();

		return idUsuario;
	}
	

	public void actualizarPersona(Persona persona) {
		
		if(persona.getId() == 0) {
			try {
				throw new Exception("La persona es nueva y no existe en la base de datos");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		entityManager.getTransaction().commit();
	}
}
