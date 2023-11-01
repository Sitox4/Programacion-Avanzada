import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    comprasSearch: null,
    lastCompraId: null
};

const comprasSearch = (state = initialState.comprasSearch, action) => {

    switch (action.type) {

        case actionTypes.GET_COMPRAS_COMPLETED:
            return action.comprasSearch;

        case actionTypes.CLEAR_COMPRAS_SEARCH:
            return initialState.comprasSearch;

        default:
            return state;

    }

}

const lastCompraId = (state = initialState.lastCompraId, action) => {

    switch (action.type) {

        case actionTypes.BUY_TICKETS_COMPLETED:
            return action.orderId;

        default:
            return state;

    }

}

const reducer = combineReducers({
    comprasSearch,
    lastCompraId
});

export default reducer;