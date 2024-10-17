import React from 'react'
import {Grid, Card, CardMedia, CardContent, CardActions, Typography, Button} from '@mui/material';
import gummyImage from '../../assets/CandyPics/CardImages/gummyBears.jpeg'
import GummyCandy from '../Candy/GummyCandy';
import { useNavigate } from 'react-router-dom';
const GummyCard = () => {
    const navigate = useNavigate();

    const navigateTo = (path) => {
        navigate(path);
    };
    return (
                    <Card name='gummyCard'>
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
                            <Button name='gummyCardButton' size='small' onClick={() => navigateTo('/gummy candy')}>View Gummy Candy</Button>
                        </CardActions>
                    </Card>
    )
}

export default GummyCard;
