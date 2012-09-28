package vggames.shared.email

import javax.servlet.{ ServletContextEvent, ServletContextListener }

class Notifier extends ServletContextListener {

  override def contextInitialized(event : ServletContextEvent) {
    Mail(to = "jonas@vidageek.net", from = "games@vidageek.net", subject = "Server just got up!",
      message = "Either a deploy or Watchdog reboot").send
  }

  override def contextDestroyed(event : ServletContextEvent) {
  }
}