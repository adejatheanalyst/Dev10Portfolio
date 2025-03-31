import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

const DEFAULT_SIGHTING = {
  sightingId: 0,
  bugType: "",
  order: {
    bugOrderId: 0,
    name: "",
    description: "",
  },
  description: "",
  date: "",
  interest: 0,
  imageUrl: "",
};

function SightingForm() {
  const [sighting, setSighting] = useState(DEFAULT_SIGHTING);
  const [orders, setOrders] = useState([]);

  const navigate = useNavigate();

  const { id } = useParams();

  useEffect(
    () => {
      fetch('http://localhost:8080/order')
        .then(response => response.json())
        .then(data => setOrders(data));
    },
  
    // Dependency array - only run this once when the component is initially loaded
    []
  );

  useEffect(
    () => {
      // Only do this if there is an `id`
      if (id) {
        fetch(`http://localhost:8080/sighting/${id}`)
          .then((response) => {
            if (response.status !== 200) {
              return Promise.reject("sighting fetch failed");
            }
            return response.json();
          })
          .then((data) => setSighting(data))
          .catch(console.log);
      }
    },

    // Dependency array - only run this in response to any `id`
    [id]
  );

  const handleChange = (event) => {
    const updatedSighting = { ...sighting };

    if (event.target.name === "order") {
      const bugOrderId = Number.parseInt(event.target.value);
      updatedSighting.order = orders.find(order => order.bugOrderId === bugOrderId);
    } else {
      updatedSighting[event.target.name] = event.target.value;
    }

    setSighting(updatedSighting);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const updatedSighting = { ...sighting };

    if (id) {
      const init = {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify(updatedSighting),
      };

      fetch(`http://localhost:8080/sighting/${id}`, init)
        .then((response) => {
          if (response.status !== 204) {
            return Promise.reject("response is not 204 No Content");
          }
          return null;
        })
        .then((data) => {
          navigate("/confirmation", { state: { msg: "👍🏾" }});
        })
        .catch(() => {
          navigate("/error", { state: { msg: "👎🏾" }});
        });
    } else {
      const init = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify(updatedSighting),
      };

      fetch("http://localhost:8080/sighting", init)
        .then((response) => {
          if (response.status !== 201) {
            return Promise.reject("response is not 200 OK");
          }
          return response.json();
        })
        .then((data) => {
          navigate("/confirmation", { state: { msg: "👍🏾" }});
        })
        .catch(() => {
          navigate("/error", { state: { msg: "👎🏾" }});
        });
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>{sighting.sightingId ? "Update A Sighting" : "Add A Sighting"}</h2>

      <div>
        <label htmlFor="bugType">Bug Type</label>
        <input
          type="text"
          id="bugType"
          required
          value={sighting.bugType}
          name="bugType"
          onChange={handleChange}
        />
      </div>

      <div>
        <label htmlFor="order">Order</label>
        <select
          id="order"
          required
          value={sighting.order.bugOrderId}
          name="order"
          onChange={handleChange}
        >
          <option value="0">-- Choose --</option>
          {orders.map(order => (
            <option key={order.bugOrderId} value={order.bugOrderId}>{order.name} ({order.description})</option>
          ))}
        </select>
      </div>

      <div>
        <label htmlFor="description">Description</label>
        <textarea
          id="description"
          required
          value={sighting.description}
          name="description"
          onChange={handleChange}
        />
      </div>

      <div>
        <label htmlFor="date">Date</label>
        <input
          type="date"
          id="date"
          required
          value={sighting.date}
          name="date"
          onChange={handleChange}
        />
      </div>

      <div>
        <label htmlFor="interest">Interest</label>
        <input
          type="number"
          id="interest"
          min="0.00"
          step="0.01"
          required
          value={sighting.interest}
          name="interest"
          onChange={handleChange}
        />
      </div>

      <div>
        <label htmlFor="imageURL">Image URL</label>
        <input
          type="url"
          id="imageURL"
          defaultValue={sighting.imageUrl}
          name="imageUrl"
          onChange={handleChange}
        />
      </div>

      <div>
        <button type="submit">Save</button>
        <Link to="/">Cancel</Link>
      </div>
    </form>
  );
}

export default SightingForm;
