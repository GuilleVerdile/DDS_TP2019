package DDS_2019.DAOs;

import javax.persistence.EntityManager;

import DDS_TP2019.Dominio.Guardarropa;

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
		
		entityManager.getTransaction().commit();
	}
}
