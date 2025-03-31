import { useLocation } from "react-router-dom";

function Error({ msg }) {
  const location = useLocation();

  return (
    <p>
      🙅🏾‍♂️ Error{" "}
      {location.state ? ` - ${location.state.msg}` : ""}
      {msg}
    </p>
  );
}

export default Error;
