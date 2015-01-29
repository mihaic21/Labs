$(document).ready(function() {
    $(".form-radio").change(showNumberBox);
    $("#grave-name").change(loadParcels);
    if($("#grave-name").find(':first-child').val() >= 0)
        $("#grave-name").trigger("change");
    $("#grave-parcel").change(loadGraves);
    $("#grave-number").change(setSurface);
    $("#doc-nr").keyup(fillNumber);
    $(".more-text").click(showHistory);
});

function showHistory() {
    if ($( ".history-div:hidden").length > 0) {
        $(".more-text").text("Ascunde");
        $(".history-div").fadeIn("slow");
    }
    else {
        $(".more-text").text("Mai multe detalii");
        $(".history-div").fadeOut("slow");
    }
}

function showNumberBox() {
    if ($(this).val() == "1" || $(this).val() == "2")
        $(".hidden-obj").show();
    else
        $(".hidden-obj").hide();
    $("#doc-nr").val("");
}

function loadParcels() {
    var found = false;
    var input = $("#grave-parcel");
    if ($(this).find(':first-child').val() < 0)
        $(this).find(":first-child").remove();
    $.get("ConcessionServlet", {act:"loadGraveDetails", field:"parcel", graveyard:$(this).find(':selected').val()},
        function(data) {
            input.empty();
            var start = -1;
            if (input.attr("value") != "")
                start = 0;
            for (var i = start; i < data.length; i++) {
                var option = document.createElement("option");
                option.setAttribute('value', i);
                if (i >= 0) {
                    option.innerHTML = data[i]['number'];
                    if (data[i]['number'] == input.attr('value')) {
                        option.setAttribute('selected', 'selected');
                        found = true;
                    }
                }
                else
                    option.innerHTML = "Selectează...";
                input.append(option);
            }
            if (found)
                $("#grave-parcel").trigger("change");
        }, "json");
    $("#grave-number").empty();
}

function loadGraves() {
    var input = $("#grave-number");
    if ($(this).find(':first-child').val() < 0)
        $(this).find(":first-child").remove();
    $.get("ConcessionServlet", {act:"loadGraveDetails", field:"grave", graveyard:$("#grave-name").find(':selected').val(),
            parcel:$(this).find(':selected').val()},
        function(data) {
            input.empty();
            var start = -1;
            if (input.attr("value") != "")
                start = 0;
            for (var i = start; i < data.length; i++) {
                var option = document.createElement("option");
                if (i >= 0 && (data[i]['free'] == true || data[i]['number'] == input.attr('value'))) {
                    option.innerHTML = data[i]['number'];
                    option.setAttribute('surface', data[i]['surface']);
                    option.setAttribute('value', i);
                    if (data[i]['number'] == input.attr('value'))
                        option.setAttribute('selected', 'selected');
                    input.append(option);
                }
                else if (i < 0) {
                    option.innerHTML = "Selectează...";
                    option.setAttribute('value', i);
                    input.append(option);
                }
            }
        }, "json");
}

function setSurface() {
    if ($(this).find(':first-child').val() < 0)
        $(this).find(":first-child").remove();
    $("#grave-surface").val($("#grave-number").find(':selected').attr("surface") + " m2");
}

function fillNumber() {
    var selected = $('input[name=receipt-cause]:checked');
    var selectedPar = $('.radio-text[value=' + selected.val() + ']');
    var s = selectedPar.text();
    var replWith = $(this).val();
    if (replWith == "")
        replWith = "____";
    if (s.search("____") > -1)
        s = s.replace("____", $(this).val());
    else if (s.search("/") > -1)
        s = s.replace(/:.*\//, ": " + replWith + "/");
    else
        s = s.replace(/:.*/, ": " + replWith);
    selectedPar.text(s);

}