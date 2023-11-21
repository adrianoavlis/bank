
function validar() {
    let nome = formConta.nome.value
    let cpf = formConta.cpf.value
    let senha = formConta.senha.value
    if (nome === "") {
        alert('Preencha o Campo Nome')
        formConta.nome.focus()
        return false
    } else if (cpf === "") {
        alert('PreenchaS o Campo CPF')
        formConta.cpf.focus()
        return false
    } else if (senha === "") {
        alert('Preencha o Campo Fone')
        formConta.senha.focus()
        return false
    } else {
        document.forms["formConta"].submit()
    }
}
function confirmar(idcon) {
    let resposta = confirm("Comfima a exclusão deste contato?")
    if (resposta === true) {
        window.location.href = 'delete?idcon=' + idcon

    }

}


function validarLogin() {
    let cpf = formLogin.cpf.value
    let senha = formLogin.senha.value
    if (cpf === "") {
        alert('Preencha o Campo CPF')
        formLogin.cpf.focus()
        return false
    } else if (senha === "") {
        alert('Preencha o Campo Senha')
        formLogin.senha.focus()
        return false
    } else {
        document.forms["formLogin"].submit()
    }
}
 