import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';

const MovieLink = ({peliculaId, tituloPelicula}) => {

    return (
        <Link to={`/catalog/peliculas/${peliculaId}`}>
            {tituloPelicula}
        </Link>
    );

}

MovieLink.propTypes = {
    peliculaId: PropTypes.number.isRequired,
    tituloPelicula: PropTypes.string.isRequired,
};

export default MovieLink;