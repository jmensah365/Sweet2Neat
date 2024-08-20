import React from 'react'
import GummyCard from '../Cards/GummyCard';
import ChoclateCard from '../Cards/ChoclateCard';
import SourCandyCard from '../Cards/SourCandyCard';
import TaffyCard from '../Cards/TaffyCard';
import LollipopCard from '../Cards/LollipopCard';
import { Typography,Container, Grid} from '@mui/material';

const CandyTypes = () => {
  return (
    <>
    <div>CandyTypes</div>
      <Container component='main' sx={{flexGrow: 1, py: 4}}>
            <Typography variant='h2' name="candyTypesHeader" gutterBottom>Types of candy</Typography>
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

export default CandyTypes;
