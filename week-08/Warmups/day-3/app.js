console.log("Simple Menu: InterFace")

//let userInput = 2
//while(userInput > 0){
//console.log("Choose a menu option");
//console.log("1. Check In");
//console.log("2. Check Out");
//console.log("3. View Guests");
//console.log("0. Exit");

const menuOptions ={
    1: {title:"1. Check In", handler: checkin, },
    2: {title:"2. Check Out", handler: checkout,},
    3: {title:"3. View Guests", handler: viewguests,},
    4: {title:"0. Exit", handler: exit,},
  }

let userInput = 1


    function handleUserInput(){
// for (const menuOption in menuOptions){
//     console.log('${menuOption}: ${menuOptions[menuOption].title}')
    
// }
// menuOptions[userInput].handler()
       switch(userInput) {
           case 1: checkin();
           break;
           case 2:checkout();
           break;
           case 3: viewguests();
           break;
           case 0: exit();
   }
   }

function checkin(){
  return console.log("You are checked in.");
}
function checkout(){
    return console.log("You are checked out.");
}
function viewguests(){
    console.log("Current Guests:");
    let newArray = ["Johny Appleseed", "James Bond","Tiffany Hill"]
     return console.log(newArray);
}
function exit(){
    console.log("GoodBye!");
}

handleUserInput()