import {FormattedMessage, FormattedDate, FormattedTime, FormattedNumber} from 'react-intl';
import PropTypes from 'prop-types';

const Compras = ({compras}) => (

    <table className="table table-striped table-hover">

        <thead>
        <tr>
            <th scope="col" className="text-center">
                <FormattedMessage id='project.shopping.fields.orderDate'/>
            </th>
            <th scope="col" className="text-center">
                <FormattedMessage id='project.shopping.fields.compraId'/>
            </th>
            <th scope="col" className="text-center">
                <FormattedMessage id='project.shopping.fields.movie'/>
            </th>
            <th scope="col" className="text-center">
                <FormattedMessage id='project.shopping.fields.tickets'/>
            </th>
            <th scope="col" className="text-center">
                <FormattedMessage id='project.shopping.fields.totalPrice'/>
            </th>
            <th scope="col" className="text-center">
                <FormattedMessage id='project.shopping.fields.sesionDate'/>
            </th>
            <th scope="col" className="text-center">
                <FormattedMessage id='project.shopping.fields.given'/>
            </th>
        </tr>
        </thead>

        <tbody>
        {compras.map(compra =>
            <tr key={compra.compraId}>
                <td className="text-center">
                    <FormattedDate value={new Date(compra.date)}/> - <FormattedTime value={new Date(compra.date)}/>
                </td>
                <td id="idCompra" className="text-center">
                    {compra.compraId}
                </td>
                <td id="tituloPelicula" className="text-center">
                    {compra.tituloPelicula}
                </td>
                <td className="text-center">
                    {compra.entradas}
                </td>
                <td className="text-center">
                    <FormattedNumber value={compra.precioTotal} style="currency" currency="EUR"/>
                </td>
                <td className="text-center">
                    <FormattedDate value={new Date(compra.fechaSesion)}/> - <FormattedTime value={new Date(compra.fechaSesion)}/>
                </td>
                <td className="text-center">
                    {compra.entregadas &&
                        <FormattedMessage id='project.shopping.fields.yesGiven'/>
                    }
                    {!compra.entregadas &&
                        <FormattedMessage id='project.shopping.fields.notGiven'/>
                    }
                </td>
            </tr>
        )}
        </tbody>

    </table>

);

Compras.propTypes = {
    compras: PropTypes.array.isRequired
};

export default Compras;