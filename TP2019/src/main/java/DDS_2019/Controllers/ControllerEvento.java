package DDS_2019.Controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import DDS_2019.DAOs.EventoDAO;
import DDS_2019.DAOs.GuardarropaDAO;
import DDS_2019.DAOs.PrendaDAO;
import DDS_TP2019.Dominio.Evento;
import DDS_TP2019.Dominio.Guardarropa;
import DDS_TP2019.Dominio.Prenda;
import db.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerEvento implements WithGlobalEntityManager{

    public ModelAndView verAtuendosSugeridos(Request req, Response res) throws Exception {
    	 Long idEvento;
    	 idEvento = Long.valueOf(req.params("id"));
    	 res.cookie("evento_id", req.params("id"));
    	 req.session().attribute("eventoID",req.params("id"));
	     System.out.println("cookie idEvento : " + idEvento);
	   	 EventoDAO eventoDAO = new EventoDAO(EntityManagerHelper.emf.createEntityManager());
	   	 Evento evento = eventoDAO.obtenerEvento(idEvento);
	   	 
	 	 System.out.println("cantidad de atuendosSugeridos en evento: " + evento.getAtuendosSugeridos().size());
	 	
         return new ModelAndView(evento, "listadoAtuendosSugeridosDeEvento.hbs");
    }
    
    public ModelAndView eliminarPrenda(Request req, Response res){
		 
		 System.out.println("Eliminando prenda..");
		 Long idPrendaAeliminar = Long.valueOf(req.params(":id"));
		 System.out.println("idPrendaAeliminar: " + idPrendaAeliminar);
		 
		 Long idGuardarropa = Long.valueOf(req.session().attribute("guardarropaID"));
	     System.out.println("cookie idGuardarropa : " + idGuardarropa);
		 GuardarropaDAO guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
		 Guardarropa guardarropa = guardarropaDAO.obtenerGuardarropa(idGuardarropa);
		 
		 guardarropa.eliminarPrendaConId(idPrendaAeliminar);
		 
		 System.out.println("Prenda eliminado del guardarropa en memoria..");
		 
		 PrendaDAO prendaDAO = new PrendaDAO(EntityManagerHelper.getEntityManager());
		 Prenda prendaAEliminaar = prendaDAO.obtenerPrenda(idPrendaAeliminar);
		 prendaDAO.eliminarPrenda(prendaAEliminaar);
		 guardarropaDAO.actualizarGuardarropa(guardarropa);
		 System.out.println("Prenda eliminado del guardarropa en BD..");
		 
		 res.redirect("/guardarropa/" + idGuardarropa + "/verPrendas");
		 return null;
	    }
    
}
