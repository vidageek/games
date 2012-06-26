package vggames.shared.tags

import net.vidageek.autoweb.taglib.AutowebSimpleTagSupport
import net.vidageek.autoweb.taglib.core.CssTagCore
import net.vidageek.autoweb.taglib.support.TagEnvironment
import scala.io.Source
import net.vidageek.autoweb.taglib.core.AutowebSimpleTag
import net.vidageek.autoweb.taglib.core.JsTagCore
import java.io.ByteArrayInputStream
import vggames.shared.vraptor.RequestData
import javax.servlet.jsp.PageContext
import javax.servlet.http.HttpServletRequest

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