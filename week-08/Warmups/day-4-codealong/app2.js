 const button = document.getElementById("change-message-form");
 button.addEventListener("submit", (evt) => {
     evt.preventDefault();
     const text = document.getElementById("billboard-message").value;
     document.getElementById("billboard").innerText = text;
     console.log(text);
     document.getElementById("billboard-message").value = "";
 });

document.getElementById("border-button-1")
.addEventListener("click", () => {
    document.getElementById("billboard").classList.add("border-style-1");
    document.getElementById("billboard").classList.remove("border-style-2");
});

const handleBorderButton2 = () => {
    document.getElementById("billboard").classList.add("border-style-2");
    document.getElementById("billboard").classList.remove("border-style-1");
};