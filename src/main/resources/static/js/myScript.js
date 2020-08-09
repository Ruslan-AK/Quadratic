$(document).ready(function () {
    $("#solve").click(
        function () {
            calculateForm('calculate');
            return false;
        }
    );
    $("#show_list_button").click(
        function () {
            showEquationsList('showPreviewEquations')
            return false;
        }
    );
});

function showEquationsList(url) {
    $.ajax({
        url: url, //url страницы
        type: "get", //метод отправки
        dataType: "json", //формат данных
        success: function (response) {
            var elem = document.getElementById("show_list_button");
            $('#result_list_form').html('');//убираю предыдущий результат
            if (elem.value == "Просмотреть предыдущие рассчеты") {
                elem.value = "Скрыть предыдущие рассчеты";
                response.forEach(function (item) {// item - один элемент массива
                    $('#result_list_form').append('<div>' +
                        item
                        + '</div>');
                });
            } else {
                elem.value = "Просмотреть предыдущие рассчеты";
            }
        },
        error: function () { // Данные не отправлены
            $('#result_list_form').html('Error');
        }
    });
}

function updateEquationsList(url) {
    $.ajax({
        url: url, //url страницы
        type: "get", //метод отправки
        dataType: "json", //формат данных
        success: function (response) {
            $('#result_list_form').html('');//убираю предыдущий результат
            response.forEach(function (item) {// item - один элемент массива
                $('#result_list_form').append('<div>' +
                    item
                    + '</div>');
            });
        }
    });
}

function calculateForm(url) {
    var coef = {}
    coef["a"] = $("#a_coef").val();
    coef["b"] = $("#b_coef").val();
    coef["c"] = $("#c_coef").val();
    $.ajax({
        url: url, //url страницы
        type: "POST", //метод отправки
        contentType: "application/json",
        timeout: 100000,
        dataType: "json", //формат данных
        data: JSON.stringify(coef),
        success: function (response) { //Данные отправлены успешно
            var message;
            if (response.error != null) {
                message = response.error;
            } else {
                message = 'Корни: x1 ' + response.x1 + ', x2 ' + response.x2;
                if (document.getElementById("show_list_button").value == "Скрыть предыдущие рассчеты")
                    updateEquationsList('showPreviewEquations');
            }
            $('#result_form').html(message);
        },
        error: function () { // Данные не отправлены
            $('#result_form').html('Ошибка');
        }
    });
}