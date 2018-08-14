define(['eventsource'], function(eventsource) {

	var userPath = "/p1/sse/user"

	//Notification
	//http://www.cnblogs.com/lxshanye/p/3560188.html
	//http://ju.outofmemory.cn/entry/144855
	//http://www.2cto.com/kf/201405/303012.html
	//https://github.com/azproduction/jquery.notification
	function notify(title, options) {
		console.info("nofity");
		if (!("Notification" in window)) {
			console.info("Brower not supported!");
		} else if (Notification.permission === "granted") {
			var notification = new Notification(title, options);
		} else if (Notification.permission !== "denied") {
			Notification.requestPermission(function (permission) {
				if (permission === "granted") {
					var notification = new Notification(title, options);
				} else {
					console.info("user " + permission);
					alert(options.body);
				}
			});
		} else {
			console.info("user " + Notification.permission);
			alert(options.body);
			return false;
		}
	}

	//sse
	//http://javascript.ruanyifeng.com/htmlapi/eventsource.html
	//http://www.ibm.com/developerworks/cn/web/1307_chengfu_serversentevent/
	//https://github.com/byjg/jquery-sse
	//https://github.com/jetty-project/jetty-eventsource-servlet
	function listen(path) {
		/*
		var es = new EventSource('events');
		es.onmessage = function(e) {
		    console.log(e.data);
		};
	
		es.addEventListener('myevent', function(e) {
		    console.log(e.data);
		});
		*/
		
		var p = path ? path : userPath,
			eventSource = new EventSource(p);
		eventSource.onmessage = function(event)
		{
		    console.info("Server-Sent Event: " + event.data);
		    notify(event.lastEventId, { body: event.data });
		};
		eventSource.onerror = function(event)
		{
			console.info("error");
			eventSource.close();
		};
	}

	return {
		notify:   notify,
		listen:   listen
	};
});