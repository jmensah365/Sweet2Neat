import React from 'react';
import '../../index.css'

import { useState,useEffect } from 'react';
import { Typography, Container} from '@mui/material';
import logoHome from '../../assets/CandyPics/CandyLogos/logo-no-background (1).png';
import { useNavigate } from "react-router-dom";
import CandyCard from '../Cards/CandyCard';





const Home = () => {
    const url = "http://localhost:8080/warehouse";
    const navigate = useNavigate();
    const [warehouses, setWarehouses] = useState([]);
    const [error, setError] = useState(null);
    const [loaded, setLoaded] = useState(false);

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
        <>
        <Container component='main' sx={{flexGrow: 1, py: 4}}>
            <br/>
            <br/>
            <div className="home-background">
            <img src={logoHome} alt='home logo' className="background-image"/>
            </div>
            {/* <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/> */}
            {/* <Typography variant='h2' gutterBottom>Types of candy</Typography>
            <div>
                <CandyCard/>
            </div> */}

        </Container>
        </>
    )
}

export default Home;

