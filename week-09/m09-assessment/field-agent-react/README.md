# M09 Assessment Test Plan

## Field Agent API Test Plan
## Edit Functionality
-[X] Navigate to agents list
-[X] Click Edit on an agent
-[X] URL suffix changes to /agents/edit/{a number}
-[X] h1 displays Edit Agent (optionally includes agent's name, etc)
-[X] See agent form pre-populated with that agent's attributes
-[X] Click link to Add Agent
-[X] URL suffix changes to /agents/add
-[X] h1 displays Add an Agent.
-[X] Form inputs are cleared
-[X] Click browser's back button
-[X] URL suffix changes to /agents/edit/{a number}
-[X] h1 displays Edit Agent (optionally includes agent's name, etc)
-[X] See agent form pre-populated with that agent's attributes
-[X] Delete first name and last name, set height to 10, set dob to yesterday
-[X] Submit form
-[X] Errors display above form
-[x] Fill in first name, last name, height, and dob to valid values
-[x] Submit form
-[x] Navigated to list view
-[x] Confirm changes to agent in list view
-[x] Click Edit on an agent
-[x] Click Cancel button
-[x] Navigated back to list view

## Delete Functionality
-[x] Navigate to agents list
-[x] Click Delete on an agent
-[x] URL suffix changes to /agents/delete/{a number}
-[x] Details of agent are displayed
-[x] Click Cancel
-[x] Navigated back to list view
-[x] Click Delete on an agent
-[x] Click confirmation button
-[x] Automatically navigated back to list
-[x] Confirm agent is deleted from list

## Not Found

-[x] Manually change URL suffix to /asdfasdf
-[x] Not Found message is displayed

## React Router
-[x] Inspect code to confirm that react router is being used

 # Reading Requirements
## Update Agent:
-[x] Replace the Edit button with a React Router Link and style it appropriately. 
    - Give it the proper to prop: /agents/edit/:id.
-[x] Link is clicked, changing the browser URL to /agents/edit/:id.
-[x] AgentForm renders.
-[x] Inside AgentForm, use the useParams hook to fetch the id from the URL.
-[x] Use the useEffect hook and fetch to fetch a specific agent from the Field Agent API.
-[x] On success, use the useState hook to set the agent.
-[x] On failure, navigate back to the agent list.
-[x] On cancel, navigate back to the agent list. (Cancel should also be a link.)
-[x] On submit, make a PUT request for update, not a POST.
-[x] On submit failure, display any validation failures.
-[x] On submit success, navigate back to the agent list.
## Delete Agent:
-[x] Replace the Delete button with a React Router Link and style it appropriately. 
        - Give it the proper to prop: /agents/delete/:id. 
        - Create a ConfirmDelete component.
-[x] Link is clicked, changing the browser URL to /agents/delete/:id.
-[x] ConfirmDelete renders.
-[x] Inside ConfirmDelete, use the useParams hook to fetch the id from the URL.
-[x] Use the useEffect hook and fetch to fetch a specific agent from the Field Agent API.
-[x] On success, use the useState hook to set the agent.
-[x] On failure, navigate back to the agent list.
-[x] On cancel, navigate back to the agent list. (Cancel should also be a link.)
-[x] On submit, delete the agent in the Field Agent API.
-[x] On submit success or failure, navigate back to the agent list.
# Technical Requirements
-[X] Use fetch for async HTTP.
-[X] Use React Router to implement the client-side routes.
-[X] Use React Router's useNavigate hook to programmatically redirect users and pass state data between components, 
        - useLocation hook to read state data passed from other components, 
        - and useParams hook to access parameters, paths, and other data.
-[X] You are not allowed to change the Field Agent HTTP Service or database
        - (unless there's a confirmed bug and your instructor approves).
-[X] Use a CSS framework.
## Stretch Goals
-[_] Improve navigation.
-[X] Add client-side routes and placeholder components for all of the top-level sections of the application.
-[_] Add an alias CRUD UI.
-[_] Add an agency CRUD UI.
-[_] Show and edit relationships between agents and agencies.


 
 
 
 
 
 
 


