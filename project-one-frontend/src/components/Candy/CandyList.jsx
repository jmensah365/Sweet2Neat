import React from 'react';
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper,
    CircularProgress, Typography, Alert, AlertTitle} from '@mui/material';

const WarehouseList = () => {
    const url = "http://localhost:8080/candy";
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

    if (!loaded) {
        return <CircularProgress />;
    }
    // if(loaded){
    //     return <Alert severity='success'>
    //         <AlertTitle>Success</AlertTitle>
    //     </Alert>
    // }

    if (error) {
        return <Alert severity='error'>
            <AlertTitle>Error</AlertTitle>
            Sorry could not get candy inventory
            </Alert>;
    }

    if (candy.length === 0) {
        return <Alert severity='error'>
            <AlertTitle>Error</AlertTitle>
            No candy found
            </Alert>;
    }

    return(
        <>
            <Typography variant="h4" gutterBottom>
                Candy List
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
                            <TableRow key={candy.id}>
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
    );
};

export default WarehouseList;