var buttonNovoEvento = document.getElementById('buttonNovoEvento');
var buttonCancelar = document.getElementById('buttonCancelar');
var novoEvento = document.getElementById('novoEvento');
var formNovoEvento = document.getElementById('formNovoEvento');
var inputNomeEvento = document.getElementById('nomeEvento');
var inputDataEvento = document.getElementById('dataEvento');
var divMensagemErro = document.getElementById('mensagemErro');
var tabelaEventos = document.getElementById('tabelaEventos');

var listaEventos = getListaEventos()||[ ];
var eventoEditado = null;

function removerEvento(i) {
    listaEventos.splice(i, 1);
    setListaEventos();
    atualizarTabelaEventos();
}

function atualizarTabelaEventos() {
    if (listaEventos.length === 0) {
        tabelaEventos.innerHTML = '<tr><td colspan="3">Nenhum evento ou meta atribuida.</td></tr>';
        return;
    }
    tabelaEventos.innerHTML = '';
    for (var i = 0; i < listaEventos.length; i++) {
        var evento = listaEventos[i];
        var linha = document.createElement('tr');
        var celulaNome = document.createElement('td');
        var celulaData = document.createElement('td');
        var celulaAcoes = document.createElement('td');
        celulaAcoes.classList.add('acoes');
        var botaoEditar = document.createElement('button');
        botaoEditar.setAttribute('data-evento', i);
        botaoEditar.classList.add('btn');
        botaoEditar.classList.add('btn-primary');
        botaoEditar.classList.add('btn-sm');
        botaoEditar.setAttribute('onclick', `mostrarNovoEvento(${i})`);
        botaoEditar.innerText = "Editar";
        var botaoExcluir = document.createElement('button');
        botaoExcluir.setAttribute('data-evento', i);
        botaoExcluir.classList.add('btn');
        botaoExcluir.classList.add('btn-danger');
        botaoExcluir.classList.add('btn-sm');
        botaoExcluir.setAttribute('onclick', `removerEvento(${i})`);  
        botaoExcluir.innerText = "Remover";
        celulaAcoes.appendChild(botaoEditar);
        celulaAcoes.appendChild(botaoExcluir);
        celulaNome.innerText = evento.nome;
        var data = new Date (evento.data);
        celulaData.innerText = data.toLocaleDateString("pt-BR", {
            timeZone: 'UTC',
        });
        linha.appendChild(celulaData);
        linha.appendChild(celulaNome);
        linha.appendChild(celulaAcoes);
        tabelaEventos.appendChild(linha);
    }
}

function limparNovoEvento () {
    inputNomeEvento.value = '';
    inputDataEvento.value = '';
    inputNomeEvento.classList.remove ('is-invalid');
    inputDataEvento.classList.remove ('is-invalid');
    divMensagemErro.classList.add('d-none');
    divMensagemErro.innerHTML = '';
}

function mostrarNovoEvento(i) {
    novoEvento.classList.remove('d-none');
     if (Number.isInteger (i)) {
        eventoEditado = i;
        inputNomeEvento.value = listaEventos[i].nome;
        inputDataEvento.value = listaEventos[i].data;
     }
}

function ocultarNovoEvento() {
    novoEvento.classList.add('d-none');
    limparNovoEvento();
}

function novoEventoValido(nomeEvento, dataEvento) {
    var validacaoOk = true;
    var erro = '';
    if (nomeEvento.trim().length === 0) {
        erro = 'O nome do evento é obrigatório!';
        inputNomeEvento.classList.add('is-invalid');
        validacaoOk = false;
    } else {
        inputNomeEvento.classList.remove('is-invalid');
    }
    var timestampEvento = Date.parse(dataEvento);
    var timestampAtual = (new Date()).getTime();
    if (isNaN(timestampEvento) || timestampEvento < timestampAtual) {
        if (erro.length > 0) {
            erro += '<br>'
        }
        erro += 'A data do evento é obrigatória e deve estar no futuro!';
        inputDataEvento.classList.add('is-invalid');
        validacaoOk = false;
    } else {
        inputDataEvento.classList.remove('is-invalid');
    }

    if (!validacaoOk) {
        divMensagemErro.innerHTML = erro;
        divMensagemErro.classList.remove('d-none');
    } else {
        divMensagemErro.classList.add('d-none');
    }

    return validacaoOk;
}

function salvarEvento(event) {
    event.preventDefault();
    var nomeEvento = inputNomeEvento.value;
    var dataEvento = inputDataEvento.value;
    if (novoEventoValido(nomeEvento, dataEvento)) {
        var evento = {
            nome: nomeEvento,
            data: dataEvento,
        }
        if (Number.isInteger(eventoEditado)) {
            listaEventos.splice(eventoEditado, 1, evento)
            eventoEditado = null;
        } else listaEventos.push(evento);
        setListaEventos();
        atualizarTabelaEventos();
        ocultarNovoEvento();
    }
}

function setListaEventos (){
    listaEventos.sort((a, b) => {
        if (!b) {
            return 0;
        }
        if (Date.parse(a.data) > Date.parse(b.data)) {
            return 1;            
        } else {
            return -1;
        }
    })
    localStorage.setItem("listaEventos", JSON.stringify(listaEventos))
}

function getListaEventos (){
   return JSON.parse(localStorage.getItem("listaEventos"))
}

buttonNovoEvento.addEventListener('click', mostrarNovoEvento);
buttonCancelar.addEventListener('click', ocultarNovoEvento);
formNovoEvento.addEventListener('submit', salvarEvento);
window.addEventListener('load', atualizarTabelaEventos);