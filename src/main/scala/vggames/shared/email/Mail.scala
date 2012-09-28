package vggames.shared.email

import org.apache.log4j.Logger
import com.amazonaws.services.simpleemail.model.Destination
import java.util.ArrayList
import com.amazonaws.services.simpleemail.model.SendEmailRequest
import com.amazonaws.services.simpleemail.model.Message
import com.amazonaws.services.simpleemail.model.Content
import com.amazonaws.services.simpleemail.model.Body
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient
import com.amazonaws.auth.BasicAWSCredentials
import vggames.shared.vraptor.Secrets

object Mail {

  val secrets = new Secrets

  def apply(to : String, from : String, subject : String, message : String) = {
    if (secrets.awsAccessKey != null)
      new AWSMail(to, from, subject, message)
    else
      new LogMail(to, from, subject, message)
  }
}

trait Mail {
  def send
}

class AWSMail(to : String, from : String, subject : String, message : String) extends Mail {
  val secrets = new Secrets

  def send = {
    val list = new ArrayList[String]
    list.add(to)
    val req = new SendEmailRequest(from, new Destination(list), new Message(new Content(subject), new Body(new Content(message))))
    val credentials = new BasicAWSCredentials(secrets.awsAccessKey, secrets.awsSecretKey)
    val answer = new AmazonSimpleEmailServiceClient(credentials).sendEmail(req)
  }
}

class LogMail(to : String, from : String, subject : String, message : String) extends Mail {

  private val log = Logger.getLogger(classOf[LogMail])

  def send =
    log.info("Got mail to send [to:%s | from:%s | subject:%s | message:%s".format(to, from, subject, message))
}