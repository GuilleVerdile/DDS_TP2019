package DDS_TP2019.Implementaciones;

import java.util.Scanner;

import DDS_TP2019.Dominio.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sistema sistema = new Sistema();
		System.out.println("Bienvenido al sistema ¿Que me pongo?");
		System.out.println("Elija la opcion deseada");
		int opcion = 0;
		int pos;
		Persona persona;
		Guardarropa guardarropa ;
		while (opcion != 6) {
			menu();
			Scanner in = new Scanner(System.in);  
			opcion = in.nextInt();
			switch (opcion) {
				case 1:
					System.out.println("Ingrese el nombre: ");
					String nombre = in.next();
					sistema.agregarPersona(new Persona(nombre));
					break;
				case 2:
					System.out.println("Elija el usuario al cual se le desea agregar un guardarropa vacio:");				
					sistema.getPersonas().forEach(usuario -> System.out.println(usuario.getNombre()));
					pos = in.nextInt();
					sistema.getPersonas().get(pos-1).agregarGuardarropa(new Guardarropa());
					System.out.println("Nuevo Guardarropa agregado al Usuario: " + sistema.getPersonas().get(pos-1).getNombre());
					break;
				case 3:
					
					break;
				case 4:
					System.out.println("Elija el usuario al cual se le desea sugerir una prenda:");				
					sistema.getPersonas().forEach(usuario -> System.out.println(usuario.getNombre()));
					pos = in.nextInt();
					System.out.println("Elija el N° de guardarropa del cual se quiere sacar la sugerencia:");	
					persona = sistema.getPersonas().get(pos-1);
					pos = in.nextInt();
					guardarropa = persona.getGuardarropas().get(pos-1);
					guardarropa.recomendarAtuendo();
					break;
				case 5:
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
		System.out.println("4. Sugerir Atuendo");
		System.out.println("5. Ver Usuario");
		System.out.println("6. Salir");
	}

}




