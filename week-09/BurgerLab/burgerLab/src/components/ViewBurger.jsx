import React from "react";

export default function ViewBurger({newIngredient, clearIngredients, submitOrder}) {
    const getIngredientClass = (ingredient) => {
        switch (ingredient) {
            case "bun":
                return "bun-btn";
            case "patty":
                return "patty-btn";
            case "cheese":
                return "cheese-btn";
            case "tomato":
                return "tomato-btn";
            case "lettuce":
                return "lettuce-btn";
            default:
                return "btn-primary"; // Default class
        }
    };
    return (
        <>
            <h2>Place your Burger Order:</h2>
            <div className={'ingredients-container'}>
                {newIngredient.length > 0 ? (newIngredient.map((ingredient, index) => (
                        <button key={index}
                                className={`btn ingredient-btn ${getIngredientClass(ingredient)}`}
                                style={{
                                    marginBottom: "5px",
                                    display: "block",
                                    }}>
                            {ingredient}
                        </button>
                    ))) : (<p>No ingredients selected</p>)}
            </div>
            <div>
                <button onClick={clearIngredients} type="button" className="btn btn-light">
                    Clear
                </button>
                <button onClick={submitOrder} type="button" className="btn btn-light">
                    Order
                </button>
            </div>
        </>
    );
}
