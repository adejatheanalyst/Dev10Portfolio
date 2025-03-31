import { Link } from "react-router-dom";

function Sighting({ sighting, handleDelete }) {
  return (
    <div>
      <figure>
        {sighting.imageUrl && (
          <img
            className="card-img-top"
            src={sighting.imageUrl}
            alt={sighting.bugType}
          />
        )}
        <figcaption>
          <h4>{sighting.bugType}</h4>
          <p>
            <strong>Order:</strong> {sighting.order.name}
          </p>
          <p>{sighting.description}</p>
          <p>
            <time dateTime={sighting.date}>{sighting.date}</time>
          </p>
          <p>
            <strong>Interest:</strong> {sighting.interest}
          </p>
        </figcaption>
      </figure>
      <footer>
        <div>
          <Link to={`/edit/${sighting.sightingId}`}>Edit</Link>
          <button onClick={() => handleDelete(sighting.sightingId)}>Delete</button>
        </div>
      </footer>
    </div>
  );
}

export default Sighting;
