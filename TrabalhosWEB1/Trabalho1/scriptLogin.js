function autenticar() {
	var loginInput = document.getElementById('loginInput').value;
	var passwordInput = document.getElementById('password').value;
	if (loginInput == "" || passwordInput == "") {
		alert("Informe os dados de autenticação");
	}else{
		localStorage.setItem("login",loginInput);
		localStorage.setItem("password",passwordInput);
		window.history.back();

	}
};


function onInit() {
	var login = "Não autenticado";
	
	if(localStorage.getItem("login") != null){
		login = localStorage.getItem("login").trim();
	}
	
	document.getElementById("login").innerHTML = login;
	}