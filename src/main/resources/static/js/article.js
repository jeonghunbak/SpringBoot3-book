//delete
const deleteButton = document.getElementById('delete-btn');

if(deleteButton){
    deleteButton.addEventListener('click', ev => {
        let id = document.getElementById('article-id').value;

        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
        .then(()=>{
            alert("DELETE SUCCESS");
            location.replace('/articles');
        });
    });
}