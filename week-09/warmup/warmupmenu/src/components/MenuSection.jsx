function MenuSection(props) {
    const { title, items } = props;
// can add title and items to MenuSection({name, description}
    return (
        <div>
            <h2>{title}</h2>
            <ul>
                {items.map((item, index) => {
                    return (
                        <li key={index}>
                            <h3>{item.name}</h3>
                            <p>{item.description}</p>
                        </li>
                    )
                })}
            </ul>
        </div>
    )
}

export default MenuSection;