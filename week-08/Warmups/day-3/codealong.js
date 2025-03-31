
let keepGoing = true;
function checkin(){
    return console.log("You are checked in.")
  }
  function checkout(){
      return console.log("You are checked out.")
  }
  function viewguests(){
      let newArray = ["Johny Appleseed", "James Bond"]
       return console.log(newArray)
  }
  function exit(){
      console.log("GoodBye!")
      keepGoing = false;
  }

  const menuOptions ={
    1: checkin,
    2:checkout,
    3: viewguests,
    4: exit
  }

let userInput = 1
while(keepGoing){
    console.log("Choose a menu option")
    console.log("1. Check In")
    console.log("2. Check Out")
    console.log("3. View Guests")
    console.log("0. Exit")
    console.log(userInput)
}

switch(userInput) {
    case 1: checkin();
    break;
    case 2:checkout();
    break;
    case 3: viewguests();
    break;
    case 0: exit();
}