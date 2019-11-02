package DDS_2019.DAOs;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import DDS_TP2019.Dominio.Guardarropa;
import DDS_TP2019.Dominio.Persona;
import db.EntityManagerHelper;

public class GuardarropaDAO{
	
	private EntityManager entityManager;

	public GuardarropaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Guardarropa obtenerGuardarropa(long idGuardarropa) {
		
		Guardarropa guardarropa = entityManager.find(Guardarropa.class, idGuardarropa);
		
		if(guardarropa == null) {
			try {
				throw new Exception("No existe un guardarropa con el id: " + idGuardarropa);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return guardarropa;
	}
	
	public long guardarGuardarropa(Guardarropa guardarropa) {
		
		long idGuardarropa;
		
		if(guardarropa.getId() != 0) {
			try {
				throw new Exception("El guardarropa no es nuevo");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		EntityManagerHelper.beginTransaction();
//    	EntityManagerHelper.getEntityManager().persist(guardarropa);
//    	EntityManagerHelper.getEntityManager().getTransaction().commit();
//    	EntityManagerHelper.closeEntityManager();	  
		entityManager.getTransaction().begin();
		entityManager.persist(guardarropa);
		entityManager.getTransaction().commit();
		idGuardarropa = guardarropa.getId();

		return idGuardarropa;
	}
	

	public void actualizarGuardarropa(Guardarropa guardarropa) {
		
		if(guardarropa.getId() == 0) {
			try {
				throw new Exception("El guardarropa es nuevo y no existe en la base de datos");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Ver si anda asi o hay q hacer merge antes del commit..
		entityManager.getTransaction().begin();
		entityManager.merge(guardarropa);
		entityManager.getTransaction().commit();
	}

	public void eliminarGuardarropa(Guardarropa guardarropa) {
		// TODO Auto-generated method stub
		entityManager.getTransaction().begin();
		entityManager.remove(guardarropa);
		entityManager.getTransaction().commit();
	}
	
	public void eliminarGuardarropa(Long idGuardarropaAeliminar) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("DELETE FROM guardarropa_persona gp WHERE gp.guardarropas_id = :id");
		query.setParameter("id", idGuardarropaAeliminar).executeUpdate();
		 query = entityManager.createQuery("DELETE FROM Guardarropa g WHERE g.id = :id");
		query.setParameter("id", idGuardarropaAeliminar).executeUpdate();
			  
	}
}
