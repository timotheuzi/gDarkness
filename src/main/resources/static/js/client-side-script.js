     // enter logic
    $(document).keyup(function(event) {
    if ($(".input").is(":focus") && event.key == "Enter") {
        variousInput()
         }
    });
    //page load
    $(document).ready(function(event) {
         updateRoom()
        });

	/*function ajaxTest()
	{
		output=[]
		var tempParams =
		{
			"value": $("#textBox").val(),
			"name": name,
			"location": current_location
		}

		$.ajax({
        type: 'GET',
        url: "/the_alley/updateRoom",
        data: JSON.stringify(tempParams),
        async: false,
        beforeSend: function (xhr)
        {
        	if (xhr && xhr.overrideMimeType) {
        		xhr.overrideMimeType('application/jsoncharset=utf-8')
        	}
		},
       	dataType: 'json',
       	success: function (data)
		{
			output = data
    	   	alert(output['value'])
    	   	alert(output['msg'])
    	   	alert(output['location'])

       }
	})}*/

	function variousInput()
	{
			var output = []
			var jsonParams =
            {
                "name": name,
                "value": $('#input').val()
            };
			//var textBox = $('#input').val()
				$.ajax({
				    type : "POST",
                    processData : true,
                    data: JSON.stringify(jsonParams),
					contentType : 'application/json',
					url: encodeURI("/the_alley/various"),
			        }).then(function(data)
                    {
                      //alert(data);
                      //output = data;
                      $("#output").append(output);
                      $("#output").append(data.code + " <br />");
                      $("#output").append(data.message + " <br />");
                      //var resp = output["output"];
                      //alert(output);
					});
					updateStatus();
	}

	/*function createNewUser(url)
    {
			var name = $('#createUser').val()
			$.ajax({
			type: 'POST',
			//data: JSON.stringify(jsonParams),
			url: encodeURI(url + "?name=" + name),
			}).then(function(data)
			{
				//$('#output').append(data)
				Redirect(encodeURI("/the_alley/home?name=" + name))
			})
	}*/
	function createNewUser()
    {
    	        var name = $('#createUser').val();
    	        var json = [];
    	        var output = []
    	        var jsonParams =
    			{
    				//indicator: , variable
    				"name": name,
    				"value": "wtf"
    			};
    			$.ajax({
    					type : "POST",
    					url : "/the_alley/createNewUser",
    					data: JSON.stringify(jsonParams),
    					contentType : 'application/json',
    					}).then(function(data)
                        {
                        	//output = data;
                        	alert(data.message);
                        	//$("#output").append(data.code + " <br />");
                        	//$("#output").append(data.message + " <br />");
                        	//$( "#output" ).fadeIn( 5000, function() {});
                        	Redirect(encodeURI("/the_alley/home?name=" + name))
                        });
                        //Redirect(encodeURI("/the_alley/home?name=" + name))
    }
    /*function handleResponse(data) {
      alert(data);
      $("#output").append(data + " <br />")
    }*/

	function init(url)
    {
            //alert('fred');
			var output = []
			$.ajax({
			url: encodeURI(url)
			}).then(function(data)
			{
				output = data;
				//alert(output.message)
				$("#output").append(data.code + " <br />");
				$("#output").append(data.message + " <br />");
				$( "#output" ).fadeIn( 5000, function() {});
			});
	}

	function initMap(url)
    {
			var output = []
			$.ajax({
			url: encodeURI(url),
			}).then(function(data)
			{
				output = data
				alert(output)
				$("#output").append(output + " ")
				$( "#output" ).fadeIn( 4000, function() {})
			})
	}
	function Redirect(url)
	{
		var ua = navigator.userAgent.toLowerCase(), verOffset = ua.indexOf('msie') !== -1, version = parseInt(ua.substr(4, 2), 10)
		// IE8 and lower fix
		if (navigator.userAgent.match(/MSIE\s(?!9.0)/))
		{
			// IE8 and lower
			// if (verOffset && version < 9) {
			var link = document.createElement('a')
			link.href = url
			document.body.appendChild(link)
			link.click()
		}
		// All other browsers
		else
		{
			window.location.href = url
		}
	}
	function updateStatus()
    	{
    			var jsonParams =
                 {
                    //indicator: , variable
                    "name": name,
                    "value": "wtf"
                 };
    			var output = {};
    			//var name = $('#name').val();
    			//var current_location = $('#location').val();
    			var textBox = $('#textBox').val();
    			var url = "/darkness/updateRoom"
    			$.ajax({
    				url: encodeURI(url),
    				}).then(function(data)
    				{
    					output = data;
    					var msg = output["msg"];
    					var users = output["users"];
    					var npcs = output["npcs"];
    					//$("#output").append(textBox + "<br />");
    					//$("#output").append(msg + "<br />");
    					$("#output").replace(description + "<br />");
    					$("#output").append(mapName + "<br />");
    					$("#updateSpace").append(users + "<br />");
    					$("#updateSpace").append(npcs + "<br />");

    					$( "#updateSpace" ).fadeIn( 3000, function() {});
    				});
    	}