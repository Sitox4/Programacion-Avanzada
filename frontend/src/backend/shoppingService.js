import {config, appFetch} from './appFetch';

export const buyTickets = (sesionId, cantidad, tarjeta, onSuccess,
                    onErrors) =>
    appFetch(`/shopping/buy`,
        config('POST', {sesionId, cantidad, tarjeta}), onSuccess, onErrors);

export const getCompras = ({page}, onSuccess) =>
    appFetch(`/shopping/historial?page=${page}`, config('GET'), onSuccess);

export const giveTickets = (compraId, creditCard, onSuccess,
                           onErrors) =>
    appFetch(`/shopping/give/${compraId}`,
        config('POST', {creditCard}), onSuccess, onErrors);