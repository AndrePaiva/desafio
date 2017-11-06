$(document).ready(function() {

  $('.submit_on_enter').keydown(function(event) {
      // enter has keyCode = 13, change it if you want to use another button
      if (event.keyCode == 13) {
          saveGoal();
          return false;
      }
  });

  $('#description').focus();

  var goalID = queryString().id;
  if (goalID) {
    $.ajax({
      url: server + 'goal/' + goalID,
      method: 'GET',
      contentType: 'application/json',
      success: function(response) {
        var selectedGoalID = response.goal ? response.goal.id: '';

        getOrganizationList(function(organizations) {
          organizations.forEach(function(organization) {
            $('#organizations').append($('<option>', {
              value: organization.id,
              text: organization.description
            }));
          });

          $('#organizations').val(response.organization.id);
          getGoalsByOrganizationId(+$('#organizations').val(), function(goals) {
            createGoalsItems(goals, selectedGoalID);
          });
        });

        $('#description').val(response.description);
        $('#type').val(response.type);
      }
    });
  } else {
    getOrganizationList(function(organizations) {
      organizations.forEach(function(organization) {
        $('#organizations').append($('<option>', {
          value: organization.id,
          text: organization.description
        }));
      });

      getGoalsByOrganizationId(+$('#organizations').val(), function(goals) {
        createGoalsItems(goals);
      });
    });
  }

  $('#organizations').on('change', function(event) {
    getGoalsByOrganizationId(+event.target.value, createGoalsItems);
  });

  $.ajax({
    url: server + 'goal',
    method: 'GET',
    contentType: 'application/json',
    success: function(response) {
      var items = "";
      response.forEach(function(goal) {
        items += createGoal(goal);
      });

      $("#table-content").append(items);
    }
  });

  function saveGoal() {
    $('#save').on('click', function(event) {
      var method = goalID ? 'PUT' : 'POST';
          type = $('#type').val(),
          organization = $('#organizations').val(),
          goal = $('#goals').val(),
          description = $('#description').val();

      var params = {
        type: type,
        description: description,
        organization: {
          id: +organization
        }
      };

      if (goal) {
        params.goal = {
          id: +goal
        };
      }

      if (goalID) {
        params.id = +goalID;
      }

      $.ajax({
        url: server + 'goal',
        method: method,
        contentType: 'application/json',
        data: JSON.stringify(params),
        success: function(response) {
          window.location.href = '/pages/goal.html';
        }
      });
    });
  }

  $('#save').on('click', function(event) {
      saveGoal();
  });

  function createGoal(goal) {
    var goalName = goal.goal ? goal.goal.description : '';
    return "<tr id=\"" + goal.id + "\">" +
      "<td>"+ goal.id +"</td>" +
      "<td>"+ goal.type +"</td>" +
      "<td>"+ goal.description +"</td>" +
      "<td>"+ goal.organization.description +"</td>" +
      "<td>"+ goalName +"</td>" +
      "<td><div class=\"actions\">"+ editLink(goal.id) + removeLink(goal.id) +"</div></td>" +
    "</tr>";
  }

  function createGoalsItems(goals, selectedGoalID) {
    $('#goals').html('<option value="">Nenhuma</option>');
    goals.forEach(function(goal) {
      $('#goals').append($('<option>', {
        value: goal.id,
        text: goal.description
      }));
    });

    $('#goals').val(selectedGoalID);
  }

  function editLink(id) {
    return '<a href="/pages/goal.html?id='+ id +'" class="action action-edit">Editar</a>';
  }

  function removeLink(id) {
    return '<a href="#" class="action action-remove" onClick="removeGoal(' + id + ');">Excluir</a>';
  }
});

function removeGoal(id) {
  $.ajax({
    url: server + 'goal/' + id,
    method: 'DELETE',
    success: function(response) {
      $("tr#" + id).remove();
    },
  });
}
