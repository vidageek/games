package vggames.shared

import br.com.caelum.vraptor.{Resource, Get}

@Resource
class UserHost {

  @Get(Array("/"))
  def home {
  }
}