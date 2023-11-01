import {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useParams} from 'react-router-dom';

import * as selectors from '../selectors';
import * as actions from '../actions';
import {BackLink} from '../../common';

const MovieDetails = () => {

    const movie = useSelector(selectors.getMovie);
    const dispatch = useDispatch();
    const {id} = useParams();

    useEffect(() => {

        const movieId = Number(id);

        if (!Number.isNaN(movieId)) {
            dispatch(actions.findMovieById(movieId));
        }

        return () => dispatch(actions.clearMovie());

    }, [id, dispatch]);

    if (!movie) {
        return null;
    }

    return (

        <div>
            <BackLink/>

            <div className="card text-center">
                <div className="card-body">
                    <h5 id="movieTitle" className="card-title">{movie.titulo}</h5>
                    <p className="card-text">{movie.resumen}</p>
                </div>
                <div className="card-body">
                    <h6><FormattedMessage id='project.catalog.fields.duration'/></h6>
                    <p className="card-text">{movie.duracion} <FormattedMessage id='project.catalog.fields.minutes'/></p>
                </div>
            </div>

        </div>

    );

}

export default MovieDetails;