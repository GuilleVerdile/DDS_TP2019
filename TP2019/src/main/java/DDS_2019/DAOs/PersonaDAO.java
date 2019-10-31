package DDS_2019.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	public Persona obtenerPersonaByMail(String mail,String contrasenia) throws Exception 
	{
		Query query = entityManager.createQuery("SELECT p FROM Persona p WHERE mail = :mail and password = :contrasenia", Persona.class);
		query.setParameter("mail", mail);
		query.setParameter("contrasenia", contrasenia);
		
		List<Persona> personas = (List<Persona>)query.getResultList();
		
		if(personas.isEmpty()) {
//			throw new Exception("No existe un usuario con el mail: " + mail);
			System.out.println("No encontre al usuario");
			return null;
		}
		
		if(personas.size() > 1) {
			throw new Exception("Existen mas de un usuario con el mail: " + mail);
		}
		System.out.println("Devolviendo al usuario..");
		System.out.println(personas.get(0).getNombre());
		return personas.get(0);
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
		entityManager.getTransaction().begin();
		entityManager.merge(persona);
		entityManager.getTransaction().commit();
	}
}
