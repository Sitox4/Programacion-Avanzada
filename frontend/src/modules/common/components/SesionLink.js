import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';
import {FormattedTime} from "react-intl";

const SesionLink = ({sesionId, date}) => {

    return (
        <Link to={`/catalog/sesiones/${sesionId}`}>
            <FormattedTime value={new Date(date)}/>
        </Link>
    );

}

SesionLink.propTypes = {
    sesionId: PropTypes.number.isRequired,
    date: PropTypes.number.isRequired,
};

export default SesionLink;