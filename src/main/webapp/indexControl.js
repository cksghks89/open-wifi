function setLocation(){
    const lat = document.querySelector("#lat");
    const lnt = document.querySelector("#lnt");

    navigator.geolocation.getCurrentPosition(
        function(position){
            lat.value = (position.coords.latitude);
            lnt.value = (position.coords.longitude);
        },
    );
}

function validateForm(e){
    const lat = Number(document.querySelector("#lat").value);
    const lnt = Number(document.querySelector("#lnt").value);


    if(isNaN(lat) || isNaN(lnt)){
        alert('숫자를 입력해 주세요');
        return false;
    }

    // 위도(lat) 범위 : -90 ~ +90
    // 경도(lnt) 범위 : -180 ~ +180
    if(-90 <= lat && lat <= 90 && -180 <= lnt && lnt <= 180){
        return true;
    }else{
        alert('위경도의 범위를 벗어났습니다. \n 위도 : -90 ~ 90 \n 경도 :-180 ~ 180');
        return false;
    }
}

