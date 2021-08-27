

    // enter logic
    $(document).keyup(function(event) {
    if ($(".input").is(":focus") && event.key == "Enter") {
        // Do work
         }
    });
	
	function ajaxTest()
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
        url: "/the_alley/various",
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
	})}

	function variousInput()
	{
			alert('in various' + name)
			var output = []
			var textBox = $('#input').val()
			if (textBox.toUpperCase().indexOf("MOVE") >= 0)
			{
				//Redirect(encodeURI("/the_alley/template_1?name=" + name))
				updateStatus()
			}
			else
			{
				$.ajax({
					contentType : 'application/json',
					data:JSON.stringify(textBox)})
					.then(function(data) 
						{			
						output = data
						//alert(output)
						//var attack = output["attack"]
						//var defense = output["defense"]
						alert(output)
						$("#output").append(name + " status:" + JSON.stringify(output))
						$( "#output" ).fadeIn( 4000, function() {})
					//location.reload()							
						})
			}
	}

	function updateStatus()
	{
			var output = {}
			//var name = $('#name').val()
			//var current_location = $('#location').val()
			//var textBox = $('#textBox').val()
    		$.ajax({
				data:JSON.stringify(textBox)})
				.then(function(data)
				{
					output = data
					var msg = output["msg"]
					var users = output["users"]
					var npcs = output["npcs"]						
					//$("#output").append(textBox + "<br />")
					//$("#output").append(msg + "<br />")
					$("#output").replaceWith(msg)
					$("#users").replaceWith("its a timo")
					$("#npcs").replaceWith(npcs)
					$( "#output" ).fadeIn( 300, function() {})
				})
	}

	function createNewUser(url)
    {
			var name = $('#createUser').val()
			$.ajax({
			//data: JSON.stringify(jsonParams),
			url: encodeURI(url + "?name=" + name),
			}).then(function(data)
			{
				//$('#output').val().(data))
				Redirect(encodeURI("/the_alley/home?name=" + name))
			})
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


 	//updates on a scheduled to update statuses
	/*window.setInternal(function()
	{
	  updateStatus()

	}, 50000)
	setInterval(function(){ updateStatus }, 3000)*/

	/*function initItem(url)
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
		/*function updateUsers(url)
    	{

    			initMap()
    			var textBox = $('#textBox').val()
    			var Alley = $('#Alley').val()
    			$.ajax({
    				url: encodeURI(url),
    				}).then(function(data)
    				{
    					$('#output').val().(data))
    				})
    	}

    	/*function update()
    	{
    		$.get("template__1", function(data) {
    		$("#some_div").html(data)
    		window.setTimeout(update, 10000)
    	})
    	}
    		/*$(function()
    	{
    		var input = document.getElementById("textBox")
    		input.addEventListener("keyup", function(event) {
     		 event.preventDefault()
      		if (event.keyCode === 13) {
        		document.getElementById("template").click()
     		 }
    		})

    		var vTimeOut
    		vTimeOut= setTimeout(updateStatus, 600)

    	})*/

    	/*function templateSubmit(url)
    	{
    			//initMap()
    			var output = []
    			var name = $('#name').val()
    			var textBox = $('#textBox').val()
    			$.ajax({
    				url: encodeURI(url),
    				}).then(function(data)
    				{
    					output = data
    					var resp = output["output"]
    					$("#output").append(resp + " ")
    					$( "#output" ).fadeIn( 4000, function() {})
    					if(resp == 'move')
    					{
    						alert('FRED')
    						//Redirect(encodeURI("/the_alley/template_1?name=" + name))
    					}
    				})
    	}*/
    	/*function createNewUser(url)
        {
    		$(document).ready(function()
    		{
    			var name = $('#createUser').val()
    			var jsonParams =
    			{
    					//indicator: , variable
    					"name": name,
    			}
    				$.ajax({
    					type : "POST",
    					url : url,
    					processData : true,
    					data: JSON.stringify(jsonParams),
    					contentType : 'application/json',
    					success : function(response)
    					{
    						alert(response)
    						_Redirect(encodeURI("/home?name=" + name))
    					}
    					error : function(xhr, status, error)
    					{
    						alert(xhr.responseText)
    						$("#alert-message-container").html("An unknown error occured when trying to create a new user!  Please try again later.")
    						$("#alert-message-display").dialog("open")
    					}
    				})
    		})
    	}*/