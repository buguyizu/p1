// TODO
const base = '/p1';
const datatables_ver = '1.10.21';
/*
var require = {
	baseUrl: base + '/webjars',
	paths: {
//		jquery:      'jquery/1.12.4/jquery',
//		bootstrap:   'bootstrap/3.3.7-1/js/bootstrap',
//		'datatables.net':   'datatables/1.10.19/js/jquery.dataTables',
		'datatables.bs':    'datatables/1.10.19/js/dataTables.bootstrap',
		eventsource: '../fw/eventsource/eventsource',
		sse:         '../js/sse',
		base:        '../js/base'
	},
	shim: {
		eventsource: { exports: 'eventsource' },
//		bootstrap:   { deps: ['jquery'] },
//		'datatables.net':  { deps: ['jquery'] },
		'datatables.bs':   { deps: ['datatables.net', 'bootstrap'] }
	},
	map: {
		'*': {
			
		}
	}
};
*/
if (typeof require ==="object") {
	require['baseUrl'] = base + '/webjars';
	require['paths'] = {
			jquery:      webjars.path("jquery", "jquery"),
			eventsource: '../fw/eventsource/eventsource',
			sse:         '../js/sse',
			base:        '../js/base',
			'datatables.net':   'datatables/' + datatables_ver + '/js/jquery.dataTables',
			'datatables.bs':    'datatables/' + datatables_ver + '/js/dataTables.bootstrap'
		};
	require['shim'] = {
			eventsource:       { exports: 'eventsource' },
			'datatables.net':  { deps: ['jquery'] },
			'datatables.bs':   { deps: ['datatables.net', 'bootstrap'] }
		};
}