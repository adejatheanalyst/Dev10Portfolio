const fontSize = document.getElementById("fontSize");

fontSize.onchange = function (evt) {
    document.body.style.fontSize = evt.target.value;
};
