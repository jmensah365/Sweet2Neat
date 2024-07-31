import React from 'react';
import '../../index.css'
import { useState,useEffect } from 'react';
//import './Home.css'
import { Typography, Box, Card, CardMedia, Container, Grid, CardContent, Button, CardActions} from '@mui/material';
import logoHome from '../../assets/CandyPics/CandyLogos/logo-no-background (1).png';
import { useNavigate } from "react-router-dom";
import GummyCard from '../Cards/GummyCard';
import ChoclateCard from '../Cards/ChoclateCard';
import SourCandyCard from '../Cards/SourCandyCard';
import TaffyCard from '../Cards/TaffyCard';
import LollipopCard from '../Cards/LollipopCard';



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
            <Box sx={{textAlign:'center', mb: 4}}>
                <Typography variant='h4' gutterBottom>Manage your candy stock with efficiency</Typography>
                <Button variant='contained' color='primary' size='large' onClick={() => navigateTo('/warehouses')}>Add a warehouse</Button>
                <Typography variant='h3' gutterBottom>Real-Time tracking of warehouses</Typography>
                <Typography variant='h2' gutterBottom>Warehouse count: {warehouses.length} </Typography>
            </Box>
            <div className="home-background">
            <img src={logoHome} alt='home logo' className="background-image"/>
            </div>
            <br/>
            <br/>
            <br/>
            <br/>
            <Typography variant='h2' gutterBottom>Types of candy</Typography>
            <Grid container spacing={8}>
                <Grid item xs={12} sm={6} md={4}>
                    <GummyCard/>
                </Grid>
                <Grid item xs={12} sm={6} md={4}>
                    <ChoclateCard/>
                </Grid>
                <Grid item xs={12} sm={6} md={4}>
                    <SourCandyCard/>
                </Grid>
                <Grid item xs={12} sm={6} md={4}>
                    <TaffyCard/>
                </Grid>
                <Grid item xs={12} sm={6} md={4}>
                    <LollipopCard/>
                </Grid>
            </Grid>
        </Container>
        </>
    )
}

export default Home;

