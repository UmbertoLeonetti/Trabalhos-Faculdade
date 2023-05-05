const loginInput = document.getElementById('login');
const passwordInput = document.getElementById('password');
const autenticarBtn = document.getElementById('autenticar');

autenticarBtn.addEventListener('click', function(event) {
	event.preventDefault();
	if (loginInput.value.trim() === '' || passwordInput.value.trim() === '') {
		alert('Informe os dados de autenticação');
	} else {
		localStorage.setItem('authenticatedUser', loginInput.value);
		window.location.href = 'index.html';
	}
});