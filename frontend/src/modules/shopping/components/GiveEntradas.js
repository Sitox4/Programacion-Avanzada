import {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import {Errors, Success} from '../../common';
import * as actions from '../actions';

const GiveEntradas = () => {

    const dispatch = useDispatch();
    const [creditCard, setCreditCard] = useState('');
    const [orderId, setOrderId] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    const [backendSuccess, setBackendSuccess] = useState(false);
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {
            dispatch(actions.giveTickets(orderId,
                creditCard,
                () => setBackendSuccess(true),
                errors => setBackendErrors(errors)));
        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }

    return (
        <div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            { backendSuccess === true &&
                <Success onClose={() => setBackendSuccess(false)} message={"Entradas entregadas correctamente"}/>
            }
            <form ref={node => form = node} className="needs-validation" noValidate onSubmit={e => handleSubmit(e)}>
                <div className="form-group row">
                    <label htmlFor="compraId" className="offset-md-3 col-md-2 col-form-label">
                        <FormattedMessage id="project.shopping.fields.orderId"/>
                    </label>
                    <div className="col-md-4">
                        <input type="text" id="compraId" className="form-control"
                               value={orderId}
                               onChange={e => setOrderId(e.target.value)}
                               autoFocus
                               required/>
                        <div className="invalid-feedback">
                            <FormattedMessage id='project.shopping.validator.required'/>
                        </div>
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="creditCard" className="offset-md-3 col-md-2 col-form-label">
                        <FormattedMessage id="project.shopping.fields.creditcard"/>
                    </label>
                    <div className="col-md-4">
                        <input type="text" id="creditCard" className="form-control"
                               value={creditCard}
                               onChange={e => setCreditCard(e.target.value)}
                               autoFocus
                               required/>
                        <div className="invalid-feedback">
                            <FormattedMessage id='project.shopping.validator.required'/>
                        </div>
                    </div>
                </div>
                <div className="form-group row">
                    <div className="offset-md-6 col-md-2">
                        <button id="submitButton" type="submit" className="btn btn-primary">
                            <FormattedMessage id="project.shopping.giveTickets"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    );

}

export default GiveEntradas;