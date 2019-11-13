package DDS_2019.DAOs;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import DDS_TP2019.Dominio.Evento;

public class EventoDAO {

    private EntityManager entityManager;

    public EventoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Evento obtenerEvento(long idEvento) {

        Evento evento = entityManager.find(Evento.class, idEvento);

        if(evento == null) {
            try {
				throw new Exception("No existe un usuario con el id: " + idEvento);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return evento;
    }

    public long guardarEvento(Evento evento) {

        long idEvento;

        if(evento.getId() != 0) {
            try {
				throw new Exception("El Evento no es nuevo");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        entityManager.getTransaction().begin();
        entityManager.persist(evento);
        entityManager.getTransaction().commit();
        idEvento = evento.getId();

        return idEvento;
    }

    public long eliminarEvento(Evento evento) {

        long idEvento;

        if(evento.getId() == 0) {
            try {
				throw new Exception("El Evento no existe");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        entityManager.getTransaction().begin();
        entityManager.remove(evento);
        entityManager.getTransaction().commit();
        idEvento = evento.getId();

        return idEvento;
    }

    public void actualizarEvento(Evento evento) {

        if(evento.getId() == 0) {
            try {
				throw new Exception("El evento es nuevo y no existe en la base de datos");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        entityManager.getTransaction().begin();
		entityManager.merge(evento);
        entityManager.getTransaction().commit();
    }

	public Long obtenerUltimoIDEventoInsertado() {
		Query query = entityManager.createQuery("SELECT max(e.id) from Evento e");
		Long idEvento = (Long)query.getSingleResult();
		System.out.println("idEvento: " + idEvento);
		return idEvento;
	}

	public void eliminarUltimoEventoInsertado() {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("DELETE top 1 FROM Evento e order by e.id DESC");
		query.executeUpdate();
	}
}
