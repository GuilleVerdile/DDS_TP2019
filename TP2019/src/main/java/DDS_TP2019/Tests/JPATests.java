package DDS_TP2019.Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DDS_2019.DAOs.EventoDAO;
import DDS_2019.DAOs.GuardarropaDAO;
import DDS_2019.DAOs.PersonaDAO;
import DDS_TP2019.Dominio.Guardarropa;
import DDS_TP2019.Dominio.Persona;
import DDS_TP2019.Dominio.Prenda;
import DDS_TP2019.Dominio.TipoDeUsuario;
import DDS_TP2019.Dominio.TipoPrenda;
import DDS_TP2019.Dominio.UsuarioPremium;
import db.EntityManagerHelper;

public class JPATests {

		protected static EntityManagerFactory emf;
	    protected static EntityManager em;
	    private Persona personaNotificar;
		
		private GuardarropaDAO guardarropaDAO;
		private PersonaDAO personaDAO;
		private EventoDAO eventoDAO;
		private Guardarropa guardarropa1;
		private TipoDeUsuario tipoDeUsuarioPremium;
		private Persona persona1;
		private Persona persona2;
		
		@Before
		public void initialize() {
			guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
			personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
			eventoDAO = new EventoDAO(EntityManagerHelper.getEntityManager());
			
			List<String> telas =  new ArrayList<String>();
			telas.add("algodon");
		    List<String> tipoEventoFormal = new ArrayList<String>();
		    tipoEventoFormal.add("FORMAL");
		    List<String> tipoEventoInformal = new ArrayList<String>();
		    tipoEventoInformal.add("INFORMAL");
		    List<String> tipoEventoMixto =  new ArrayList<String>();
		    tipoEventoMixto.add("FORMAL");
		    tipoEventoMixto.add("INFORMAL");
			Prenda jeanTrabajo = new Prenda("gris","",new TipoPrenda("jean","parteinferior",telas,tipoEventoFormal,2),"gabardina",20);
			Prenda zapatos = new Prenda("negro","",new TipoPrenda("zapatos","calzado",telas,tipoEventoFormal,2),"cuero",10);
			Prenda zapatillasCalle = new Prenda("rojo","negro",new TipoPrenda("zapatillas","calzado",telas,tipoEventoInformal,2),"cuero",10);
			Prenda medias1 = new Prenda("negro","",new TipoPrenda("zapatos","calzado",telas,tipoEventoMixto,1),"algodon",5);
			Prenda medias2 = new Prenda("rojo","negro",new TipoPrenda("zapatillas","calzado",telas,tipoEventoMixto,1),"algodon",5);
		
	        guardarropa1 = new Guardarropa();
	        guardarropa1.agregarPrenda(medias2);
	        guardarropa1.agregarPrenda(medias1);
	        guardarropa1.agregarPrenda(zapatillasCalle);
	        guardarropa1.agregarPrenda(zapatos);
	        guardarropa1.agregarPrenda(jeanTrabajo);
	        
			tipoDeUsuarioPremium = new UsuarioPremium();
//			tipoDeUsuarioPremium.se
	        persona1 = new Persona("Jonathan",tipoDeUsuarioPremium);
	        persona1.agregarGuardarropa(guardarropa1);
	        persona2 = new Persona("Juan",tipoDeUsuarioPremium);
		}
		
	    @BeforeClass
	    public static void init() throws Exception {
	        emf = Persistence.createEntityManagerFactory("db");
	        em = emf.createEntityManager();
	    }

	    @AfterClass
	    public static void tearDown(){
	        em.clear();
	        em.close();
	        emf.close();
	    }

	    @Test
		public void persistirUsuario() {
//	    	persona1.setId(1);
//	    	EntityManagerHelper.beginTransaction();
//	    	EntityManagerHelper.getEntityManager().persist(tipoDeUsuarioPremium);
//	    	EntityManagerHelper.getEntityManager().getTransaction().commit();
			long idUsuario = personaDAO.guardarPersona(persona2);
			
			Persona usuarioDB = personaDAO.obtenerPersona(idUsuario);
			System.out.println("Se guardo una nueva persona - id nueva persona: " + usuarioDB.getId());
			GuardarropaDAO guardarropaDAO2 = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
			Guardarropa g1 = new Guardarropa();
			 persona2.agregarGuardarropa(g1);
			 System.out.println("Guardando nuevo guardarropa..");
			 g1.agregarPersona(usuarioDB);
			 long nuevoId = guardarropaDAO2.guardarGuardarropa(g1);
			 g1.setId(nuevoId);
			 System.out.println("Se agrego un nuevo g1: id nuevo guardarropa: " + g1.getId());
		
			 Guardarropa g2 = new Guardarropa();
			 GuardarropaDAO guardarropaDAO3 = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
			 persona2.agregarGuardarropa(g2);
			 System.out.println("Guardando nuevo guardarropa..");
			 g2.agregarPersona(usuarioDB);
			 long nuevoId2 = guardarropaDAO3.guardarGuardarropa(g2);
			 g2.setId(nuevoId2);
			 System.out.println("Se agrego un nuevo g2: id nuevo guardarropa: " + g2.getId());
			 
			 Guardarropa g3 = new Guardarropa();
			 GuardarropaDAO guardarropaDAO1 = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
			 persona2.agregarGuardarropa(g3);
			 System.out.println("Guardando g3 guardarropa..");
			 g3.agregarPersona(usuarioDB);
			 long nuevoId3 = guardarropaDAO1.guardarGuardarropa(g3);
			 g3.setId(nuevoId3);
			 System.out.println("Se agrego un nuevo guardarropa: id nuevo guardarropa: " + g3.getId());
			
			 Persona usuarioDBActual = personaDAO.obtenerPersona(idUsuario);
			 
			 assertEquals(usuarioDBActual.getGuardarropas().size(), 3);
		}
	    
	    
}
