import React from 'react';
import '../../components/Forms.css'
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, Typography, Alert, AlertTitle,
    TextField, Button, IconButton, Snackbar, Box} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit'
import DeleteIcon from '@mui/icons-material/Delete'

const Orders = () => {
    const url = "http://localhost:8080/orders";
    // const url = "http://sweet2neat.us-east-1.elasticbeanstalk.com/orders";
    const [order, setOrder] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [error, setError] = useState(null);
    const [newOrder, setNewOrder] = useState({
        customerName: '',
        orderDate: '',
        status: '',
        customerAddress: '',
    }); //setting the state for the new order form

    const [editingOrders, setEditingOrders] = useState(null);
    const [successMessage, setSuccessMessage] = useState('');

    //fetch order data when the component mounts
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

    //handles the form submission for adding or updating an order
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

    //function to handle form input changes
    const handleInputChange = (e) => {
        const {name, value} = e.target;
        if(editingOrders){
            setEditingOrders({...editingOrders, [name]: value});
        } else{
            setNewOrder({...newOrder, [name]: value});
        }
    };

    //setting the order to edit
    const handleEdit = (order) => {
        setEditingOrders(order);
    };

    //handles deletion of an order
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

    if (error) {
        return <Alert severity='error'>
            <AlertTitle>Error</AlertTitle>
            Sorry could not get order information
            </Alert>;
    }

    return(
        <>
            <Box name='orderListBox' component="form" onSubmit={handleSubmit} sx={{ mb: 2, mt: 8, padding: 2, borderRadius: 1, boxShadow: 10 }}>
                <TextField
                    label='Customer Name'
                    name='customerName'
                    value={editingOrders ? editingOrders.customerName : newOrder.customerName}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                    className='textField'
                />
                <TextField
                    label='Order Date'
                    name='orderDate'
                    value={editingOrders ? editingOrders.orderDate : newOrder.orderDate}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                    className='textField'
                />
                <TextField
                    label='Status'
                    name='status'
                    value={editingOrders ? editingOrders.status : newOrder.status}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                    className='textField'
                />
                <TextField
                    label='Customer Address'
                    name='customerAddress'
                    value={editingOrders ? editingOrders.customerAddress : newOrder.customerAddress}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                    className='textField'
                />
                <Button name='orderListBtn' type='submit' variant='contained' color='primary'>
                    {editingOrders ? 'Update order' : 'Add order'}
                </Button>
                {editingOrders && (
                    <Button name='cancelEditBtn' variant='contained' color='secondary' onClick={() =>setEditingOrders(null)}>
                        Cancel edit
                    </Button>
                )}
            </Box>
            <Typography name='orderListTitle' variant="h4" gutterBottom>
                Order List
            </Typography>
            <TableContainer component={Paper}>
                <Table name='orderListTable'>
                    <TableHead>
                        <TableRow>
                        <TableCell>Order Id</TableCell>
                            <TableCell>Customer Name</TableCell>
                            <TableCell>Order Date</TableCell>
                            <TableCell>Status</TableCell>
                            <TableCell>Customer Address </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {order.map(order => (
                            <TableRow key={order.id}>
                                <TableCell>{order.id}</TableCell>
                                <TableCell>{order.customerName}</TableCell>
                                <TableCell>{order.orderDate}</TableCell>
                                <TableCell>{order.status}</TableCell>
                                <TableCell>{order.customerAddress}</TableCell>
                                <TableCell>
                                    <IconButton name='editIcon' onClick={() => handleEdit(order)}>
                                        <EditIcon />
                                    </IconButton>
                                    <IconButton name='deleteIcon' onClick={() => handleDelete(order.id)}>
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
            name='orderListSnackbar'
            onClose={handleCloseSnackbar}
            > 
                <Alert
                onClose={handleCloseSnackbar}
                severity='success'
                >
                    {successMessage}
                </Alert>
            </Snackbar>
        </>
    );
};

export default Orders;