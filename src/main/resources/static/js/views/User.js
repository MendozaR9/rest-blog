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
                <input disabled id="new-password" name="new-password" type="password" value="${props.user.password}"/>
                <button disabled id="change-password-button" data-id="${props.user.id}" type="submit">Change Password</button>
                <button id="edit" type="button">Edit</button>
            </form>
        </main>
`;
}

export function UserEvent(){
    addUpdatePasswordListener()
    $(document).on("click", "#edit",function (){
        console.log("edit button")
        // document.getElementById("email").disabled = false;
        $("#email").prop("disabled", false);
        $("#new-password").prop("disabled",false);
        $("#change-password-button").prop("disabled", false);
    })
}

function addUpdatePasswordListener(){
    $(document).on("click", "#change-password-button", function (){

    const id = $(this).data("id");
        console.log(id);
     const newPassword = $("#new-password").val();
        console.log(newPassword)
        if (newPassword.length>=3) {
            const request = {
                method: "PUT",
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            }

            fetch(`${url}/${id}/updatePassword?newPassword=${newPassword}`, request)
                .then(res => {
                    console.log(res.status);
                })
                .catch(err => {
                    console.log(err)
                }).finally(() => {
                createView("/user")
            })
        }else {
            alert("The new password must be at least 3 characters long")
        }
    })
}