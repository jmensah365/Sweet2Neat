import React from 'react';
import '../../index.css'
//import './Home.css'
import logoHome from '../../assets/CandyPics/CandyLogos/logo-no-background (1).png'



const Home = () => {
    return (
        <div className="home-background">
            <h1>Welcome to your Candy Inventory Management</h1>
            <p>Manage your candy stocks efficently</p>
            <img src={logoHome} alt='home logo' className="background-image"/>
        </div>
    )
}

export default Home;

