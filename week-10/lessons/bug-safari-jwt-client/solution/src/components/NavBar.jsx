// NEW: import the useContext hook.
import { useContext } from "react";
import { Link } from "react-router-dom";
// NEW: import the AuthContext
import AuthContext from "../context/AuthContext";

function NavBar() {
  // NEW: grab the value attribute from AuthContext.Provider
  const auth = useContext(AuthContext);

  // NEW: If we have an auth.user, render an "Add" link,
  // the user's username, and a logout button.
  // If we don't have an auth.user, render "Login"
  // and "Register" navigation.
  return (
    <nav>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        {auth.user ? (
          <li>
            <Link to="/add">Add</Link>
          </li>
        ) : (
          <>
            <li>
              <Link to="/login">Login</Link>
            </li>
            <li>
              <Link to="/register">Register</Link>
            </li>
          </>
        )}
      </ul>
      {auth.user && (
        <div>
          Welcome {auth.user.username}!
          <button onClick={() => auth.logout()}>Logout</button>
        </div>
      )}
    </nav>
  );
}

export default NavBar;
