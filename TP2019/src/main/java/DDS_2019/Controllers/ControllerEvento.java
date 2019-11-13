package DDS_2019.Controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import DDS_2019.DAOs.AtuendoDAO;
import DDS_2019.DAOs.EventoDAO;
import DDS_2019.DAOs.GuardarropaDAO;
import DDS_2019.DAOs.PersonaDAO;
import DDS_2019.DAOs.PrendaDAO;
import DDS_TP2019.Dominio.Atuendo;
import DDS_TP2019.Dominio.Evento;
import DDS_TP2019.Dominio.Guardarropa;
import DDS_TP2019.Dominio.Persona;
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
    
    public ModelAndView aceptarAtuendo(Request req, Response res){ 
    	
    	 System.out.println("Aceptando Atuendo..");
		 Long idAtuendo = Long.valueOf(req.params(":id"));
		 System.out.println("idAtuendo: " + idAtuendo);
		 Long idEvento = Long.valueOf(req.session().attribute("eventoID"));
	     System.out.println("cookie eventoID : " + idEvento);
	     EventoDAO eventoDAO = new EventoDAO(EntityManagerHelper.getEntityManager());
	     Evento evento = eventoDAO.obtenerEvento(idEvento);
		 AtuendoDAO atuendoDAO = new AtuendoDAO(EntityManagerHelper.getEntityManager());
		 Atuendo atuendo = atuendoDAO.obtenerAtuendo(idAtuendo);
		 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
		 Persona persona = personaDAO.obtenerPersona(Long.valueOf(req.cookie("uid")));
		 persona.aceptarAtuendo(evento, atuendo);
		 atuendo.setPersona(persona);
		 atuendo.setEventoAceptado(evento);
		 eventoDAO.actualizarEvento(evento);
		 personaDAO.actualizarPersona(persona);
		 atuendoDAO.actualizarAtuendo(atuendo);
		 res.redirect("/evento/" + idEvento + "/verAtuendosSugeridos"); 
    	 return null;
    }
    
   public ModelAndView rechazarAtuendo(Request req, Response res){ 
    	
	     System.out.println("Aceptando Atuendo..");
		 Long idAtuendo = Long.valueOf(req.params(":id"));
		 System.out.println("idAtuendo: " + idAtuendo);
		 Long idEvento = Long.valueOf(req.session().attribute("eventoID"));
	     System.out.println("cookie eventoID : " + idEvento);
	     EventoDAO eventoDAO = new EventoDAO(EntityManagerHelper.getEntityManager());
	     Evento evento = eventoDAO.obtenerEvento(idEvento);
		 AtuendoDAO atuendoDAO = new AtuendoDAO(EntityManagerHelper.getEntityManager());
		 Atuendo atuendo = atuendoDAO.obtenerAtuendo(idAtuendo);
		 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
		 Persona persona = personaDAO.obtenerPersona(Long.valueOf(req.cookie("uid")));
		 persona.rechazarAtuendo(evento, atuendo);
		 atuendo.setPersona(persona);
		 atuendo.setEventoAceptado(evento);
		 eventoDAO.actualizarEvento(evento);
		 personaDAO.actualizarPersona(persona);
		 atuendoDAO.actualizarAtuendo(atuendo);
		 res.redirect("/evento/" + idEvento + "/verAtuendosSugeridos"); 
		 return null;
    }
    
}
