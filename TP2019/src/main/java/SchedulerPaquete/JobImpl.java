package SchedulerPaquete;

import java.util.ArrayList;
import java.util.List;

//import java.io.IOException;
//import java.util.Set;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import DDS_2019.DAOs.PersonaDAO;
import DDS_TP2019.Clima.ServicioOpenWeather;
import DDS_TP2019.Dominio.Persona;
import db.EntityManagerHelper;

public class JobImpl implements Job {

    private static int count;

    public void execute(JobExecutionContext jobContext) throws JobExecutionException{
    	
//    	Guardarropa guardarropa = new Guardarropa();
//    	Evento evento = new Evento();
//    	//ServicioOpenWeather servicio = new ServicioOpenWeather();
//    	//String ubicacion = evento.getUbicacion();
//    	Double temperatura = 20.0;
//    	
//    	GenerarSugerencias genSug = new GenerarSugerencias();
//		genSug.ejecutar(guardarropa.getPrendas(), temperatura, evento.getTipoDeEvento(), evento.getFechaInicioEvento(), evento.getFechaFinEvento());
		
    	System.out.println("--------------------------------------------------------------------");
        System.out.println("Inicio: " + jobContext.getFireTime());
        JobDetail jobDetail = jobContext.getJobDetail();
        System.out.println("Info: " + jobDetail.getJobDataMap().getString("info"));
        
        PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
        List<Persona> personas = new ArrayList<>();
        try {
			personas = personaDAO.cargarUsuarios();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ServicioOpenWeather servicioMeteorologico = new ServicioOpenWeather();
        personas.stream().forEach(p -> p.sugerirAtuendosParaEventosProximos(servicioMeteorologico));
        
        System.out.println("Fin: " + jobContext.getJobRunTime() + ", key: " + jobDetail.getKey());
        System.out.println("Proxima ejecucion: " + jobContext.getNextFireTime());
        System.out.println("--------------------------------------------------------------------");

        ILatch latch = (ILatch) jobDetail.getJobDataMap().get("latch");
        latch.countDown();
        count++;
        System.out.println("Job count " + count);
        if (count == 2) {
            throw new RuntimeException("Some RuntimeException!");
        }
        if (count == 4) {
            throw new JobExecutionException("Some JobExecutionException!");
        }
    }

}
