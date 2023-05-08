function onInit() {
    var login = "Não autenticado";
    
    localStorage.clear();
    if(localStorage.getItem("login") != null){
        login = localStorage.getItem("login").trim();
    }
    
    document.getElementById("login").innerHTML = login;
    console.log("lOGIN: "+login);
    }