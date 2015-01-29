$(document).ready(function () {
    loadMenu();
    if (window.location.search.indexOf('?') == -1)
        loadOptions($("title").text());
    $(".autocomplete-select").delegate("li", "click", fillData);
    $("#year-selection").change(changeYear);
    $(".autocomplete").keyup(showSuggestions);
    if ($(".img-button").length != 0)
        $(".img-button").css('left', $(".data-table").offset().left - $(".content").offset().left );
    if ($(".search-box").length != 0)
        $(".search-box").css('right', ($(".content").innerWidth() - $(".data-table").innerWidth())/2);
    prepareForAutocomplete();
    if ($("#download").length > 0 && $("#download").attr("href").match(/.*doc/)) {
        window.location = $('#download').attr('href');
        $("#download").attr("href", "resources/temp/");
    }
    $(".selected-con").click(enableButton);
});

function enableButton() {
    if ($(".selected-con:checked").length > 0) {
        $(".img-button:not(:first-child)").prop('disabled', false);
        $(".img-button:not(:first-child)").css("opacity", "1.0");
    }
    else {
        $(".img-button:not(:first-child)").prop('disabled', true);
        $(".img-button:not(:first-child)").css("opacity", "0.2");
    }
}

$(document).click(function() {
    $(".autocomplete-select").hide();
});

function loadOptions(name) {
    $(".content").empty();
    name = name.trim();
    $.getJSON('resources/options.json', function(data) {
        if (data[name] == undefined || data[name] == null)
            return;
        var f = document.createElement("form");
        f.setAttribute('class', 'option-form')
        f.setAttribute('method',"get");
        f.setAttribute('action',data[name]["Servlet"]);

        $.each(data[name]["Options"], function(key, val) {
            var input = document.createElement("input");
            input.setAttribute('type',"submit");
            input.setAttribute('class',"option")
            input.setAttribute('value', val.Name);
            input.setAttribute('name', "act")
            f.appendChild(input);
        })

        document.getElementsByClassName("content")[0].appendChild(f)
    });
}

function loadMenu() {
    var title = $("title").text();
    $.getJSON('resources/menu.json', function(data) {
        var ul = document.createElement("ul");
        ul.setAttribute("class", "nav");

        $.each(data, function(key, val) {
            var li = document.createElement("li");
            var anch = document.createElement("a");
            anch.setAttribute('href', val.Ref)
            if (val.Name == title)
                anch.setAttribute("class", "active");
            var span = document.createElement("span");
            span.innerHTML = val.Name;
            anch.appendChild(span);
            li.appendChild(anch);
            ul.appendChild(li);
        });
        document.getElementsByClassName("menu")[0].appendChild(ul);
    });
}

function changeYear() {
    var yearval = $(this).find(':selected').text();
    var actval = $(this).attr("name");
    $.get("ConcessionServlet", {year:yearval, act:actval},
        function(data) {
            $(".content").html($(data).filter(".content").html());
        }, "html");
}

function prepareForAutocomplete() {
    if ($(".autocomplete").length != 0) {
        $(".autocomplete-select").each(function() {
            var name = $(this).attr("name");
            var input = $(".autocomplete[name=" + name + "]");
            $(this).css('left', input.offset().left - $(".content").offset().left);
            $(this).css('top', input.offset().top - $(".content").offset().top + input.outerHeight());
        });
    }
}

function showSuggestions() {
    var name = $(this).attr("name");
    var input = $(".autocomplete-select[name=" + name + "]");
    if ($(this).val().length == 0)
        input.hide();
    else {
        $.get($(".data-form").attr("action"), {act:"Autocomplete", filter:$(this).val(), field:input.attr("property")},
            function(data) {
                input.empty();
                for (var i = 0; i < data.length; i++) {
                    var li = document.createElement("li");
                    li.setAttribute('class', 'autocomplete-li');
                    li.innerHTML = data[i][input.attr("property")];
                    input.append(li);
                }
            }, "json");
        input.show();
    }
}

function fillData() {
    var data = $(this).text();
    var name = $(this).parent().attr("name");
    var input = $(".autocomplete[name=" + name + "]");
    var index = $(".autocomplete-li").index($(this));

    $.get($(".data-form").attr("action"), {act:"Autocomplete", filter:(input.val()), field:$(this).parent().attr("property")},
        function(data) {
            $(".autocomplete-select").each(function() {
                if (data[index][$(this).attr("property")] != undefined)
                    $(".autocomplete[name=" + $(this).attr("name") + "]").val(data[index][$(this).attr("property")])
            });
        }, "json");
}
