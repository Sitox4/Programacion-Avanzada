import {FormattedMessage, FormattedTime} from 'react-intl';
import PropTypes from 'prop-types';

import {MovieLink} from '../../common';
import SesionLink from "../../common/components/SesionLink";

const Movies = ({movies}) => (

    <table className="table table-striped table-hover">

        <thead>
        <tr>
            <th scope="col">
                <FormattedMessage id='project.catalog.fields.title'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.catalog.fields.sesions'/>
            </th>
        </tr>
        </thead>

        <tbody>
        {movies.map(movie =>
            <tr key={movie.peliculaId}>
                <td><MovieLink peliculaId={movie.peliculaId} tituloPelicula={movie.tituloPelicula}/></td>
                <td>
                    <table>
                        <tbody>
                        <tr>
                            {movie.listaHoras.map(sesion =>
                                <td key={sesion.date}><SesionLink date={sesion.date} sesionId={sesion.sesionId}/></td>
                            )}
                        </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        )}
        </tbody>

    </table>

);

Movies.propTypes = {
    movies: PropTypes.array.isRequired,
};

export default Movies;