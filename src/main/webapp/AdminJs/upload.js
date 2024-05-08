/*
Reference: http://jsfiddle.net/BB3JK/47/
*/

//메인사진 파일
(function hide(){
    $(this).hide();
})(jQuery);

$(document).ready( function(){
$('#fileArray').val( JSON.stringify( {} ) );

$('#mainFile').change(function(e) {
 var files = e.target.files;
 var obj = {};
     for (var i = 0; i <= files.length; i++) {
   
   var file = files[i];
   var reader = new FileReader();
   if(i==files.length){
     if('0' != files.length){
       setTimeout(function(){ updateArray(obj); }, 100); 
     }
     break;
   }
   
   reader.onload = (function(file, i) {
     return function(event){
    obj[i] =  event.target.result ;
       
       $('#fileList').prepend('<li data-id="'+file.lastModified+'"><div class="block"><img src="' + event.target.result + '" onerror="hide()" style="width: 150px; hight: 225px;" /> <div class="removeBtn">X</div></div> </div></li>');
     };
   })(file, i);
   
   reader.readAsDataURL(file);
 }

});


$('#fileList').on('click','.removeBtn', function(){
 var src = $(this).parents('.block').children('img').attr('src');
 $(this).parents('li').remove();
 removeItem(src);
});


function removeItem( items ){
 var obj = $('#fileArray').val();
 obj = JSON.parse(obj);
 
 var y = [];
 if (typeof items != "object") {
   $.each(obj ,function (chiave,valore) {
           if (valore == items) {
               delete obj[chiave];
               return false;
         }
   });
   
   $('#fileArray').val( JSON.stringify(obj) );
   return false;
 }

 $.each( obj, function(c,v){
   y.push(v);
 });
 $.each(items, function(c, v){
   if( $.inArray(v , y) != -1 ){
     return true;
   }
   y.push(v);
 });

 obj = $.extend({}, y);
 return obj;
}

function updateArray(items){
 var newArray = removeItem(items);
 $('#fileArray').val( JSON.stringify( newArray ) );
}

});

//설명사진 파일
(function hide(){
    $(this).hide();
})(jQuery);

$(document).ready( function(){
$('#fileArray2').val( JSON.stringify( {} ) );

$('#desFile').change(function(e) {
 var files = e.target.files;
 var obj = {};
     for (var i = 0; i <= files.length; i++) {
   
   var file = files[i];
   var reader = new FileReader();
   if(i==files.length){
     if('0' != files.length){
       setTimeout(function(){ updateArray(obj); }, 100); 
     }
     break;
   }
   
   reader.onload = (function(file, i) {
     return function(event){
    obj[i] =  event.target.result ;
       
       $('#fileList2').prepend('<li data-id="'+file.lastModified+'"><div class="block"><img src="' + event.target.result + '" onerror="hide()" style="width: 150px; hight: 225px;" /> <div class="removeBtn">X</div></div> </div></li>');
     };
   })(file, i);
   
   reader.readAsDataURL(file);
 }

});


$('#fileList2').on('click','.removeBtn', function(){
 var src = $(this).parents('.block').children('img').attr('src');
 $(this).parents('li').remove();
 removeItem(src);
});


function removeItem( items ){
 var obj = $('#fileArray2').val();
 obj = JSON.parse(obj);
 
 var y = [];
 if (typeof items != "object") {
   $.each(obj ,function (chiave,valore) {
           if (valore == items) {
               delete obj[chiave];
               return false;
         }
   });
   
   $('#fileArray2').val( JSON.stringify(obj) );
   return false;
 }

 $.each( obj, function(c,v){
   y.push(v);
 });
 $.each(items, function(c, v){
   if( $.inArray(v , y) != -1 ){
     return true;
   }
   y.push(v);
 });

 obj = $.extend({}, y);
 return obj;
}

function updateArray(items){
 var newArray = removeItem(items);
 $('#fileArray2').val( JSON.stringify( newArray ) );
}

});

document.getElementById('mainFile').addEventListener('change', function(e) {
  

      var files = e.target.files;
      for (var i = 0; i < files.length; i++) {
          var file = files[i];
          var img = document.createElement('img');
          img.src = URL.createObjectURL(file);
          img.width = 240;
          img.height = 120;
          img.onload = function() {
              URL.revokeObjectURL(this.src);
          }
      }
  });
  
  
  document.getElementById('desFile').addEventListener('change', function(e) {
   
      var files = e.target.files;
      for (var i = 0; i < files.length; i++) {
          var file = files[i];
          var img = document.createElement('img');
          img.src = URL.createObjectURL(file);
          img.width = 240;
          img.height = 120;
          img.onload = function() {
              URL.revokeObjectURL(this.src);
          }
      }
  });
  
  
  function validateForm() {
      let Ssize = document.frm.Ssize.value;
      let Msize = document.frm.Msize.value;
      let Lsize = document.frm.Lsize.value;
      let desElement = document.getElementById("options").value;
      if(Ssize.length > 3 || Msize.length > 3 || Lsize.length > 3) {
          alert("수량은 3자리 숫자를 넘을 수 없습니다.");
          return false;
      }else if(!validatePwd){
        alert("관리자 비밀번호로 인증해주세요")
      }else if(desElement == 0){
    	  alert("카테고리를 선택해주세요")
      }else{    
        document.frm.Ssize.value = document.frm.Ssize.value.padStart(3, 0);
        document.frm.Msize.value = document.frm.Msize.value.padStart(3, 0);
        document.frm.Lsize.value = document.frm.Lsize.value.padStart(3, 0);
        document.frm.submit();	
      }

  }
 
  function showOptions() {
      var cat_nm = document.getElementById("category").value;
      var desElement = document.getElementById("options");

		axios.get('/admin/selDes', {
			params : {
				cat_nm
			}
		  }).then(function (res) {
		  while (desElement.childElementCount > 1) {
			  desElement.removeChild(desElement.lastChild);
			}
			for(let i= 0; i < res.data.length; i++){
				const option = document.createElement("option");
				option.value = res.data[i].cat_id;
				option.innerHTML = res.data[i].cat_des;
				desElement.appendChild(option);
				
			}
			
			  
		  })



}

