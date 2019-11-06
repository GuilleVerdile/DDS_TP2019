package DDS_2019.Controllers;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import DDS_2019.DAOs.GuardarropaDAO;
import DDS_2019.DAOs.PersonaDAO;
import DDS_TP2019.Dominio.Guardarropa;
import DDS_TP2019.Dominio.Persona;
import DDS_TP2019.Dominio.Sistema;
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
	    
//		 Persona persona = req.session().attribute("persona");
		 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
		 Persona persona = personaDAO.obtenerPersona(Long.valueOf(req.cookie("uid")));
		
	     return new ModelAndView(persona, "listadoGuardarropas.hbs");
	    }
	 
	 public ModelAndView agregarGuardarropa(Request req, Response res){
		    
//		 Persona persona = req.session().attribute("persona");
		 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
		 Persona persona = personaDAO.obtenerPersona(Long.valueOf(req.cookie("uid")));
		 Guardarropa guardarropa = new Guardarropa();
		 System.out.println("Guardando nuevo guardarropa..");
		 guardarropa.agregarPersona(persona);
		 GuardarropaDAO guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
		 long nuevoId = guardarropaDAO.guardarGuardarropa(guardarropa);
		 persona.agregarGuardarropa(guardarropa);
		 personaDAO.actualizarPersona(persona);
		 System.out.println("id nuevo guardarropa: " + guardarropa.getId());
		 res.redirect("/misguardarropas");
		 return null;
	 }
	 
	 public ModelAndView eliminarGuardarropa(Request req, Response res){
		 
		 System.out.println("Eliminando guardarropa..");
		 Long idGuardarropaAeliminar = Long.valueOf(req.params(":id"));
		 System.out.println("idGuardarropaAeliminar: " + idGuardarropaAeliminar);
//		 Persona persona = req.session().attribute("persona");
//		 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
//		 Persona persona = personaDAO.obtenerPersona(Long.valueOf(req.cookie("uid")));
//		 persona.eliminarGuardarropaConId(idGuardarropaAeliminar);
		 System.out.println("Guardarropa eliminado de la persona en memoria..");
		 GuardarropaDAO guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
		 Guardarropa guardarropa = guardarropaDAO.obtenerGuardarropa(idGuardarropaAeliminar);
		 guardarropaDAO.eliminarGuardarropa(guardarropa);
		 res.redirect("/misguardarropas");
		 return null;
	 }
	 
	 public ModelAndView compartirGuardarropa(Request req, Response res){
		
		System.out.println("id guardarropa elegido: " + req.params("id"));
	  	res.cookie("g_id", req.params("id"));
	  	System.out.println("cookie id guardarropa elegido: " + req.cookie("g_id"));
	  	 
	  	req.session().attribute("guardarropaID",req.params("id"));
	  	Persona persona = req.session().attribute("persona");
	    Sistema sistema = new Sistema();
	 	PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
	 	java.util.List<Persona> personas = new ArrayList<Persona>();
	 	try {
			personas = personaDAO.obtenerPersonasDistintasA(persona.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	if (persona.esUsuarioPremium()) {
	 		personas.stream().filter(p-> p.esUsuarioPremium());
	 	}
	 	else {
	 		personas.stream().filter(p-> p.esUsuarioGratuito());
	 	}
		sistema.setPersonas(personas);
	    return new ModelAndView(sistema, "compartirGuardarropa_elegirUsuarios.hbs");
 	}
	 
	public ModelAndView agregarGuardarropaCompartido(Request req, Response res){
		 
		 System.out.println("Compartiendo Guardarropa..");
		 String idUsuarioAcompartir = req.queryParams("codigosUsuarios");
		 System.out.println(idUsuarioAcompartir);
		 java.util.List<String> items = Arrays.asList(idUsuarioAcompartir.split("\\s*,\\s*"));
		 java.util.List<Long> idPersonas = new ArrayList<Long>();
		 items.stream().forEach(id -> idPersonas.add(Long.valueOf(id)));
		 //al guardarropa de la session atribute, agregarle la persona , y a la persona agregarle este guardarropa
		 // despues peristir todo y volver
		 
//		 System.out.println("idGuardarropaAeliminar: " + idGuardarropaAeliminar);
//		 Persona persona = req.session().attribute("persona");
//		 persona.eliminarGuardarropaConId(idGuardarropaAeliminar);
//		 System.out.println("Guardarropa eliminado de la persona en memoria..");
//		 GuardarropaDAO guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
//		 Guardarropa guardarropa = guardarropaDAO.obtenerGuardarropa(idGuardarropaAeliminar);
//		 guardarropaDAO.eliminarGuardarropa(guardarropa);
		 
		 res.redirect("/misguardarropas");
		 return null;
	 }
 
}
