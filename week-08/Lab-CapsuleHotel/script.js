



const checkInButton = document.getElementById("check-in-button")

checkInButton.addEventListener("click", checkin);
let isOccupied = false;

function checkin(evt) {
    evt.preventDefault();
    checkCapsule()
    if (isOccupied === true) {
        return;
    }
    const capsuleNumber = document.getElementById("capsule-number").value
    const capsuleSelect = document.getElementById(capsuleNumber)
    capsuleSelect.innerText = document.getElementById("guest-name").value
        if (capsuleSelect.classList.contains("data")) {
            capsuleSelect.classList.remove("data");
            capsuleSelect.classList.add("occupied");
            isOccupied = true// isOccupied = true;
        }
        document.getElementById("capsule-number").value = "";
        document.getElementById("guest-name").value = "";
}
function checkCapsule() {
    const capsuleNumber = document.getElementById("capsule-number").value
    const capsuleSelect = document.getElementById(capsuleNumber)
    if (capsuleSelect.classList.contains("occupied")) {
        const error = document.getElementById("error-message")
        error.innerText = "Capsule is Already occupied!"
        isOccupied = true;
    } else {
        const error = document.getElementById("error-message")
        error.innerText = ""
    }
}


const checkOutButton = document.getElementById("check-out-button")




checkOutButton.addEventListener("click", checkout);

function checkout(evt) {
    evt.preventDefault();
    const capsuleNumber = document.getElementById("capsule-number2").value
    const capsuleSelect = document.getElementById(capsuleNumber)
    capsuleSelect.innerText = "Unoccupied"
    capsuleSelect.classList.remove("occupied");
    capsuleSelect.classList.add("data");
    document.getElementById("capsule-number2").value = ""
    console.log(capsuleSelect);
}


