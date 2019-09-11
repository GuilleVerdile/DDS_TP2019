package DDS_2019.DAOs;

import javax.persistence.EntityManager;

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

        entityManager.remove(evento);
        //entityManager.getTransaction().commit();
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

        entityManager.getTransaction().commit();
    }
}
