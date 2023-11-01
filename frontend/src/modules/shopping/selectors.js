const getModuleState = state => state.shopping;

export const getComprasSearch = state =>
    getModuleState(state).comprasSearch;

export const getLastCompraId = state =>
    getModuleState(state).lastCompraId;