function sentToBack(data) {
  fetch('/usuario/cadastro', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ data: data }),
  })
    .then(response => {

      if (response.ok) {
        console.log('Data sent to backend successfully');

      } else {
        console.error('Error sending data to backend');

      }
    })
    .catch(error => {
      console.error('Error sending data to backend:', error);

    });
}

function sentToBack(data) {
  fetch(`/usuario/cadastro?data=${encodeURIComponent(data)}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    },
  })
    .then(response => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error('Error sending data to backend');
      }
    })
    .then(responseData => {
      console.log('Data received from backend:', responseData);
      
    })
    .catch(error => {
      console.error('Error sending or receiving data:', error);
    });
}
