import React from 'react'
import {Grid, Card, CardMedia, CardContent, CardActions, Typography, Button} from '@mui/material';
import AssortedCandy from '../../assets/CandyPics/CardImages/assorted-candy.jpg'
import { useNavigate } from 'react-router-dom';

const CandyCard = () => {
    const navigate = useNavigate();

    const navigateTo = (path) => {
        navigate(path);
    };
    return (
        <Card>
            <CardMedia
                component='img'
                height='140'
                image={AssortedCandy}
                alt='Assorted Candy'
            />
            <CardContent>
                <Typography gutterBottom variant='h5' component='div'>
                    Candy Categories
                </Typography>
            </CardContent>
            <CardActions>
                <Button size='small' onClick={() => navigateTo('/candyTypes')}>View candy categories</Button>
            </CardActions>
        </Card>
    )
}

export default CandyCard;
