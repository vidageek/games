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
class HtmlGameSpec extends Specification with WebBrowser {

  sequential

//  "HtmlGame" should {
//
//    "basic 0" in {
//
//      task("html", 0)
//      solve("<p>Hello world!</p>")
//      verifyOk must beTrue
//
//    }
//
//    "basic fail" in {
//
//      task("html", 0)
//      solve("<p>Hello world!!!!</p>")
//      verifyOk must beFalse
//
//    }
//    "basic 1" in {
//
//      task("html", 1)
//      solve("<h1>título</h1>")
//      verifyOk must beTrue
//    }
//
//    "basic 2" in {
//      task("html", 2)
//      solve("<h2>título 2</h2>")
//      verifyOk must beTrue
//    }
//
//    "basic 3" in {
//      task("html", 3)
//      solve("<h3>título 3</h3>")
//      verifyOk must beTrue
//    }
//
//    "structure 1" in
//      {
//        task("html", 4)
//        solve("<html><head><title>Página html</title></head><body>Oi mundo</body></html>")
//        verifyOk must beTrue
//      }
//
//    "structure 2" in
//      {
//        task("html", 5)
//        solve("<html><head><title>Minha página</title></head><body><b>Conteúdo da minha página</b></body></html>")
//        verifyOk must beTrue
//      }
//
//    "structure 3" in
//      {
//        task("html", 6)
//        solve("""<html><head><title>Página com link</title></head><body><a href="www.google.com">Google</a></body></html>""")
//        verifyOk must beTrue
//      }
//
//    "paragraph 1" in
//    {
//      task("html", 7)
//      solve("<p>Oi mundo</p>")
//      verifyOk must beTrue
//    }
//    
//    "paragraph 2" in
//    {
//      task("html", 8)
//      solve("<p><b>Oi</b> mundo</p>")
//      verifyOk must beTrue
//    }
//    
//    "format 1" in
//    {
//      task("html", 9)
//      solve("texto em <i>itálico</i>")
//      verifyOk must beTrue
//    }
//    
//    "format 2" in
//    {
//      task("html", 10)
//      solve("texto <big>grande</big>")
//      verifyOk must beTrue
//    }
//    
//    "format 3" in
//    {
//      task("html", 11)
//      solve("texto <small>pequeno</small>")
//      verifyOk must beTrue
//    }
//    
//    "format 4" in
//    {
//      task("html", 12)
//      solve("O html permite texto em <b>negrito</b>, <i>itálico</i>, <big>grande</big>, <small>pequeno</small> e muito mais.")
//      verifyOk must beTrue
//    }
//    
//    "format 5" in
//    {
//      task("html", 13)
//      solve("""<code>System.out.println("Hello world!")</code>""")
//      verifyOk must beTrue
//    }
//    
//    "format 6" in // TODO: Arrumar esse teste quando não houver mais bugs no verificador
//    {
//      task("html", 14)
//      solve("<pre>Esse texto    contém 4 espaços e uma quebra de linha</pre>")
//      verifyOk must beTrue // Alterar!
//    }
//    
//    "link 1" in 
//    {
//      task("html", 15)
//      solve("""<a href="http://www.google.com">Google</a>""")
//      verifyOk must beTrue
//    }
//    
//    "link 2" in 
//    {
//      task("html", 16)
//      solve("""<a href="http://home.com"><h1>Home</h1></a>""")
//      verifyOk must beTrue
//    }
//    
//    "link 3" in 
//    {
//      task("html", 17)
//      solve("""<a href="#indice">Vai</a><a name="indice">Indice 1</a>""")
//      verifyOk must beTrue
//    }
//    
//    "image 1" in 
//    {
//      task("html", 18)
//      solve("""<img src="http://www.vidageek.net/images/logo.png" alt="logo">""")
//      verifyOk must beTrue
//    }
//    
//    "image 2" in 
//    {
//      task("html", 19)
//      solve("""<img src="http://www.vidageek.net/images/logo.png" height="100" alt="logo">""")
//      verifyOk must beTrue
//    }
//    
//    "image 3" in 
//    {
//      task("html", 20)
//      solve("""<img src="http://www.vidageek.net/images/logo.png" height="100" width="200" alt="logo">""")
//      verifyOk must beTrue
//    }
//    
//    "list 1" in 
//    {
//      task("html", 21)
//      solve("""<ul><li> Carro </li><li> Moto </li><li> Barco </li></ul>""")
//      verifyOk must beTrue
//    }
//    
//    "list 2" in 
//    {
//      task("html", 22)
//      solve("""<ol><li> Abacaxi </li><li> Uva </li><li> Banana </li></ol>""")
//      verifyOk must beTrue
//    }
//    
//    "list 3" in 
//    {
//      task("html", 23)
//      solve("""<dl><dt>Grama</dt><dd>Verde</dd><dt>Sol</dt><dd>Amarelo</dd></dl>""")
//      verifyOk must beTrue
//    }
//    
//    "table 1" in 
//    {
//      task("html", 24)
//      solve("""<table border="1"><tr><td>1</td><td>2</td></tr><tr><td>3</td><td>4</td></tr></table>""")
//      verifyOk must beTrue
//    }
//    
//    "table 2" in 
//    {
//      task("html", 25)
//      solve("""<table border=1><tr><td colspan=2><b>Negrito</b></td></tr><tr><td>Esquerda</td><td>Direita</td></tr></table>""")
//      verifyOk must beTrue
//    }
//    
//    "table 3" in 
//    {
//      task("html", 26)
//      solve("""<table border=1><tr><td rowspan=2><b>Negrito</b></td><td>Acima</td></tr><tr><td>Abaixo</td></tr></table>""")
//      verifyOk must beTrue
//    }
//    
//    "div 1" in 
//    {
//      task("html", 27)
//      solve("""<div><span>Minha querida div</span></div>""")
//      verifyOk must beTrue
//    }
//  }
}
