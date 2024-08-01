import React from 'react';
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, CircularProgress, Typography, Box,
    TextField, Alert, Button, IconButton, Snackbar, AlertTitle
} from '@mui/material';
import Edit from '@mui/icons-material/Edit';
import Delete from '@mui/icons-material/Delete';

const OrderInfo = () => {
    const orderItemUrl = "http://localhost:8080/orderItem";
    const orderUrl = "http://localhost:8080/orders";
    const candyUrl = 'http://localhost:8080/candy';
    const [orderItem, setOrderItem] = useState([]);
    const [order, setOrder] = useState([]);
    const [candy, setCandy] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [error, setError] = useState(null);
    const [newOrderItem, setNewOrderItem] = useState({
        orderId: '',
        candyId: '',
        price: '',
        quantity: '',
    });

    const [editingOrderItem, setEditingOrderItem] = useState(null);
    const [successMessage, setSuccessMessage] = useState('');

    useEffect(() => {
        fetch(orderItemUrl)
            .then(response => response.json())
            .then(returnedData => {
                console.log('Fetched order items:', returnedData); // Log the response to debug
                setOrderItem(returnedData);
                setLoaded(true);
            })
            .catch(err => setError(err));
        
        fetch(orderUrl)
            .then(response => response.json())
            .then(orderData => setOrder(orderData))
            .catch(err => setError(err));

        fetch(candyUrl)
            .then(response => response.json())
            .then(candyData => setCandy(candyData))
            .catch(err => setError(err));
    }, []);

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        if(editingOrderItem) {
            setEditingOrderItem({...editingOrderItem, [name]: value});
        } else{
            setNewOrderItem({...newOrderItem, [name]: value});
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const orderItemData = editingOrderItem || newOrderItem;
        const method = editingOrderItem ? 'PUT' : 'POST';
        const endpoint = editingOrderItem ? `${orderItemUrl}/${editingOrderItem.id}` : orderItemUrl;

        fetch(endpoint, {
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(orderItemData),
        })
        .then(response => response.json())
        .then(data =>{
            if(editingOrderItem) {
                setOrderItem(orderItem.map(oi => oi.id === data.id ? data : oi))
                setEditingOrderItem(null);
            } else{
                setOrderItem([...orderItem, data]); //update the state with the new order item
            }
            setNewOrderItem({orderId: '', candyId: '', price: '', quantity: '',}); // reset form
            setSuccessMessage(editingOrderItem ? 'Updated order item successfully!' : 'Added order item successfully');
        })
        .catch(err => setError('Failed to update order item'));
    }

    const handleEdit = (orderItem) => {
        setEditingOrderItem(orderItem);
    }

    const handleDeleteOrderItem = (id) => {
        fetch(`${orderItemUrl}/${id}`,{
            method: 'DELETE'
        })
        .then (() => {
            setOrderItem(orderItem.filter(oi => oi.id !== id));
            setSuccessMessage('Order item deleted successfully');
        })
        .catch(err => setError(err));
    }

    const handleCloseSnackbar = () => {
        setSuccessMessage('');
    }

    if (!loaded) {
        return <CircularProgress />;
    }


    if (orderItem.length === 0) {
        return <Alert severity='error'>
            <AlertTitle>Error</AlertTitle>
            No order information found
            </Alert>;
    }

    const getCustomerName = (id) => {
        const foundOrder = order.find(o => o.id === id);
        return foundOrder ? foundOrder.customerName : 'Unknown Customer Name';
    }

    const getCandyName = (id) => {
        const foundCandy = candy.find(c => c.candyId === id);
        return foundCandy ? foundCandy.name : 'Unknown Candy';
    };

    return(
        <>
            <Box component='form' onSubmit={handleSubmit} sx={{mb:2}}>
                <TextField
                    label='Order Id'
                    name='orderId'
                    value={editingOrderItem ? editingOrderItem.orderId : newOrderItem.orderId}
                    onChange={handleInputChange}
                    required
                    fullWidth
                    margin='normal'
                />
                <TextField
                    label='Candy Id'
                    name='candyId'
                    value={editingOrderItem ? editingOrderItem.candyId : newOrderItem.candyId}
                    onChange={handleInputChange}
                    required
                    fullWidth
                    margin='normal'
                />
                <TextField
                    label='Price'
                    name='price'
                    value={editingOrderItem ? editingOrderItem.price : newOrderItem.price}
                    onChange={handleInputChange}
                    required
                    fullWidth
                    margin='normal'
                />
                <TextField
                    label='Quantity'
                    name='quantity'
                    value={editingOrderItem ? editingOrderItem.quantity : newOrderItem.quantity}
                    onChange={handleInputChange}
                    required
                    fullWidth
                    margin='normal'
                />
            <Button type='submit' variant='contained' color='primary'>
                    {editingOrderItem ? 'Update order item' : 'Add order item'}
                </Button>
                {editingOrderItem && (
                    <Button variant='contained' color='secondary' onClick={() => setEditingOrderItem(null)}>
                        Cancel edit
                    </Button>
                )}
        </Box>
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
                            <TableCell>Actions </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {orderItem.map(orderItem => (
                            <TableRow key={orderItem.id}>
                                <TableCell>{getCustomerName(orderItem.orderId)}</TableCell>
                                <TableCell>{getCandyName(orderItem.candyId)}</TableCell>
                                <TableCell>{orderItem.price}</TableCell>
                                <TableCell>{orderItem.quantity}</TableCell>
                                <TableCell>
                                <IconButton onClick={() => handleEdit(orderItem)}>
                                    <Edit />
                                </IconButton>
                                <IconButton onClick={() => handleDeleteOrderItem(orderItem.id)}>
                                    <Delete />
                                </IconButton>
                            </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <Snackbar
                open={!!successMessage}
                autoHideDuration={6000}
                onClose={handleCloseSnackbar}
                message={successMessage}
            />
        </>
    );
};

export default OrderInfo;