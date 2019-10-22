$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

/**
*  Delete button functionality
*  When successful, after deleting the item form the database, fades out the whole row.
*/
$("a.delete").click(function(){
    var rowId = $(this).attr("id");
    var r = confirm("¿Are you sure, you want to delete this item?");
    if (r == true) {
        var idRecord = $(this).attr("name");
        $.ajax({
              type: "DELETE",
              url: "delete/" + idRecord,
              success: function(){ $("#"+rowId).fadeOut(); },
              error: function(){ alert("There was a problem in the item deletion...") }
          });
    }
});


