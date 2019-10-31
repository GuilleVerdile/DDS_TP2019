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
//		 Guardarropa guardarropa1 = new Guardarropa();
//		 Guardarropa guardarropa2 = new Guardarropa();
//		 Guardarropa guardarropa3 = new Guardarropa();
//		 Set <Guardarropa> g = Sets.newHashSet();
//		 persona.agregarGuardarropa(guardarropa1);
//		 persona.agregarGuardarropa(guardarropa2);
//		 persona.agregarGuardarropa(guardarropa3);
	        return new ModelAndView(persona, "listadoGuardarropas.hbs");
	    }
	 
	 public ModelAndView agregarGuardarropa(Request req, Response res){
		    
		 Persona persona = req.session().attribute("persona");
		 Guardarropa guardarropa = new Guardarropa();
		 persona.agregarGuardarropa(guardarropa);
		 System.out.println("Actualizando usuario..");
		 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
//		 personaDAO.actualizarPersona(persona);
		 System.out.println("Guardando nuevo guardarropa..");
		 GuardarropaDAO guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
		 guardarropa.agregarPersona(persona);
		 guardarropaDAO.guardarGuardarropa(guardarropa);
		 res.redirect("/misguardarropas");
		 return null;
	    }
}
