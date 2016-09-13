function validate() {
    var login = document.getElementById("login").value;
    var password = document.getElementById("password").value;

    var logFlag = validateLogin(login);
    var passFlag = validatePassword(password);
    return !!(logFlag == true && passFlag == true);

}

function regValidate() {
    var firstName = document.getElementById("fn").value;
    var lastName = document.getElementById("ln").value;
    var email = document.getElementById("em").value;
    var login = document.getElementById("lg").value;
    var password = document.getElementById("ps").value;


    var fnFlag = validateName(firstName, "fnError");
    var lnFlag = validateName(lastName, "lnError");
    var emFlag = validateEmail(email);
    var logFlag = validateLogin(login);
    var passFlag = validatePassword(password);

    return !!(fnFlag == true && lnFlag == true && emFlag == true && logFlag == true && passFlag == true);
}


function validateLogin(login) {

    if (login === null || login === undefined || login.length == 0) {
        document.getElementById("logError").innerHTML = 'Cant be empty';
        return false;
    }

    if (!/^[A-ZА-Яа-яa-z0-9_-]+$/.test(login)) {
        document.getElementById("logError").innerHTML = 'Field can contain only latin letters, numbers, hyphen or underscore';
        return false;
    }

    if (login.length < 4 || login.length > 100) {
        document.getElementById("logError").innerHTML = 'Field must contain from 4 to 100 symbols';
        return false;
    }

    document.getElementById("logError").innerHTML = '';
    return true;
}

function validatePassword(password) {
    var space = ' ';
    if (password === null || password === undefined || password.length == 0) {
        document.getElementById("passError").innerHTML = 'Cant be empty';
        return false;
    }
    if (password.indexOf(space) != -1) {
        document.getElementById("passError").innerHTML = 'Field must not contain whitespaces';
        return false;
    }
    if (password.length < 5 || password.length > 100) {
        document.getElementById("passError").innerHTML = 'Field must contain from 5 to 100 symbols';
        return false;
    }

    document.getElementById("passError").innerHTML = '';
    return true;
}

function validateName(name, id) {

    if (name === null || name === undefined || name.length == 0) {
        document.getElementById(id).innerHTML = 'Cant be empty';
        return false;
    }

    if (!/^[A-ZА-Яа-яa-z]+$/.test(name)) {
        document.getElementById(id).innerHTML = 'Field must contain only letters';
        return false;
    }

    if (name.length < 3 || name.length > 100) {
        document.getElementById(id).innerHTML = 'Field must contain from 3 to 100 symbols';
        return false;
    }

    document.getElementById(id).innerHTML = '';
    return true;
}

function validateEmail(email) {

    if (email === null || email === undefined || email.length == 0) {
        document.getElementById("emError").innerHTML = 'Cant be empty';
        return false;
    }

    if (!/^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/.test(email)) {
        document.getElementById("emError").innerHTML = 'Invalid email';
        return false;
    }

    if (email.length < 5 || email.length > 100) {
        document.getElementById("emError").innerHTML = 'Field must contain from 5 to 100 symbols';
        return false;
    }

    document.getElementById("emError").innerHTML = '';
    return true;
}

