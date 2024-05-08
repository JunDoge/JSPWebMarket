const imgs = document.querySelectorAll('.img-select a');
const imgBtns = [...imgs];
let imgId = 1;

imgBtns.forEach((imgItem) => {
    imgItem.addEventListener('click', (event) => {
        event.preventDefault();
        imgId = imgItem.dataset.id;
        slideImage();
    });
});

function slideImage(){
    const displayWidth = document.querySelector('.img-showcase img:first-child').clientWidth;

    document.querySelector('.img-showcase').style.transform = `translateX(${- (imgId - 1) * displayWidth}px)`;
}

window.addEventListener('resize', slideImage);


  function validateForm() {
      let Ssize = document.frm.Ssize.value;
      let Msize = document.frm.Msize.value;
      let Lsize = document.frm.Lsize.value;
      if(Ssize.length > 3 || Msize.length > 3 || Lsize.length > 3) {
          alert("수량은 3자리 숫자를 넘을 수 없습니다.");
          return false;
      }else if(!validatePwd){
        alert("관리자 비밀번호로 인증해주세요")
      }else{    
        document.frm.Ssize.value = document.frm.Ssize.value.padStart(3, 0);
        document.frm.Msize.value = document.frm.Msize.value.padStart(3, 0);
        document.frm.Lsize.value = document.frm.Lsize.value.padStart(3, 0);
        document.frm.submit();	
      }

  }