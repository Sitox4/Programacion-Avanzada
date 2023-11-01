import * as actionTypes from './actionTypes';
import * as selectors from './selectors';
import backend from '../../backend';

const getBillboardCompleted = movies => ({
    type: actionTypes.GET_BILLBOARD_COMPLETED,
    movies
});

export const getBillboard = criteria => dispatch => {

    dispatch(clearMoviesSearch());
    backend.catalogService.getBillboard(criteria,
        result => dispatch(getBillboardCompleted({criteria, result})));

}

const clearMoviesSearch = () => ({
    type: actionTypes.CLEAR_MOVIES_SEARCH
});

const findMovieByIdCompleted = movie => ({
    type: actionTypes.FIND_MOVIE_BY_ID_COMPLETED,
    movie
});

export const findMovieById = id => dispatch => {
    backend.catalogService.findMovieById(id,
        movie => dispatch(findMovieByIdCompleted(movie)));
}

export const clearMovie = () => ({
    type: actionTypes.CLEAR_MOVIE
});

const findSesionByIdCompleted = sesion => ({
    type: actionTypes.FIND_SESION_BY_ID_COMPLETED,
    sesion
});

export const findSesionById = (id,onErrors) => dispatch => {
    backend.catalogService.findSesionById(id,
        sesion => dispatch(findSesionByIdCompleted(sesion)),onErrors);
}

export const clearSesion = () => ({
    type: actionTypes.CLEAR_SESION
});