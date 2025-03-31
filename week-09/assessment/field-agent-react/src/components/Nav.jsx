function Nav({ view, setView }) {

    function handleAdd() {
        setView("form");
    }

    function handleList(evt) {
        evt.preventDefault();
        setView("list");
    }

    return (
        <div className="d-flex align-items-center">
            <ul className="nav my-4">
                <li className="nav-item">
                    <a id="linkAgents" href="#" className="nav-link" onClick={handleList}>Agents</a>
                </li>
                <li className="nav-item">
                    <a id="linkAgencies" href="#" className="nav-link" onClick={evt => evt.preventDefault()}>Agencies</a>
                </li>
            </ul>
            {view !== "form" &&
                <div className="d-flex flex-grow-1 justify-content-end">
                    <button id="btnAdd" className="btn btn-primary" onClick={handleAdd}>Add Agent</button>
                </div>}
        </div>
    );
}

export default Nav;