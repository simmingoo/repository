function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function addComma(data) {
    return data.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

function calcPercent (divideVal, totalVal) {
    return Math.ceil(divideVal / totalVal * 100).toString() + "%";
}