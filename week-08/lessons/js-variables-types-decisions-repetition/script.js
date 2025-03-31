

console.log("Hello, from JavaScript Variables, Types, Decisions, and Repetition!");
let value;
console.log(typeof value); // undefined

value = 25;
console.log(typeof value); // number

value = "Hodgepodge";
console.log(typeof value); // string

value = true;
console.log(typeof value); // boolean

value = {};
console.log(typeof value); // object

value = [];
console.log(typeof value); // object

value = null;
console.log(typeof value); // object

let value2 = 2 / 3;
console.log(value2); // 0.6666666666666666 (promote to floating point)

value = 0.1 + 0.2;
console.log(value2); // 0.30000000000000004 (floating point errors)

value = 2 / 0;
console.log(value2); // Infinity

value2 = 2 + "5";           // string concatenation
console.log(value2);        // 25
console.log(typeof value2); // string

value2 = 2 * "5";           // math operation
console.log(value2);        // 10
console.log(typeof value2); // number

value2 = "15" - "5";        // math operation
console.log(value2);        // 10
console.log(typeof value2); // number

value2 = 2 / "Hodgepodge";  // math operation
console.log(value2);        // NaN
console.log(typeof value2); // number

console.log(value == NaN); // false
console.log(isNaN(value2)); // true

console.log("5" == 5);          // true
console.log(5.0 == 5);          // true
console.log(0 == false);        // true
console.log(1 == true);         // true
console.log(null == undefined); // true

console.log("5" == true);       // false
console.log(5 == "artichoke");  // false

// These 'ifs' evaluate to true (truthy).
console.log("These 'ifs' evaluate to true (truthy).")
console.log("======================================");

if (true) {
    console.log("true literal is true");
}

if ("Hodgepodge") {
    console.log("'Hodgepodge' literal is true");
}

if ({}) {
    console.log("object literal is true");
}

if ([]) {
    console.log("array literal is true");
}

// These 'ifs' evaluate to false (falsey).
//console.log("\nThese 'ifs' evaluate to false (falsey).")
//console.log("=======================================");
//if (false) {
    console.log("false literal is true");
//}

//if ("") {
   // console.log("empty string literal is true");
//}

//if (null) {
    //console.log("null literal is true");
//}

//if (undefined) {
   // console.log("undefined literal is true");
//}
// String literals
let name = "Frank Ocean";      // Literals are delimited with either double quotes
name = 'Frank Ocean';
console.log(name);         // or single quotes.

let helloWorld = "你好，世界！";
console.log(helloWorld); // unicode

// Choose the delimiter that makes it easy to express the literal. 
let message = "She doesn't like y'all.";               // If it contains a ", use ''.             
message = 'He keeps doing those stupid "air quotes".'; // If it contains a ', use ""
console.log(message);
// Delimiters and special characters can be escaped.
let message2 = 'She doesn\'t like y\'all.';           
message = "He keeps doing those stupid \"air quotes\".";
message = "This message\nspans\nmultiple\nlines and contains\ttabs.";
console.log(message2)
// get the number of UTF-16 code units (characters, roughly) with a string's length property
let codeUnitCount = name.length;      // 14
codeUnitCount = "你好，世界！".length;
console.log(codeUnitCount);// 6

let name2 = "Nomi";
let petCount = 3;
let petType = "dogs";
let verticalJump = 37.68;

let message3 = `${name2} has ${petCount} ${petType} and can jump ${verticalJump} inches vertically.`;

console.log(message3); // Nomi has 3 dogs and can jump 37.68 inches vertically.

let a = 2.33;
let b = -1.111;

console.log(`${a} + ${b} = ${a + b}`); // 2.33 + -1.111 = 1.219
// i is not visible here (err).
function loop() {

    // i is not visible here (err).

    for (let i = 0; i < 3; i++) {
        // i is only visible in this block.
        console.log(i);
    }

    // i is not visible here (err).
}

// i is not visible here (err).

const value3 = "sesame oil";
console.log(value3); // sesame oil

// ERROR:
// TypeError: Assignment to constant variable.
value4 = "grapeseed oil";
console.log(value4);


const animalCount = 2;
const canFly = true;

if (animalCount > 1 && canFly) { // true
    console.log("a flock");      // executes
}

if (!canFly) {                   // false
    console.log("walker");       // doesn't execute
    console.log("swimmer");
    console.log("crawler");
}

const animalCount2 = 1;
const canFly2 = false;

if (animalCount2 > 1 && canFly2) { // false
    // doesn't execute
    console.log("a flock");
} else {
    // executes
    console.log("could be a single bird");
    console.log("a herd?");
    console.log("either they can't fly");
    console.log("or there's one or less animals");
}

if (!canFly2) { // true
    // executes
    console.log("walker");
    console.log("swimmer");
    console.log("crawler");
} else {
    // doesn't execute
    console.log("plane, bird, dragonfly?")
}
const packageWeight = 0.55;

if (packageWeight > 100.0) {      // check first condition
    console.log("too big for standard shipping");
} else if (packageWeight < 1.0) { // check a second condition
    console.log("too small. send a letter.");
} else {                          // the `else` clause executes if all other conditions are false.
    console.log("can ship");
}

const color = "orange";

if (color === "red") {
    console.log("red's compliment is green");
} else if (color === "blue") {
    console.log("blue's compliment is orange");
} else if (color === "yellow") {
    console.log("yellow's compliment is purple");
} else if (color === "green") {
    console.log("green's compliment is red");
} else if (color === "orange") {
    console.log("orange's compliment is blue");
} else if (color === "purple") {
    console.log("purple's compliment is yellow");
} else {
    console.log("I don't know that color");
}

const place = 2;
let ribbonColor;

// 1. switch keyword, then an expression (often a variable) in parentheses, followed by a block
switch (place) {
    case 1: // 2. case keyword, then a literal value, then colon.
        // if the switch's expression resolves to the case's value,
        // all code nested inside the case executes.
        ribbonColor = "blue";
        console.log("first place!");
        break; // 3. break keyword, immediately exits the switch's block
    case 2:
        ribbonColor = "red";
        console.log("second place!");
        break;
    case 3:
        ribbonColor = "white";
        console.log("third place!");
        break;
    default: // 4. default keyword, executes when no other case matched
        ribbonColor = "unknown";
}

console.log(ribbonColor);

const message5 = "Visit Mars.";
let index = 0;

while (index < message5.length) {
    console.log(message5.charAt(index));
    index = index + 1;
}
let r = 0;
do {
    r = Math.random();
    console.log(r);
} while (r > 0.1); // ~10% chance to exit

/*
initialize: one-time set-up before looping begins 
condition: a boolean expression, looping continues while it evaluates to true 
after-each-loop: operations that occur after each loop

for (<initialize> ; <condition>; <after-each-loop>) {

}
*/

const message4 = "Looping...";

// Standard format
for (let index = 0; index < message4.length; index++) {
    console.log(message4.charAt(index));
}

// This loop is formatted to demonstrate the clauses.
// The format isn't standard and may be confusing to a Java developer.
for (let index = 0;               // initialize
    index < message4.length; // condition
    index++                   // after each loop
) {
    console.log(message4.charAt(index));
}

for (let i = 0; i < 5; i++) {
    console.log(`Number: ${i}`);
    if (i < 3) {
        // jump to the next loop
        continue;
    }
    console.log("Are we there yet?");
}
console.log("We're there!");












