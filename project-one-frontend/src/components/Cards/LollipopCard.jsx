import React from 'react'
import {Grid, Card, CardMedia, CardContent, CardActions, Typography, Button} from '@mui/material';
import LollipopImage from '../../assets/CandyPics/CardImages/lollipop-candy.jpg'
import { useNavigate } from 'react-router-dom';


const LollipopCard = () => {
    const navigate = useNavigate();

    const navigateTo = (path) => {
        navigate(path);
    };
    return (
        <Card>
        <CardMedia
            component='img'
            height='140'
            image={LollipopImage}
            alt='Lollipop Candy'
        />
        <CardContent>
            <Typography gutterBottom variant='h5' component='div'>
                Lollipops
            </Typography>
        </CardContent>
        <CardActions>
            <Button size='small' onClick={() => navigateTo('/lollipopCandy')}>View Lollipops</Button>
        </CardActions>
    </Card>
    )
}

export default LollipopCard