/**
 * 
 */
$(document).ready(function() {
    $("#leftside-navigation .sub-menu > a").click(function(e) {
        $("#leftside-navigation ul ul").slideUp(), $(this).next().is(":visible") || $(this).next().slideDown(),
        e.stopPropagation()
    })
});
$(function(){
    var duration = 300;
 
    var $sidebar = $('.sidebar');
    var $sidebarButton = $sidebar.find('button').on('click', function(){
        $sidebar.toggleClass('open');
        if($sidebar.hasClass('open')){
            $sidebar.stop(true).animate({left: '-70px'}, duration, 'easeOutBack');
            $sidebarButton.find('span').text('CLOSE');
        }else{
            $sidebar.stop(true).animate({left: '-270px'}, duration, 'easeInBack');
            $sidebarButton.find('span').text('OPEN');
        };
    });
});


function search(event) {
  if (event.keyCode === 13) {
	let searchValue =  event.target.value;
	var strReplace = searchValue.replace(/(\s*)/g,'');
    location.href = "http://localhost:9123/admin/prdMain?search=" + strReplace;
  }
}