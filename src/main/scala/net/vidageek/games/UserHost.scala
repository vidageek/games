package net.vidageek.games

import br.com.caelum.vraptor.Get
import br.com.caelum.vraptor.Resource

@Resource
class UserHost {

  @Get(Array("/"))
  def home {
  }
}