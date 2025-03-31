import {Link, NavLink} from "react-router-dom";

function Nav() {

    // function handleAdd() {
    //     setView("form");
    // }

    // function handleList(evt) {
    //     evt.preventDefault();
    //     setView("list");
    // }

    return (
        <div className="d-flex align-items-center">
            <ul className="nav my-4">
                <li className="nav-item">
                    <NavLink id="linkAgents" to="/agents" className="nav-link">Agents</NavLink>
                </li>
                <li className="nav-item">
                    <NavLink id="linkAgencies" to="/agencies" className="nav-link" onClick={evt => evt.preventDefault()}>Agencies</NavLink>
                </li>
                <li className="nav-item">
                    <NavLink id="linkLanding" to="/" className="nav-link">Home</NavLink>
                </li>
            </ul>
            {"linkAgents" &&
                <div className="d-flex flex-grow-1 justify-content-end">
                    <Link id="btnAdd" className="btn btn-primary" to ="/agents/add">Add Agent</Link>
                </div>}
        </div>
    );
}

export default Nav;