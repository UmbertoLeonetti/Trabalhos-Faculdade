function autenticar() {
	let loginInput = document.getElementById('login').value;
	let passwordInput = document.getElementById('password').value;
	if (loginInput == "" || passwordInput == "") {
		alert("Informe os dados de autenticação");
	}
}
function onInit() {
	var login = "Não autenticado";
	
	localStorage.clear();
	if(localStorage.getItem("login") != null){
		login = localStorage.getItem("login").trim();
	}
	
	document.getElementById("login").innerHTML = login;
	console.log("lOGIN: "+login);
	}