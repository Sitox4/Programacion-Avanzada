import {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';
import {useNavigate} from 'react-router-dom';

import {Errors} from '../../common';
import * as actions from '../actions';

const BuyTickets = ({sesionId}) => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [quantity, setQuantity] = useState(1);
    const [creditCard, setCreditCard] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {
            dispatch(actions.buyTickets(sesionId,
                quantity, creditCard,
                () => navigate('/shopping/purchase-completed'),
                errors => setBackendErrors(errors)));
        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }

    return (
        <div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <form ref={node => form = node} className="needs-validation" noValidate onSubmit={e => handleSubmit(e)}>
                <div className="form-group row">
                    <label htmlFor="quantity" className="offset-md-5 col-md-1 col-form-label">
                        <FormattedMessage id="project.shopping.fields.quantity"/>
                    </label>
                    <div className="col-md-2">
                        <input type="number" id="quantity" className="form-control"
                               value={quantity}
                               onChange={e => setQuantity(Number(e.target.value))}
                               autoFocus
                               min="1" max="10"/>
                        <div className="invalid-feedback">
                            <FormattedMessage id='project.shopping.validator.incorrectQuantity'/>
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
                            <FormattedMessage id="project.shopping.buy"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    );

}


BuyTickets.propTypes = {
    sesionId: PropTypes.number.isRequired
};

export default BuyTickets;