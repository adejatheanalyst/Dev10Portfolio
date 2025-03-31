import MenuSection from "./MenuSection.jsx";

export default function MenuItemsSection() {
    const data = {
        menu: [
            {
                title: "Appetizers",
                items: [
                    {
                        name: "Bruschetta",
                        description: "Grilled bread topped with fresh tomatoes, basil, garlic, and olive oil."
                    },
                    {
                        name: "Stuffed Mushrooms",
                        description: "Mushrooms filled with a savory mix of cheese, herbs, and breadcrumbs."
                    },
                    {
                        name: "Crispy Calamari",
                        description: "Lightly battered and fried squid served with marinara sauce."
                    },
                    {
                        name: "Spinach Artichoke Dip",
                        description: "Creamy blend of spinach, artichokes, and cheese, served with tortilla chips."
                    }
                ],
            },
            {
                title: "Main Courses",
                items: [
                    {
                        name: "Grilled Salmon",
                        description: "Fresh salmon fillet grilled to perfection with a lemon butter sauce."
                    },
                    {
                        name: "Chicken Parmesan",
                        description: "Breaded chicken breast topped with marinara sauce and melted mozzarella."
                    },
                    {
                        name: "Beef Tenderloin",
                        description: "Juicy beef tenderloin steak cooked to your liking with a red wine reduction."
                    },
                    {
                        name: "Vegetable Stir-Fry",
                        description: "A mix of seasonal vegetables sautéed with a savory soy-ginger sauce."
                    }
                ],
            },
            {
                title: "Desserts",
                items: [
                    {
                        name: "Chocolate Lava Cake",
                        description: "Warm chocolate cake with a gooey molten center, served with vanilla ice cream."
                    },
                    {
                        name: "New York Cheesecake",
                        description: "Classic creamy cheesecake with a graham cracker crust and berry compote."
                    },
                    {
                        name: "Tiramisu",
                        description: "Italian dessert made with layers of espresso-soaked ladyfingers and mascarpone cream."
                    },
                    {
                        name: "Lemon Sorbet",
                        description: "Refreshing and tangy lemon sorbet served in a chilled glass."
                    }
                ]
            },
        ]
    };

    return (
        <div className={"section-card"}>
            {data.menu.map((section, index) => {
                return (
                    <MenuSection key={index} title={section.title} items
                        ={section.items}/>
                )
            })}
        </div>
    )
}