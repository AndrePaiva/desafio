var server = "/";

function getGoalList(callback) {
	$.get(server + "goal", callback);
}

function getGoalsByOrganizationId(id, callback) {
	$.get(server + 'goal/allByOrganizationId/' + id, callback);
}

function getSectorsList(callback) {
	$.get(server + "sector", callback);
}

function getOrganizationList(callback) {
	$.get(server + "organization", callback);
}

function getOrganizationsToSelect() {
	getOrganizationList(function(data) {
		data.forEach(function(organization) {
			$('#organizations').append($('<option>', {
				value: organization.id,
				text: organization.description
			}));
		});
	});
}

function getMetasToSelect() {
	getGoalList(function(data) {
		data.forEach(function(goal) {
			$('#goals').append($('<option>', {
			    value: goal.id,
			    text: goal.description
			}));
		});
	});
}

function getSectorsToSelect() {
	getSectorsList(function(data) {
		data.forEach(function(sector) {
			$('#sectors').append($('<option>', {
			    value: sector.id,
			    text: sector.description
			}));
		});
	});
}

function queryString() {
  var parse_url = /^(?:([a-zA-Z]+):)?(\/{0,3})([0-9.\-a-zA-Z]+)(?::(\d+))?(?:\/([^?#]*))?(?:\?([^#]*))?(?:#(.*))?$/,
      names = ['url', 'scheme', 'slash', 'host', 'port', 'path', 'query', 'hash'],
      query_string = {},
      result = parse_url.exec(window.location.href);

	if (!result[6]) {
		return {};
	}

  var vars = result[6].split('&');

  for (var i = 0; i < vars.length; i++) {
      var pair = vars[i].split('=');
      query_string[pair[0]] = pair[1];
  }

  return query_string;
}
