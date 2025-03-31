const capsuleContainer = document.getElementById('capsules');
const messages = document.getElementById('messages');
const BACKENDURL = "http://localhost:8080/api/capsule"

/**
 * Displays the capsules in the capsule container.
 */
async function displayCapsules() {// async will need to be annotated to use await
  // TODO Use the Fetch API to get the capsules from the API.
    const response = await fetch(BACKENDURL);
    if(response.status !== 200){
      console.log(`There was a error: ${response.status}`);
      return Promise.reject("response is not 200 OK")
    }
    const capsules = await response.json();
    // const count = capsuleData.length;
    // console.log(capsuleData)
    // console.log(count)
    // const capsules = [];
    // capsules.push(...capsuleData)//insert each array(data element) 
    console.log(capsules)
    const capsulesHtml = capsules.map((capsule) => `
    <div>
      <span class="badge badge-pill ${capsule.guestName ? 'badge-warning' : 'badge-success'}">Capsule #${capsule.capsuleNumber}</span>
      &nbsp;
      <span>${capsule.guestName ? capsule.guestName : 'Unoccupied'}</span>
    </div>
  `);
  capsuleContainer.innerHTML = capsulesHtml.join('');
}




/**
 * Resets the check-in form.
 */
function resetCheckInForm() {
  document.getElementById('guestName').value = '';
  document.getElementById('checkInCapsuleNumber').value = '';
}

/**
 * Resets the check-out form.
 */
function resetCheckOutForm() {
  document.getElementById('checkOutCapsuleNumber').value = '';
}

/**
 * Displays the error messages in the messages container.
 * @param {string[]} errorMessages - The error messages to display.
 */
function displayErrorMessages(errorMessages) {
  messages.className = 'alert alert-danger';
  errorMessagesHtml = errorMessages.map((errorMessage) => `<li>${errorMessage}</li>`);
  messages.innerHTML = `
    <p>The following errors were found:</p>
    <ul>${errorMessagesHtml.join('')}</ul>
  `;
}

/**
 * Displays the success message in the messages container.
 * @param {string} message - The success message to display.
 */
function displaySuccessMessage(message) {
  messages.className = 'alert alert-success';
  messages.innerText = message;
}

/**
 * Handles the check-in form submission.
 * @param {Event} evt - The event object.
 */
async function checkIn(evt) {
  evt.preventDefault();

  const guestName = document.getElementById('guestName').value.trim();
  const capsuleNumber = parseInt(
    document.getElementById('checkInCapsuleNumber').value,
    10
  );

  // TODO Use the Fetch API to check in the guest using the API.
  const newCapsule = {
    guestName,
    capsuleNumber
  };
  const init = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
    },
    body: JSON.stringify(newCapsule),
  };
  
  const response = await fetch(BACKENDURL, init);
  // TODO If the response status is 400, display the error messages using the `displayErrorMessages()` function.
  if(response.status === 400){
    const errors = await response.json();
    displayErrorMessages(errors)
    return;
  } else if(response.status !== 201){
    displayErrorMessages(errors)
    return Promise.reject("Response not 201")
  }
  const added = await response.json();
  if(response.status === 201){
    displaySuccessMessage(` ${guestName} checked in to ${capsuleNumber} successfully`)
    displayCapsules(added);
    resetCheckInForm();
  }
  

 
}

/**
 * Handles the check-out form submission.
 * @param {Event} evt - The event object.
 */
async function checkOut(evt) {
  evt.preventDefault();

  let capsuleNumber = parseInt(
    document.getElementById('checkOutCapsuleNumber').value,
    10
  );

  if (isNaN(capsuleNumber)) {
    displayErrorMessages(['Capsule number is required.']);
    return;
  }
  // TODO Use the Fetch API to check out the guest using the API.
  const response = await fetch(`http://localhost:8080/api/capsule/${capsuleNumber}` ,{
    method: "DELETE",
  });
  // TODO If the response status is 400, display the error messages using the `displayErrorMessages()` function.
  if(response.status === 400){
    const error = await response.json()
    displayErrorMessages(error)
  }else if (response.status === 204){// TODO If the response status is 204, display a success message using the `displaySuccessMessage()` function.
    displaySuccessMessage(`Check Out was successful. Capsule ${capsuleNumber} is Unoccupied `)
    displayCapsules();
    resetCheckOutForm();
  }else{
    displayErrorMessages(`Deletion Failed: ${response.status}`)
  }
  
}

displayCapsules();
