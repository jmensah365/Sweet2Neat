import React from 'react';
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper,
    CircularProgress, Typography, Alert, AlertTitle} from '@mui/material';

const WarehouseList = () => {
    const url = "http://localhost:8080/warehouse";
    const [warehouses, setWarehouses] = useState([]);
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
                setWarehouses(returnedData);
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
            Sorry could not get warehouses
            </Alert>;
    }

    if (warehouses.length === 0) {
        return <Alert severity='error'>
            <AlertTitle>Error</AlertTitle>
            No warehouses found
            </Alert>;
    }

    return(
        <>
            <Typography variant="h4" gutterBottom>
                Warehouse List
            </Typography>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Location</TableCell>
                            <TableCell>Capacity</TableCell>
                            <TableCell>Current Stock</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {warehouses.map(warehouse => (
                            <TableRow key={warehouse.id}>
                                <TableCell>{warehouse.location}</TableCell>
                                <TableCell>{warehouse.capacity}</TableCell>
                                <TableCell>{warehouse.currentStock}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </>
    );
};

export default WarehouseList;