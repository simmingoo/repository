const methods = {
	getMktDivCd:(value) => {
        if(value == "11") {
            return "KOSPI";
        } else if(value == "12") {
            return "KOSDAQ";
        } else if(value == "14") {
            return "KONEX";
        } else if(value == "15") {
            return "ETC";
        } else {
            return "ETC";
        }
	},
	addComma:(value) => {
		return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
    getDateTime:() => {
        const currentDay = new Date(); 
        const year = currentDay.getFullYear();
        const month = ("00" + (currentDay.getMonth() + 1)).slice(-2);
        const day = ("00" + currentDay.getDate()).slice(-2);
        const hour = ("00" + currentDay.getHours()).slice(-2);
        const minute = ("00" + currentDay.getMinutes()).slice(-2);
        const second = ("00" + currentDay.getSeconds()).slice(-2);
        const rtv =  year+month+day+hour+minute+second;
        return rtv;
    },
    calcPercent:(divideVal, totalVal) => {
        return Math.ceil(divideVal / totalVal * 100);
    },
    getSectorColor:(sectorVal) => {
        var rtv = "";
        sectorVal = sectorVal.toString();
        switch (sectorVal) {
            case "1":
                rtv = "#1b4872";
                break;
            case "2":
                rtv = "#20499b";
                break;
            case "3":
                rtv = "#3b85c2";
                break;
            case "4":
                rtv = "#4ac4c9";
                break;
            case "5":
                rtv = "#99c0db";
                break;
            case "6":
                rtv = "#f1592a";
                break;
            case "7":
                rtv = "#9aca3a";
                break;
            case "8":
                rtv = "#75cde1";
                break;
            case "9":
                rtv = "#3ecad1";
                break;
            case "10":
                rtv = "#b71755";
                break;
            case "11":
                rtv = "#fbd54e";
                break;
            case "12":
                rtv = "#0095a7";
                break;
            case "13":
                rtv = "#53285e";
                break;
            case "14":
                rtv = "#9b59a1";
                break;
            case "15":
                rtv = "#8d643e";
                break;
            case "16":
                rtv = "#634b47";
                break;
            case "17":
                rtv = "#d14244";
                break;
            case "18":
                rtv = "#e47178";
                break;
            case "19":
                rtv = "#017949";
                break;
            case "20":
                rtv = "#f4c5bf";
                break;
            case "21":
                rtv = "#ec902a";
                break;
            case "22":
                rtv = "#20499b";
                break;
            default:
                rtv = "#979fa9";
                break;
        }
        return rtv;
    },
    getTableNumberValue:(value) =>{
        if(value == 0) {
            return "";
        } else {
            return value;
        }
    },
    getDateConvert:(value) => {
        return value.toString().substr(0,4) + '/'+value.toString().substr(4,2);
    },
    getDateConvert2:(value) => {
        return value.toString().substr(0,4) + '-'+value.toString().substr(4,2) + '-' +value.toString().substr(6,2);
    },
    fnGetPriceClassVal (val) {
        if(val  > 0) {
            return "txt_red";
        } else if(val == 0) {
            return "txt_gray";
        } else if(val < 0) {
            return "txt_blue";
        }
    },
    fnGetPriceTxtVal (val) {
        val = Math.round(val * 100) / 100
        if(val > 0) {
            return "+"+val;
        } else if(val == 0 || val < 0) {
            return val;
        }
    },
    getCurrentDate(){
        const date = new Date();
        const year = date.getFullYear().toString();

        let month = date.getMonth() + 1;
        month = month < 10 ? '0' + month.toString() : month.toString();

        let day = date.getDate();
        day = day < 10 ? '0' + day.toString() : day.toString();

        let hour = date.getHours();
        hour = hour < 10 ? '0' + hour.toString() : hour.toString();

        let minites = date.getMinutes();
        minites = minites < 10 ? '0' + minites.toString() : minites.toString();

        let seconds = date.getSeconds();
        seconds = seconds < 10 ? '0' + seconds.toString() : seconds.toString();

        return year + month + day + hour + minites + seconds;
    },
    getCalcDate(val, type){
        let date = new Date();
        if(type == 'Y') {
            date.setFullYear(date.getFullYear()+val)
        } else if(type == 'M') {
            date.setMonth(date.getMonth()+val);
        } else if(type == 'D') {
            date.setDate(date.getDate()+val);
        }

        const year = date.getFullYear().toString();

        let month = date.getMonth() + 1;
        month = month < 10 ? '0' + month.toString() : month.toString();

        let day = date.getDate();
        day = day < 10 ? '0' + day.toString() : day.toString();

        let hour = date.getHours();
        hour = hour < 10 ? '0' + hour.toString() : hour.toString();

        let minites = date.getMinutes();
        minites = minites < 10 ? '0' + minites.toString() : minites.toString();

        let seconds = date.getSeconds();
        seconds = seconds < 10 ? '0' + seconds.toString() : seconds.toString();

        return year + month + day + hour + minites + seconds;
    },
    fillZero(n, width){
        n = n + '';
        return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
    },
    checkNullVal(val) {
        if(val == 0 || val == null) {
            return "-";
        }else if(val.length == 0) {
            return "-";
        }else {
            return val;
        }
    },
    checkNullValPerPbr(val) {
        if(val == 0 || val == null || val.length == 0 || val < 0) {
            return "N/A";
        } else {
            return val;
        }
    },
}

export default {
	install(Vue) {
		Vue.prototype.$getMktDivCd = methods.getMktDivCd
		Vue.prototype.$addComma = methods.addComma
		Vue.prototype.$getDateTime = methods.getDateTime
		Vue.prototype.$calcPercendetailCorpt = methods.calcPercent
		Vue.prototype.$getSectorColor = methods.getSectorColor
		Vue.prototype.$getTableNumberValue = methods.getTableNumberValue
		Vue.prototype.$getDateConvert = methods.getDateConvert
		Vue.prototype.$getDateConvert2 = methods.getDateConvert2
		Vue.prototype.$fnGetPriceClassVal = methods.fnGetPriceClassVal
		Vue.prototype.$fnGetPriceTxtVal = methods.fnGetPriceTxtVal
        Vue.prototype.$getCurrentDate = methods.getCurrentDate
        Vue.prototype.$fillZero = methods.fillZero
        Vue.prototype.$checkNullVal = methods.checkNullVal
        Vue.prototype.$checkNullValPerPbr = methods.checkNullValPerPbr
        Vue.prototype.$getCalcDate = methods.getCalcDate
	}
}
