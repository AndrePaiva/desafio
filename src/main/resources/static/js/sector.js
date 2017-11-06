$(document).ready(function() {

    $('.submit_on_enter').keydown(function(event) {
        // enter has keyCode = 13, change it if you want to use another button
        if (event.keyCode == 13) {
            saveSector();
            return false;
        }
    });

    $('#sector-description').focus();

    var sectorId = queryString().id;
  if (sectorId) {
    $.ajax({
      url: server + 'sector/' + sectorId,
      method: 'GET',
      success: function(response) {
        $('#sector-description').val(response.description);
      }
    })
  }

  function fetchData() {
    $.ajax({
      url: server + 'sector',
      method: 'GET',
      success: function(response) {
        var items = "";
        response.forEach(function(sector) {
          items += createSector(sector);
        });

        $("#table-content").append(items);
      },
    });
  }

    function saveSector() {
        var method = sectorId ? 'PUT' : 'POST',
            sectorInput = $('#sector-description'),
            params = {description: sectorInput.val()};

        if (sectorInput) {
            params.id = +sectorId;
        }

        if (sectorInput.val()) {
            $.ajax({
                url: server + 'sector',
                method: method,
                contentType: 'application/json',
                data: JSON.stringify(params),
                success: function (sector) {
                    window.location.href = '/pages/sector.html';
                }
            })
        }
    }

    $('#save').on('click', function(event) {
        saveSector();
  });

  function createSector(sector) {
    return "<tr id=\"" + sector.id + "\">" +
      "<td>"+ sector.id +"</td>" +
      "<td>"+ sector.description +"</td>" +
      "<td><div class=\"actions\">"+ editLink(sector.id) + removeLink(sector.id) +"</div></td>" +
    "</tr>";
  }

  function editLink(id) {
    return '<a href="/pages/sector.html?id='+ id +'" class="action action-edit">Editar</a>';
  }

  function removeLink(id) {
    return '<a href="#" class="action action-remove" onClick="removeSector(' + id + ');">Excluir</a>';
  }

  fetchData();
});

function removeSector(id) {
  $.ajax({
    url: server + 'sector/' + id,
    method: 'DELETE',
    success: function(response) {
      $("tr#" + id).remove();
    },
  });
}
