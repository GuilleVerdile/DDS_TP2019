package DDS_2019.Controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import DDS_2019.DAOs.AtuendoDAO;
import DDS_TP2019.Dominio.Atuendo;
import db.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerAtuendo implements WithGlobalEntityManager{

    public ModelAndView verPrendas(Request req, Response res) throws Exception {
    	Long idAtuendo;
    	idAtuendo = Long.valueOf(req.params("id"));
	  	AtuendoDAO atuendoDAO = new AtuendoDAO(EntityManagerHelper.emf.createEntityManager());
	  	Atuendo atuendo = atuendoDAO.obtenerAtuendo(idAtuendo);
	 	System.out.println("cantidad de prendas del atuendo: " + atuendo.getPrendas().size());
        return new ModelAndView(atuendo, "detallesAtuendo.hbs");
    }
    
}
