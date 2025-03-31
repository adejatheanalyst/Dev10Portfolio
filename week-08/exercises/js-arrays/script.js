const myArray = ["a", "b", "c"]
// () => {}
const myMappedArray = myArray.map((letter) => {return letter.toUpperCase()})


console.log(myMappedArray)

//sorting// filer

const myFilter = myArray.filter(letter => letter === "c")
console.log(myFilter);

//splice and slice
// myArray.slice(0,1) // does not change og array
// myArray.splice(0,1) // does change og array