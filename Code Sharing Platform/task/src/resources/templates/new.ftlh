<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
<div>
    <textarea id="code_snippet"></textarea>
</div>
<div>
    <label>Time
        <input id="time_restriction" type="text" value="0"/>
    </label>
</div>
<div>
    <label>Views
        <input id="views_restriction" type="text" value="0"/>
    </label>
</div>
<div>
    <button id="send_snippet" type="submit" onclick="send();">Submit</button>
</div>
<script>
        function send() {
            let object = {
                "code": document.getElementById("code_snippet").value,
                "views": document.getElementById("views_restriction").value,
                "time": document.getElementById("time_restriction").value
            };

            let json = JSON.stringify(object);

            let xhr = new XMLHttpRequest();
            xhr.open("POST", '/api/code/new', false)
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);

            if (xhr.status == 200) {
              alert("Success!");
            }
        }

    </script>
</body>
</html>