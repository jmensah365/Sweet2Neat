import React from 'react';
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper,
    CircularProgress, Typography} from '@mui/material';

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

    if (error) {
        return <Typography variant="h6" color="error">Error: {error.message}</Typography>;
    }

    if (warehouses.length === 0) {
        return <Typography variant="h6">No warehouses found</Typography>;
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
                            <TableCell>ID</TableCell>
                            <TableCell>Location</TableCell>
                            <TableCell>Capacity</TableCell>
                            <TableCell>Current Stock</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {warehouses.map(warehouse => (
                            <TableRow key={warehouse.id}>
                                <TableCell>{warehouse.id}</TableCell>
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