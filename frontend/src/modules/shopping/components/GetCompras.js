import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as actions from '../actions';
import * as selectors from '../selectors';
import {Pager} from '../../common';
import Compras from './Compras';

const GetCompras = () => {

    const comprasSearch = useSelector(selectors.getComprasSearch);
    const dispatch = useDispatch();

    if (!comprasSearch) {
        return null;
    }

    if (comprasSearch.result.items.length === 0) {
        return (
            <div className="alert alert-info" role="alert">
                <FormattedMessage id='project.shopping.FindOrdersResult.noOrders'/>
            </div>
        );
    }

    return (

        <div>
            <Compras compras={comprasSearch.result.items}/>
            <Pager
                back={{
                    enabled: comprasSearch.criteria.page >= 1,
                    onClick: () => dispatch(actions.previousGetComprasResultPage(comprasSearch.criteria))}}
                next={{
                    enabled: comprasSearch.result.existMoreItems,
                    onClick: () => dispatch(actions.nextGetComprasResultPage(comprasSearch.criteria))}}/>
        </div>

    );

}

export default GetCompras;
