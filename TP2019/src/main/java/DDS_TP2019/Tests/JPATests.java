package DDS_TP2019.Tests;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class JPATests {

	 protected static EntityManagerFactory emf;
	    protected static EntityManager em;

	    @BeforeClass
	    public static void init() throws Exception {
	        emf = Persistence.createEntityManagerFactory("defaultPU");
	        em = emf.createEntityManager();
	    }

	    @AfterClass
	    public static void tearDown(){
	        em.clear();
	        em.close();
	        emf.close();
	    }

	  
}
