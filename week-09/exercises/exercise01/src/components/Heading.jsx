export default function Heading(props) {
    const {title, title2, title3} = props;

    return <div className={"title"}>
        <h1>{title}</h1>
        <h2>{title2}</h2>
        <h3>{title3}</h3>
    </div>


}