package DDS_2019.DAOs;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import DDS_TP2019.Dominio.Atuendo;
import DDS_TP2019.Dominio.Persona;
import db.EntityManagerHelper;

public class AtuendoDAO{
	
	private EntityManager entityManager;

	public AtuendoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Atuendo obtenerAtuendo(long idAtuendo) {
		
		Atuendo atuendo = entityManager.find(Atuendo.class, idAtuendo);
		
		if(atuendo == null) {
			try {
				throw new Exception("No existe un atuendo con el id: " + idAtuendo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return atuendo;
	}
	
	public long guardarAtuendo(Atuendo atuendo) {
		
		long idAtuendo;
		
		if(atuendo.getId() != 0) {
			try {
				throw new Exception("El atuendo no es nuevo");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		EntityManagerHelper.beginTransaction();
//    	EntityManagerHelper.getEntityManager().persist(atuendo);
//    	EntityManagerHelper.getEntityManager().getTransaction().commit();
//    	EntityManagerHelper.closeEntityManager();	  
		entityManager.getTransaction().begin();
		entityManager.persist(atuendo);
		entityManager.getTransaction().commit();
		idAtuendo = atuendo.getId();

		return idAtuendo;
	}
	

	public void actualizarAtuendo(Atuendo atuendo) {
		
		if(atuendo.getId() == 0) {
			try {
				throw new Exception("El atuendo es nuevo y no existe en la base de datos");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Ver si anda asi o hay q hacer merge antes del commit..
		entityManager.getTransaction().begin();
		entityManager.merge(atuendo);
		entityManager.getTransaction().commit();
	}

	public void eliminarAtuendo(Atuendo atuendo) {
		// TODO Auto-generated method stub
		entityManager.getTransaction().begin();
		entityManager.remove(atuendo);
		entityManager.getTransaction().commit();
	}
	
	public Long obtenerUltimoIDAtuendoInsertado() {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("SELECT max(a.id) from Atuendo a");
		Long idAtuendo = (Long)query.getSingleResult();
		System.out.println("idAtuendo: " + idAtuendo);
		return idAtuendo;
	}
	
}
