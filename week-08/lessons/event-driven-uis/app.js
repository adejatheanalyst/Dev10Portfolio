// evt is the event object.
function handleClick(evt) {
    // 1. Remove any color-ish classes from body.
    document.body.classList.remove(
        "red-ish", "blue-ish", "yellow-ish");
    
    // 2. Add the class from the button that was clicked.
    // evt.target is the element that triggered the event.
    document.body.classList.add(evt.target.className);
}
// 1. Grab a reference to the element with its id.
const fontSize = document.getElementById("fontSize");

// 2. Assign an event listener to the change event.
fontSize.onchange = function (evt) {

    // 3. Change the body element's fontSize to
    // the value selected in the `select` element.
    document.body.style.fontSize = evt.target.value;
};


// 1. Grab list items to update.
const lis = document.querySelectorAll("ul>li");
// 2. Grab the animal input.
const animal = document.getElementById("animal");

// 3. Register a keyup listener.
// The event name does not include the "on" prefix.
// In this case, we don't bother with an event parameter because
// we don't need the details.

animal.addEventListener("keyup", () => {

    // 4. As we type, grab the current input value and trim it.
    let message = animal.value.trim();
    if (message.length === 0) {
        message = "...";
    }
    if (message === "Focus"){
        message = "Concentration..."
    }

    // 5. Update the list items with the trimmed value.
    for (const li of lis) {
        li.innerText = message;
    }
});

function handleSubmit(evt) {

    // 1. preventDefault() short-circuits default event behavior,
    // in this case form submission. The form isn't submitted.
    evt.preventDefault();

    // 2. Even though submission is cancelled, the event continues
    // to "bubble up".
    // stopPropagation() stops the bubble. The event is over.
    evt.stopPropagation();

    // 3. Swap username and password.
    const password = document.getElementById("password").value;
    document.getElementById("password").value = document.getElementById("username").value;
    document.getElementById("username").value = password;
}

// 1. Grab x and y spans for use later.
const spnX = document.getElementById("spnX");
const spnY = document.getElementById("spnY");

// 2. This is our mouse move listener.
// It's not currently in use. It doesn't get registered until
// we check the checkbox. Any time the checkbox is unchecked we
// remove the listener.
function handleMouseMove(evt) {

    // 3. Mouse events include coordinate information.
    // Display it in the spans.
    spnX.innerText = evt.pageX;
    spnY.innerText = evt.pageY;
}

// 4. Grab the checkbox.
const chkTracking = document.querySelector('input[type="checkbox"]');

// 5. Register a change listener.
chkTracking.onchange = () => {
    const noTrackMessage = "Not tracking...";

    if (chkTracking.checked) {
        // 6. When the checkbox is changed to checked, add mouse tracking.
        document.body.addEventListener("mousemove", handleMouseMove);
    } else {
        // 7. When the checkbox is changed to unchecked, remove mouse tracking.
        document.body.removeEventListener("mousemove", handleMouseMove);
        spnX.innerText = noTrackMessage;
        spnY.innerText = noTrackMessage;
    }
};


