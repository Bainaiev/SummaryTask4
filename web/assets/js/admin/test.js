function validate() {
    var title = document.getElementById("title").value;
    var complexity = document.getElementById("complexity").value;
    var time = document.getElementById("timePassing").value;
    //
    var titleFlag = validateTitle(title);
    var compFlag = validateComplexity(complexity);
    var timeFlag = validateTime(time);
    
    return !!(titleFlag == true && compFlag == true && timeFlag == true);

}


function validateTitle(title) {

    if (title === null || title === undefined || title.length == 0) {
        document.getElementById("titleError").innerHTML = 'Cant be empty';
        return false;
    }

    if (!/^[A-ZА-Яа-яa-z0-9_-]+$/.test(title)) {
        document.getElementById("titleError").innerHTML = 'Field can contain only latin letters, numbers, hyphen or underscore';
        return false;
    }

    if (title.length < 3 || title.length > 100) {
        document.getElementById("titleError").innerHTML = 'Field must contain from 3 to 100 symbols';
        return false;
    }

    document.getElementById("titleError").innerHTML = '';
    return true;
}

function validateComplexity(complexity) {

    if (complexity === null || complexity === undefined || complexity.length == 0) {
        document.getElementById("compError").innerHTML = 'Cant be empty';
        return false;
    }

    if (!/^[0-9]+$/.test(complexity)) {
        document.getElementById("compError").innerHTML = 'Field can contain only numbers';
        return false;
    }

    var number = parseInt(complexity);

    if (number > 10) {
        document.getElementById("compError").innerHTML = 'Number must be less or equal 10';
        return false;
    }

    document.getElementById("compError").innerHTML = '';
    return true;
}

function validateTime(time) {

    if (time === null || time === undefined || time.length == 0) {
        document.getElementById("timeError").innerHTML = 'Cant be empty';
        return false;
    }

    if (!/^[0-9]+$/.test(time)) {
        document.getElementById("timeError").innerHTML = 'Field can contain only numbers';
        return false;
    }

    var number = parseInt(time);

    if (number > 150) {
        document.getElementById("timeError").innerHTML = 'Number must be less or equal 150';
        return false;
    }

    document.getElementById("timeError").innerHTML = '';
    return true;
}
