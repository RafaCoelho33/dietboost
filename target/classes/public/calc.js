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