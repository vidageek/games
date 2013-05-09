package vggames.browser

import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor

trait WebBrowser {

  	var driver = new FirefoxDriver
  	var baseUrl = "http://localhost:8080";
	
	def task(game: String, numberTask: Int) {driver.get(baseUrl + "/play/"+ game + "/task/" + numberTask);}
  
  	def solve(response: String) : Unit = {
  		var js = driver.asInstanceOf[JavascriptExecutor];
    	js.executeScript("""editor.setValue('""" + response + """');""")
    }

    def verifyOk : Boolean = 
		driver.findElement(By.cssSelector("BODY")).getText().contains("Ok!") || 
		driver.findElement(By.cssSelector("BODY")).getText().contains("Compartilhe a sua conquista")
}