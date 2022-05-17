const url ="http://localhost:8080/api/posts"
export default function PostIndex(props) {
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div id="posts-container">
                ${props.posts.map(post => `<h3>${post.title}</h3>`).join('')}   
            </div>
            <input type="text" id="id-post" placeholder="give id"><br>
            <input type="text" id="title-post" placeholder="title" ><br>
            <textarea id="content-post" placeholder="Enter your content here"></textarea><br>
            <button id="post-btn" type="button">Post</button><br>
            <button id ="edit-btn" type="button">Edit</button>
        </main>
    `;
}
// PostsEvent();
export function PostsEvent() {
submitPost();
editPost();
}

function submitPost(){
    $(document).on('click', '#post-btn', function (){
        let newPost = {
            id: $("#id-post").val(),
            title: $("#title-post").val(),
            content: $("#content-post").val()
        }
        sendPost(newPost)
    })
}
function editPost(){
    $(document).on('click', '#edit-btn', function (){
        let selId =$("#id-post").val()
        let newPost = {
            id: selId ,
            title: $("#title-post").val(),
            content: $("#content-post").val()
        }
        changePost(newPost, selId)
    })
}

function sendPost(post){
    fetch(url, {
        method: "POST",
        body: JSON.stringify(post),
        headers: {"Content-type": "application/json; charset=UTF-8"},
    })
        .then(json => console.log(json))
        .then(res => console.log(post))
        .catch(err => {
            console.log('you have error plz cry', err)
        });
}

function changePost(post, id) {
    fetch(`${url}/`+id, {
        method: "PUT",
        body: JSON.stringify(post),
        headers: {"Content-type": "application/json; charset=UTF-8"},
    })
        .then(json => console.log(json))
        .then(res => console.log(post))
        .catch(err => {
            console.log('you have error plz cry', err)
        });
}
