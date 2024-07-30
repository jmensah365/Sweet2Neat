import React from 'react';
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper,
    CircularProgress, Typography, Alert, AlertTitle} from '@mui/material';

const WarehouseList = () => {
    const url = "http://localhost:8080/orders";
    const [order, setOrder] = useState([]);
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
                setOrder(returnedData);
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
            Sorry could not get order information
            </Alert>;
    }

    if (order.length === 0) {
        return <Alert severity='error'>
            <AlertTitle>Error</AlertTitle>
            No orders found
            </Alert>;
    }

    return(
        <>
            <Typography variant="h4" gutterBottom>
                Order Information
            </Typography>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Customer Name</TableCell>
                            <TableCell>Order Date</TableCell>
                            <TableCell>Status</TableCell>
                            <TableCell>Customer Address </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {order.map(order => (
                            <TableRow key={order.id}>
                                <TableCell>{order.customerName}</TableCell>
                                <TableCell>{order.orderDate}</TableCell>
                                <TableCell>{order.status}</TableCell>
                                <TableCell>{order.customerAddress}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </>
    );
};

export default WarehouseList;