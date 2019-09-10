package SchedulerPaquete;

//import java.io.IOException;
//import java.util.Set;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//import com.google.maps.errors.ApiException;

//import DDS_TP2019.Clima.ServicioOpenWeather;
//import DDS_TP2019.Dominio.Atuendo;
import DDS_TP2019.Dominio.Evento;
import DDS_TP2019.Dominio.GenerarSugerencias;
import DDS_TP2019.Dominio.Guardarropa;

public class JobImpl implements Job {

    private static int count;

    public void execute(JobExecutionContext jobContext) throws JobExecutionException{
    	
    	Guardarropa guardarropa = new Guardarropa();
    	Evento evento = new Evento();
    	//ServicioOpenWeather servicio = new ServicioOpenWeather();
    	//String ubicacion = evento.getUbicacion();
    	Double temperatura = 20.0;
    	
    	GenerarSugerencias genSug = new GenerarSugerencias();
		genSug.ejecutar(guardarropa.getPrendas(), temperatura, evento.getTipoDeEvento(), evento.getFechaInicioEvento(), evento.getFechaFinEvento());
		

       
    	System.out.println("--------------------------------------------------------------------");
        System.out.println("Inicio: " + jobContext.getFireTime());
        JobDetail jobDetail = jobContext.getJobDetail();
        System.out.println("Info: " + jobDetail.getJobDataMap().getString("info"));
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
