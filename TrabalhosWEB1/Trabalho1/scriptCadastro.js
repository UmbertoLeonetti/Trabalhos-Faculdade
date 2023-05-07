const selectElement = document.querySelector('select');
const divElement = document.querySelector('#conteudo');

selectElement.addEventListener('change', (event) => {
  if (event.target.value === '2') {
    divElement.innerHTML = 'Novo conteúdo'; // aqui você pode definir o novo conteúdo da div
  } else {
    divElement.innerHTML = 'Conteúdo normal'; // aqui você pode definir o conteúdo normal da div
  }
});