	
new DataTable('#example');
$(".menu-Bar").click(function () {
    $(this).toggleClass("open");
    $(".menuWrap").toggleClass("open");
    $("body").toggleClass("ovr-hiddn");
});