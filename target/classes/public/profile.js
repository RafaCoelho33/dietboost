let usuarioCorrente = null;
let usuarioCorrenteJSON = sessionStorage.getItem("usuarioCorrente");
if (usuarioCorrenteJSON) {
  usuarioCorrente = JSON.parse(usuarioCorrenteJSON);
}
$('.profile #name').val (usuarioCorrente.nome)
$('.profile #login').val (usuarioCorrente.login)
$('.profile #email').val (usuarioCorrente.email)
$('.profile #password').val (usuarioCorrente.password)

