function onInit() {
    var login = "Não autenticado";
    
    if(localStorage.getItem("login") != null){
        login = localStorage.getItem("login").trim();
    }
    
    document.getElementById("login").innerHTML = login;
    }


    function cadastrar() {
        var loginInput = document.getElementById('firstname').value;
        var passwordInput = document.getElementById('password').value;
        if (loginInput == "" || passwordInput == "") {
            alert("Informe os dados obrigatorios de cadastro.");
        }else{
            localStorage.setItem("login",loginInput);
            localStorage.setItem("password",passwordInput);
            window.history.back();
    
        }
    };
