package DDS_2019.Tests;

import db.EntityManagerHelper;

//import DDS_TP2019.Dominio.Evento;
import DDS_TP2019.Dominio.Persona;
//import DDS_TP2019.Dominio.Guardarropa;
//import DDS_TP2019.Dominio.Uso;

//import org.junit.Assert;
import org.junit.Test;
//import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

//import java.time.LocalDate;

import javax.persistence.EntityManager;

public class EmTest{

	@Test
	public void testParaEntity() {
		
		Persona u = new Persona();
		u.setNombre("caro");
		EntityManager em = EntityManagerHelper.getEntityManager();
		
		EntityManagerHelper.beginTransaction();
		em.persist(u);
		EntityManagerHelper.commit();
	}
	
	/*
	 * @Test public void persistir1UsuarioTest(){ Usuario usuario = new Usuario();
	 * usuario.setNombre("Eze"); usuario.setApellido("Escobar");
	 * usuario.setTelefono(44889966); usuario.setLegajo(1527778);
	 * usuario.setFechaDeNacimiento(LocalDate.of(1995,10,14));
	 * 
	 * EntityManagerHelper.beginTransaction();
	 * EntityManagerHelper.getEntityManager().persist(usuario);
	 * EntityManagerHelper.commit(); }
	 * 
	 * @Test public void recuperandoAEze(){ Usuario eze = (Usuario)
	 * EntityManagerHelper.createQuery("from Usuario where nombre = 'Eze'").
	 * getSingleResult(); Assert.assertEquals("Eze", eze.getNombre()); }
	 * 
	 * @Test public void persistir2Aporte(){ Topico topicoApunte = new Topico();
	 * topicoApunte.setNombre("Apunte");
	 * topicoApunte.setDescripcion("Colaboración de un apunte");
	 * 
	 * EntityManagerHelper.beginTransaction();
	 * EntityManagerHelper.getEntityManager().persist(topicoApunte);
	 * EntityManagerHelper.commit();
	 * 
	 * Aporte unAporte = new Aporte(); unAporte.setTopico(topicoApunte); Usuario eze
	 * = (Usuario)
	 * EntityManagerHelper.createQuery("from Usuario where nombre = 'Eze'").
	 * getSingleResult(); unAporte.setUsuario(eze);
	 * unAporte.setNombre("Apunte de diseño");
	 * unAporte.setDescripcion("Taller ORM");
	 * 
	 * EntityManagerHelper.beginTransaction();
	 * EntityManagerHelper.getEntityManager().persist(unAporte);
	 * EntityManagerHelper.commit(); }
	 */
}
