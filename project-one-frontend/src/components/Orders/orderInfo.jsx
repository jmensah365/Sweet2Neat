import React from 'react';
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper,
    CircularProgress, Typography, Alert, AlertTitle} from '@mui/material';

const OrderInfo = () => {
    const url = "http://localhost:8080/orderItem";
    const [orderItem, setOrderItem] = useState([]);
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
                setOrderItem(returnedData);
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

    if (orderItem.length === 0) {
        return <Alert severity='error'>
            <AlertTitle>Error</AlertTitle>
            No order information found
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
                            <TableCell>Customer</TableCell>
                            <TableCell>Candy Ordered</TableCell>
                            <TableCell>Total price</TableCell>
                            <TableCell>Quantity </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {orderItem.map(orderItem => (
                            <TableRow key={orderItem.id}>
                                <TableCell>{orderItem.orderId.customerName}</TableCell>
                                <TableCell>{orderItem.price}</TableCell>
                                <TableCell>{orderItem.quantity}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </>
    );
};

export default OrderInfo;