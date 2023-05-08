function onInit() {
var login = "Não autenticado";

if(localStorage.getItem("login") != null){
    login = localStorage.getItem("login").trim();
}

document.getElementById("login").innerHTML = login;
}