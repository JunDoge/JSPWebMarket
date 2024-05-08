/**
 * 
 */
var star1 = document.querySelector('.starj1');
var star2 = document.querySelector('.starj2');
var star3 = document.querySelector('.starj3');
var star4 = document.querySelector('.starj4');
var star5 = document.querySelector('.starj5');
var score = ""

//making the star system work

star1.addEventListener('click', function(){
  if(star1.classList.contains('far')){
    star1.classList.remove('far')
    star1.classList.add('fas')
  }else if(star2.classList.contains('far') && star3.classList.contains('far') && star4.classList.contains('far') && star5.classList.contains('far')){
    star1.classList.remove('fas')
    star1.classList.add('far')
  }
  if(star2.classList.contains('fas')){
    star2.classList.remove('fas')
    star2.classList.add('far')
  }
  if(star3.classList.contains('fas')){
    star3.classList.remove('fas')
    star3.classList.add('far')
  }
  if(star4.classList.contains('fas')){
    star4.classList.remove('fas')
    star4.classList.add('far')
  }
  if(star5.classList.contains('fas')){
    star5.classList.remove('fas')
    star5.classList.add('far')
  }
  score=1;
})

star2.addEventListener('click', function(){
  if(star2.classList.contains('far')){
    star2.classList.remove('far')
    star2.classList.add('fas')
    star1.classList.remove('far')
    star1.classList.add('fas')
  }
  if(star3.classList.contains('fas')){
    star3.classList.remove('fas')
    star3.classList.add('far')
  }
  if(star4.classList.contains('fas')){
    star4.classList.remove('fas')
    star4.classList.add('far')
  }
  if(star5.classList.contains('fas')){
    star5.classList.remove('fas')
    star5.classList.add('far')
  }
  score=2;
})

star3.addEventListener('click', function(){
  if(star3.classList.contains('far')){
    star3.classList.remove('far')
    star3.classList.add('fas')
    star2.classList.remove('far')
    star2.classList.add('fas')
    star1.classList.remove('far')
    star1.classList.add('fas')
  }
  if(star4.classList.contains('fas')){
    star4.classList.remove('fas')
    star4.classList.add('far')
  }
  if(star5.classList.contains('fas')){
    star5.classList.remove('fas')
    star5.classList.add('far')
  }
  score=3;
})

star4.addEventListener('click', function(){
  if(star4.classList.contains('far')){
    star4.classList.remove('far')
    star4.classList.add('fas')
    star3.classList.remove('far')
    star3.classList.add('fas')
    star2.classList.remove('far')
    star2.classList.add('fas')
    star1.classList.remove('far')
    star1.classList.add('fas')
  }
  if(star5.classList.contains('fas')){
    star5.classList.remove('fas')
    star5.classList.add('far')
  }
  score=4;
})

star5.addEventListener('click', function(){
  if(star5.classList.contains('far')){
    star5.classList.remove('far')
    star5.classList.add('fas')
    star4.classList.remove('far')
    star4.classList.add('fas')
    star3.classList.remove('far')
    star3.classList.add('fas')
    star2.classList.remove('far')
    star2.classList.add('fas')
    star1.classList.remove('far')
    star1.classList.add('fas')
  }else{
    star5.classList.remove('fas')
    star5.classList.add('far')
  }
  score=5;
})

