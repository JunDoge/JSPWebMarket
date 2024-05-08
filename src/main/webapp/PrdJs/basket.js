
var IMP = window.IMP;
IMP.init("imp16540835");

var isKakaoPayActive = true;
var istossPayActive = true;
let totalPrice = 0;
var orderNames = "통합결제";
var today = new Date();
var hours = today.getHours(); // 시
var minutes = today.getMinutes();  // 분
var seconds = today.getSeconds();  // 초
var milliseconds = today.getMilliseconds();
var makeMerchantUid = hours + minutes + seconds + milliseconds;

var bks = [];

function selectAll(selectAll) {
   const checkboxes = document.getElementsByName('product');

   checkboxes.forEach((checkbox) => {
      if (selectAll.checked !== checkbox.checked) {
         checkbox.checked = selectAll.checked;
         // 체크박스 상태 변경 후 check 함수 호출
         if (checkbox.checked) {
            check(parseInt(checkbox.dataset.price), parseInt(checkbox.dataset.bk_id));
         } else {
            checkRemove(parseInt(checkbox.dataset.price), parseInt(checkbox.dataset.bk_id));
         }
      }
   })
}
function checkRemove(price, bk_id) {
   let index = bks.indexOf(bk_id);
   if (index >= 0) {
      bks.splice(index, 1);
      totalPrice -= price;
      document.getElementById('totalPrice').innerHTML = totalPrice + '원';
   }
}
function check(price, bk_id) {
   let index = bks.indexOf(bk_id)
   if (index < 0) {
      bks.push(bk_id);
      totalPrice += price;      
   } else {
      bks.splice(index, 1);
      totalPrice -= price;
   }


   document.getElementById('totalPrice').innerHTML = totalPrice + '원';


}

function handleCheckboxClick(checkbox, price, bk_id) {
   if (checkbox.checked) {
      check(price, bk_id);
   } else {
      checkRemove(price, bk_id);
   }
}
function bkDelete(bk_id) {
    axios.post('/prd/delBasket', null, {
        params: {
            bk_id
        }
    }).then(function(response) {
        if (response.data == 1) {
         window.location.reload();
            document.getElementById(bk_id).remove();
        }

    })
}
function confirmDelete(bk_id) {
    swal({
        title: '정말 삭제하겠습니까?',
        text: "다시 생각해보십시오",
        icon: 'warning',
        buttons: {
            cancel: {
                text: "취소",
                value: null,
                visible: true,
            },
            confirm: {
                text: "응 !",
                value: true,
                visible: true,
                className: "confirm-button-class",
                closeModal: true
            }
        },
        dangerMode: true,
    }).then(function(result) {
        if (result) {
            bkDelete(bk_id);
            swal(
                '아닝 !',
                '제거되었습니다.',
                'success'
            );
            
          	history.go(0)
        }
    });
}
function calculateTotalPrice(index, price, quantity) {
    if (quantity < 0) {
        alert("수량은 0 이상이어야 합니다.");
        return;
    }
    var totalPrice = price * quantity;
    document.getElementById('totalPrice' + index).innerHTML = totalPrice;
    document.getElementById('totalPrice').innerHTML = totalPrice + '원'
    return totalPrice;  // 계산된 가격 반환
}

$("#kakao").click(function() {
    if (istossPayActive) {
      $("#toss").css("background-color", "#ffffff");
      $("#kakao").css("background-color", "#f7e400");
      istossPayActive = false;
      isKakaoPayActive = true;
    }
  });

  // 토스페이 버튼 클릭 시
  $("#toss").click(function() {
    if (isKakaoPayActive) {
      $("#kakao").css("background-color", "#ffffff");
      $("#toss").css("background-color", "#c6d8f7");
      isKakaoPayActive = false;
	  istossPayActive = true;
    }
  });


function kakao() {
   PortOne.requestPayment({
      storeId: 'store-d05e2fa2-4bf4-43a9-8fa6-79a8f3512887',
      channelKey: 'channel-key-3a974b87-fba5-4986-aaa5-c5b3d223e99c',
      paymentId: makeMerchantUid + '',
      orderName: orderNames,
      totalAmount: totalPrice,
      bypass: {
         "kakaopay": {
            "custom_message": "잔액 확인 후 결제 바랍니다."
         }
      },
      currency: 'KRW',
      payMethod: "EASY_PAY"
   }).then(function(res) {
      if(res.code == null){
         sucessPay("kakao")
      }
   });


}



function toss() {

   IMP.request_pay({
      pg: "tosspay.tosstest",
      pay_method: "card",
      merchant_uid: makeMerchantUid, // 상점에서 생성한 고유 주문번호
      name: orderNames, // 필수 파라미터 입니다.
      amount: totalPrice,
      buyer_email: '${loginUser.email}',
      buyer_name: '${loginUser.name}',
      buyer_tel: '${loginUser.ph_nm}',
      buyer_addr: '${loginUser.email}',
      buyer_postcode: "123-456"
   }, function(rsp) { // callback 로직
      if (rsp.success) {
         sucessPay("toss")
      }
   });

}

function payment(){
	if(totalPrice > 0){
		if(isKakaoPayActive){
			kakao()
		}else{
			toss()
		}
	}

}

function sucessPay(pay){
	const params = new URLSearchParams();

	for (let i = 0; i < bks.length; i++) {
	  let count = document.getElementById(bks[i]).value;
	  params.append(bks[i], count);
	}
	params.append('total_price', totalPrice);
	params.append('payment', pay);

	axios.post('/prd/insPay', params)
	.then(function (res) {
	    location.href="/prd/selPayHis";
	});

  
	
	
	
}