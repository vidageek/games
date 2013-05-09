package vggames.html;

import java.util.concurrent.TimeUnit
import org.junit.runner.RunWith
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import vggames.browser.WebBrowser

@RunWith(classOf[JUnitRunner])
class HtmlGameSpec extends Specification with WebBrowser{
  
  "HtmlGame" should {
    "teste" in {
      
      task("html", 1)
      solve("<p>Hello world!</p>")
      verifyOk must beTrue
     
    }
  }

}
