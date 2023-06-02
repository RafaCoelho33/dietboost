// declara um conjunto inicial de alimentos
var db_alimentos_inicial = {
    "data": [
        {
            "id": 1,
            "nome": 'Água',
            "calorias": "0",
            "carboidratos": "0",
            "proteinas": "0",
            "gorduras": "0",
        },
        {
            "id": 2,
            "nome": 'Banana',
            "calorias": "98",
            "carboidratos": "15",
            "proteinas": "1.3",
            "gorduras": "0.1",
        },
        {
            "id": 3,
            "nome": 'Ovo',
            "calorias": "155",
            "carboidratos": "1.1",
            "proteinas": "13",
            "gorduras": "11",
        },
        {
            "id": 4,
            "nome": 'Arroz Branco',
            "calorias": "125",
            "carboidratos": "28",
            "proteinas": "2.5",
            "gorduras": "0.2",
        },
        {
            "id": 5,
            "nome": 'Feijão',
            "calorias": "155",
            "carboidratos": "27",
            "proteinas": "11",
            "gorduras": "0.6",
        },
        {
            "id": 6,
            "nome": 'Açai Polpa',
            "calorias": "58",
            "carboidratos": "6.2",
            "proteinas": "0.8",
            "gorduras": "0.1",
        },
        {
            "id": 7,
            "nome": 'Pão Francês',
            "calorias": "300",
            "carboidratos": "58.7",
            "proteinas": "8",
            "gorduras": "2.6",
        },
        {
            "id": 8,
            "nome": 'Peito de Frango',
            "calorias": "163",
            "carboidratos": "0",
            "proteinas": "31.5",
            "gorduras": "3.2",
        },
        {
            "id": 9,
            "nome": 'Chocolate ao Leite',
            "calorias": "539.6",
            "carboidratos": "59.6",
            "proteinas": "7.2",
            "gorduras": "28.4",
        },
        {
            "id": 10,
            "nome": 'Brócolis',
            "calorias": "25.5",
            "carboidratos": "4",
            "proteinas": "3.6",
            "gorduras": "0.4",
        },
    ]
}

// Caso os dados já estejam no Local Storage, caso contrário, carrega os dados iniciais
var db = JSON.parse(localStorage.getItem('db_alimento'));
if (!db) {
    db = db_alimentos_inicial
};

// Exibe mensagem em um elemento de ID msg
function displayMessage(msg) {
    $('#msg').html('<div class="alert alert-warning">' + msg + '</div>');
}

function insertAlimento(alimento) {
    // Calcula novo Id a partir do último código existente no array (PODE GERAR ERRO SE A BASE ESTIVER VAZIA)
    let novoId = 1;
    if (db.data.length != 0)
        novoId = db.data[db.data.length - 1].id + 1;
    let novoAlimento = {
        "id": novoId,
        "nome": alimento.nome,
        "calorias": alimento.calorias,
        "carboidratos": alimento.carboidratos,
        "proteinas": alimento.proteinas,
        "gorduras": alimento.gorduras
    };

    // Insere o novo objeto no array
    db.data.push(novoAlimento);
    displayMessage("alimento inserido com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_alimento', JSON.stringify(db));
}

function updateAlimento(id, alimento) {
    // Localiza o indice do objeto a ser alterado no array a partir do seu ID
    let index = db.data.map(obj => obj.id).indexOf(id);

    // Altera os dados do objeto no array
    db.data[index].nome = alimento.nome,
        db.data[index].calorias = alimento.calorias,
        db.data[index].carboidratos = alimento.carboidratos,
        db.data[index].proteinas = alimento.proteinas,
        db.data[index].gorduras = alimento.gorduras

    displayMessage("Alimento alterado com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_alimento', JSON.stringify(db));
}

function deleteAlimento(id) {
    // Filtra o array removendo o elemento com o id passado
    db.data = db.data.filter(function (element) { return element.id != id });

    displayMessage("Alimento removido com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_alimento', JSON.stringify(db));
}