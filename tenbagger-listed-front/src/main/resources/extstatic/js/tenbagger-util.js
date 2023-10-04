//공통으로 사용하는 스크립트는 여기다 작성

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

const fnIsMobile = () => {
    return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)
}

const fnIsMts = () => {
    return /sksapp|joopasoo_/i.test(navigator.userAgent)
}

function replaceIpName(value) {
    let rtv = "";
    rtv = value.substring(0,value.indexOf("{"));
    return rtv;
}

HashMap = function () {
    this.map = new Array();
}

HashMap.prototype = {
    put : function (key,value) {
        this.map[key] = value;
    },
    get : function (key) {
        return this.map[key];
    },
    getAll : function () {
        return this.map;
    },
    clear : function () {
        this.map = new Array();
    },
    getKeys : function () {
        let keys = new Array();
        for(i in this.map) {
            keys.push(i);
        }
        return keys;
    }
}

openTab = function (idx, sectorNo) {
    $(".xa-sectortab-btn").each(function (index, item) {
        $(this).css("background-color","#ffffff");
        $(this).css("color","#000000");
    });
    $("#_sector-b"+idx).css("background-color","#000000");
    $("#_sector-b"+idx).css("color","#ffffff");

    let url = "/sectorreport/admin/modify/"+sectorNo+"/"+idx;
    $("#_sector_body").load(url);
}

