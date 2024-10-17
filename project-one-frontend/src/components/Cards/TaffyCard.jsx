import React from 'react'
import {Grid, Card, CardMedia, CardContent, CardActions, Typography, Button} from '@mui/material';
import TaffyImage from '../../assets/CandyPics/CardImages/taffy-candy.jpg'
import { useNavigate } from 'react-router-dom';

const TaffyCard = () => {
    const navigate = useNavigate();

    const navigateTo = (path) => {
        navigate(path);
    }
    return (
        <Card name='taffyCard'>
            <CardMedia
                component='img'
                height='140'
                image={TaffyImage}
                alt='Taffy Candy'
            />
            <CardContent>
                <Typography gutterBottom variant='h5' component='div'>
                    Taffy Candy
                </Typography>
            </CardContent>
            <CardActions>
                <Button name='taffyCardButton' size='small' onClick={() => navigateTo('/taffy candy')}>View Taffy Candy</Button>
            </CardActions>
        </Card>
    )
}

export default TaffyCard;
