import * as actions from './actions';
import * as actionTypes from './actionTypes';
import reducer from './reducer';
import * as selectors from './selectors';

export {default as Billboard} from './components/Billboard';
export {default as MovieDetails} from './components/MovieDetails';
export {default as SesionDetails} from './components/SesionDetails';

// eslint-disable-next-line
export default {actions, actionTypes, reducer, selectors};