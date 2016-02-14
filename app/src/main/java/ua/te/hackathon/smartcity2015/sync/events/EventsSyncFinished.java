package ua.te.hackathon.smartcity2015.sync.events;

/**
 * @author victor
 * @since 2016-02-14
 */
public class EventsSyncFinished {

  private boolean success;
  private Throwable error;

  public EventsSyncFinished() {
    success = true;
  }

  public EventsSyncFinished(Throwable error) {
    success = false;
    this.error = error;
  }

  public boolean isSuccess() {
    return success;
  }

  public Throwable getError() {
    return error;
  }
}
