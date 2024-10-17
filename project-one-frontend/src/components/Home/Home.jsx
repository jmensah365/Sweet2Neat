import '../../index.css';
import React, { useState, useEffect } from 'react';
import { Container, Typography, Grid, Card, CardContent, Box, CircularProgress, Alert } from '@mui/material';
import logoHome from '../../assets/CandyPics/CandyLogos/logo-no-background (1).png';
import { useNavigate } from 'react-router-dom';

const Home = () => {
    const apiUrl = import.meta.env.VITE_API_URL;
    const url = `${apiUrl}/warehouse`;
    const navigate = useNavigate();
    const [warehouses, setWarehouses] = useState([]);
    const [error, setError] = useState(null);
    const [loaded, setLoaded] = useState(false);

    // Fetching warehouse data to put on the home page
    useEffect(() => {
        fetch(url)
            .then((response) => {
                if (!response.ok) throw new Error('Network response was not ok');
                return response.json();
            })
            .then((returnedData) => {
                setWarehouses(returnedData);
                setLoaded(true);
            })
            .catch((err) => {
                setError(err);
                setLoaded(true);
            });
    }, []);

    const getCardColor = (stock, capacity) => {
        return stock === capacity ? '#FFCDD2' : '#C8E6C9';
    };

    return (
        <>
            <Container component="main" sx={{ flexGrow: 1, py: 4, textAlign: 'center' }}>
                <div className="logo">
                    <img src={logoHome} alt="home logo" className="background-image" />
                </div>
            </Container>

            <Container component="main" sx={{ py: 4 }}>
                <Typography variant="h5" align="center" gutterBottom>
                    Available Warehouses
                </Typography>

                {error ? (
                    <Alert severity="error">Failed to load warehouses: {error.message}</Alert>
                ) : !loaded ? (
                    <Box display="flex" justifyContent="center" py={3}>
                        <CircularProgress />
                    </Box>
                ) : (
                    <Box
                        sx={{
                            display: 'flex',
                            overflowX: 'auto',
                            gap: 2,
                            py: 2,
                            px: 2,
                            '&::-webkit-scrollbar': { height: 8 },
                            '&::-webkit-scrollbar-thumb': { backgroundColor: '#888', borderRadius: 4 },
                            '&::-webkit-scrollbar-thumb:hover': { backgroundColor: '#555' },
                        }}
                    >
                        {warehouses.map((warehouse) => (
                            <Card
                                key={warehouse.id}
                                elevation={3}
                                sx={{ 
                                    minWidth: 300, 
                                    maxWidth: 300, 
                                    flex: '0 0 auto', 
                                    backgroundColor: getCardColor(warehouse.currentStock, warehouse.capacity), 
                                    transition: 'background-color 0.3s ease',
                                }}
                            >
                                <CardContent>
                                    <Typography variant="h6" component="div">
                                        {warehouse.name}
                                    </Typography>
                                    <Typography variant="body2" color="textSecondary">
                                        Location: {warehouse.location}
                                    </Typography>
                                    <Typography variant="body2" color="textSecondary">
                                        Capacity: {warehouse.capacity}
                                    </Typography>
                                    <Typography variant="body2" color="textSecondary">
                                        Current Stock: {warehouse.currentStock}
                                    </Typography>
                                    <Typography variant="body2" color="textSecondary">
                                        {warehouse.currentStock === warehouse.capacity ? 'Warehouse is full' : ''}
                                    </Typography>
                                </CardContent>
                            </Card>
                        ))}
                    </Box>
                )}
            </Container>
        </>
    );
};

export default Home;
