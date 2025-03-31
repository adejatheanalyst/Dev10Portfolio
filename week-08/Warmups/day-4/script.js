// onclick function - when the button is clicked, this function is called (or addEventListener, hook to an id  )

const button = document.getElementById("button");

button.addEventListener("click", changeBorder);

const el = document.querySelector('#billboard');


function changeBorder() {
//   alert ("Hello World!");
//   console.log("Hello World!");

// 1. Remove any color-ish classes from body.
    el.classList.remove(
        "border", "border-blue", "border-green", "border-red");

    let randomClass = generateQuote();
// 2. Add the class from the button that was clicked.
// evt.target is the element that triggered the event.
    el.classList.add(randomClass);
}

const classes = [
    "border",
    "border-blue",
    "border-green",
    "border-red",
];

// TODO Write your code here.

generateQuote = () => {
    const randomIndex = Math.floor(Math.random() * classes.length);
    // gets random number which will decide which quote to display
    console.log(randomIndex);

    return classes[randomIndex];
}

