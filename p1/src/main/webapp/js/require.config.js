// TODO
var base = '/p1';
var require = {
	baseUrl: base + '/webjars',
	shim: {
		eventsource: { exports: 'eventsource' },
		bootstrap:   { deps: ['jquery'] },
		'datatables.net':  { deps: ['jquery'] },
		datatables:  { deps: ['datatables.net', 'bootstrap'] }
	},
	paths: {
		jquery:      'jquery/1.12.4/jquery',
		bootstrap:   'bootstrap/3.3.7-1/js/bootstrap',
		'datatables.net':   'datatables/1.10.19/js/jquery.dataTables',
		datatables:       'datatables/1.10.19/js/dataTables.bootstrap',
		eventsource: '../fw/eventsource/eventsource',
		sse:         '../js/sse',
		base:         '../js/base'
	},
	map: {
		'*': {
			
		}
	}
};