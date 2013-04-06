package cmu.ds.mr.mapred;


import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import cmu.ds.mr.conf.JobConf;
import cmu.ds.mr.mapred.JobStatus.JobState;

/**
 * Job class for running job
 * 
 * */
public class Job implements RunningJob {

  private static final Log LOG = LogFactory.getLog(Job.class);

  private JobID jid;
  private String message;
  private JobConf jobConf;
  private JobStatus jobStatus;
  
  public Job(JobID jid, JobConf jobConf, JobStatus jobStatus) {
    this.jid = jid;
    this.jobStatus = jobStatus;
    this.jobConf = jobConf;
  }
  
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public JobConf getJobConf() {
    return jobConf;
  }

  public void setJobConf(JobConf jobConf) {
    this.jobConf = jobConf;
  }

  public void setJobStatus(JobStatus jobStatus) {
    this.jobStatus = jobStatus;
  }

  public JobID getID() {
    return jid;
  }

  @Override
  public String getJobName() {
    return jobConf.getJobName();
  }

  @Override
  public float mapProgress() throws IOException {
    return jobStatus.getMapProgress();
  }

  @Override
  public float reduceProgress() throws IOException {
    return jobStatus.getReduceProgress();
  }

  @Override
  public boolean isComplete() throws IOException {
    ensureState(JobState.RUNNING);
    return jobStatus.isJobComplete();
  }

  @Override
  public boolean isSuccessful() throws IOException {
    ensureState(JobStatus.JobState.RUNNING);
    return jobStatus.isJobComplete();
  }
  
  @Override
  public void killJob() throws IOException {
    ensureState(JobStatus.JobState.RUNNING);
    jobStatus.setState(JobState.KILLED);
  }

  /**
   * block and wait for job completion
   * */
  @Override
  public void waitForCompletion() throws IOException {
    while (!isComplete()) {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException ie) {
      }
    }
  }

  @Override
  public JobState getJobState() throws IOException {
    return jobStatus.getState();
  }
  
  private void ensureState(JobState state) throws IllegalStateException {
    if (state != jobStatus.getState()) {
      LOG.error("Job in state "+ jobStatus.getState() + 
              " instead of " + state);
      throw new IllegalStateException("Job in state "+ jobStatus.getState() + 
                                      " instead of " + state);
    }
  }
  
  


}