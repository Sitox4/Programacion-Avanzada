import * as actions from './actions';
import reducer from './reducer';
import * as selectors from './selectors';

export {default as BuyTickets} from './components/BuyTickets';
export {default as GetCompras} from './components/GetCompras';
export {default as Historial} from './components/Historial';
export {default as CompraCompletada} from './components/CompraCompletada';
export {default as GiveEntradas} from './components/GiveEntradas';
// eslint-disable-next-line
export default {actions, reducer, selectors};