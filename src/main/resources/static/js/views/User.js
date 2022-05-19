import createView from "../createView.js";

const url ="http://localhost:8080/api/users"
export default function User(props){
    return `
 <header>
            <h1>Welcome, ${props.user.username}</h1>
        </header>
        <main>
            <form id="user-info-form">
                <label for="email">Email</label>
                <input disabled id="email" name="email" type="email" value="${props.user.email}">
                <label for="new-password">New Password</label>
                <input id="new-password" name="new-password" type="password" value="${props.user.password}"/>
                <button id="change-password-button" data-id="${props.user.id}" type="submit">Change Password</button>
            </form>
        </main>
`;
}

export function UserEvent(){
    addUpdatePasswordListener()
}

function addUpdatePasswordListener(){
    $(document).on("click", "#change-password-button", function (){
    const id = $(this).data("id");
        console.log(id);
     const newPassword = $("#new-password").val();
        console.log(newPassword)
    const request = {
        method: "PUT",
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    }

    fetch(`${url}/${id}/updatePassword?newPassword=${newPassword}`, request)
        .then(res=>{
            console.log(res.status);
        })
        .catch(err=>{
            console.log(err)
        }).finally(()=>{
            createView("/user")
    })
    })
}