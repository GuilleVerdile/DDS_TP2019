package DDS_2019.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import DDS_TP2019.Dominio.Persona;
import DDS_TP2019.Dominio.TipoPrenda;
import db.EntityManagerHelper;

public class TipoPrendaDAO {
	
	private EntityManager entityManager;
	
	public TipoPrendaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;		
	}

	public TipoPrenda obtenerTipoPrendaById(long idUsuario) {
		
		TipoPrenda persona = entityManager.find(TipoPrenda.class, idUsuario);
		
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
	
	public List<TipoPrenda> obtenerTiposPrenda() throws Exception 
	{
		Query query = entityManager.createQuery("SELECT p FROM TipoPrenda p", TipoPrenda.class);
		
		List<TipoPrenda> tiposPrenda = (List<TipoPrenda>)query.getResultList();
		
		System.out.println("Devolviendo los tiposPrenda..");
		return tiposPrenda;
	}

	public List<String> obtenerTiposTelasPosible(Long idTipoPrenda) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("SELECT telas FROM TipoPrenda tp join tp.tiposTelaPosible telas where tp.id = :idTipoPrenda");
		query.setParameter("idTipoPrenda",idTipoPrenda);
		List<String> tiposPrenda = (List<String>)query.getResultList();
		
		System.out.println("Devolviendo los tiposPrenda..");
		System.out.println(tiposPrenda.get(0));
		return tiposPrenda;
	}
	
}
