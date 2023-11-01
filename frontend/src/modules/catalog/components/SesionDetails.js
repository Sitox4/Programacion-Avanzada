import {useEffect, useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedDate, FormattedMessage, FormattedNumber, FormattedTime} from 'react-intl';
import {useParams} from 'react-router-dom';

import users from '../../users';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {BackLink, Errors, MovieLink} from '../../common';
import BuyTickets from "../../shopping/components/BuyTickets";

const SesionDetails = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const user = useSelector(users.selectors.getUser);
    const sesion = useSelector(selectors.getSesion);
    const dispatch = useDispatch();
    const {id} = useParams();
    const [backendErrors, setBackendErrors] = useState(null);

    useEffect(() => {

        const sesionId = Number(id);

        if (!Number.isNaN(sesionId)) {
            dispatch(actions.findSesionById(sesionId,errors => setBackendErrors(errors)));
        }

        return () => dispatch(actions.clearSesion());

    }, [id, dispatch]);

    if (!sesion && !backendErrors) {
        return null;
    }

    return (

        <div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <BackLink/>

            {!backendErrors && <div className="card text-center">

                <div className="card-body">
                    <h3 id="movieTitle"><MovieLink peliculaId={sesion.peliculaId} tituloPelicula={sesion.tituloPelicula}/></h3>
                </div>

                <div className="card-body">
                    <h6><FormattedMessage id='project.catalog.fields.duration'/></h6>
                    <p className="card-text">{sesion.duracion} <FormattedMessage id='project.catalog.fields.minutes'/></p>
                    <h6><FormattedMessage id='project.catalog.fields.datehour'/></h6>
                    <p><FormattedDate value={new Date(sesion.date)}/>&nbsp;&nbsp;&nbsp;<FormattedTime value={new Date(sesion.date)}/></p>
                    <h6><FormattedMessage id='project.catalog.fields.roomname'/></h6>
                    <p>{sesion.nombreSala}</p>
                </div>

                <div className="card-body">
                    <h6><FormattedMessage id='project.catalog.fields.price'/></h6>
                    <p className="card-text"><FormattedNumber value={sesion.precio} style="currency" currency="EUR"/></p>
                    <h6><FormattedMessage id='project.catalog.fields.ticketsleft'/></h6>
                    <p>{sesion.ticketsLibres}</p>
                </div>

            </div>}

            <br/>
            {loggedIn && !backendErrors && (user.role==="ESPECTADOR") &&
                <div>
                    <br/>
                    <BuyTickets sesionId={Number(id)}/>
                </div>
            }

        </div>

    );

}

export default SesionDetails;