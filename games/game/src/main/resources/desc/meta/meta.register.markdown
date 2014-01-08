O [gameFactory][1] mantêm um registro de todos os jogos implementados no vidageek.games para ele poder ser publicado.

Para fazer o registro é preciso adicionar a linha 

	"math" -> new MathGame(cache.get("math")))
	 
ao mapa: 

	class GameFactoryCache(cache : DescriptionsCache) {
	  val games = Map(
	    "regex" -> new RegexGame(cache.get("regex")),
	    "scala" -> new ScalaGame(cache.get("scala")),
	    "css" -> new CssGame(cache.get("css")),
	    "html" -> new HtmlGame(cache.get("html")),
	    "git" -> new GitGame(cache.get("git")),
	    "meta" -> new MetaGame(cache.get("meta")))	    

	  def apply(gameName : String) = games.get(gameName)
	}




[1]:https://github.com/vidageek/games/blob/master/src/main/scala/vggames/shared/vraptor/GameFactory.scala