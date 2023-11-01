import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    movies: null,
    billboardDate: null,
    movie: null,
    sesion: null
};

const movies = (state = initialState.movies, action) => {

    switch (action.type) {

        case actionTypes.GET_BILLBOARD_COMPLETED:
            return action.movies;

        case actionTypes.CLEAR_MOVIES_SEARCH:
            return initialState.movies;

        default:
            return state;

    }

}

const billboardDate = (state=initialState.billboardDate, action) => {

    switch (action.type) {
        case actionTypes.GET_BILLBOARD_COMPLETED:
            return action.movies.criteria;
        default:
            return state;
    }
}

const sesion = (state = initialState.sesion, action) => {

    switch (action.type) {

        case actionTypes.FIND_SESION_BY_ID_COMPLETED:
            return action.sesion;

        case actionTypes.CLEAR_SESION:
            return initialState.sesion;

        default:
            return state;

    }

}


const movie = (state = initialState.movie, action) => {

    switch (action.type) {

        case actionTypes.FIND_MOVIE_BY_ID_COMPLETED:
            return action.movie;

        case actionTypes.CLEAR_MOVIE:
            return initialState.movie;

        default:
            return state;

    }

}


const reducer = combineReducers({
    movies,
    billboardDate,
    movie,
    sesion
});

export default reducer;