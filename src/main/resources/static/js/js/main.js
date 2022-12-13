$(document).ready(function () {
    console.log("setting up datatable")
    $('#example').DataTable({
        select:true
    });
});

search = document.getElementById("search");
els = document.querySelectorAll(".el");

search.addEventListener("keyup", function() {

    Array.prototype.forEach.call(els, function(el) {
        if (el.textContent.trim().indexOf(search.value) > -1)
            el.style.display = 'block';
        else el.style.display = 'none';
    });

});