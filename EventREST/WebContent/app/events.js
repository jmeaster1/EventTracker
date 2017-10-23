$(document).ready(function() {
  console.log('page loaded');
  getAllEvents();

  function getAllEvents() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/EventREST/rest/events",
        dataType: "json"
      })

      .done(function(data, status) {
        buildDOM(data);
      })

      .fail(function(xhr, status, error) {
        console.log('ERROR! Something went wrong!');
      });
  }

  function buildDOM(data) {
    //thead
    var $table = $('<table>');
    $("body").append($table);
    var $thead = $('<thead>');
    $table.append($thead);
    var $tr = $('<tr>');

    $thead.append($tr);
    var $th = $('<th>');
    $thead.text('Contents');
    $tr.append($th);
    //tbody
    var $tbody = $('<tbody>');
    $table.append($tbody);

    $('#content').append($table);

    for (var i = 0; i < data.length; i++) {
      var $tr2 = $('<tr>');
      $tbody.append($tr2);

      var $td = $('<td>');
      var $td2 = $('<td>');
      var $td3 = $('<td>');

      var deleteButton = $('<button>');

      $td2.attr("id", data[i].id);
      $tr2.append($td);
      $tr2.append($td2);

      $tr2.append($td3);
      $td3.append(deleteButton);
      deleteButton.text('Delete');

      deleteButton.click(function(e) {
        e.preventDefault();

        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/EventREST/rest/events/" + $td2.attr('id'),
            dataType: "json"

          })
          .done(function(data, status) {
            $('#content').empty();
            getAllEvents();
          })

          .fail(function(xhr, status, error) {
            console.log('ERROR! Something went wrong!');
          });
      });

      $td.text(data[i].id);
      $td2.text(data[i].commonName);

      $td2.click(function() {
        $('#content').empty();
        var fish = $(this).attr('id');

        var myReq = $.ajax({
          type: "GET",
          url: "http://localhost:8080/EventREST/rest/events/" + fish,
          dataType: "json"
        });

        myReq.done(function(data, status) {
          showFish(data);
        });

        myReq.fail(function(xhr, status, error) {
          console.log('ERROR! Something went wrong!');
        });


        function showFish(fish) {
          var $ul = $('<ul>');
          $("#content").append($ul);

          var name = $('<li>');
          name.text(fish.commonName);
          $ul.append(name);

          var flyUsed = $('<li>');
          flyUsed.text(fish.flyUsed)
          $ul.append(flyUsed);

          var length = $('<li>');
          length.text(fish.length);
          $ul.append(length);

          var weight = $('<li>');
          weight.text(fish.weight);
          $ul.append(weight);

          var button1 = $('<button>');
          button1.text('Return');
          button1.click(function() {
            $('#content').empty();
            buildDOM(data);
          })
          $ul.append(button1);
        };
      });
    };
  };
  $('#sub').click(function(e) {
    e.preventDefault();
    var newFish = {
//      id: $('input[name="id"]').val(),
      commonName: $('input[name="commonName"]').val(),
      flyUsed: $('input[name="flyUsed"]').val(),
      length: $('input[name="length"]').val(),
      weight: $('input[name="weight"]').val(),
    };
    var myReq = $.ajax({
      type: "POST",
      url: "http://localhost:8080/EventREST/rest/events",
      dataType: "json",
      contentType: 'application/json',
      data: JSON.stringify(newFish)
    });

    myReq.done(function(data, status) {
      $('#content').empty();
      getAllEvents();
      $('#createEvent').children('input').val('')
    });

    myReq.fail(function(xhr, status, error) {
      console.log('ERROR! Something went wrong!');
//      console.log(newFish);
    });
  });
});