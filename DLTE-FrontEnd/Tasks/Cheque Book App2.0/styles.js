window.onload=()=>{
    document.getElementsByTagName("iframe")[0].style.height='100vh'
}

const alter=()=>{
    var page = document.getElementById("user-source").value
    page+=".html"
    document.getElementsByTagName("iframe")[0].src=page
}

