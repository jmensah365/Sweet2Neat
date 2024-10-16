import React from 'react';
import GummyCard from '../Cards/GummyCard';
import ChoclateCard from '../Cards/ChoclateCard';
import SourCandyCard from '../Cards/SourCandyCard';
import TaffyCard from '../Cards/TaffyCard';
import LollipopCard from '../Cards/LollipopCard';
import BubbleGumCard from '../Cards/BubbleGumCard';
import { Typography, Container, Box } from '@mui/material';

const CandyTypes = () => {
    return (
        <>
            <Container component='main' sx={{ flexGrow: 1, py: 4 }}>
                <Typography variant='h2' name="candyTypesHeader" gutterBottom>Types of Candy</Typography>
                <Box
                    sx={{
                        display: 'flex',
                        overflowX: 'auto',
                        gap: 2, 
                        py: 2,
                    }}
                >
                    <Box sx={{ minWidth: 300 }}>
                        <GummyCard />
                    </Box>
                    <Box sx={{ minWidth: 300 }}>
                        <ChoclateCard />
                    </Box>
                    <Box sx={{ minWidth: 300 }}>
                        <SourCandyCard />
                    </Box>
                    <Box sx={{ minWidth: 300 }}>
                        <TaffyCard />
                    </Box>
                    <Box sx={{ minWidth: 300 }}>
                        <LollipopCard />
                    </Box>
                    <Box sx={{ minWidth: 300 }}>
                        <BubbleGumCard />
                    </Box>
                </Box>
            </Container>
        </>
    );
};

export default CandyTypes;
