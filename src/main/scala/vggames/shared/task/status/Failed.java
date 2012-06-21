package vggames.shared.task.status;

import vggames.shared.task.JudgedTask;

final public class Failed implements JudgedTask {

  private final String reason;

  public Failed(final String reason) {
    this.reason = reason;
  }

  public Failed(final Faileds fails) {
    reason = fails.getReason();
  }

  public boolean getOk() {
    return false;
  }

  public String getReason() {
    return reason;
  }

  @Override
  public String toString() {
    return reason;
  }

}
