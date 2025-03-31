export default function Numbers(props){
   // let numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    const {min, max} = props;

    const ListItems = [];
    for (let i = min; i <= max; i++) {
        ListItems.push(
            <li key={i}>
                <h3>{i}</h3>
            </li>
        );
    }

    return (
        <div>
            <h2>Numbers</h2>
            <ul>
                {ListItems}
            </ul>
        </div>
    );
}