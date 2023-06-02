function sentToBack(data)
{
    fetch('/usuario/cadastro', {
        method: 'POST', 
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ data: myData }),
      })
        .then(response => {
          
        })
        .catch(error => {
          
        });
      


}