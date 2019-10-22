/**
*  Delete button functionality
*  When successful, after deleting the item form the database, fades out the whole row.
*/
$("a.delete").click(function(){
    var rowId = $(this).attr("id");
    var r = confirm("¿Are you sure, you want to delete this project?");
    if (r == true) {
        var idUser = $(this).attr("name");
        $.ajax({
              type: "DELETE",
              url: "delete/" + idUser,
              success: function(){ $("#"+rowId).fadeOut(); },
              error: function(){ alert("There was a problem in the action deletion...") }
        });
    }
});