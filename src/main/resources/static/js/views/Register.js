import createView from "../createView.js";

export default function Register(props) {
    return `<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Register</title>
</head>
<body>
<h1>Register</h1>

<form id="new-user-form">
<label for="email">Email</label>
<input id="email" name="email" type="email"/><br>
    <label for="username">Username</label>
    <input id="username" name="username" type="text"/><br>
    <label for="password">Password</label>
    <input id="password" name="password" type="password"/><br>
    <input id="create-btn" type="submit" value="Sign Up"/>
</form>
</body>
</html>`;
}
export function RegisterEvent(){
    $(document).on('click', '#create-btn', function (){
        let today = new Date()
        let user = {
            id: 3,
            username:$('#username').val(),
            email:$('#email').val(),
            password: $('#password').val(),
            createdAt:null,
            // createdAt: today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate(),
            role: "USER"
        }
        console.log(user)
        let request = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(user)
        };

        fetch("http://localhost:8080/api/users", request)
            .then((response) => {
                console.log(response.status)
                createView("/");
            });
    })


}


