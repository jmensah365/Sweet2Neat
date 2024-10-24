import React from 'react'
import { useState, useEffect } from 'react';
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, Typography, TableFooter} from '@mui/material';

const Favorites = () => {
    const apiUrl = import.meta.env.VITE_API_URL;
    const url = `${apiUrl}/favoriteCandies`;
    const [favoriteCandies, setFavoriteCandies] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(returnedData => {
                setFavoriteCandies(returnedData);
            })
            .catch(err => {
                setError(err);
            });
    }, []);

    return (
        <>
            <Typography variant='h3' gutterBottom>
                Favorited Candy
            </Typography>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Candy Name</TableCell>
                            <TableCell>Candy Type</TableCell>
                            <TableCell>Flavor</TableCell>
                            <TableCell>Price ($)</TableCell>
                            <TableCell>Weight (oz.)</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                    {favoriteCandies.map(favorites => (
                            <TableRow key={favorites.id} sx={{ '&:hover': { backgroundColor: 'grey.200' }}}>
                                <TableCell sx={{ padding: '16px 24px' }}>{favorites.candy.name}</TableCell>
                                <TableCell >{favorites.candy.type}</TableCell>
                                <TableCell>{favorites.candy.flavor}</TableCell>
                                <TableCell>{favorites.candy.price.toFixed(2)}</TableCell>
                                <TableCell>{favorites.candy.weight.toFixed(2)}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                    <TableFooter>
                        <TableRow>
                            <TableCell colSpan={5}>Total Favorited Candies: {favoriteCandies.length}</TableCell>
                        </TableRow>
                    </TableFooter>
                </Table>
            </TableContainer> 
        </>
    )

}

export default Favorites;