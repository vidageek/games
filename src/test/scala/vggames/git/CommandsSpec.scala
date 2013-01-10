package vggames.git

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CommandsSpec extends Specification {

  "commit command" should {
    "produce a meaningfull challenge" in {
      Commit("commit legal").challenge should_== "Fa&ccedil;a um commit com a mensagem <code>commit legal</code>"
    }

    "produce a meaningfull challenge for flag -a" in {
      Commit("commit legal", true).challenge should_==
        "Fa&ccedil;a um commit com a mensagem <code>commit legal</code> que tamb&eacute;m inclua os arquivos <strong>modified</strong>"
    }
  }

  "branch command" should {
    "produce a meaningfull challenge" in {
      Branch("asdrubal").challenge should_== "Crie o branch <code>asdrubal</code>"
    }

    "produce a meaningfull challenge for delete" in {
      DeleteBranch("asdrubal").challenge should_== "Apague o branch <code>asdrubal</code>"
    }

    "produce a meaningfull challenge for move" in {
      MoveBranch("work", "asdrubal").challenge should_== "Renomeie o branch <code>work</code> para <code>asdrubal</code>"
    }
  }

  "init command" should {
    "produce a meaningfull challenge" in {
      Init("asdrubal").challenge should_== "Crie o reposit&oacute;rio <code>asdrubal</code>"
    }
  }

  "checkout command" should {
    "produce a meaningfull challenge" in {
      Checkout("asdrubal").challenge should_== "Mude para o branch <code>asdrubal</code>"
    }

    "produce a meaningfull challenge if b flag is present" in {
      Checkout("asdrubal", true).challenge should_== "Crie o branch <code>asdrubal</code> e mude para ele"
    }
  }

  "add command" should {
    "produce meaningfull challenge" in {
      Add("a").challenge should_== "Adicione o arquivo <code>a</code> &agrave; lista de commit candidate"
    }

    "produce meaningfull challenge for folders" in {
      Add("a", true).challenge should_== "Adicione todos os arquivos da pasta <code>a</code> &agrave; lista de commit candidate"
    }

    "produce meaningfull challenge for folders" in {
      Add(".").challenge should_== "Adicione todos os arquivos da pasta atual &agrave; lista de commit candidate"
    }
  }

  "merge command" should {
    "produce a meaningfull challenge" in {
      Merge("asdrubal").challenge should_== "Faça o merge dos commits do branch <code>asdrubal</code> no branch atual."
    }
  }

}