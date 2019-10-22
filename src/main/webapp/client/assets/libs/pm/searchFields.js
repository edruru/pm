//Function for injecting the csrf (Spring Security) token in every ajax request.
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});


function GetURLParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
}


/**
* Function to change the input field(s), depending on the criteria selected. ej. By User, Project, or Date.
*/
$("#type").change( function(){
    switch( $("#type").val() ){
        //If the value of the select element is 4, means it is a search by date.
        case "4":
            $("#queryBlock").html( "<input id='searchBeginDate' type='date' class='form-control' name='beginDate' id='beginDate' />" +
                                   " - to - " +
                                   "<input id='searchFinishDate' type='date' class='form-control' name='finishDate' id='finishDate'/>"
            );
            break;
        default:
            $("#queryBlock").html("<input id='searchText' type='text' class='form-control' name='text' id='text' />");
    }
    setSearchValues();
}).change();

function setSearchValues(){
    $("#searchBeginDate").val( GetURLParameter('beginDate') );
    $("#searchFinishDate").val( GetURLParameter('finishDate') );
    $("#searchText").val( GetURLParameter('text') );
}


var rowId, idRequirement, requirementSelected;
/**
*  Delete button functionality
*  When successful, after deleting the item from the database, fades out the whole row.
*/

$("a.delete").click(function(){
    rowId = $(this).attr("id");
    idRequirement = $(this).attr("name");
    var r = confirm("¿Are you sure, you want to delete this item?");
    if (r == true) {
        $.ajax({
              type: "DELETE",
              contentType: "application/json; charset=utf-8",
              url: "deleteRequirement/" + idRequirement,
              success: function(){ $("#"+rowId).fadeOut(); },
              error: function(){ alert("There was a problem in the item deletion...") }
          });
    }
});


/**
*  Update button functionality.
*  jQuery code to replace current row for inputs to edit.
*/

var StatusEnum = {
  NEW: 1,
  WORKING: 2,
  ONHOLD: 3,
  CHANGE: 4,
  CANCEL: 5,
  COMPLETE: 6
};

//Code to fetch the elements in the database via GET Request in Ajax, the result will be a JSON string.
var usersList, projectsList, priorityList;
$.getJSON("fetchUsersList", function( users ){ usersList = users; });
$.getJSON("fetchProjectsList", function( projects ){ projectsList = projects });
$.getJSON("fetchPriorityList", function( priorities ){ priorityList = priorities });


//Best practice, take the html item just once in page load, and use it on the page life cycle.
var updateFieldId = $("#updateId"),
    updateFieldCreationDate = $("#updateCreationDate"),
    updateFieldUserId = $("#updateUserId"),
    updateFieldProjectId = $("#updateProjectId"),
    updateFieldPriorityId = $("#updatePriorityId"),
    updateFieldText = $("#updateText"),
    updateFieldBeginDate = $("#updateBeginDate"),
    updateFieldFinishDate = $("#updateFinishDate"),
    updateFieldSave = $("#updateSave"),
    updateStatusBody = $("#statusBody"),
    updateActionsToImplement = $("#actionsToImplement");

var reqHistory;
$("a.details").click(function(){
    idRequirement = $(this).attr("id"),
    requirementSelected = idRequirement;
    rowId = $(this).attr("name");
    $.getJSON( "fetch/" + idRequirement , function( requirement ) {
        var usersDropDown = populateDropDown(ListEnum.USER, requirement),
            projectsDropDown = populateDropDown(ListEnum.PROJECT, requirement),
            priorityDropDown = populateDropDown(ListEnum.PRIORITY, requirement);

        updateFieldId.html( requirement.id );
        updateFieldCreationDate.html( requirement.creationDate );
        updateFieldUserId.html( usersDropDown );
        updateFieldProjectId.html( projectsDropDown );
        updateFieldPriorityId.html( priorityDropDown );
        updateFieldText.val( requirement.text );
        updateFieldBeginDate.val( requirement.beginDate );
        updateFieldFinishDate.val( requirement.finishDate );

        var statusBodyContent;
        for (var i=0; i < requirement.history.length; i++){
            statusBodyContent += "<tr>"
                              + "<td class='col-xs-1'>" + (i+1) + "</td>"
                              + "<td class='col-xs-5'>" + requirement.history[i].text + "</td>"
                              + "<td class='col-xs-2'>" + printStatus(requirement.history[i].statusId) + "</td>"
                              + "<td class='col-xs-4'>" + requirement.history[i].date + "</td>"
                              + "</tr>";
        }
        updateStatusBody.html(statusBodyContent);

        updateActionsToImplement.html( createActionsToImplement( requirement.history[ requirement.history.length -1 ].statusId ) );

        $("#myModalLabel").html("Requirement #" + requirement.id);
    });  //Fetch JSON
});

//Helper function to print in text form the status.
function printStatus( id ){
    switch(id){
        case 1: return "New";
        case 2: return "Working";
        case 3: return "On-Hold";
        case 4: return "Change";
        case 5: return "Cancel";
        case 6: return "Complete";
        default: return "Error";
    }
}

/**
* createActionsToImplement()
* Returns the buttons depending on the current status of the requirement.
*/
var workingBtn = "<a class='btn btn-primary' onClick='doAction(StatusEnum.WORKING)' >  Start Working <span class='glyphicon glyphicon-play'></span> </a> ",
    onholdBtn = "<a class='btn btn-warning' onClick='doAction(StatusEnum.ONHOLD)' > Set On-Hold <span class='glyphicon glyphicon-pause'></span> </a> ",
    cancelBtn = "<a class='btn btn-danger' onClick='doAction(StatusEnum.CANCEL)' > Cancel <span class='glyphicon glyphicon-remove'></span> </a>",
    completeBtn = "<a class='btn btn-success' onClick='doAction(StatusEnum.COMPLETE)' > Done <span class='glyphicon glyphicon-ok'></span> </a> ";
    defaultBtn = "<br/><br/><b><i>No actions left to implement<i></b>";
function createActionsToImplement( currentStatus){
    switch( currentStatus ){
        case 1 : return workingBtn + onholdBtn + cancelBtn;
        case 2 : return onholdBtn + completeBtn + cancelBtn;
        case 3 : return workingBtn + cancelBtn;
        case 4 : return workingBtn + onholdBtn + cancelBtn;
        default : return defaultBtn;
    }
}

//Sends the status change to the server.
function doAction( action ){
    var r = true;
    var msg = "Requirement Closed";
    if( action == StatusEnum.CANCEL ){
        r = confirm("You are about to cancel the request. Continue?");
        msg = "Requirement Cancelled";
    } else {
        msg = prompt("Please enter a message");
        if( !(msg.length > 0) ){
            msg = "Requirement status changed";
        }
    }
    var newObject = { id: requirementSelected, status: action, text: msg};

    if( r ){
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "changeStatus",
            data: JSON.stringify(newObject),
            dataType: "json",
            success: function(){
                $("#closeModal").click();
                refreshUpdatedRow();
            },
                error: function(){ alert("There was a problem updating the item..."); }
            });

            $("a.doUpdate").click();
    }

}

/**
*  Update button functionality.
*  jQuery code to take the values in the modal, and send them to the server vía PUT.
*/

$("a.doUpdate").click( function(){
    var _id     = idRequirement,
        _userId = updateFieldUserId.val(),
        _text   = updateFieldText.val(),
        _projectId  = updateFieldProjectId.val(),
        _priorityId = updateFieldPriorityId.val(),
        _beginDate  = updateFieldBeginDate.val(),
        _finishDate = updateFieldFinishDate.val();

        var newObject = { id: _id, userId: _userId, text: _text, projectId: _projectId,
                    priorityId: _priorityId, beginDate: _beginDate, finishDate: _finishDate};

      $.ajax({
          type: "PUT",
          contentType: "application/json; charset=utf-8",
          url: "updateRequirement",
          data: JSON.stringify(newObject),
          dataType: "json",
          success: function(){
            refreshUpdatedRow();
          },
          error: function(){ alert("There was a problem updating the item..."); }
      });

});


//Helper function to populate with html code the drop-downs based on the type (ListEnum) criteria.
var ListEnum = {
  USER: "user",
  PROJECT: "project",
  PRIORITY: "priority"
};

/**
* @param type ListEnum, declares the type of the dropdown to create.
* @param requirement The element fetched from the database.
* @return A dropdown (HTML) filled with the correct values depending on the type given.
*/
function populateDropDown(type,requirement){
    var dropDown, itemList, idSelected;

    switch(type){
        case ListEnum.USER:
            itemList = usersList;
            idSelected = requirement.userId;
            dropDown = "<select class='form-control' name='userId" + requirement.id + "' id='userId" + requirement.id + "'>";
            break;
        case ListEnum.PROJECT:
            itemList = projectsList;
            idSelected = requirement.projectId;
            dropDown = "<select class='form-control' name='projectId" + requirement.id + "' id='projectId" + requirement.id + "'>";
            break;
        case ListEnum.PRIORITY:
            itemList = priorityList;
            idSelected = requirement.priorityId;
            dropDown = "<select class='form-control' name='priorityId" + requirement.id + "' id='priorityId" + requirement.id + "'>";
            break;
        default:
            dropDown = "<select>";
    }



    if( type === ListEnum.USER || type === ListEnum.PROJECT ){
        dropDown += "<option value='0'>(Unassigned)</option>";
        for ( var i=0; i < itemList.length; i++ ){
            if( idSelected == (i+1) ){
                dropDown += "<option value=" + itemList[i].id + " selected>" + itemList[i].name + "</option>";
            }
            else {
                dropDown += "<option value=" + itemList[i].id + ">" + itemList[i].name + "</option>";
            }
        }
    } else {
        for ( var i=0; i < itemList.length; i++ ){
            if( idSelected == (i+1) ) {
                dropDown += "<option value=" + itemList[i].id + " selected>" + itemList[i].type + "</option>";
            }
            else {
                dropDown += "<option value=" + itemList[i].id + ">" + itemList[i].type + "</option>";
            }
        }
    }

    dropDown += "</select>";

    return dropDown;
}


/**
* Function to refresh only the updated row.
*/
function refreshUpdatedRow(){
    $.getJSON( "fetchDetail/" + idRequirement , function( requirement ) {
      var refreshRow = "#"+rowId;
      $(refreshRow + " td[name=id]").html( requirement.id );
      $(refreshRow + " td[name=user]").html( ((typeof requirement.userAssigned === 'undefined') ? "N/A" : requirement.userAssigned) );
      $(refreshRow + " td[name=text]").html( requirement.text );
      //$(refreshRow + " td[name=project]").html( ((typeof requirement.projectAssigned === 'undefined') ? "N/A" : requirement.projectAssigned) );
      $(refreshRow + " td[name=priority]").html( requirement.priorityAssigned );
      //$(refreshRow + " td[name=creationDate]").html( requirement.creationDate );
      $(refreshRow + " td[name=beginDate]").html( requirement.beginDate );
      $(refreshRow + " td[name=finishDate]").html( requirement.finishDate );
      $(refreshRow + " td[name=status]").html( requirement.status );

 });  //Fetch JSON
}




$("#generateCsvReport").click( function(){
    var _data;
    if ( GetURLParameter('type') == 4 ){
        _data = { "beginDate" : GetURLParameter('beginDate'), "finishDate" : GetURLParameter('finishDate'), "text" : "", "criteria" : GetURLParameter('type')  };
    } else {
        _data = { "beginDate" : "", "finishDate" : "", "text" : GetURLParameter('text'), "criteria" : GetURLParameter('type') };
    }

    $.ajax({
        type: "GET",
        url: "fetchSearchedItems",
        data: _data
    })
    .done(function( requirement ) {
        //alert( "" );
        JSONToCSVConverter(requirement, "Search Report", true);
    });

});

/**
* Function to export data to csv file.
*/
function JSONToCSVConverter(JSONData, ReportTitle, ShowLabel) {
    //If JSONData is not an object then JSON.parse will parse the JSON string in an Object
    var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;

    var CSV = '';
    //Set Report title in first row or line

    CSV += ReportTitle + '\r\n\n';

    //This condition will generate the Label/Header
    if (ShowLabel) {
        var row = "";

        //This loop will extract the label from 1st index of on array
        for (var index in arrData[0]) {

            //Now convert each value to string and comma-seprated
            row += index + ',';
        }

        row = row.slice(0, -1);

        //append Label row with line break
        CSV += row + '\r\n';
    }

    //1st loop is to extract each row
    for (var i = 0; i < arrData.length; i++) {
        var row = "";

        //2nd loop will extract each column and convert it in string comma-seprated
        for (var index in arrData[i]) {
            row += '"' + arrData[i][index] + '",';
        }

        row.slice(0, row.length - 1);

        //add a line break after each row
        CSV += row + '\r\n';
    }

    if (CSV == '') {
        alert("Invalid data");
        return;
    }

    //Generate a file name
    var fileName = "MyReport_";
    //this will remove the blank-spaces from the title and replace it with an underscore
    fileName += ReportTitle.replace(/ /g,"_");

    //Initialize file format you want csv or xls
    var uri = 'data:text/csv;charset=utf-8,' + escape(CSV);

    // Now the little tricky part.
    // you can use either>> window.open(uri);
    // but this will not work in some browsers
    // or you will not get the correct file extension

    //this trick will generate a temp <a /> tag
    var link = document.createElement("a");
    link.href = uri;

    //set the visibility hidden so it will not effect on your web-layout
    link.style = "visibility:hidden";
    link.download = fileName + ".csv";

    //this part will append the anchor tag and remove it after automatic click
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}