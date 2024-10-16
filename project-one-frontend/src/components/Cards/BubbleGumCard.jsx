import React from 'react'
import {Grid, Card, CardMedia, CardContent, CardActions, Typography, Button} from '@mui/material';
import bubbleGumImage from '../../assets/CandyPics/CardImages/bubble-gum.jpeg';
import { useNavigate } from 'react-router-dom';

const BubbleGumCard = () => {
    const navigate = useNavigate();

    const navigateTo = (path) => {
        navigate(path);
    };
    return (
        <Card name ='bubblegumCard'>
            <CardMedia
                component='img'
                height='140'
                image={bubbleGumImage}
                alt='Bubble Gum'
            />
            <CardContent>
                <Typography gutterBottom variant='h5' component='div'>
                    Bubble Gum
                </Typography>
            </CardContent>
            <CardActions>
                <Button name='bubbleGumCardButton' size='small' onClick={() => navigateTo('/bubblegum')}>View Bubble Gum</Button>
            </CardActions>
        </Card>
    )
}

export default BubbleGumCard;
