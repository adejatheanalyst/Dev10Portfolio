import Product from "./Product";

const products = [
  { price: "$49.99", customizable: true, name: "Football" },
  { price: "$9.99", customizable: true, name: "Baseball" },
  { price: "$29.99", customizable: false, name: "Basketball" },
  { price: "$99.99", customizable: true, name: "iPod Touch" },
  { price: "$399.99", customizable: false, name: "iPhone 5" },
  { price: "$199.99", customizable: true, name: "Nexus 7" },
];

const ProductList = () =>
  products.map((product, index) => (
    <Product key={index} product={product} />
  ));

export default ProductList;
