package DDS_2019.Controllers;
import java.util.HashMap;
import java.util.Set;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import com.google.common.collect.Sets;

import DDS_2019.DAOs.GuardarropaDAO;
import DDS_2019.DAOs.PersonaDAO;
import DDS_TP2019.Dominio.Guardarropa;
import DDS_TP2019.Dominio.Persona;
import DDS_TP2019.Dominio.Prenda;
import db.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerPersona implements WithGlobalEntityManager {
    private static HashMap<String, Object> init(Request req) {
    	HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("persona", req.session().attribute("persona"));
		return viewModel;
    	
    }
	public static void chequearClienteLogueado(Request req, Response res) {
		if (req.session().attribute("persona") == null)
        	res.redirect("/usuarioHome");
	}
	
	public ModelAndView mostrarPerfil(Request req, Response res){
		 Persona persona = req.session().attribute("persona");
	     return new ModelAndView(persona, "perfil.hbs");
	}
	
	 public ModelAndView listarGuardarropas(Request req, Response res){
	    
		 Persona persona = req.session().attribute("persona");
	     return new ModelAndView(persona, "listadoGuardarropas.hbs");
	    }
	 
	 public ModelAndView agregarGuardarropa(Request req, Response res){
		    
		 Persona persona = req.session().attribute("persona");
		 Guardarropa guardarropa = new Guardarropa();
		 persona.agregarGuardarropa(guardarropa);
		 System.out.println("Guardando nuevo guardarropa..");
		 GuardarropaDAO guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
		 guardarropa.agregarPersona(persona);
		 long nuevoId = guardarropaDAO.guardarGuardarropa(guardarropa);
		 guardarropa.setId(nuevoId);
		 System.out.println("id nuevo guardarropa: " + guardarropa.getId());
		 res.redirect("/misguardarropas");
		 return null;
	 }
	 
	 public ModelAndView eliminarGuardarropa(Request req, Response res){
		 
		 System.out.println("Eliminando guardarropa..");
		 Long idGuardarropaAeliminar = Long.valueOf(req.params(":id"));
		 System.out.println("idGuardarropaAeliminar: " + idGuardarropaAeliminar);
		 Persona persona = req.session().attribute("persona");
		 persona.eliminarGuardarropaConId(idGuardarropaAeliminar);
		 System.out.println("Guardarropa eliminado de la persona en memoria..");
		 GuardarropaDAO guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
		 Guardarropa guardarropa = guardarropaDAO.obtenerGuardarropa(idGuardarropaAeliminar);
		 guardarropaDAO.eliminarGuardarropa(guardarropa);
		 res.redirect("/misguardarropas");
		 return null;
	    }
}
