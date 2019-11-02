package DDS_2019.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import DDS_TP2019.Dominio.Prenda;
import db.EntityManagerHelper;

public class PrendaDAO {
	
	private EntityManager entityManager;
	
	public PrendaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;		
	}

	public Prenda obtenerPrenda(long idUsuario) {
		
		Prenda prenda = entityManager.find(Prenda.class, idUsuario);
		
		if(prenda == null) {
			try {
				throw new Exception("No existe una prenda con el id: " + idUsuario);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return prenda;
	}
	
	public long guardarPrenda(Prenda prenda) {
		
		long idUsuario;
		
		if(prenda.getId() != 0) {
			try {
				throw new Exception("La prenda no es nueva");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		EntityManagerHelper.beginTransaction();
    	EntityManagerHelper.getEntityManager().persist(prenda);
    	EntityManagerHelper.getEntityManager().getTransaction().commit();
//		entityManager.persist(prenda);
//		entityManager.getTransaction().commit();
		idUsuario = prenda.getId();

		return idUsuario;
	}
	

	public void actualizarPrenda(Prenda prenda) {
		
		if(prenda.getId() == 0) {
			try {
				throw new Exception("La prenda es nueva y no existe en la base de datos");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		entityManager.getTransaction().begin();
		entityManager.merge(prenda);
		entityManager.getTransaction().commit();
	}
}
