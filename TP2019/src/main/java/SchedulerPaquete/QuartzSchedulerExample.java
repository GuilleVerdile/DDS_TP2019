package SchedulerPaquete;

import java.util.concurrent.CountDownLatch;
import java.text.ParseException;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;

import DDS_TP2019.Dominio.Evento;

public class QuartzSchedulerExample implements ILatch {

    private int repeatCount = 1;
    private CountDownLatch latch = new CountDownLatch(repeatCount);
	Evento evento = new Evento();


    public static void main(String[] args) throws Exception {
        QuartzSchedulerExample quartzSchedulerExample = new QuartzSchedulerExample();

        quartzSchedulerExample.fireJob();

    }

    
    public void fireJob() throws SchedulerException, InterruptedException, ParseException {
        

        JobBuilder jobBuilder = JobBuilder.newJob(JobImpl.class);
        JobDataMap data = new JobDataMap();
        data.put("latch", this);


        JobDetail jobDetail = jobBuilder.usingJobData("example", "QuartzSchedulerExample")
                .usingJobData(data)
                .withIdentity("myJob", "group1")
                .build();        

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
               // .startAt(triggerStartTime)
                .withSchedule(CronScheduleBuilder.cronSchedule("0 27 12 ? * *"))
                .forJob("myJob", "group1")
                .build();
        
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedFact.getScheduler();
        scheduler.start();

        scheduler.scheduleJob(jobDetail, trigger);
        latch.await();
        
        System.out.println("All triggers executed. Shutdown scheduler");
        
        scheduler.shutdown();
        
        
    }

    public void countDown() {
        latch.countDown();
    }
    
}
