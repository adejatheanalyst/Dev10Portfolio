// async function getAll() {
//     fetch("http://localhost:8080/sighting")
//       .then((response) => response.json())
//       .then((json) => console.log(json));
//   }
//   async function getAll() {
//     const init = {
//       method: "GET",
//       headers: {
//         Accept: "application/json",
//       },
//     };
  
//     fetch("http://localhost:8080/sighting", init)
//       .then((response) => {
//         if (response.status !== 200) {
//           console.log(`Bad status: ${response.status}`);
//           return Promise.reject("response is not 200 OK");
//         }
//         return response.json();
//       })
//       .then((json) => console.log(json));
//   }
async function getAll() {
    const init = {
      method: "GET",
      headers: {
        Accept: "application/json",
      },
    };
  
    const response = await fetch("http://localhost:8080/sighting", init);
    if (response.status !== 200) {
      console.log(`Bad status: ${response.status}`);
      return Promise.reject("response is not 200 OK");
    }
    const json = await response.json();
  
    // Add data to the DOM.
    let html = "";
    for (const sighting of json) {
      html += `<div><strong>${sighting.bugType}</strong> ${sighting.description}</div>`;
    }
    document.getElementById("results").innerHTML = html;
  }
  

    async function getById() {
        const init = {
          method: "GET",
          headers: {
            Accept: "application/json",
          },
        };
      
        // includes a sightingId
        const response = await fetch("http://localhost:8080/sighting/2", init);
        if (response.status === 404) {
          console.log("That sighting doesn't exist.");
          return;
        } else if (response.status !== 200) {
          console.log(`Bad status: ${response.status}`);
          return Promise.reject("response is not 200 OK");
        }
        const json = await response.json();
        console.log(json);
    //   let html = "";
    // for (const sighting of json) {
    //   html += `<div><strong>${sighting.bugType}</strong> ${sighting.description}</div>`;
    // }
    // document.getElementById("results").innerHTML = html;
      
  }
  

async function post() {
  // 1. An object to send with the request.
  const sighting = {
    bugType: "Mosquito",
    description: "mosquitos are jerks",
    date: "2020-08-04",
    order: "Diptera",
    interest: 0.0,
  };

  const init = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
    },
    body: JSON.stringify(sighting),
  };

  const response = await fetch("http://localhost:8080/sighting", init);
  if (response.status === 400) {
    const errors = await response.json();
        // Add error messages to the DOM.
        const errorsHtml = errors.map((error) => `<li>${error}</li>`);
        document.getElementById("results").innerHTML = `
                <p>The following errors were found:</p>
                <ul>${errorsHtml.join("")}</ul>`
                ;
    return;
  } else if (response.status !== 201) {
    console.log("Failed to save the sighting.");
    return Promise.reject("response is not 201 Created");
  }
  const json = await response.json();
  console.log(json);
    }


async function put() {
    const sighting = {
      sightingId: 4,
      // NEW Remove the bugType property from the sighting object
      // to trigger a 400 response from the API.
      bugType: "Mosquito",
      description: "mosquitos are jerks",
      date: "2020-08-04",
      order: "Diptera",
      interest: 0.0,
    };
  
    const init = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(sighting),
    };
  
    const response = await fetch("http://localhost:8080/sighting/4", init);
  
    if (response.status === 404) {
      console.log("Sighting not found.");
      // NEW If the response status is 400
      // the API has returned an array of error messages.
    } else if (response.status === 400) {
      const errors = await response.json();
      console.log(errors);
    } else if (response.status === 204) {
      console.log("Sighting updated!");
    } else {
      console.log(
        `Sighting id ${sighting.sightingId} update failed with status ${response.status}.`
      );
    }
  }
  
  

// `delete` is a JavaScript keyword
// so we use `doDelete` instead.
async function doDelete() {
    const response = await fetch("http://localhost:8080/sighting/4", {
      method: "DELETE",
    });
    if (response.status === 204) {
      console.log("Delete success.");
    } else if (response.status === 404) {
      console.log("Sighting not found.");
    } else {
      console.log(`Delete failed with status: ${response.status}`);
    }
  }
  
