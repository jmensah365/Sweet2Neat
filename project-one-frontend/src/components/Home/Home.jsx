import '../../index.css'
import React, { useState,useEffect } from 'react';
import {Container} from '@mui/material';
import logoHome from '../../assets/CandyPics/CandyLogos/logo-no-background (1).png';
import { useNavigate } from "react-router-dom";

const Home = () => {

    const apiUrl = import.meta.env.VITE_API_URL;
    const url = `${apiUrl}/warehouse`;
    const navigate = useNavigate();
    const [warehouses, setWarehouses] = useState([]);
    const [error, setError] = useState(null);
    const [loaded, setLoaded] = useState(false);

    //fetching warehouse data to put on home page
    useEffect(() => {
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(returnedData => {
                setWarehouses(returnedData);
                setLoaded(true);
            })
            .catch(err => {
                setError(err);
                setLoaded(true);
            });
    }, []);

    const navigateTo = (path) => {
        navigate(path);
    }
    return (
        //setting home page background to candy logo
        <>
        <Container component='main' sx={{flexGrow: 1, py: 4}}>
            <br/>
            <br/>
            <div className='logo'>
            <img src={logoHome} alt='home logo' className="background-image"/>
            </div>
        </Container>
        </>
    )
}

export default Home;

