package DDS_2019.Controllers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import DDS_2019.DAOs.PersonaDAO;
import DDS_TP2019.Dominio.Persona;
import db.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

public class ControllerLogin implements WithGlobalEntityManager{
    public ModelAndView mostrarLogin(Request req, Response red){
        return new ModelAndView(null, "login.hbs");
    }

	public ModelAndView redirigirAGuardarropas (Request req, Response res) throws Exception {
	    long idUsuario = 0;
	    PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
	    Persona usuario = personaDAO.obtenerPersonaByMail(req.params("mail"),req.params("password"));
	    
	    idUsuario = usuario.getId();
	    res.cookie("uid", Long.toString(idUsuario));
	    
	    res.redirect("/misguardarropas");
	    
	    return null;

	}

	public ModelAndView redirigirAPerfil (Request req, Response res) throws Exception {
		long idUsuario = 0;
		Persona usuario = obtenerUsuarioByLogin(req);
		if(usuario == null)
		{
			res.redirect("/usuarioHome"); 
		}
		else if (usuario != null) 
		{
			idUsuario = usuario.getId();
			res.cookie("uid", Long.toString(idUsuario));
			Session session = req.session(true);
			session.attribute("persona",usuario);
			res.redirect("/persona/"+idUsuario+"/perfil");
		}
		return null;
	}

	
	private Persona obtenerUsuarioByLogin(Request req) throws Exception
	{
		System.out.println("Buscando usuario..");
		PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
		Persona usuario = personaDAO.obtenerPersonaByMail(req.queryParams("user"),req.queryParams("password"));
	    return usuario;
	}

	public ModelAndView login(Request req, Response res) {
		req.session().removeAttribute("persona");
		return new ModelAndView(null, "login.hbs");
	}
	
	
	private String hashedPassword(String pass) {
		try {
			return new String(Base64.getEncoder().encode(MessageDigest.getInstance("SHA-256").digest(pass.getBytes(StandardCharsets.UTF_8))));
		}
    	catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}