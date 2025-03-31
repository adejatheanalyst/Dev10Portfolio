import { useContext } from "react";
import UserContext from "../../context/UserContext";

const ProductRow = ({ product }) => {
  const currentUser = useContext(UserContext);
  
  return (
    <li>
      {product.price} - {product.name}{" "}
      {/* If we know their name and it's customizable... */}
      {currentUser && product.customizable
        ? `Engrave this with your name, ${currentUser}!`
        : null}
    </li>
  );
};

export default ProductRow;
