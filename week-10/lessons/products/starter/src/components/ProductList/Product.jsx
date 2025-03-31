const ProductRow = ({ product, currentUser }) => (
  <li>
    {product.price} - {product.name}{" "}
    {/* If we know their name and it's customizable... */}
    {currentUser && product.customizable
      ? `Engrave this with your name, ${currentUser}!`
      : null}
  </li>
);

export default ProductRow;
