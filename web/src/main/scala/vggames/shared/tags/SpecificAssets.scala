package vggames.shared.tags

import java.io.ByteArrayInputStream

import scala.io.Source

import javax.servlet.http.HttpServletRequest
import javax.servlet.jsp.PageContext
import net.vidageek.autoweb.taglib.core.{JsTagCore, CssTagCore, AutowebSimpleTag}
import net.vidageek.autoweb.taglib.support.TagEnvironment
import net.vidageek.autoweb.taglib.AutowebSimpleTagSupport
import vggames.shared.vraptor.RequestData

class SpecificCss extends AutowebSimpleTagSupport {

  override def doTag : Unit = {
    new AssetSupport(this, "css", name => new CssTagCore("/css/specific/" + name)).printAssets
  }
}

class SpecificJs extends AutowebSimpleTagSupport {
  override def doTag : Unit = {
    new AssetSupport(this, "js", name => new JsTagCore("/js/specific/" + name)).printAssets
  }
}

class AssetSupport(val support : AutowebSimpleTagSupport, val assetType : String,
  val tagCreator : (String => AutowebSimpleTag)) {

  private def gameName = new RequestData(support.getJspContext.asInstanceOf[PageContext].
    getRequest.asInstanceOf[HttpServletRequest]).game

  def printAssets = {
    Source.fromInputStream(findStream).getLines.foreach(file => {
      val env = new TagEnvironment(support)
      tagCreator(file).applyTo(env)
      env.write("\n")
    })
  }

  private def findStream = {
    Option(this.getClass.getResourceAsStream("/" + gameName + "." + assetType + ".files")).
      getOrElse(new ByteArrayInputStream(Array()))
  }
}