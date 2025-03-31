# Personal Solar Farm

-[x] create user:
    - db: users table w/ username & password
    - backend: user model, repo, repo test, service, service test, controller, controller test; all only need create method
    - frontend: signup form, nav link to signup form,

-[x] login:
    - backend: repo needs findByUsername, service needs findByUsername, controller needs POST /login (returns   user or 403)
    - frontend: login form, nav link to login form, show error messages on error, console.log(user) on success

-[x] 3 reflect logged in state in frontend:
    - on successful login, navigate("/").
    - save user in state: must live in App so it can flow down to list and form
    - show welcome message in header if logged in
    - if logged in show logOut link, if logged out show sign up & log in links

-[x] Site ends:
    - refresh does not log you out.
    - save user into local storage
    - load user from local storage after refresh
-[x] Todos:
    - localstorage blink.
    - save user into local storage
    - load user from local storage after refresh
-[x] Unique username:
    - validate at service level.
-[x] want to connect solar panels to user:
    - only show panels for that user.
    -added user_id fk to solarpanel table
    -modified panelRepo to work with user_id
    -add user must exist validation
    -modified service tests 
-[x] allow users to view thier own solar panels and add to their solar   panels
- only show panels for that user.
[x] protecting front end routes
- if you visit login or sign up pages via url bar while logged in, redirect
-if you visit mypanels or add panels pages via url bar while logged out, redirect
[x] protecting front end routes and component changes
-use jwt to protect components from being edited
-cleaned up code and fixed any issues with tests.
[x] jwt decoding
[x]Secure database
-make sure password is not stored in database
-hash password
-backend
used Bcrypt to hash password
     
 