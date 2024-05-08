/**
 * 
 */

document.getElementById('phone-change').addEventListener('click', function() {
  var phoneGroup = document.getElementById('phone-group');
  var verificationGroup = document.getElementById('verification-group');
  var ph_nm = document.getElementById('ph_nm').value;
  var phonePattern = /^[0-9]{3}-?[0-9]{4}-?[0-9]{4}$/;

  if (!ph_nm || !phonePattern.test(ph_nm)) {
    alert("올바른 휴대전화 번호를 입력해주세요.");
    return;
  }

  if (!verificationGroup) {
    axios.post('/usr/validatePhone', null, {
      params: {
        ph_nm
      }
    }).then(function(response) {
      if (response.data.length != 0) {
        verificationGroup = document.createElement('div');
        verificationGroup.id = 'verification-group';
        verificationGroup.className = 'input-group mb-3';

        var verificationInput = document.createElement('input');
        verificationInput.type = 'text';
        verificationInput.className = 'form-control';
        verificationInput.id = 'code'
        verificationInput.placeholder = '인증번호';
        verificationGroup.appendChild(verificationInput);

        var verificationButton = document.createElement('div');
        verificationButton.className = 'input-group-append';
        verificationGroup.appendChild(verificationButton);

        var verificationButtonContent = document.createElement('button');
        verificationButtonContent.className = 'btn btn-outline-secondary';
        verificationButtonContent.type = 'button';
        verificationButtonContent.textContent = '확인';
        verificationButtonContent.id = 'checkCode';
        verificationButton.appendChild(verificationButtonContent);

        phoneGroup.after(verificationGroup);

        // checkCode 함수 등록
        document.getElementById('checkCode').addEventListener('click', function() {
          var ph_nm = document.getElementById('ph_nm').value;
          var ph_code = document.getElementById('code').value;
          axios.post('/usr/validateCode', null, {
            params: {
              ph_nm,
              ph_code
            }
          }).then(function(response) {
            if (response.data == 1) {
              updateUsr('ph_nm')
            } else {
              alert("인증번호를 다시 확인해주세요.")
            }
          });
        });
      } else {
        alert("핸드폰 번호를 다시 확인해주세요.")
      }
    });
  }
});

function updateUsr(key){
	let value = document.getElementById(key).value
	const params = new URLSearchParams();

	params.append(key, value);
	axios.post('/usr/usrUpdate', params)
	.then(function (res) {
		if(res.data != 0){
			alert("변경되었습니다.")
		    location.href="/prd/main";		
		}

	});
	
}


