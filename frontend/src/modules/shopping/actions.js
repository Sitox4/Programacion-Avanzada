import backend from '../../backend';
import * as actionTypes from './actionTypes';

const buyTicketsCompleted = (orderId) => ({
    type: actionTypes.BUY_TICKETS_COMPLETED,
    orderId
});

export const buyTickets = (sesionId, cantidad, tarjeta, onSuccess, onErrors) => dispatch =>
    backend.shoppingService.buyTickets(sesionId,cantidad, tarjeta, id => {
            dispatch(buyTicketsCompleted(id));
            onSuccess();
        },
        onErrors);

const giveTicketsCompleted = (compraId) => ({
    type: actionTypes.GIVE_TICKETS_COMPLETED,
    compraId
});

export const giveTickets = (compraId, creditCard, onSuccess, onErrors) => dispatch =>
    backend.shoppingService.giveTickets(compraId,creditCard, id => {
            dispatch(giveTicketsCompleted(id));
            onSuccess();
        },
        onErrors);

const getComprasCompleted = comprasSearch => ({
    type: actionTypes.GET_COMPRAS_COMPLETED,
    comprasSearch
});

const clearComprasSearch = () => ({
    type: actionTypes.CLEAR_COMPRAS_SEARCH
});

export const getCompras = criteria => dispatch => {

    dispatch(clearComprasSearch());
    backend.shoppingService.getCompras(criteria,
        result => dispatch(getComprasCompleted({criteria, result})));

}

export const previousGetComprasResultPage = criteria =>
    getCompras({page: criteria.page-1});

export const nextGetComprasResultPage = criteria =>
    getCompras({page: criteria.page+1});