<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP - Hello World</title>
    <style>
        table {
            border: 1px solid #dbdbdb;
            border-collapse: collapse;
        }
        th, td {
            box-sizing: border-box;
            border: 1px solid #dbdbdb;
            padding: 5px 10px;
        }
    </style>
</head>
<body>
    <h1>계좌 생성</h1>
    <button type="button" class="create-button">생성하기</button>
    <table>
        <tr>
            <th>ID</th>
            <th>AccountNo</th>
            <th>Owner</th>
            <th>Balance</th>
        </tr>
        <tr>
            <td><input type="text"></td>
            <td><input type="text"></td>
            <td><input type="text"></td>
            <td><input type="text"></td>
        </tr>
    </table>
    <script>
        main();
        function main() {
            const createButton = document.querySelector(".create-button");
            createButton.onclick = () => {
                const inputs = document.querySelectorAll("input");
                const accountObj = {
                    id: inputs[0].value,
                    accountNo: inputs[1].value,
                    owner: inputs[2].value,
                    balance: inputs[3].value,
                }

                // fetch (괄호안 매개변수, "객체"Object) fetch는 요청, 다운로드 로 알고 있어라 새로운 정보가 있으면 받아 오고 이전 정보와 같으면 안받아옴
                // fetch API 요청.
                // method: 는 post, get, put, delet 가 있다 CRUD 로 생성, 읽기, 업데이트, 삭제
                // body: 읽어올 데이터가 무슨 데이터인지 분간해주는 역화 JSON 즉 확장자명과 같다
                fetch( "http://localhost:8080/servlet_study_war_exploded/api/accounts",{
                    method: "post",
                    body: JSON.stringify(accountObj),
                    headers: {
                        "Content-Type": "application/json",
                    }
                })
            }
        }
    </script>
</body>
</html>