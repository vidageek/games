package vggames.git

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import vggames.shared.task.Descriptions
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GitGameSpec extends Specification with Mockito {

  val descriptions = mock[Descriptions]

  "git game" should {
    "have answers for all games" in {
      val game = new GitGame(descriptions)

      answers.size must_== game.getSize

      println(game.getSize)

      0 until game.getSize foreach { i =>
        game.task(i).judge(answers(i)).ok aka (
          "%s task %d answer is %s".format(game.getClass().getSimpleName(), i, answers(i))) must beTrue
      }
    }
  }

  val answers = Vector(
    """git init repositorio""",
    """git init repo2""",
    """git add arquivo.txt""",
    """git add pasta""",
    """git add arquivo""",
    """git add arquivos""",
    """git add arquivo1.txt""",
    """git add arq2""",
    """git add .""",
    """git add arquivo1.txt""",
    """git add .""",
    """git commit -m "meu primeiro commit"""",
    """git add arquivo.txt""",
    """git commit -m "commit de um arquivo untracked"""",
    """git add .""",
    """git commit -m "commit de um arquivo untracked e um modified"""",
    """git commit -a -m "commit de um arquivo modified sem usar git add"""",
    """git add arquivo.txt""",
    """git commit -m "commit apenas do arquivo.txt"""",
    """git commit -a -m "commit do arquivo2.txt sem usar add"""",
    """git add arquivo2.txt""",
    """git commit -m "commit apenas do arquivo2.txt"""",
    """git add pasta""",
    """git commit -m "commit dos arquivos da pasta 'pasta'"""",
    """git add arquivo.txt""",
    """git commit -m "commit do arquivo.txt"""",
    """git commit -m "commit dos arquivos que faltam" -a""",
    """git branch work""",
    """git branch feature""",
    """git branch -D work""",
    """git branch master""",
    """git branch -m master outroBranch""",
    """git branch -D outroBranch""",
    """git branch master""",
    """git branch work""",
    """git checkout work""",
    """git checkout -b work""",
    """git checkout -b outraFeature""",
    """git commit -m "commit no master"""",
    """git branch work""",
    """git commit -m "outro commit no master"""",
    """git checkout work""",
    """git commit -m "commit no work"""",
    """git checkout -b outro""",
    """git commit -m "commit no branch outro"""",
    """git checkout work""",
    """git commit -m "mais um commit no work"""",
    """git checkout -b work""",
    """git branch outro""",
    """git commit -m "primeiro commit"""",
    """git checkout outro""",
    """git merge work""",
    """git commit -m "commit no branch outro"""",
    """git checkout work""",
    """git commit -m "commit no work"""",
    """git merge outro""",
    """git commit -m "commit depois do commit de merge"""",
    """git checkout outro""")

}