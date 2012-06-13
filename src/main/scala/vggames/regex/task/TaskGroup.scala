package vggames.regex.task

import java.util.ArrayList
import java.util.Collections

import scala.collection.JavaConversions.asScalaIterator

import br.com.caelum.vraptor.ioc.ApplicationScoped
import br.com.caelum.vraptor.ioc.Component
import vggames.regex.Descriptions
import vggames.task.Task

class TaskGroup(name: String, groupName: String, descriptions: Descriptions) extends Iterable[Task] {
  val tasks = new ArrayList[Task]

  def add(task: Task): Unit = tasks.add(task)

  override def size = tasks.size

  def task(index: Int) = tasks.get(index)

  def iterator = Collections.unmodifiableCollection(tasks).iterator

  def getDescription = descriptions.forGroup(groupName)

  def getName = name
}