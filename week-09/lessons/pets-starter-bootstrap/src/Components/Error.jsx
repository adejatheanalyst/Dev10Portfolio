// src/components/Error.jsx
import { Link } from 'react-router-dom';

export default function Error() {
    return (
        <>
            <div role='alert' className='alert alert-danger'>
                <h1>Error</h1>
                <p>An unexpected error occurred. Please try again later.</p>
            </div>
            <Link to='/'>Return home.</Link>
        </>
    );
}
