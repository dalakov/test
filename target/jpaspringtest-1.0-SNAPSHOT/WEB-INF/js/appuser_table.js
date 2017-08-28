$(document).ready( function () {
     var userurl = /*[[@{/appusers}]]*/ 'appusers';
	 var table = $('#appuserTable').DataTable({
			"sAjaxSource": userurl,
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"columns": [
			      { "data": "id"},
		          { "data": "username" },
				  { "data": "password" },
				  { "data": "enabled" }
			],
         	"paging" : true,
         	"pageLength" : 2,
         	"ordering" : true,
         	select: true
	 });


});