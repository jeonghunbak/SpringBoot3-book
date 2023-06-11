//delete
const deleteButton = document.getElementById('delete-btn');

if(deleteButton){
    deleteButton.addEventListener('click', ev => {
        let id = document.getElementById('article-id').value;

        fetch(`/api/articles/${id}`, {
            method : 'DELETE'
        })
        .then(()=>{
            alert("DELETE SUCCESS");
            location.replace('/articles');
        });
    });
}

//modify
const modifyButton = document.getElementById('modify-btn');

if(modifyButton){
    modifyButton.addEventListener('click', ev => {
        let param = new URLSearchParams(location.search);
        let id = param.get('id');

        fetch(`/api/articles/${id}`, {
            method : 'PUT',
            headers : {
                'Content-Type' : 'application/json'
            },
            body : JSON.stringify({
                'title' : document.getElementById('title').value,
                'content' : document.getElementById('content').value
            }),
        })
        .then(()=>{
            alert('Modify Success');
            location.replace(`/articles/${id}`);
        });
    });
}