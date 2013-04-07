package cmu.ds.mr.mapred;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class TaskScheduler {
  public static final Log LOG = LogFactory.getLog(TaskScheduler.class);
  
  protected TaskTrackerManager taskTrackerManager;
 

  public synchronized void setTaskTrackerManager(
      TaskTrackerManager taskTrackerManager) {
    this.taskTrackerManager = taskTrackerManager;
  }
  
  /**
   * Lifecycle method to allow the scheduler to start any work in separate
   * threads.
   * @throws IOException
   */
  public void start() throws IOException {
    // do nothing
  }
  
  /**
   * Lifecycle method to allow the scheduler to stop any work it is doing.
   * @throws IOException
   */
  public void terminate() throws IOException {
    // do nothing
  }

  /**
   * Returns the tasks we'd like the TaskTracker to execute right now.
   * 
   * @param taskTracker The TaskTracker for which we're looking for tasks.
   * @return A list of tasks to run on that TaskTracker, possibly empty.
   */
  public List<Task> assignTasks(TaskTrackerStatus taskTracker)
    throws IOException{
    return null;
  }

  /**
   * Returns a collection of jobs in an order which is specific to 
   * the particular scheduler.
   * @param queueName
   * @return
   */
  public Collection<JobInProgress> getJobs(String queueName){
    return null;
  }
   
}