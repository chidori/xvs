$(document).ready(function() {
  var xmlEditor = ace.edit("xml-editor");
  xmlEditor.getSession().setMode("ace/mode/xml");

  var schemaEditor = ace.edit("schema-editor");
  schemaEditor.getSession().setMode("ace/mode/xml");

  $("#validate").click(function() {
    var params = {
      'xml': xmlEditor.getValue(),
      'schema': schemaEditor.getValue()
    };
    $.ajax({
      type: "POST",
      url: "/validate",
      data: params,
      dataType: 'json',
      success: function(response) {
        console.log(response)
        $('#output').html(response.status + "<br>" + (response.status == "ok" ? "" : response.message))
      }
    });

  });

});
