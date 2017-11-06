$(document).ready(function() {

    $('.submit_on_enter').keydown(function(event) {
        // enter has keyCode = 13, change it if you want to use another button
        if (event.keyCode == 13) {
            saveOrganization();
            return false;
        }(this)
    });

    $('#organization-description').focus();

    var organizationID = queryString().id;
	if (organizationID) {
		$.ajax({
			url: server + 'organization/' + organizationID,
			method: 'GET',
			contentType: 'application/json',
			success: function(response) {
				$('#organization-description').val(response.description);
				getSectorsList(function(data) {
					data.forEach(function(sector) {
						$('#sectors').append($('<option>', {
						    value: sector.id,
						    text: sector.description
						}));
					});

					$('#sectors').val(response.sectors.map(function(sector) {
						return sector.id;
					}));
				});
			}
		});
	} else {
		getSectorsToSelect();
	}

	function fetchData() {
		$.ajax({
	    url: server + 'organization',
	    method: 'GET',
	    success: function(response) {
	      var items = "";
	      response.forEach(function(organization) {
	        items += "<tr id=\"" + organization.id + "\">" +
	          "<td>"+ organization.id +"</td>" +
	          "<td>"+ organization.description +"</td>" +
	          "<td>"+ organization.sectors.length +"</td>" +
	          "<td>"+ organization.goals.length +"</td>" +
	          "<td><div class=\"actions\">"+ editLink(organization.id) + removeLink(organization.id) +"</div></td>" +
	        "</tr>";
	      });

	      $("#table-content").append(items);
	    },
	  });
	}

    function saveOrganization() {
        var method = organizationID ? 'PUT' : 'POST',
            sectors = $('#sectors'),
            description = $('#organization-description'),
            params = {description: description.val(), sectors: []};

        sectors.val().map(function (sector) {
            params.sectors.push({
                id: +sector
            });
        });

        if (organizationID) {
            params.id = +organizationID;
        }

        if (description.val()) {
            $.ajax({
                url: server + 'organization',
                method: method,
                contentType: 'application/json',
                data: JSON.stringify(params),
                success: function (sector) {
                    window.location.href = '/pages/organization.html';
                }
            });
        }
    }

    $('#save').on('click', function(event) {
        saveOrganization();
  });

  function editLink(id) {
    return '<a href="/pages/organization.html?id='+ id +'" class="action action-edit">Editar</a>';
  }

  function removeLink(id) {
    return '<a href="#" class="action action-remove" onClick="removeOrganization(' + id + ');">Excluir</a>';
  }

	fetchData();
});

function removeOrganization(id) {
  $.ajax({
    url: server + 'organization/' + id,
    method: 'DELETE',
    success: function(response) {
      $("tr#" + id).remove();
    },
  });
}
