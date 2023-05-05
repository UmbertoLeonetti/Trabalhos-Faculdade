function autenticar() {
	let loginInput = document.getElementById('login').value.trim();
	let passwordInput = document.getElementById('password').value.trim();
	if (loginInput == "" || passwordInput == "") {
		alert("“Usuário não autenticado");
	} else {
		localStorage.setItem('authenticatedUser', loginValue);
		window.location.href = 'index.html';
	}
}