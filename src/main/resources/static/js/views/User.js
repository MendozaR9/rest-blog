import createView from "../createView.js";

export default function User(props){
    return `<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>User</title>
</head>
<body>
<h1>User</h1>
 ${props.user.map(post => `<h3>${post.username}</h3>
<h3>${post.password}</h3>
`).join('')}
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

function getUserByUsername(){
    let request = {
        method: "GET",
        body: "testUser"
    };
    fetch("http://localhost:8080/api/users/username", request)
        .then((response) => {
            console.log(response.status)
            createView("/");
        })
}