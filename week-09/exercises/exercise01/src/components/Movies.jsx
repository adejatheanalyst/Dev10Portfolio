export default function Movies(){
    const movies = [
        { id: 1, title: 'Toy Story', releaseYear: 1995 },
        { id: 2, title: 'The Iron Giant', releaseYear: 1999 },
        { id: 3, title: 'Spider-Man: Into the Spider-Verse', releaseYear: 2018 },
    ];
    return (
        <div>
            <h2>Movies</h2>
            <ul>
                {movies.map((movie) => {
                    return (
                        <li key={movie.id}>
                            <h3>{movie.title}</h3>
                            <p>{movie.releaseYear}</p>
                        </li>
                    )
                })}
            </ul>
        </div>
    )
}