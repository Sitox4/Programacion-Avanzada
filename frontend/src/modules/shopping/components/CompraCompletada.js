import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';

const CompraCompletada = () => {

    const compraId = useSelector(selectors.getLastCompraId);

    if (!compraId) {
        return null;
    }

    return (
        <div className="alert alert-success" role="alert">
            <FormattedMessage id="project.shopping.PurchaseCompleted.purchaseOrderGenerated"/>:
            &nbsp;
            <p id="compraId">{compraId}</p>
        </div>
    );

}

export default CompraCompletada;
