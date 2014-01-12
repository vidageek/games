package vggames.shared

import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ApplicationScoped
import java.io.ByteArrayInputStream
import scala.collection.JavaConverters._
import java.util.concurrent.ConcurrentHashMap
import scala.io.Source
import java.util.zip.GZIPOutputStream
import java.io.ByteArrayOutputStream
import org.apache.log4j.Logger

@Component
@ApplicationScoped
class ResourceCache {

  private val log = Logger.getLogger(classOf[ResourceCache])

  private val cache = new ConcurrentHashMap[String, Array[Byte]]().asScala

  def css(baseName : String) : Array[Byte] = {
    fromCache(baseName, "css")
  }

  def js(baseName : String) : Array[Byte] = {
    fromCache(baseName, "js")
  }

  private def fromCache(baseName : String, asset : String) = {
    cache.getOrElseUpdate(s"$asset-$baseName", {
      findAndGZip(baseName, asset)
    })
  }

  def findAndGZip(baseName : String, assetType : String) = {
    val outStream = new ByteArrayOutputStream()
    val asset = Source.fromInputStream(findFiles(baseName, assetType)).getLines.map { file =>
      val fullname = s"/$baseName/$assetType/$file"
      Option(this.getClass.getResourceAsStream(fullname)).map { stream =>
        Source.fromInputStream(stream).getLines.mkString("\n")
      }.getOrElse({ log.warn(s"Couldn't find resource $fullname. Ignoring it."); "" })

    }.map(_ + "\n\n").foldLeft(new GZIPOutputStream(outStream)) { (stream, content) => stream.write(content.getBytes()); stream }

    asset.close()
    val ret = outStream.toByteArray()
    outStream.close()
    ret
  }

  private def findFiles(baseName : String, assetType : String) = {
    Option(this.getClass.getResourceAsStream(s"/$baseName.$assetType.files")).
      getOrElse(new ByteArrayInputStream(Array()))
  }

}