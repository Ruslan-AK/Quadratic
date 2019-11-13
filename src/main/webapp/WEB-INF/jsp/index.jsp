<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
<head>
    <spring:url value="/resources/js/myScript.js"
                var="myScript"/>
    <meta charset="UTF-8">
    <title>Create Client</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="${myScript}"></script>
</head>
<body>
<fieldset>
    <em>
        Введите значения коэффициентов a, b и с:
        <br>
    </em>
    <input id="a_coef" size="2" tabindex="1" required>
    <span>x</span><sup>2</sup>+
    <input id="b_coef" size="2" tabindex="2" required>
    <span class="oms_formula">x</span>+
    <input id="c_coef" size="2" tabindex="3" required>=0
    <p><input id="solve" value="Рассчитать" type="button"></p>
    <br>
    <div id="result_form"></div>
</fieldset>
<br>
<fieldset>
    <p><input id="show_list" value="Просмотреть предыдущие рассчеты" type="button"></p>
    <div id="result_list_form"></div>
</fieldset>
</body>
</html>