import {config, appFetch} from './appFetch';

export const getBillboard = (date, onSuccess) => {

    let path = `/catalog/billboard`;

    path += date ? `?date=${date}` : "";

    appFetch(path, config('GET'), onSuccess);

}

export const findMovieById = (id, onSuccess) =>
    appFetch(`/catalog/peliculas/${id}`, config('GET'), onSuccess);
export const findSesionById = (id, onSuccess, onErrors) =>
    appFetch(`/catalog/sesiones/${id}`, config('GET'), onSuccess, onErrors);