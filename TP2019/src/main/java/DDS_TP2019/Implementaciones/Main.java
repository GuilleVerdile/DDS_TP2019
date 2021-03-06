package DDS_TP2019.Implementaciones;

import java.io.IOException;

import org.joda.time.DateTime;

import com.google.maps.errors.ApiException;

import DDS_2019.Controllers.ControllerAtuendo;
import DDS_2019.Controllers.ControllerEvento;
import DDS_2019.Controllers.ControllerGuardarropa;
import DDS_2019.Controllers.ControllerLogin;
import DDS_2019.Controllers.ControllerPersona;
import DDS_2019.DAOs.AtuendoDAO;
import DDS_2019.DAOs.EventoDAO;
import DDS_TP2019.Clima.GoogleAPI;
import DDS_TP2019.Clima.ServicioOpenWeather;
import DDS_TP2019.Dominio.Evento;
import DDS_TP2019.Dominio.Sistema;
import db.EntityManagerHelper;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main {

	public static void main(String[] args)throws ApiException, InterruptedException, IOException{
		
		Sistema sistema = new Sistema();
		try {			
			sistema.setTiposPrendas(Sistema.importarTipoPrendas());
			sistema.setColores(Sistema.importarColores());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Spark.port(9000);
		Spark.staticFiles.location("/public"); 
        Spark.init();
       
        ControllerPersona controllerPersona = new ControllerPersona();
        ControllerLogin controllerLoggin = new ControllerLogin();
        ControllerGuardarropa controllerGuardarropa = new ControllerGuardarropa();
        ControllerEvento controllerEvento = new ControllerEvento();
        ControllerAtuendo controllerAtuendo = new ControllerAtuendo();
        HandlebarsTemplateEngine transformer = new HandlebarsTemplateEngine();
        
        Spark.get("/usuarioHome", controllerLoggin::login, transformer);		
		Spark.before("/persona/*", ControllerPersona::chequearClienteLogueado);
		Spark.get("/persona/:id/perfil",controllerPersona::mostrarPerfil,transformer);
		Spark.post("/usuarioHome",controllerLoggin::redirigirAPerfil,new HandlebarsTemplateEngine());
		Spark.get("/misguardarropas", controllerPersona::listarGuardarropas,transformer);
		Spark.post("/agregarGuardarropa", controllerPersona::agregarGuardarropa,transformer);
		Spark.post("/eliminarGuardarropa/:id",controllerPersona::eliminarGuardarropa,transformer);
		Spark.post("/guardarropa/:id/agregarPrenda",controllerGuardarropa::agregarPrenda,transformer);
		Spark.post("/guardarropa/agregarPrendaPaso2",controllerGuardarropa::agregarPrendaPaso2,transformer);
		Spark.post("/guardarropa/agregarPrendaPaso3",controllerGuardarropa::agregarPrendaPaso3,transformer);
		Spark.post("/guardarropa/agregarPrendaPaso4",controllerGuardarropa::agregarPrendaPaso4,transformer);
		Spark.post("/guardarropa/construirPrenda",controllerGuardarropa::construirPrenda,transformer);
		Spark.get("/guardarropa/:id/verPrendas",controllerGuardarropa::verPrendas,transformer);
		Spark.post("/guardarropa/:id/eliminarPrenda",controllerGuardarropa::eliminarPrenda,transformer);
		Spark.post("guardarropa/:id/compartir",controllerPersona::compartirGuardarropa,transformer);
		Spark.post("/agregarGuardarropaCompartido",controllerPersona::agregarGuardarropaCompartido,transformer);
		Spark.get("/evento/:id/verAtuendosSugeridos",controllerEvento::verAtuendosSugeridos,transformer);
		Spark.post("/atuendo/:id/aceptarAtuendo",controllerEvento::aceptarAtuendo,transformer);
		Spark.post("/atuendo/:id/rechazarAtuendo",controllerEvento::rechazarAtuendo,transformer);
		Spark.get("/atuendo/:id/verPrendas",controllerAtuendo::verPrendas,transformer);
		Spark.get("/misEventos", controllerPersona::listarEventos,transformer);
		Spark.post("/altaEvento", controllerPersona::altaEvento,transformer);
		Spark.post("/eliminarEvento/:id",controllerPersona::eliminarEvento,transformer);
		Spark.post("/evento/:id/modificarEvento",controllerPersona::modificarEvento,transformer);
		Spark.post("/construirEvento",controllerPersona::construirEvento,transformer);
		
//		Long idEvento = Long.valueOf(12);
//	     System.out.println("cookie eventoID : " + idEvento);
//	     EventoDAO eventoDAO = new EventoDAO(EntityManagerHelper.getEntityManager());
//	     Evento evento = eventoDAO.obtenerEvento(idEvento);
//	     System.out.println(evento.getFechaInicioEvento());
//	     System.out.println(evento.getFechaInicioEvento().getHourOfDay());
//	     System.out.println(evento.getFechaFinEvento());
//	     
//	     AtuendoDAO atuendoDAO = new AtuendoDAO(EntityManagerHelper.getEntityManager());
//	     atuendoDAO.obtenerAtuendo(5).mostrarPrendas();
	     
//		String direccion = GoogleAPI.buscarDireccion("La Plata, Arg");
//		System.out.println(direccion);
//		System.out.println(new ServicioOpenWeather().obtenerTemperaturaFutura(new DateTime(2019,11,23,21,00), direccion));
//		System.out.println(GoogleAPI.obtenerCoordenadas(direccion));
//         System.out.println(GoogleAPI.obtenerCoordenadas("Buenos Aires, Argentina"));   
		
		/* String connectionUrl = "jdbc:mysql://localhost:3306/dds_2019;databaseName=DDS_2019;user=DDS_2019;password=DDS_2019";

	        try {
	            // Load SQL Server JDBC driver and establish connection.
	            System.out.print("Connecting to SQL Server ... ");
	            try (Connection connection = DriverManager.getConnection(connectionUrl)) {
	                System.out.println("Done.");
	            }
	        } catch (Exception e) {
	            System.out.println();
	            e.printStackTrace();
	        }*/
		
	/*
		
                System.out.println(GoogleAPI.buscarDireccion("La Plata, Argentina"));
                System.out.println(GoogleAPI.obtenerCoordenadas("La Plata, Argentina"));                
                System.out.println(new ServicioOpenWeather().obtenerTemperatura("La Plata"));                
                System.out.println(new ServicioOpenWeather().obtenerTemperatura("Tierra del Fuego"));
		Sistema sistema = new Sistema();	
		System.out.println(java.nio.charset.Charset.defaultCharset());
		try {			
			sistema.setPersonas(Sistema.importarUsuarios());
			sistema.setTiposPrendas(Sistema.importarTipoPrendas());
			sistema.setColores(Sistema.importarColores());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Bienvenido al sistema �Que me pongo?");
		System.out.println("Elija la opcion deseada");
		int opcion = 0;
		int pos;
		Persona persona;
		Guardarropa guardarropa ;
		while (opcion != 9) {
			menu();
			Scanner in = new Scanner(System.in);  
			opcion = in.nextInt();
			switch (opcion) {
				case 1:
					System.out.println("Ingrese el nombre: ");
					String nombre = in.next();
					System.out.println("Ingrese el tipo de usuario (1 = gratuito ; 2 = premium)");
					int tipoUser = in.nextInt();
					if(tipoUser == 1) {
						System.out.println("Ingrese el numero maximo de prendas que podran tener sus guardarropas: ");
						int numeroMaximoPrendas = in.nextInt();
						sistema.agregarPersona(new Persona(nombre,new UsuarioGratuito(numeroMaximoPrendas)));
					}
					if(tipoUser == 2) {
						sistema.agregarPersona(new Persona(nombre,new UsuarioPremium()));
					}
					break;
				case 2:
					System.out.println("Elija el usuario al cual se le desea agregar un guardarropa vacio:");				
					sistema.getPersonas().forEach(usuario -> System.out.println(usuario.getNombre()));
					pos = in.nextInt();
					sistema.getPersonas().get(pos-1).agregarGuardarropa(new Guardarropa());
					System.out.println("Nuevo Guardarropa agregado al Usuario: " + sistema.getPersonas().get(pos-1).getNombre());
					break;
				case 3:
					System.out.println("Elija el usuario al cual se le desea agregar una prenda:");				
					sistema.getPersonas().forEach(usuario -> System.out.println(usuario.getNombre()));
					int posPersona = in.nextInt();
					System.out.println("Elija el N� de guardarropa:");	
					persona = sistema.getPersonas().get(posPersona-1);
					int posGuardarropa = in.nextInt();
					guardarropa = persona.getGuardarropas().get(posGuardarropa-1);
					System.out.println("Elija el tipo de prenda deseado:");
					int i = 1;
					for (TipoPrenda tipoPrenda : sistema.getTiposPrendas()) {
						System.out.println(i);
						tipoPrenda.mostrarDetalles();
						i++;
					}	    
					int posPrenda = in.nextInt();
					System.out.println("Escriba la cantidad de calorias de la prenda:");	
					int calorias = in.nextInt();
					TipoPrenda tipoPrenda = sistema.getTiposPrendas().get(posPrenda-1);
					System.out.println("Elija el tipo de tela deseado:");
					i = 1;
					for (String tipoTela : sistema.getTiposPrendas().get(posPrenda-1).getTiposTelaPosible()) {
						System.out.println(i);
						System.out.println(tipoTela);
						i++;
					}
					int posTela = in.nextInt();
					String tipoTela = sistema.getTiposPrendas().get(posPrenda-1).getTiposTelaPosible().get(posTela-1);
					System.out.println("Elija el color primario deseado:");
					i = 1;
					for (String color : sistema.getColores()) {
						System.out.println(i);
						System.out.println(color);
						i++;
					}
					int posColor = in.nextInt();
					String colorPrimario = sistema.getColores().get(posColor-1);
					System.out.println("Elija el color secundario deseado:");
					i = 1;
					List<String> coloresPosibles = new ArrayList<String>();
					for (String color : sistema.getColores()) {
						if(sistema.getColores().get(posColor-1) != color) {
							coloresPosibles.add(color);
							System.out.println(i);
							System.out.println(color);
							i++;
						}
					}
					System.out.println(i);
					System.out.println("Ninguno");
					pos = in.nextInt();
					String colorSecundario;
					if(pos == i) {
						colorSecundario = "";
					}
					else{
						colorSecundario = coloresPosibles.get(pos-1);
					}
					Prenda nuevaPrenda = new Prenda(colorPrimario, colorSecundario, tipoPrenda, tipoTela,calorias);
					try {
						sistema.getPersonas().get(posPersona-1).agregarPrendaAGuardarropa(posGuardarropa-1,nuevaPrenda);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("Prenda elegida: ");
					nuevaPrenda.mostrarDetalles();
					System.out.println("Prenda agregada exitosamente");
					break;
				case 4:
					System.out.println("Elija el usuario al cual se le desea agregar un evento");
					sistema.getPersonas().forEach(usuario -> System.out.println(usuario.getNombre()));
					pos = in.nextInt();
					persona = sistema.getPersonas().get(pos-1);
					System.out.println("Ingrese una descripcion para el evento");
					in.nextLine();
					String descripcionEvento = in.nextLine();
					System.out.println("Ingrese el a�o de inicio del evento");
					int anio = in.nextInt();
					System.out.println("Ingrese el mes de inicio del evento");
					int mes = in.nextInt();
					System.out.println("Ingrese el dia de inicio del evento");
					int dia = in.nextInt();
					System.out.println("Ingrese la hora de inicio del evento");
					int hora = in.nextInt();
					System.out.println("Ingrese los minutos de inicio del evento");
					int minutos = in.nextInt();
					System.out.println("Ingrese la ciudad en la que se ubicar� el evento");
					in.nextLine();
					String ubicacion = in.nextLine();
					DateTime fechaInicio = new DateTime(anio,mes,dia,hora,minutos);
					System.out.println("Ingrese el a�o de fin del evento");
					anio = in.nextInt();
					System.out.println("Ingrese el mes de fin del evento");
					mes = in.nextInt();
					System.out.println("Ingrese el dia de fin del evento");
					dia = in.nextInt();
					System.out.println("Ingrese la hora de fin del evento");
					hora = in.nextInt();
					System.out.println("Ingrese los minutos de fin del evento");
					minutos = in.nextInt();
					DateTime fechaFin = new DateTime(anio,mes,dia,hora,minutos);
					System.out.println("Indique el nivel de formalidad del evento");
					String formalidad ="";
					System.out.println("1. Formal");
					System.out.println("2. Informal");
					switch (in.nextInt()){
						case 1 :formalidad = "FORMAL";break;
						case 2 :formalidad = "INFORMAL";break;
					}
					try {
						sistema.getPersonas().get(pos-1).agregarEvento(descripcionEvento, fechaInicio, fechaFin, ubicacion, formalidad);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					break;
				case 5:
					System.out.println("Elija el usuario al cual se le desea sugerir una prenda:");				
					sistema.getPersonas().forEach(usuario -> System.out.println(usuario.getNombre()));
					pos = in.nextInt();
					System.out.println("Elija el N� de guardarropa del cual se quiere sacar la sugerencia:");	
					persona = sistema.getPersonas().get(pos-1);
					pos = in.nextInt();
					guardarropa = persona.getGuardarropas().get(pos-1);
					guardarropa.recomendarAtuendo();
					break;
				case 6:
					System.out.println("Elija el usuario que ir� al evento: ");
					sistema.getPersonas().forEach(usuario -> System.out.println(usuario.getNombre()));
					pos = in.nextInt();
					persona = sistema.getPersonas().get(pos-1);
					System.out.println("Elija el evento para el cual recomendar el atuendo: ");
					persona.getEventos().forEach(evento -> evento.mostrarDetalles());
					pos = in.nextInt();
					Evento evento = persona.getEventos().get(pos-1);
					System.out.println("Elija el N� guardarropas con cuyas prendas se recomendar� el atuendo");
					pos = in.nextInt();
					guardarropa = persona.getGuardarropas().get(pos-1);
					ServicioOpenWeather servicio = new ServicioOpenWeather();
					Set<Atuendo> atuendos = guardarropa.sugerirAtuendos(evento.temperatura(servicio), evento.getTipoEvento(),evento.getFechaInicioEvento(),evento.getFechaFinEvento());
					atuendos.forEach(atuendo -> atuendo.getPrendas().forEach(prenda->prenda.mostrarDetalles()));
					break;
				case 7:
					System.out.println("Elija el usuario del cual se desea ver todos sus pertenencias:");				
					sistema.getPersonas().forEach(usuario -> System.out.println(usuario.getNombre()));
					pos = in.nextInt();
					persona = sistema.getPersonas().get(pos-1);
					System.out.println(persona.getNombre() + "tiene " + persona.getGuardarropas().size() + " guardarropas");
					System.out.println("Elija el N� de guardarropa del cual se quiere sacar la sugerencia:");					
					pos = in.nextInt();
					guardarropa = persona.getGuardarropas().get(pos-1);
					System.out.println("Prendas presentes en el guardarropas:");
					guardarropa.mostrarPrendas();
					break;
				case 8:
					System.out.println("Elija al usuario del cual se desea ver todos los eventos pendientes: ");
					sistema.getPersonas().forEach(usuario -> System.out.println(usuario.getNombre()));
					pos = in.nextInt();
					sistema.getPersonas().get(pos-1).getEventos().forEach(eventos -> eventos.mostrarDetalles());
					break;
				case 9:break;
				default:
					System.out.println("Opcion Invalida");
					break;
			
			}
				
			
		}
	*/			
	}
	
	
	public static void menu() {
		System.out.println("1. Crear Usuario");
		System.out.println("2. Agregar Guardarropa");
		System.out.println("3. Agregar Prenda");
		System.out.println("4. Agregar Evento");
		System.out.println("5. Sugerir Atuendo");
		System.out.println("6. Sugerir Atuendo para un evento");
		System.out.println("7. Ver Prendas de Usuario");
		System.out.println("8. Ver Eventos de Usuario");
		System.out.println("9. Salir");
	}

}
