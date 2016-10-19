var pageLoad = function() {};
$(document).ready(function() {
	try {
		pageLoad();
	} catch (e) {
		console.log(e);
	}
	
});
function logout() {
	/*
	$.post(url, d, function(data) {
		console.log(data);
	});*/
	$('#f').submit();
}

//Notification
//http://www.cnblogs.com/lxshanye/p/3560188.html
//http://ju.outofmemory.cn/entry/144855
//http://www.2cto.com/kf/201405/303012.html
//https://github.com/azproduction/jquery.notification
function notify(title, options) {
	if (!("Notification" in window)) {
		alert("Brower not supported!");
	} else if (Notification.permission === "granted") {
		var notification = new Notification(title, options);
	} else if (Notification.permission !== "denied") {
		Notification.requestPermission(function (permission) {
			if (permission === "granted")
				var notification = new Notification(title, options);
			else
				alert("user " + permission);
		});
	} else {
		alert("user " + Notification.permission);
		return false;
	}
}

//sse
//http://javascript.ruanyifeng.com/htmlapi/eventsource.html
//http://www.ibm.com/developerworks/cn/web/1307_chengfu_serversentevent/
//https://github.com/byjg/jquery-sse
//https://github.com/jetty-project/jetty-eventsource-servlet
function listener() {
	/*
	var es = new EventSource('events');
	es.onmessage = function(e) {
	    console.log(e.data);
	};

	es.addEventListener('myevent', function(e) {
	    console.log(e.data);
	});
	*/
	var eventSource = new EventSource("/p1/sse/user");
	eventSource.onmessage = function(event)
	{
	    window.console.info("Server-Sent Event: " + event.data);
	};
}