import React, {useEffect, useState} from "react";


export default function BurgerIngredients({handleAddIngredient}) {

        return (
            <>
                <h2>Pick your ingredients here...</h2>
                <button onClick={() => handleAddIngredient("bun")} className="btn btn-primary bun-btn" type="submit">Bun</button>
                <button onClick={() => handleAddIngredient("patty")} className="btn btn-primary patty-btn" type="submit">Patty</button>
                <button onClick={() => handleAddIngredient("cheese")} className="btn btn-primary cheese-btn" type="submit">Cheese</button>
                <button onClick={() => handleAddIngredient("tomato")} className="btn btn-primary tomato-btn" type="submit">Tomato</button>
                <button onClick={() => handleAddIngredient("lettuce")} className="btn btn-primary lettuce-btn" type="submit">Lettuce</button>
            </>
        )

}