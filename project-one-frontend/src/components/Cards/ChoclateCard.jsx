import React from 'react'
import {Grid, Card, CardMedia, CardContent, CardActions, Typography, Button} from '@mui/material';
import chocolateImage from '../../assets/CandyPics/CardImages/choclate-candy.jpg'
import { useNavigate } from 'react-router-dom';

const ChoclateCard = () => {
    const navigate = useNavigate();

    const navigateTo = (path) => {
        navigate(path);
    };
    return (
        <Card name ='choclateCard'>
            <CardMedia
                component='img'
                height='140'
                image={chocolateImage}
                alt='Choclate Candy'
            />
            <CardContent>
                <Typography gutterBottom variant='h5' component='div'>
                    Chocolate Candy
                </Typography>
            </CardContent>
            <CardActions>
                <Button name='chocolateCardButton' size='small' onClick={() => navigateTo('/chocolate candy')}>View Choclate Candy</Button>
            </CardActions>
        </Card>
    )
}

export default ChoclateCard;
