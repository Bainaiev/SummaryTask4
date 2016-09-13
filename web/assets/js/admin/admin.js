function validate() {
    var login = document.getElementById("login").value;
    var password = document.getElementById("password").value;

    var logFlag = validateLogin(login);
    var passFlag = validatePassword(password);
    return !!(logFlag == true && passFlag == true);

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
