     // enter logic
    $(document).keyup(function(event) {
    if ($(".input").is(":focus") && event.key == "Enter") {
        // Do work
<<<<<<< HEAD
        alert('enter pushed')
        variousInput()
         }
    });

	function ajaxTest()
=======
           variousInput()
         }
    });
	
	/*function ajaxTest()
>>>>>>> 510a04f928fc6712670c769ad2aad9a92e8f3119
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
	})}*/

	function variousInput()
	{
<<<<<<< HEAD
			var output = []
			var textBox = $('#input').val()
				$.ajax({
				    //data:JSON.stringify(textBox)
=======

			var output = []
			var textBox = $('#input').val()
			alert('in various ' + name + " textbox = " + textBox)
			if (textBox.toUpperCase().indexOf("MOVE") >= 0)
			{
				//Redirect(encodeURI("/the_alley/template_1?name=" + name))
				updateStatus()
			}
			else
			{
				$.ajax({
				    type: 'POST',
                    url: "/the_alley/various",
                    async: true,
                    beforeSend: function (xhr),
>>>>>>> 510a04f928fc6712670c769ad2aad9a92e8f3119
					contentType : 'application/json',
					url: encodeURI("/the_alley/various" + "?name=" + name + "&value=" + textBox),
					}).then(function(data)
						{
						output = data
<<<<<<< HEAD
						alert(output)
						$("#mapInfo").append(output['mapinfo'])
						$("#npcInfo").append(output['npcinfo'])
						//$("#npcInfo").append(JSON.stringify(output))
=======
						//alert(output)
						//var attack = output["attack"]
						//var defense = output["defense"]
						//alert(output)
						$("#output").replaceWith(name + " status:" + JSON.stringify(output))
>>>>>>> 510a04f928fc6712670c769ad2aad9a92e8f3119
						$( "#output" ).fadeIn( 4000, function() {})
						})
<<<<<<< HEAD
=======
			}
	}

	function updateStatus()
	{
			var output = {}
			//var name = $('#name').val()
			//var current_location = $('#location').val()
			//var textBox = $('#textBox').val()
    		$.ajax({
                type: 'POST',
                url: "/the_alley/updateRoom",
                async: true,
                beforeSend: function (xhr),
                contentType : 'application/json',
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
>>>>>>> 510a04f928fc6712670c769ad2aad9a92e8f3119
	}

	function createNewUser(url)
    {
			var name = $('#createUser').val()
			$.ajax({
			//data: JSON.stringify(jsonParams),
			url: encodeURI(url + "?name=" + name),
			}).then(function(data)
			{
				$('#output').append(data)
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
<<<<<<< HEAD
				//alert(output)
				$("#output").append(output + " <br />")
=======
				//alert(output)					
				$("#output").replaceWith(output + " <br />")
>>>>>>> 510a04f928fc6712670c769ad2aad9a92e8f3119
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