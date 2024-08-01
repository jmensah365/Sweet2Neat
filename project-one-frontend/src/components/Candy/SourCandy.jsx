import React from 'react'
import { useState, useEffect } from 'react';
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper,
    CircularProgress, Typography, Alert, AlertTitle} from '@mui/material';

const SourCandy = () => {
    const url = "http://localhost:8080/candy/getByType?type=Sour Candy";
    const [candy, setCandy] = useState([]);
    const [loaded, setLoaded] = useState(false);
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
                setCandy(returnedData);
                setLoaded(true);
            })
            .catch(err => {
                setError(err);
                setLoaded(true);
            });
    }, []);
    return (
        <>
            <Typography variant='h3' gutterBottom>
                Sour Candy
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
                    {candy.map(candy => (
                            <TableRow key={candy.candyId}>
                                <TableCell>{candy.name}</TableCell>
                                <TableCell>{candy.type}</TableCell>
                                <TableCell>{candy.flavor}</TableCell>
                                <TableCell>{candy.price}</TableCell>
                                <TableCell>{candy.weight}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer> 
        </>
    )
}

export default SourCandy;
