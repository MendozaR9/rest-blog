const url ="http://localhost:8080/api/posts"
let requestMethod = "POST";
let postId = "";
 const fakeUser = {
     username: "testUser",
     email: "test@user.com",
     password: "password"
 }
export default function PostIndex(props) {
    //language=HTML
    return `
      <header>
        <h1>Posts Page</h1>
      </header>
      <main>
        <div id="posts-container">
          ${props.posts.map(post =>
        // TODO: make sure you wrap each post in its own container with a css id attribute linked to the post id
        `<div class="post-container" id = "post-${post.id}">
                  <h3 id="title-${post.id}">${post.title}</h3>
                  <p id="content-${post.id}">${post.content}</p>
                  <p class="post-author">${post.user.username}</p>
                  <button type="submit" class="btn btn-primary edit-button" data-id="${post.id}">Edit</button>
                  <button type="submit" class="btn btn-danger delete-button" data-id="${post.id}">Delete</button>
              </div>
            `).join('')}
        </div>
        <div id="add-post-form">
          <div>
            <input type="text" class="form-control" id="add-post-title" placeholder="Add Post Title">
          </div>
          <br>
          <div>
            <textarea class="form-control" rows="4" id="add-post-content"
                placeholder="Add Post Content"></textarea>
          </div>
          <br>
          <div>
            <button type="submit" class="btn btn-primary" id="submit-btn">Submit</button>
          </div>
        </div>
      </main>
    `;
}
// PostsEvent();
export function PostsEvent() {
submitPost();
editPost();
deletePostBtn()
}

function submitPost(){
    $(document).on('click', '#submit-btn', function (e) {
        e.preventDefault();
        const newPost = {
            title: $("#add-post-title").val(),
            content: $("#add-post-content").val()
        }

        const request = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newPost)
        }

        let requestUrl = "";

        // **** HERE ****
        const fakeUsername = 'testUser'; // TODO: replace once we implement login
        if (fakeUsername) {
            requestUrl = `${url}/${fakeUsername}`; // **** MAKE SURE YOU HAVE AN @PostMapping which matches /api/posts/{username}
        } else {
            requestUrl = `${url}`;
        }

        fetch(requestUrl, request) // TODO: if the post was successful, no need to parse out the response.. just have a catch and finally block
            .catch(error => {
                console.log(error);
            })
            .finally(() => {
                postId = "";
                 requestMethod = "POST";
            })
    })

    }

function editPost() {
    $(document).on('click', '.edit-button', function (e) {
         postId = $(this).data("id");

        const editPostTitle = $(`#title-${postId}`).text();
        const editPostContent = $(`#content-${postId}`).text();
        let editedPost ={
            id: postId,
            title: editPostTitle,
            content: editPostContent,
            user: fakeUser
        }
        console.log(editedPost)
        const request = {
            method: "PUT",
            body: JSON.stringify(editedPost),
            headers: {"Content-type": "application/json; charset=UTF-8"},
        }

        fetch(`${url}/` + postId, request)
            .then(json => console.log(json))
            .catch(err => {
                console.log('you have error plz cry', err)
            });
    })
}

function deletePostBtn(){
    $(document).on('click', '.delete-button', function (e) {
        e.preventDefault();

        const id = $(this).data("id");

        const request = {
            method: "DELETE"
        };

        fetch(`${url}/${id}`, request)
            .then(res => {
                console.log(res.status);
                // TODO: once we get a successful response, remove the post element from the DOM
                $(`#post-${id}`).remove();
            })
            .catch(error => {
                console.log(error);
            });
    })
}

