//Written by Jeroen van der Heiden
function DateNow() {
    var t = new Date();
    var hrs = t.getHours();
    var min = t.getMinutes();
    var sec = t.getSeconds();
    var yyyy = t.getFullYear();
    var mm = t.getMonth() +1;
    var dd = t.getDate();
    
//Digit under 10 add a 0    
if(hrs<10) {
    hrs='0'+hrs
}

if(min<10) {
    min='0'+min
}

if(sec<10) {
    sec='0'+sec
}

if(dd<10) {
    dd='0'+dd
} 

if(mm<10) {
    mm='0'+mm
}

    var time = hrs + ":" + min + ":" + sec;
    var date = yyyy + "-" + mm + "-" + dd;
    
    document.getElementById('DateNow').innerHTML =
    "The local time is: " +  date.bold() + " " + time.bold();

    var refresh = setTimeout(DateNow, 500);
}