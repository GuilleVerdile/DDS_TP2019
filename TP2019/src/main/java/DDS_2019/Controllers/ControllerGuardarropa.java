package DDS_2019.Controllers;

import java.awt.List;
import java.io.IOException;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import DDS_2019.DAOs.GuardarropaDAO;
import DDS_2019.DAOs.TipoPrendaDAO;
import DDS_TP2019.Dominio.Guardarropa;
import DDS_TP2019.Dominio.Persona;
import DDS_TP2019.Dominio.Prenda;
import DDS_TP2019.Dominio.Sistema;
import DDS_TP2019.Dominio.TipoPrenda;
import db.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerGuardarropa implements WithGlobalEntityManager{


    public ModelAndView agregarPrenda(Request req, Response res) {
        System.out.println("id guardarropa elegido: " + req.params("id"));
   	    res.cookie("g_id", req.params("id"));
   	    Sistema sistema = new Sistema();
//	   	 try {			
//				sistema.setTiposPrendas(Sistema.importarTipoPrendas());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
	 	TipoPrendaDAO tipoPrendaDAO = new TipoPrendaDAO(EntityManagerHelper.getEntityManager());
		 try {
			java.util.List<TipoPrenda> tiposPrendas = tipoPrendaDAO.obtenerTiposPrenda();
			sistema.setTiposPrendas(tiposPrendas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ModelAndView(sistema, "altaPrenda.hbs");
    }

    public ModelAndView agregarPrendaPaso2(Request req, Response res) throws Exception {
        System.out.println("Buscando tipos telas posible..");
        System.out.println("id tipo prenda elegido: " +  req.queryParams("tipoPrenda"));
   	    res.cookie("prenda_idTipoPrenda", req.queryParams("tipoPrenda"));
   	    Long idTipoPrenda = Long.valueOf( req.queryParams("tipoPrenda"));
	   	TipoPrendaDAO tipoPrendaDAO = new TipoPrendaDAO(EntityManagerHelper.getEntityManager());
		 java.util.List<String> tiposTelas = tipoPrendaDAO.obtenerTiposTelasPosible(idTipoPrenda);
		TipoPrenda tp = new TipoPrenda();
		tp.setTiposTelaPosible(tiposTelas);
        return new ModelAndView(tp, "altaPrendaPaso2.hbs");
    }
   
    public ModelAndView agregarPrendaPaso3(Request req, Response res) throws Exception {
        System.out.println("tipo tela elegido: " +  req.queryParams("tipoTela"));
   	    res.cookie("prenda_tipoTela", req.queryParams("tipoTela"));
   	  Sistema sistema = new Sistema();
	   	 try {			
				sistema.setColores(Sistema.importarColores());
			} catch (IOException e) {
				e.printStackTrace();
			}
        return new ModelAndView(sistema, "altaPrendaPaso3.hbs");
    }
    
    public ModelAndView construirPrenda(Request req, Response res) throws Exception {
     System.out.println("colorPrimario: " +  req.queryParams("colorPrimario"));
     System.out.println("colorSecundario: " +  req.queryParams("colorSecundario"));
   	 String colorSecundario =  req.queryParams("colorSecundario");
   	 String colorPrimario =  req.queryParams("colorPrimario");
   	 String prenda_tipoTela = req.cookie("prenda_tipoTela");
   	 Long prenda_idTipoPrenda = Long.valueOf(req.cookie("prenda_idTipoPrenda"));
   	 TipoPrendaDAO tipoPrendaDAO = new TipoPrendaDAO(EntityManagerHelper.getEntityManager());
   	 TipoPrenda tipoPrenda = tipoPrendaDAO.obtenerTipoPrendaById(prenda_idTipoPrenda);
   	 Long idGuardarropa = Long.valueOf(req.cookie("g_id"));
   	 GuardarropaDAO guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
   	 Guardarropa guardarropa = guardarropaDAO.obtenerGuardarropa(idGuardarropa);
//     Prenda prenda = new Prenda(colorPrimario, colorSecundario, tipoPrenda, prenda_tipoTela, calorias);
   	 // HACER OTRA VENTANA PARA INGRESAR LAS CALORIAS DE LA PRENDA
     Prenda prenda = new Prenda(colorPrimario, colorSecundario, tipoPrenda, prenda_tipoTela, 100);
   	 guardarropa.agregarPrenda(prenda);
   	guardarropaDAO.actualizarGuardarropa(guardarropa);
   	System.out.println("id nueva prenda: " + prenda.getId());
   	 //MOSTRAR UN MODAL O ALGUN CARTEL DICIENDO Q LA PRENDA SE CREO BIEN
   	// PONER EN CADA VENTANA UN BOTON DE VOLVER ATRAS
   	
   	
	 res.redirect("/misguardarropas");
	 return null;
       
    }

}
