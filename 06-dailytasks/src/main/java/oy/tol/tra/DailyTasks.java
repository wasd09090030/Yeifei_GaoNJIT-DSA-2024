package oy.tol.tra;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class showing your daily schedule using a timer.
 */
public class DailyTasks {

   private QueueInterface<String> dailyTaskQueue = null;
   private Timer timer = null;
   private static final int TASK_DELAY_IN_SECONDS = 1 * 1000;

   private DailyTasks() {
   }

   /**
    * Execute from the command line:  <code>java -jar target/04-queue-1.0-SNAPSHOT-jar-with-dependencies.jar</code>
    * @param args Not used.
    */
   public static void main(String[] args) {
      DailyTasks tasks = new DailyTasks();
      tasks.run();
   }

   private void run() {
      try {

         dailyTaskQueue=QueueFactory.createStringQueue();
         readTasks();

         timer = new Timer();

         timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {


               if(dailyTaskQueue.isEmpty())
               {
                  timer.cancel();
               }
               else{

                  while(dailyTaskQueue.size()>0){
                     var ss= dailyTaskQueue.dequeue();
                     System.out.println(ss);}

                  timer.cancel();


               }





            }
         }, TASK_DELAY_IN_SECONDS, TASK_DELAY_IN_SECONDS);
      } catch (IOException e) {
         System.out.println("Something went wrong :( " + e.getLocalizedMessage());
      }
   }

   private void readTasks() throws IOException {
      String tasks;
      tasks = new String(getClass().getClassLoader().getResourceAsStream("DailyTasks.txt").readAllBytes());
      String[] allTasks = tasks.split("\\r?\\n");
      for (String task : allTasks) {

         dailyTaskQueue.enqueue(task);


      }

      System.out.println(dailyTaskQueue.size());


   }
}
