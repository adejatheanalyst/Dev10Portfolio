import { useEffect, useState } from "react";
import ProductList from "./components/ProductList";
import UserContext from "./context/UserContext";

function App() {
  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
    setTimeout(() => {
      setCurrentUser("Jim");
    }, 200);
  }, []);

  return (
    <UserContext.Provider value={currentUser}>
      <ProductList />
    </UserContext.Provider>
  );
}

export default App;
