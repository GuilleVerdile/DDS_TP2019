package DDS_2019.Controllers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.joda.time.DateTime;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import DDS_2019.DAOs.EventoDAO;
import DDS_2019.DAOs.GuardarropaDAO;
import DDS_2019.DAOs.PersonaDAO;
import DDS_TP2019.Clima.ServicioOpenWeather;
import DDS_TP2019.Dominio.Evento;
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
		 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.emf.createEntityManager());
		 Persona persona = personaDAO.obtenerPersona(Long.valueOf(req.cookie("uid")));
		
	     return new ModelAndView(persona, "listadoGuardarropas.hbs");
	    }
	 
	 public ModelAndView agregarGuardarropa(Request req, Response res){
		    
//		 Persona persona = req.session().attribute("persona");
		 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
		 Persona persona = personaDAO.obtenerPersona(Long.valueOf(req.cookie("uid")));
		 Guardarropa guardarropa = new Guardarropa();
		 guardarropa.setIdDuenio(persona.getId());
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
		 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
		 Persona persona = personaDAO.obtenerPersona(Long.valueOf(req.cookie("uid")));
		 persona.eliminarGuardarropaConId(idGuardarropaAeliminar);
		 System.out.println("Guardarropa eliminado de la persona en memoria..");
		 GuardarropaDAO guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
		 Guardarropa guardarropa = guardarropaDAO.obtenerGuardarropa(idGuardarropaAeliminar);
		 guardarropaDAO.eliminarGuardarropa(guardarropa);
		 personaDAO.actualizarPersona(persona);
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
		 
		 Long idGuardarropa = Long.valueOf(req.session().attribute("guardarropaID"));
			System.out.println(idGuardarropa);
		 GuardarropaDAO guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
	   	 Guardarropa guardarropa = guardarropaDAO.obtenerGuardarropa(idGuardarropa);
	   	System.out.println(guardarropa.getId());
	   	 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
	   	 idPersonas.stream().forEach(idPersona -> {
	   		 Persona p = personaDAO.obtenerPersona(idPersona);
	   		 guardarropa.agregarPersona(p)  ;
	   		 guardarropaDAO.actualizarGuardarropa(guardarropa); 
		     p.agregarGuardarropa(guardarropa);
			 personaDAO.actualizarPersona(p);
	   	 });
	   
//	  	System.out.println(" idGuardarropa BD: " + guardarropa.getId());

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
	
	public ModelAndView dejarDeCompartirGuardarropa(Request req, Response res){
		 
		 System.out.println("Dejando de Compartir Guardarropa..");
		 Long idGuardarropa = Long.valueOf(req.session().attribute("guardarropaID"));
		 System.out.println(idGuardarropa);
		 GuardarropaDAO guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
	   	 Guardarropa guardarropa = guardarropaDAO.obtenerGuardarropa(idGuardarropa);
	   	System.out.println(guardarropa.getId());
	   	
	   	 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
	   	 Persona personaLoggeada = personaDAO.obtenerPersona(Long.valueOf(req.cookie("uid")));
	   	guardarropa.getPersonas().stream().forEach(persona -> {
	   		 Persona p = personaDAO.obtenerPersona(persona.getId());
	   		 guardarropa.eliminarPersonaConId(p.getId())  ;
	   		 guardarropaDAO.actualizarGuardarropa(guardarropa); 
		     p.agregarGuardarropa(guardarropa);
			 personaDAO.actualizarPersona(p);
	   	 });
	   
		 res.redirect("/misguardarropas");
		 return null;
	 }
	
	
	 public ModelAndView listarEventos(Request req, Response res){
		    
//		 Persona persona = req.session().attribute("persona");
		 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.emf.createEntityManager());
		 Persona persona = personaDAO.obtenerPersona(Long.valueOf(req.cookie("uid")));
		
	     return new ModelAndView(persona, "listadoEventos.hbs");
	    }
 
	  public ModelAndView eliminarEvento(Request req, Response res){
			 
		 System.out.println("Eliminando evento..");
		 Long idEventoAeliminar = Long.valueOf(req.params(":id"));
		 System.out.println("idEventoAeliminar: " + idEventoAeliminar);
		 
		 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
		 Persona persona = personaDAO.obtenerPersona(Long.valueOf(req.cookie("uid")));
		 persona.eliminarEventoConId(idEventoAeliminar);
		 
		 System.out.println("Evento eliminado de la persona en memoria..");
		 
		 EventoDAO eventoDAO = new EventoDAO(EntityManagerHelper.getEntityManager());
		 Evento eventoAEliminar = eventoDAO.obtenerEvento(idEventoAeliminar);
		 eventoDAO.eliminarEvento(eventoAEliminar);
		 personaDAO.actualizarPersona(persona);
		 System.out.println("Evento eliminado de la persona en BD..");
		 
		 res.redirect("/misEventos");
		 return null;
	    }
	  
	  public ModelAndView altaEvento(Request req, Response res) {
		  res.cookie("operacionEvento", "ALTA");
		  System.out.println("operacionEvento recien seteada..: " + req.cookie("operacionEvento"));
	        return new ModelAndView(null, "altaEvento.hbs");
	    }
	  
	  public ModelAndView modificarEvento(Request req, Response res) {
		  res.cookie("operacionEvento", "MODIFICACION");
		  System.out.println("operacionEvento recien seteada..: " + req.cookie("operacionEvento"));
		  EventoDAO eventoDAO = new EventoDAO(EntityManagerHelper.emf.createEntityManager());
		  Evento eventoAModificar = eventoDAO.obtenerEvento(Long.valueOf(req.params(":id")));
//		  eventoAModificar.setFechaInicioEvento(new SimpleDateFormat("MM/dd/yyyy").format(eventoAModificar.getFechaInicioEvento()));
		 String fechaInicio =  eventoAModificar.getFechaInicioEvento().toString();
		 System.out.println("tipoEvento: " + eventoAModificar.getTipoDeEvento());
		 System.out.println("ubicacion: " + eventoAModificar.getUbicacion());
		 String aux = eventoAModificar.getUbicacion().replace(' ', '_');
		 String desc = eventoAModificar.getDescripcionEvento().replace(' ', '_');
		  fechaInicio = fechaInicio.substring(0, 10);
		  System.out.println("fechaInicio..: " + fechaInicio.substring(0, 10));
		  String fechaFin =  eventoAModificar.getFechaFinEvento().toString();
		  fechaFin = fechaFin.substring(0, 10);
		  System.out.println("fechaFin..: " + fechaFin.substring(0, 10));  
		  HashMap<String, Object> viewModel = new HashMap<>();
		  viewModel.put("evento", eventoAModificar);
		  viewModel.put("fechaInicioEvento", fechaInicio);
		  viewModel.put("fechaFinEvento", fechaFin);
		  viewModel.put("ubicacionEvento", aux);
		  viewModel.put("descripcionEvento", desc);
		  return new ModelAndView(viewModel, "altaEvento.hbs");
	    }
 
	  public ModelAndView construirEvento(Request req, Response res) throws Exception {
		  PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
		  Persona persona = personaDAO.obtenerPersona(Long.valueOf(req.cookie("uid")));
		  EventoDAO eventoDAO = new EventoDAO(EntityManagerHelper.getEntityManager());
    	Evento evento = new Evento();
    	
    	
    	//Atributos Evento
    	String descripcion = req.queryParams("descripcionEvento");
    	DateTime fechaInicio = DateTime.parse(req.queryParams("fechaInicioEvento"));
    	DateTime fechaFin = DateTime.parse(req.queryParams("fechaFinEvento"));
        String ubicacion = req.queryParams("ubicacionEvento");
        String tipoDeEvento = req.queryParams("tipoDeEvento");
       

        if(req.queryParams("idEvento").length() > 0)
    	{
    		System.out.println("modificando evento en bd..: ");
    		Evento eventoAModificar = eventoDAO.obtenerEvento(Long.valueOf(req.queryParams("idEvento")));
    		eventoAModificar.setDescripcionEvento(descripcion);
    		eventoAModificar.setFechaInicioEvento(fechaInicio);
    		eventoAModificar.setFechaFinEvento(fechaFin);
    		eventoAModificar.setUbicacion(ubicacion);
    		eventoAModificar.setTipoDeEvento(tipoDeEvento);
    		eventoDAO.actualizarEvento(eventoAModificar);
    		res.redirect("/misEventos");
    		return null;
    	}
        
	    //Setteo del evento y creacion
        evento.setDescripcionEvento(descripcion);
        evento.setFechaInicioEvento(fechaInicio);
        evento.setFechaFinEvento(fechaFin);
        evento.setUbicacion(ubicacion);
        evento.setTipoDeEvento(tipoDeEvento);
	    
       
	   	 evento.setPersona(persona);
	   	 eventoDAO.guardarEvento(evento);
	 	System.out.println("Se agrego el nuevo evento al usuario en memoria. " );
	 	 persona.agregarEvento(evento);
	 	personaDAO.actualizarPersona(persona);
	 	System.out.println("aaaaaaaaaaa");
		Long idEvento = eventoDAO.obtenerUltimoIDEventoInsertado();
//		System.out.println("eeeeeeeeeeeee");
	   	evento.setId(idEvento);
	   	
	 	System.out.println("id nuevo evento: " + evento.getId());
	 	System.out.println("cantidad de eventos de la persona: " + persona.getEventos().size());
	   	
		Evento eventoTest = eventoDAO.obtenerEvento(idEvento);
	 	
	 	persona.obtenerAtuendosParaEventoProximo(eventoTest, new ServicioOpenWeather());
	 	
	 	 res.redirect("/misEventos");
		 return null;
	 
    }
	  
}
