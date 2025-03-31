const form = document.querySelector("form");
let currentView = "landing";

function changeView(view) {
    for (const element of document.querySelectorAll(`.${currentView}`)) {
        element.style.display = "none";
    }
    for (const element of document.querySelectorAll(`.${view}`)) {
        element.style.display = "block";
    }
    currentView = view;
}

function showUpdate(agentId) {
    fetchAgent(agentId)
        .then(agent => {
            document.getElementById("agentId").value = agent.agentId;
            document.getElementById("firstName").value = agent.firstName;
            document.getElementById("middleName").value = agent.middleName;
            document.getElementById("lastName").value = agent.lastName;
            document.getElementById("dob").value = agent.dob;
            document.getElementById("heightInInches").value = agent.heightInInches;
            changeView("form");
        });
}

function confirmDelete(agentId) {
    fetchAgent(agentId)
        .then(agent => {
            document.querySelector(".delete ul").innerHTML = `
                <li>First Name: ${agent.firstName}</li>
                <li>Middle Name: ${agent.middleName ? agent.middleName : "N/A"}</li>
                <li>Last Name: ${agent.lastName}</li>
                <li>DOB: ${agent.dob ?? "N/A"}</li>
                <li>Height (inches): ${agent.heightInInches}</li>
            `;
            document.getElementById("deleteAgentId").value = agent.agentId;
            changeView("delete");
        });
}

function deleteAgent() {
    const agentId = document.getElementById("deleteAgentId").value;

    fetch(`http://localhost:8080/api/agent/${agentId}`, {
        method: "DELETE"
    })
        .then(response => {
            if (response.ok) {
                showList();
            } else {
                return Promise.reject("Failed to delete agent.");
            }
        })
        .catch(console.error);
}

function populateAgents(agents) {
    const table = document.querySelector(".list > table");
    const tbody = table.querySelector("tbody");
    const warning = document.querySelector(".list > div");

    if (agents.length === 0) {
        table.style.display = "none";
        warning.style.display = "block";
        return;
    }

    table.style.display = "table";
    warning.style.display = "none";

    let html = "";
    for (const agent of agents) {
        html += `<tr>
            <td>${agent.firstName}${agent.middleName ? " " + agent.middleName : ""} ${agent.lastName}</td>
            <td>${agent.dob ?? "N/A"}</td>
            <td>${agent.heightInInches}</td>
            <td>
                <button type="button" onClick="confirmDelete(${agent.agentId})">Delete</button>
                <button type="button" onClick="showUpdate(${agent.agentId})">Edit</button>
            </td>
        </tr>`;
    }

    tbody.innerHTML = html;
}

function fetchAgents() {
    fetch("http://localhost:8080/api/agent")
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            return Promise.reject();
        })
        .then(agents => populateAgents(agents))
        .catch(console.error);
}

function fetchAgent(agentId) {
    return fetch(`http://localhost:8080/api/agent/${agentId}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            return Promise.reject();
        })
        .catch(console.error);
}

function showList() {
    fetchAgents();
    changeView("list");
}

function showValidationSummary(errors) {
    let html = '<ul>';
    for (const err of errors) {
        html += `<li>${err}</li>`;
    }
    html += '</ul>';
    const validationSummary = document.getElementById("validationSummary");
    validationSummary.style.display = "block";
    validationSummary.innerHTML = html;
}

function hideValidationSummary() {
    document.getElementById("validationSummary").style.display = "none";
}

function submitForm(evt) {
    evt.preventDefault();
    evt.stopPropagation();
    hideValidationSummary();

    if (form.checkValidity()) {
        const agent = {
            firstName: document.getElementById("firstName").value,
            middleName: document.getElementById("middleName").value,
            lastName: document.getElementById("lastName").value,
            dob: document.getElementById("dob").value,
            heightInInches: document.getElementById("heightInInches").value
        };

        const agentId = document.getElementById("agentId").value;

        let url = "http://localhost:8080/api/agent";
        let method = "POST";

        // If we have an agentId, we're updating an existing agent...
        if (agentId) {
            // Add the agentId to the agent object, and change the URL and method.
            agent.agentId = agentId;
            url += `/${agentId}`;
            method = "PUT";
        }

        const config = {
            method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(agent)
        };

        fetch(url, config)
            .then(response => {
                if (response.ok) {
                    showList();
                } else {
                    return response.json();
                }
            })
            .then(errors => {
                if (errors) {
                    showValidationSummary(errors);
                }
            })
            .catch(console.error);
    }
}

// event handlers

document.getElementById("linkAgents")
    .addEventListener("click", evt => {
        evt.preventDefault();
        showList();
    });
document.getElementById("home")
 .addEventListener("click", evt => {
        evt.preventDefault();
        changeView("landing");
        });

document.getElementById("linkAgencies")
    .addEventListener("click", evt => {
        evt.preventDefault();
    });

document.querySelector(".list button")
    .addEventListener("click", () => {
        changeView("form");
    });

form.addEventListener("submit", submitForm);

document.querySelector("form button[type=button]")
    .addEventListener("click", () => {
        showList();
    });

document.getElementById("btnDelete")
    .addEventListener("click", () => {
        deleteAgent();
    });

document.getElementById("btnCancel")
    .addEventListener("click", (evt) => {
        showList();
    });

// initialize the app

function init() {
    document.querySelector(".list").style.display = "none";
    document.querySelector(".form").style.display = "none";
    document.querySelector(".delete").style.display = "none";
}

init();
