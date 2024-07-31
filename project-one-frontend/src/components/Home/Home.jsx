import React from 'react';
import '../../index.css'
//import './Home.css'
import { Typography, Box, Card, CardMedia, Container, Grid, CardContent, Button, CardActions} from '@mui/material';
import logoHome from '../../assets/CandyPics/CandyLogos/logo-no-background (1).png';
import { useNavigate } from "react-router-dom";
import gummyImage from '../../assets/CandyPics/CardImages/gummyBears.jpeg'



const Home = () => {
    const navigate = useNavigate();

    const navigateTo = (path) => {
        navigate(path);
    }
    return (
        <>
        <Container component='main' sx={{flexGrow: 1, py: 4}}>
            <Box sx={{textAlign:'center', mb: 4}}>
                <Typography variant='h4' gutterBottom>Manage your candy stock with efficiency</Typography>
                <Button variant='contained' color='primary' size='large' onClick={() => navigateTo('/warehouses')}>Add a warehouse</Button>
            </Box>
            <div className="home-background">
            <img src={logoHome} alt='home logo' className="background-image"/>
            </div>
            <br/>
            <br/>
            <br/>
            <br/>
            <Typography variant='h2' gutterBottom>Types of candy</Typography>
            <Grid container spacing={4}>
                <Grid item xs={12} sm={6} md={4}>
                    <Card>
                        <CardMedia
                            component='img'
                            height='140'
                            image={gummyImage}
                            alt='Gummy Bears'
                        />
                        <CardContent>
                            <Typography gutterBottom variant='h5' component='div'>
                                Gummy Candy
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <Button size='small'>View Gummy Candy</Button>
                        </CardActions>
                    </Card>
                </Grid>
            </Grid>
        </Container>
        </>
    )
}

export default Home;

