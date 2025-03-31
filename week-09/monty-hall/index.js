'use strict';

// prompt-sync exports a create function
const createPrompt = require('prompt-sync');
// create a new prompt function with the create function.
const prompt = createPrompt();

// Generates a random integer.
// maxExclusive: the maximum value, not included
// Example: randomInt(5) will generate 1 of 5 values
// between 0 and 4.
function randomInt(maxExclusive) {
    return Math.floor(Math.random() * maxExclusive);
}

// Prompts the user for an integer between min and max.
// Re-prompts if the input is not a number
// or is not in the valid range.
function readInt(message, min, max) {
    let valid = false;
    let result;

    do {
        const input = prompt(message);
        result = parseInt(input, 10);
        if (isNaN(result)) {
            console.log(`'${input}' is not a number.`);
        } else if (result < min || result > max) {
            console.log(`Value must be between ${min} and ${max}.`);
        } else {
            valid = true;
        }
    } while (!valid);

    return result;
}

function getRevealedDoor(prizeDoor, playerDoor) {
    let result;
    if (prizeDoor === playerDoor) {
        // we can chose either of the two remaining doors
        result = (prizeDoor + 1 + randomInt(2)) % 3;
    } else {
        // we have only one option
        result = 3 - prizeDoor - playerDoor;
    }
    return result;
}

function playGame() {
    // prizeDoor is zero-based, 1-3 becomes 0-2
    const prizeDoor = randomInt(3);
    //console.warn(prizeDoor);

    console.log("Let's Solve the Monty Hall Problem!");
    console.log('There are three doors labeled 1-3.');

    let playerDoor = readInt('Choose a door [1-3]: ', 1, 3);
    // make playDoor zero-based as well
    playerDoor--;

    console.log(`You chose door #${playerDoor + 1}.`);

    const revealedDoor = getRevealedDoor(prizeDoor, playerDoor);

    console.log(`There's a goat behind door #${revealedDoor + 1}.`);

    const change = prompt('Switch doors [y/n]?: ').toLowerCase() === 'y';
    if (change) {
        playerDoor = 3 - playerDoor - revealedDoor;
        console.log(`You switched to door #${playerDoor + 1}`);
    }

    if (playerDoor === prizeDoor) {
        console.log('You win!');
    } else {
        console.log('You lose.');
    }
}

playGame();



