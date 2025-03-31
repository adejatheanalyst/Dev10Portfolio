export default function FormErrors({ errors }) {
    if (!errors || !errors.length) return null;

    return (
        <div className='alert alert-danger' role="alert">
            <h2>The following errors occurred:</h2>
            <ul>
                {errors.map(error => (
                    <li key={error}>{error}</li>
                ))}
            </ul>
        </div>
    );
}
