     // enter logic
    /*$(document).keyup(function(event) {
    if ($(".input").is(":focus") && event.key == "Enter") {
        // Do work
        variousInput()
         }
    });*/

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
					url: encodeURI("/various"),
					success : function(response)
                    {
                        alert(response);
                        //Redirect(encodeURI("/home?name=" + name));
						output = data
						alert(output)
						$("#mapInfo").append(output['mapinfo'])
						$("#npcInfo").append(output['npcinfo'])
						//$("#npcInfo").append(JSON.stringify(output))
						$( "#output" ).fadeIn( 4000, function() {})
					}
				});
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
	function createNewUser(url)
    {
    	        var name = $('#createUser').val();
    	        var jsonParams =
    			{
    				//indicator: , variable
    				"name": name,
    				"value": "hahaha"
    			};
    			$.ajax({
    					type : "POST",
    					url : url,
    					processData : true,
    					data: jsonParams,
    					contentType : 'application/json',
    					success : function(response)
    					{
    						alert(response);
    						Redirect(encodeURI("/home?name=" + name));
    					}
    					/*error : function(xhr, status, error)
    					{
    						alert(xhr.responseText);
    						$("#alert-message-container").html("An unknown error occured when trying to create a new user!  Please try again later.");
    						$("#alert-message-display").dialog("open");
    					}*/
    			});
    }
	function init(url)
    {
			var output = []
			$.ajax({
			url: encodeURI(url),
			}).then(function(data)
			{
				output = data
				//alert(output)
				$("#output").append(output + " <br />")
				$( "#output" ).fadeIn( 5000, function() {})

			})
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