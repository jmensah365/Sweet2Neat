import React from 'react';
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper,
    CircularProgress, Typography, Alert, AlertTitle,
    TextField, Button, IconButton, Snackbar, Box} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit'
import DeleteIcon from '@mui/icons-material/Delete'

const Orders = () => {
    const url = "http://localhost:8080/orders";
    const [order, setOrder] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [error, setError] = useState(null);
    const [newOrder, setNewOrder] = useState({
        customerName: '',
        orderDate: '',
        status: '',
        customerAddress: '',
    });

    const [editingOrders, setEditingOrders] = useState(null);
    const [successMessage, setSuccessMessage] = useState('');

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

    const handleSubmit = (e) => {
        e.preventDefault();
        const orderData = editingOrders || newOrder;
        const method = editingOrders ? 'PUT' : 'POST';
        const endpoint = editingOrders ? `${url}/${editingOrders.id}` : url;

        fetch(endpoint, {
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(orderData),
        })
        .then(response => response.text())
        .then(text => {
            if (text) {
                return JSON.parse(text);
            } else{
                return {};
            }
        })
        .then(data => {
            if (editingOrders) {
                setOrder(order.map(o => o.id === data.id ? data : o))
                setEditingOrders(null);
            }else{
                setOrder([...order, data]);
            }
            setNewOrder({customerName: '', orderDate: '', status: '', customerAddress: '',});
            setSuccessMessage(editingOrders ? 'Order updated successfully!' : 'Order added successfully!');
        })
        .catch(err => {
            console.log(err);
            setError('Failed to update order');
        });
    };

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        if(editingOrders){
            setEditingOrders({...editingOrders, [name]: value});
        } else{
            setNewOrder({...newOrder, [name]: value});
        }
    };

    const handleEdit = (order) => {
        setEditingOrders(order);
    };

    const handleDelete = (id) => {
        fetch(`${url}/${id}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            setOrder(order.filter(o => o.id !== id));
            setSuccessMessage('Order deleted successfully!');
        })
        .catch(err => setError(err));
    };

    const handleCloseSnackbar = () => {
        setSuccessMessage('');
    };

    if (!loaded) {
        return <CircularProgress />;
    }

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
            <Box component="form" onSubmit={handleSubmit} sx={{mb: 2}}>
                <TextField
                    label='Customer Name'
                    name='customerName'
                    value={editingOrders ? editingOrders.customerName : newOrder.customerName}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                />
                <TextField
                    label='Order Data'
                    name='orderDate'
                    value={editingOrders ? editingOrders.orderDate : newOrder.orderDate}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                />
                <TextField
                    label='Status'
                    name='status'
                    value={editingOrders ? editingOrders.status : newOrder.status}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                />
                <TextField
                    label='Customer Address'
                    name='customerAddress'
                    value={editingOrders ? editingOrders.customerAddress : newOrder.customerAddress}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                />
                <Button type='submit' variant='contained' color='primary'>
                    {editingOrders ? 'Update order' : 'Add order'}
                </Button>
                {editingOrders && (
                    <Button variant='contained' color='secondary' onClick={() =>setEditingOrders(null)}>
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
                                <TableCell>
                                    <IconButton onClick={() => handleEdit(order)}>
                                        <EditIcon />
                                    </IconButton>
                                    <IconButton onClick={() => handleDelete(order.id)}>
                                        <DeleteIcon />
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

export default Orders;