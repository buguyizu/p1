 /** 
  *格式化数字显示方式
  *用法
  * formatNumber(12345.999,'#,###0.00'); 
  * formatNumber(12345.999,'#,###0.##'); 
  * formatNumber(123,'000000'); 
  * @param num 
  * @param pattern 
  */  
 function formatNumber(num,pattern){  
	var strarr = num?num.toString().split('.'):['0'];  
	var fmtarr = pattern?pattern.split('.'):[''];  
   	var retstr='';  
   
   	//整数部分
	var str = strarr[0];  
	var fmt = fmtarr[0];  
	var i = str.length-1;    
	var comma = false;  
	for(var f=fmt.length-1;f>=0;f--){
		switch(fmt.substr(f,1)){  
       	case '#':  
         if(i>=0 ) retstr = str.substr(i--,1) + retstr;  
         break;  
       	case '0':  
         if(i>=0) retstr = str.substr(i--,1) + retstr;  
         else retstr = '0' + retstr;  
         break;  
       	case ',':  
         comma = true;  
         retstr=','+retstr;  
         break;  
		}  
	}  
   	if(i>=0){  
     	if(comma){  
	       	var l = str.length;  
	       	for(;i>=0;i--){  
				retstr = str.substr(i,1) + retstr;  
				if(i>0 && ((l-i)%4)==0) retstr = ',' + retstr;   
	       	}  
		}else retstr = str.substr(0,i+1) + retstr;  
	}  
   
	retstr = retstr+'.'; 
   	//处理小数部分
   	str=strarr.length>1?strarr[1]:'';  
   	fmt=fmtarr.length>1?fmtarr[1]:'';  
   	i=0;  
   	for(var f=0;f<fmt.length;f++){  
		switch(fmt.substr(f,1)){  
		case '#':  
			if(i<str.length) retstr+=str.substr(i++,1);  
         	break;  
		case '0':  
			if(i<str.length) retstr+= str.substr(i++,1);  
         	else retstr+='0';  
		break;  
		}  
	}  
   	return retstr.replace(/^,+/,'').replace(/\.$/,'');  
 }
 
 function formatDecimal(decimal, pattern) {
	 if (!pattern) {
		 pattern = '#,###0.00';
	 }
	 if (!decimal) {
		 return '';
	 } else {
		 return formatNumber(new Number(decimal.toString()), pattern);
	 }
 }
 
 function isNumber(value){
	return parseFloat(value) == value;
 }
 
 function addComma(nStr){  
	if(nStr==null || nStr.length == 0){
   		return nStr;
   	}
	x = nStr.split('.');  
	x1 = x[0];  
	x2 = x.length > 1 ? '.' + x[1] : '';  
	var rgx = /(\d+)(\d{4})/;  
	while (rgx.test(x1)) {  
	   x1 = x1.replace(rgx, '$1' + ',' + '$2');  
	}  
	return x1 + x2;  
 } 
 
 
 function delComma(nStr) {
	if(nStr != null) {
		return nStr.toString().replace(/,/g, '');
    }
    return "";
 }

 //取日期，0当前;-1上月底;1带时间
function getDate(f) {
 	var dt = new Date();
 	
	if (f == -1) {
 		//上月底
		dt.setDate(1);
		var t = dt.getTime() - 24 * 3600 * 1000;
		dt.setTime(t);
 	}
 	var y = dt.getFullYear(), m = dt.getMonth() + 1, d = dt.getDate(),
 		h = dt.getHours(), mi = dt.getMinutes(), s = dt.getSeconds();
 	if (m < 10) {
 		m = '0' + m.toString();
 	}
 	if (d < 10) {
 		d = '0' + d.toString();
 	}
 	if (h < 10) {
 		h = '0' + h.toString();
 	}
 	if (mi < 10) {
 		mi = '0' + mi.toString();
 	}
 	if (s < 10) {
 		s = '0' + s.toString();
 	}
	return y + "-" + m + "-" + d + ((f == 1) ? (' ' + h + ':' + mi + ':' + s) : '');
}

/**
 画面IME设定
*/
function imeSwitch(imeMode){
 $('.ime-ctrl').each(function(idx){
     $(this).css("ime-mode",imeMode);
 })
}
 
/**
 datepicker设定
*/
function setDatePicker(eleStr) {
	if (!eleStr) {
		eleStr = ".datepicker";
	}
	if ($(eleStr).length > 0) {
		$(eleStr)
			.datepicker()
			.prop('readonly', true);
	}
}

$(function() {
	try {
		showWait();
	    //全体提交等待设定
	    $(window).load(unblock).load(setTitle).on('beforeunload', showWait);
	    $('form').on('submit', showWait);
	    $(':text').on('mouseover', function() {
	    	//某些页面不需要
	    	var loc = location.href;
	    	if (loc.indexOf("u4010") == -1
	    		&& loc.indexOf("u4020") == -1
	    		&& loc.indexOf("u4060") == -1) {
		    	$(this).select().focus();
	    	}
	    }).on('blur', function() {
	    	var v = $(this).val();
	    	//禁止输入
	    	v = v.replace(/\\|\/|\?|\+|\*|\=|\-|\%|\'|\"|\^|\$|\&|\||\#|\<|\>|\(|\)|\[|\]|\{|\}/g, '');
	    	$(this).val($.trim(v));
	    });
	    var f = $('fieldset:first');
	    if (f.height() > 60 && $('fieldset').length > 1 && f.find('table').length > 0) {
	    	var l = f.find('legend');
	    	l.html(l.text() + ' <a href="#" id="udUrl" title="点击切换[展开][折叠]状态。" onclick="return toggleFieldset(this);">⇕</a>')
	    }
	    // title="点击切换侧栏宽度。"
	    //$('.main-side ul').append('<li class="bottom-submenu" onclick="return shiftMenu();"><span>⇔</span></li>');
	    $.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	    $.datepicker.setDefaults({
			 	showButtonPanel: true,
			 	showOn: "both",
			 	showAnim: "fadeIn",
			  	showOtherMonths: true,
			  	showWeek: true,
			  	showMonthAfterYear: true,
			  	monthNamesShort: [ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" ],
			  	selectOtherMonths: true,
			   	changeYear: true,
			   	changeMonth: true,
			   	buttonText:"...",
			  	yearRange: "2014:2050",
			  	closeText: "清除"
			});
		setDatePicker();
	    $(":text[readonly]").off('mouseover').off('blur')
		.keydown( function(e) {
			if (e.keyCode == 8)
				return false;
		});;
		$("input[type=button], input[type=submit], button").button();
	    //ime设定
	    imeSwitch('active');
	} catch (err) {
		console.error(err);
	}
});

function goTop() {
	$(window).scrollTop(0);
	return false;
}

function toggleFieldset(ele) {
	$(ele).parents('fieldset').find('table').toggle();
	return false;
}

function waitSubmit(form) {
	if (!form) {
		form = $("form");
	}
	form.off('submit');
	$(window).off('beforeunload');
	showWaitInfo();
    $(document).bind("contextmenu",function(e){ 
		return false; 
	});
	form.submit();
	return false;
}

//提交时间较长时
function showWaitInfo() {
	$.blockUI({
		css: {
			border: 'none',
			backgroundColor: 'transparent',
			padding: '15px',
			width: '300px',
			fontSize: '20pt',
			color: 'white'
		},
		message: '执行中，请耐心等待...'
	});
}

//页面跳转，提交时
function showWait() {
	$.blockUI({
		overlayCSS: {
			backgroundColor: 'white',
			opacity: 0.2
		},
		css: {
			border: 'none',
			backgroundColor: 'transparent'
		},
		message: ''
	});
	$(document).bind("contextmenu",function(e){ 
		return false; 
	});
}

//弹出子窗口时
function block() {
    $.blockUI({
			css: {
				width: '150px',
				padding: '10px 0px'
			},
			message: '请先关闭弹出窗口...'
		});
    $(document).bind("contextmenu",function(e){ 
		return false; 
	});
}
//解除窗口锁定
function unblock() {
    $.unblockUI();
    $(document).unbind("contextmenu");
}

//弹窗对象
var openWin;
//票据弹窗调用函数
function showBillPopup(url){
    openWin = window.open(url,'u0010','scrollbars=no,top='+(window.screen.availHeight-410)/2+',left='+(window.screen.availWidth-480)/2+',width=480,height=365,statusbar=0,resizable=no,menubar=no,toolbar=no');
    return false;
}
//结算弹窗调用函数
function showPayPopup(url){
    openWin = window.open(url,'u0020','scrollbars=yes,top='+(window.screen.availHeight-530)/2+',left='+(window.screen.availWidth-900)/2+',width=900,height=500,statusbar=0,resizable=no,menubar=no,toolbar=no');
    return false;
}
//日明细弹窗调用函数
function showDayDetailPopup(url){
    openWin = window.open(url,'u0030','scrollbars=yes,top='+(window.screen.availHeight-530)/2+',left='+(window.screen.availWidth-900)/2+',width=900,height=500,statusbar=0,resizable=no,menubar=no,toolbar=no');
    return false;
}
//日汇总弹窗调用函数
function showWorkDayPopup(url){
    openWin = window.open(url,'u0040','scrollbars=yes,top='+(window.screen.availHeight-530)/2+',left='+(window.screen.availWidth-900)/2+',width=900,height=500,statusbar=0,resizable=no,menubar=no,toolbar=no');
    return false;
}
//开结算查询弹窗调用函数
function showCardPayPopup(url){
    openWin = window.open(url,'u0050','scrollbars=yes,top='+(window.screen.availHeight-530)/2+',left='+(window.screen.availWidth-900)/2+',width=900,height=500,statusbar=0,resizable=no,menubar=no,toolbar=no');
    return false;
}
//菜单切换
function shiftMenu() {
	$('.main-body').toggleClass('main-body2');
	return false;
}
function createUl(ar) {
	var ul = "<ul><li>" + ar.join("</li><li>") + "</li></ul>";
	return ul;
}
//jquery-ui Tooltip
function setTitle() {
	var loc = location.href,
		//各页面帮助提示定义
		helps = {
				"u2020": createUl([MT031, MT032]),
				"u3010": createUl([MT020, MT015, MT016, MT017, MT019]),
				"u3020": createUl([MT024, MT022, MT023]),
				"u4010": MT002,
				"u4020": createUl([MT001, MT002]),
				"u4060": createUl([MT025, MT026]),
				"u5010": MT018,
				"u5030": MT033,
				"u5060": MT029,
				"u5080": createUl([MT027, MT028])
			},
		hp = null;
	if (loc.indexOf("u2020") > -1) {
		//批量开票
		hp = helps["u2020"];
	} else if (loc.indexOf("u3010") > -1) {
		//结算
		hp = helps["u3010"];
	} else if (loc.indexOf("u3020") > -1) {
		//加工者结算
		hp = helps["u3020"];
	} else if (loc.indexOf("u4010") > -1) {
		//编码管理
		hp = helps["u4010"];
	} else if (loc.indexOf("u4020") > -1) {
		//规格管理
		hp = helps["u4020"];
	} else if (loc.indexOf("u4060") > -1) {
		//加工者管理
		hp = helps["u4060"];
	} else if (loc.indexOf("u5010") > -1) {
		//结算查询
		hp = helps["u5010"];
	} else if (loc.indexOf("u5030") > -1) {
		//日志查询
		hp = helps["u5030"];
	} else if (loc.indexOf("u5060") > -1) {
		//加工者日汇总查询
		hp = helps["u5060"];
	} else if (loc.indexOf("u5080") > -1) {
		//加工者结算查询
		hp = helps["u5080"];
	} else {
		$("#pgtip").hide();
	}
 	$(document).tooltip({ items: "#pgtip:visible, [title]", track: true,
 		content: function() {
	 		var element = $(this);
	 		if ( element.is( "#pgtip" ) ) {
	 			return hp;
	 		} else {
	 			return element.attr( "title" );
	 		}
 	} });
}
//s: status: 0,1,2,3
//t: title
function setCardReaderStatus(s, t) {
	//var p = $('.footer p:first-child'), st;
	var p = $('#navigation .col-b'), st;
	if (!s || s == 0) {
		st = '读卡器未检测';
		return;
	} else if (s == 1) {
		st = '读卡器未连接';
	} else if (s == 2) {
		st = '读卡器连接';
	} else if (s == 3) {
		st = '读卡器就绪';
	}
	if (p.length > 0) {
		var ss = $('#crs');
		if (ss.length > 0) {
			ss.text(st);
		} else {
			p.prepend('<span id="crs" style="margin-right: 20px;">' + st + '</span>');
			ss = $('#crs');
		}
		if (s == 1) {
			ss.css('color', 'red');
		}
		if (t) {
			ss.attr('title', t);
		 	$(document).tooltip({ items: ss, content: t });
		}
	}
}
//消息定义
var MT001 = "如查询结果过多，可以再加上合适的条件查询。",
MT002 = "如要放弃编辑，请点击 [查询] 。",
MT003 = "点击排序。",
MT004 = "另外有{0}张无效票据。",
MT005 = "请输入数字或字母。",
MT006 = "必须输入。",
MT007 = "下面有{0}个小类型。",
MT008 = "被使用{0}次。",
MT009 = "请选择 [规格小类型] 所基于的 [规格大类型] 。",
MT010 = "全部必须输入。",
MT011 = "请输入数字，整数2位，小数2位。",
MT012 = "另外有{0}张无效票据。",
MT013 = "请输入数字。",
MT014 = "今年正月初一",
MT015 = "结算前打印的是预结单；结算后打印的是正式结算单。",
MT016 = "结算成功后，建议立即打印正式结算单，校对后签名盖章，然后交由加工者去财务处领款。",
MT017 = "本页面打印的正式结算单，应作为唯一领款凭据。",
MT018 = "财务人员确认正式结算单，向加工者支付款项后，应立即点击[支付]按钮，将此结算标注为[已付]。",
MT019 = "正式结算单只允许打印一张，如果打印了多余的正式结算单，结算员将承担相应责任。",
MT020 = "请用条码扫描录入，如果因为手工录入而导致错误结算，结算员将承担相应责任。",
MT021 = "加工者添加完成后，证件号码不可更改。",
MT022 = "加工者结算时，结算开始日期自动选择：该加工者未结算的最早日期，不可修改。",
MT023 = "结算截止日：可以参与结算的最大日期，可以在[系统设定-其他设定]中修改，或联系系统管理员进行调整。",
MT024 = "加工者编号，请用刷卡方式输入。",
MT025 = "输入卡号时，请在文本框中，用刷卡方式输入。",
MT026 = "无效的加工者，不持有卡号。",
MT027 = "在加工者编号文本框中刷卡，将查询出该加工者最新的结算信息。",
MT028 = "加工者编号和结算编号，输入任何一项即可查询。",
MT029 = "在加工者编号文本框中现有编号基础上输入回车，将实时再查询该加工者的当前余额与最早未结算日期。",
MT030 = "如果加工者编号为空，则由系统自动生成（从1001开始）。",
MT031 = "如果开票规格单位为时间，请注意[批量开票时间输入方式]。（可以在[系统设定-其他设定]中修改）",
MT032 = "如果开票规格单位为时间，则均分模式不起作用。",
MT033 = "日志类型为[分配]时的[票据编号]，表示分配编号，可在[分配一览查询]页面查询分配信息。"
;
