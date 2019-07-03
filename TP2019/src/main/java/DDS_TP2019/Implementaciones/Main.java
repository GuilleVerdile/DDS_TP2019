package DDS_TP2019.Implementaciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.joda.time.DateTime;

import com.google.maps.errors.ApiException;

import DDS_TP2019.Dominio.Evento;
import DDS_TP2019.Dominio.Guardarropa;
import DDS_TP2019.Dominio.Persona;
import DDS_TP2019.Dominio.Prenda;
import DDS_TP2019.Dominio.Sistema;
import DDS_TP2019.Dominio.TipoPrenda;
import DDS_TP2019.Dominio.UsuarioGratuito;
import DDS_TP2019.Dominio.UsuarioPremium;

public class Main {

	public static void main(String[] args)throws ApiException, InterruptedException, IOException{

                //System.out.println(GoogleAPI.buscarDireccion("Plaza Italia"));
		Sistema sistema = new Sistema();	
		System.out.println(java.nio.charset.Charset.defaultCharset());
		try {			
			sistema.setPersonas(Sistema.importarUsuarios());
			sistema.setTiposPrendas(Sistema.importarTipoPrendas());
			sistema.setColores(Sistema.importarColores());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Bienvenido al sistema ¿Que me pongo?");
		System.out.println("Elija la opcion deseada");
		int opcion = 0;
		int pos;
		Persona persona;
		Guardarropa guardarropa ;
		while (opcion != 8) {
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
					System.out.println("Elija el N° de guardarropa:");	
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
					sistema.getPersonas().get(posPersona-1).agregarPrendaAguardarropa(posGuardarropa-1,nuevaPrenda);
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
					System.out.println("Ingrese el año del evento");
					int anio = in.nextInt();
					System.out.println("Ingrese el mes del evento");
					int mes = in.nextInt();
					System.out.println("Ingrese el dia del evento");
					int dia = in.nextInt();
					System.out.println("Ingrese la hora de inicio del evento");
					int hora = in.nextInt();
					System.out.println("Ingrese los minutos de inicio del evento");
					int minutos = in.nextInt();
					System.out.println("Ingrese la ciudad en la que se ubicará el evento");
					in.nextLine();
					String ubicacion = in.nextLine();
					DateTime fecha = new DateTime(anio,mes,dia,hora,minutos);
					System.out.println("Indique el nivel de formalidad del evento");
					String formalidad ="";
					System.out.println("1. Formal");
					System.out.println("2. Informal");
					switch (in.nextInt()){
						case 1 :formalidad = "FORMAL";break;
						case 2 :formalidad = "INFORMAL";break;
					}
					try {
						sistema.getPersonas().get(pos-1).agregarEvento(descripcionEvento, fecha, ubicacion, formalidad);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					break;
				case 5:
					System.out.println("Elija el usuario al cual se le desea sugerir una prenda:");				
					sistema.getPersonas().forEach(usuario -> System.out.println(usuario.getNombre()));
					pos = in.nextInt();
					System.out.println("Elija el N° de guardarropa del cual se quiere sacar la sugerencia:");	
					persona = sistema.getPersonas().get(pos-1);
					pos = in.nextInt();
					guardarropa = persona.getGuardarropas().get(pos-1);
					guardarropa.recomendarAtuendo();
					break;
				case 6:
					System.out.println("Elija el usuario del cual se desea ver todos sus pertenencias:");				
					sistema.getPersonas().forEach(usuario -> System.out.println(usuario.getNombre()));
					pos = in.nextInt();
					persona = sistema.getPersonas().get(pos-1);
					System.out.println(persona.getNombre() + "tiene " + persona.getGuardarropas().size() + " guardarropas");
					System.out.println("Elija el N° de guardarropa del cual se quiere sacar la sugerencia:");					
					pos = in.nextInt();
					guardarropa = persona.getGuardarropas().get(pos-1);
					System.out.println("Prendas presentes en el guardarropas:");
					guardarropa.mostrarPrendas();
					break;
				case 7:
					System.out.println("Elija al usuario del cual se desea ver todos los eventos pendientes: ");
					sistema.getPersonas().forEach(usuario -> System.out.println(usuario.getNombre()));
					pos = in.nextInt();
					sistema.getPersonas().get(pos-1).getEventos().get(0).mostrarDetalles();
					break;
				case 8:break;
				default:
					System.out.println("Opcion Invalida");
					break;
			
			}
				
			
		}
				
	}
	
	
	public static void menu() {
		System.out.println("1. Crear Usuario");
		System.out.println("2. Agregar Guardarropa");
		System.out.println("3. Agregar Prenda");
		System.out.println("4. Agregar Evento");
		System.out.println("5. Sugerir Atuendo");
		System.out.println("6. Ver Prendas de Usuario");
		System.out.println("7. Ver Eventos de Usuario");
		System.out.println("8. Salir");
	}

}
