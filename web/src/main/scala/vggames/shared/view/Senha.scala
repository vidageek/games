package vggames.shared.view

import scalatags._

class Senha extends TypedView[Unit] {

  override def render(t : Unit) = {

    html(
      head(
        Tags.title("VidaGeek Games - Porquê você não precisa colocar a sua senha?"),
        meta(name := "robots", content := "noindex")),
      body(
        header(
          h1("Porquê você não precisa colocar a sua senha?")),
        div("row".cls)(
          div("span8 offset2 well".cls)(
            p(
              "Uma das razões para você não precisar de uma senha é simplesmente a facilidade de uso. É uma coisa a " +
                "menos para te atrapalhar no momento em que você quiser jogar."),
            p(
              "Mas não é apenas isso. Não é uma boa troca deixar segurança de lado para aumentar a facilidade."),
            p(
              "A principal razão que nos motivou a fazer dessa forma é que o modelo de usuário e senha não funciona."),
            p(
              "Para uma senha ser segura, é necessário que ele seja o mais única possível e difícil de ser adivinhada."),
            p(
              "O problema é que senhas assim são muito difíceis de serem lembradas. E o que fazemos quando não conseguimos ",
              "lembrar de algo que precisamos? Anotamos em um ", a(target := "_blank", href := "https://www.google.com/search?tbm=isch&q=post+it+password")("post-it "),
              "embaixo do teclado. Ou na parede. Enfim, em um lugar que outras pessoas vão ver."),
            p(
              "Ou seja, a senha passa a ser apenas um incômodo, sem acrescentar segurança real."),
            p(
              "Para resolver esse problema, o que fizemos:",
              ul(
                li(
                  "Quando você entra no site pela primeira vez, clica em Login e coloca o seu email, nós criamos uma senha super-segura pra você ",
                  "(com 120 caracteres). Essa senha é completamente inviável de ser copiada para um post it. Também não dá para lembrar."),
                li(
                  "Como você não consegue se lembrar dela, não vamos pedir para você fazer isso. Só vamos pedir para você clicar em um link no seu ",
                  "email. Esse link vai enviar para nós a sua senha super segura."),
                li(
                  "Quando nós recebermos a sua senha super segura, vamos logar você e deixar você logado até que você clique em Logout. Se você ",
                  "estiver no seu computador de casa, poderá ficar logado para sempre. Se estiver em um computador compartilhado, você precisará ",
                  "clicar em Logout antes de sair (do mesmo modo que com senha)"),
                li(
                  "Se algum dia você perder o email com o link de login, é só colocar o seu email novamente que enviamos o link para você.")))))))
  }

}