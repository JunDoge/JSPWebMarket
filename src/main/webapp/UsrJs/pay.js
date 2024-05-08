/**
 * 
 */

 function moveDetailPrd(prd_id) {
	location.href = "http://localhost:9123/prd/detail?prd_id=" + prd_id;
}


function delPayList(pay_id){
	 axios.get('/prd/delPayList', {
        params: {
            pay_id
        }
    }).then(function(response) {
		history.go(0);
    })
}