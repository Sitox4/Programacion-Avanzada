import {useSelector} from 'react-redux';
import {Route, Routes} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import users from '../../users';
import {MovieDetails, SesionDetails} from '../../catalog';
import {CompraCompletada, GetCompras, GiveEntradas, Historial} from "../../shopping";

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const user = useSelector(users.selectors.getUser);

    return (
        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Routes>
                <Route path="/*" element={<Home/>}/>
                <Route path="/catalog/peliculas/:id" element={<MovieDetails/>}/>
                <Route path="/catalog/sesiones/:id" element={<SesionDetails/>}/>
                {loggedIn && (user.role==="ESPECTADOR") && <Route path="/shopping/historial" element={<Historial/>}/>}
                {loggedIn && (user.role==="ESPECTADOR") && <Route path="/shopping/find-orders-result" element={<GetCompras/>}/>}
                {loggedIn && (user.role==="ESPECTADOR") && <Route path="/shopping/purchase-completed" element={<CompraCompletada/>}/>}
                {loggedIn && (user.role==="TAQUILLERO") && <Route path="/shopping/give-tickets" element={<GiveEntradas/>}/>}
                {loggedIn && <Route path="/users/update-profile" element={<UpdateProfile/>}/>}
                {loggedIn && <Route path="/users/change-password" element={<ChangePassword/>}/>}
                {loggedIn && <Route path="/users/logout" element={<Logout/>}/>}
                {!loggedIn && <Route path="/users/login" element={<Login/>}/>}
                {!loggedIn && <Route path="/users/signup" element={<SignUp/>}/>}
            </Routes>
        </div>
    );

};

export default Body;