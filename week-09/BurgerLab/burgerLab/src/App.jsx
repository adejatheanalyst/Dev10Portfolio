import React, {useEffect, useState} from 'react'
import BurgerIngredients from './components/BurgerIngredients'
import ViewBurger from './components/ViewBurger';
import './App.css'
import './index.css'

function App() {
    const [ingredient, setIngredient] = useState([]);
    function handleAddIngredient(newIngredient){
        setIngredient([newIngredient, ...ingredient]);
    }
    function  clearIngredients(){
        setIngredient([]);
    }
    function submitOrder(){
        alert("Your Order was submitted!")
        clearIngredients()
    }
    useEffect(() => {
        if(ingredient.length > 0){
            console.log(ingredient)
        }else{
            console.log("no ingredients")
        }
    },[ingredient])

    return (
        <div id="app">
            <h1>Burger Builder</h1>
            <BurgerIngredients handleAddIngredient={handleAddIngredient} />
            <ViewBurger newIngredient={ingredient} clearIngredients = {clearIngredients} submitOrder={submitOrder}/>
        </div>)
}

export default App