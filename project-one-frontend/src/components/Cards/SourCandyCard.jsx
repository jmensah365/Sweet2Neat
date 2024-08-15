import React from 'react'
import {Grid, Card, CardMedia, CardContent, CardActions, Typography, Button} from '@mui/material';
import SourCandyImage from '../../assets/CandyPics/CardImages/sour-candy.jpeg'
import { useNavigate } from 'react-router-dom';

const SourCandyCard = () => {
    const navigate = useNavigate();

    const navigateTo = (path) => {
        navigate(path);
    }
    return (
        <Card name='sourCandyCard'>
            <CardMedia
                component='img'
                height='140'
                image={SourCandyImage}
                alt='Sour Candy'
            />
            <CardContent>
                <Typography gutterBottom variant='h5' component='div'>
                    Sour Candy
                </Typography>
            </CardContent>
            <CardActions>
                <Button name='sourCandyCardButton' size='small' onClick={() => navigateTo('/sourCandy')}>View Sour Candy</Button>
            </CardActions>
        </Card>
    )
}

export default SourCandyCard;
