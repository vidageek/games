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
    
    "basico 0" in {
      
      task("html", 0)
      solve("<p>Hello world!</p>")
      verifyOk must beTrue
     
    }
    
    "basico 1" in {
      
      task("html", 1)
      solve("<h1>título</h1>")
      verifyOk must beTrue
    }
    
    "basico 2" in {
      task("html", 2)
      solve("<h2>título 2</h2>")
      verifyOk must beTrue
    }
    
    "basico 3" in {
      task("html", 3)
      solve("<h3>título 3</h3>")
      verifyOk must beTrue
    }
    
    "arquivos 1" in 
    {
      task("html", 4)
      solve("<html><head><title>Página html</title></head><body>Oi mundo</body></html>")
      verifyOk must beTrue
    }
    
    "formatacao 1" in {
      task("html",8);
      solve("<p><b>Oi</b> mundo</p>");
      verifyOk must beTrue
    }
  }

}
