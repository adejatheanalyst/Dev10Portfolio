import { useEffect, useState } from "react";
import ProductList from "./components/ProductList";

function App() {
  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
    setTimeout(() => {
      setCurrentUser("Jim");
    }, 200);
  }, []);

  return <ProductList currentUser={currentUser} />;
}

export default App;
