import React from 'react';
import '../../components/Forms.css';
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, CircularProgress, Typography, Box,
    TextField, Alert, Button, IconButton, Snackbar, AlertTitle,
    FormControl, InputLabel, Select, MenuItem
} from '@mui/material';
import Edit from '@mui/icons-material/Edit';
import Delete from '@mui/icons-material/Delete';

const OrderInfo = () => {
    
    const apiUrl = import.meta.env.VITE_API_URL;
    const orderItemUrl = `${apiUrl}/orderItem`;
    const orderUrl = `${apiUrl}/orders`;
    const candyUrl = `${apiUrl}/candy`;
    const [orderItem, setOrderItem] = useState([]);
    const [order, setOrder] = useState([]);
    const [candy, setCandy] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [error, setError] = useState(null);
    //state to hold new order item data
    const [newOrderItem, setNewOrderItem] = useState({
        orderId: '',
        candyId: '',
        price: '',
        quantity: '',
    });
    
    const [orderInfo, setOrderInfo] = useState([]);
    const [candyIds, setCandyIds] = useState([]);

    const [editingOrderItem, setEditingOrderItem] = useState(null);
    const [successMessage, setSuccessMessage] = useState('');
    const [validationErrors, setValidationErrors] = useState({});

    //fetch order items, orders, and candies when the component mounts
    useEffect(() => {
        fetch(orderItemUrl)
            .then(response => response.json())
            .then(returnedData => {
                setOrderItem(returnedData);
                setLoaded(true);

            })
            .catch(err => setError(err));
        
        fetch(orderUrl)
            .then(response => response.json())
            .then(orderData => {
                setOrder(orderData);
                const orderArr = orderData.map(order => ({
                    id: order.id,
                    name: order.customerName,
                }));
                setOrderInfo(orderArr);
            })
            .catch(err => setError(err));

        fetch(candyUrl)
            .then(response => response.json())
            .then(candyData => {
                setCandy(candyData);
                const candyArr = candyData.map(candy => ({
                    id: candy.candyId,
                    name: candy.name
                }));
                setCandyIds(candyArr);
        })
            .catch(err => setError(err));
    }, []);

    //handle input changes for both new order item and editing order item
    const handleInputChange = (e) => {
        const {name, value} = e.target;
        if(editingOrderItem) {
            setEditingOrderItem({...editingOrderItem, [name]: value});
        } else{
            setNewOrderItem({...newOrderItem, [name]: value});
        }
    };

    const validateOrderItemData = (data) => {
        const errors = {};
        if (data.price < 0) {
            errors.price = 'Price must be a positive number';
        }
        if (isNaN(data.price)) {
            errors.price = 'Price cannot contain letters';
        }
        if (data.quantity <= 0) {
            errors.quantity = 'Quantity must be a positive number';
        }
        if (isNaN(data.quantity)) {
            errors.quantity = 'Quantity cannot contain letters';
        }
        return errors;
    };

    //handle form submission for adding or updating an order item
    const handleSubmit = (e) => {
        e.preventDefault();
        const orderItemData = editingOrderItem || newOrderItem;
        const method = editingOrderItem ? 'PUT' : 'POST';
        const endpoint = editingOrderItem ? `${orderItemUrl}/${editingOrderItem.id}` : orderItemUrl;
        const validationErrors = validateOrderItemData(orderItemData)
        if (Object.keys(validationErrors).length > 0) {
            setValidationErrors(validationErrors);
            return;
        }

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
                //updates states with editing order item
                setOrderItem(orderItem.map(oi => oi.id === data.id ? data : oi))
                setEditingOrderItem(null);
            } else{
                setOrderItem([...orderItem, data]); //update the state with the new order item
            }
            setNewOrderItem({orderId: '', candyId: '', price: '', quantity: '',}); // reset form
            setSuccessMessage(editingOrderItem ? 'Updated order item successfully!' : 'Added order item successfully!');
        })
        .catch(err => setError('Failed to update order item'));
    }

    //sets orderItem to edit
    const handleEdit = (orderItem) => {
        setEditingOrderItem(orderItem);
    }

    //handles deletion of an order item
    const handleDeleteOrderItem = (id) => {
        fetch(`${orderItemUrl}/${id}`,{
            method: 'DELETE'
        })
        .then (() => {
            //removes an order item from the list
            setOrderItem(orderItem.filter(oi => oi.id !== id));
            setSuccessMessage('Order item deleted successfully!');
        })
        .catch(err => setError(err));
    }

    const handleCloseSnackbar = () => {
        setSuccessMessage('');
    }

    if (!loaded) {
        return <CircularProgress />;
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
            <Box name='orderInfoBox' component='form' onSubmit={handleSubmit} sx={{ mb: 2, mt: 8, padding: 2, borderRadius: 1, boxShadow: 10 }}>
                {/* <TextField
                    label='Order Id'
                    name='orderId'
                    value={editingOrderItem ? editingOrderItem.orderId : newOrderItem.orderId}
                    onChange={handleInputChange}
                    required
                    fullWidth
                    margin='normal'
                    className='textField'
                /> */}
                <FormControl fullWidth sx={{ backgroundColor: '#e6e6fa'}}>
                    <InputLabel id="selectOrderId" >Customer Name*</InputLabel>
                    <Select 
                        labelId="selectOrderId"
                        id="orderIdSelect"
                        value={editingOrderItem ? editingOrderItem.orderId : newOrderItem.orderId}
                        name="orderId"
                        required
                        onChange={handleInputChange}
                        sx={{
                            textAlign:'left'
                        }}
                    >
                        {orderInfo.map(order => (
                            <MenuItem key={order.id} value={order.id}>{order.name}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                {/* <TextField
                    label='Candy Id'
                    name='candyId'
                    value={editingOrderItem ? editingOrderItem.candyId : newOrderItem.candyId}
                    onChange={handleInputChange}
                    required
                    fullWidth
                    margin='normal'
                    className='textField'
                /> */}
                <FormControl fullWidth sx={{ backgroundColor: '#e6e6fa', marginTop:3, marginBottom:1}}>
                    <InputLabel id="selectCandyId" >Candy Name*</InputLabel>
                    <Select 
                        labelId="selectCandyId"
                        id="candySelect"
                        value={editingOrderItem ? editingOrderItem.candyId : newOrderItem.candyId}
                        name="candyId"
                        required
                        onChange={handleInputChange}
                        sx={{
                            textAlign:'left'
                        }}
                    >
                        {candyIds.map(candy => (
                            <MenuItem key={candy.id} value={candy.id}>{candy.name}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <TextField
                    label='Price'
                    name='price'
                    value={editingOrderItem ? editingOrderItem.price : newOrderItem.price}
                    onChange={handleInputChange}
                    required
                    fullWidth
                    error={!!validationErrors.price}
                    helperText={validationErrors.price}
                    margin='normal'
                    className='textField'
                />
                <TextField
                    label='Quantity'
                    name='quantity'
                    value={editingOrderItem ? editingOrderItem.quantity : newOrderItem.quantity}
                    onChange={handleInputChange}
                    required
                    fullWidth
                    error={!!validationErrors.quantity}
                    helperText={validationErrors.quantity}
                    margin='normal'
                    className='textField'
                />
            <Button name='orderInfoBtn' type='submit' variant='contained' color='primary'>
                    {editingOrderItem ? 'Update order item' : 'Add order item'}
                </Button>
                {editingOrderItem && (
                    <Button name='cancelEditBtn' variant='contained' color='secondary' onClick={() => setEditingOrderItem(null)}>
                        Cancel edit
                    </Button>
                )}
        </Box>
            <Typography name='orderInfoTitle' variant="h4" gutterBottom>
                Order Information
            </Typography>
            <TableContainer component={Paper}>
                <Table name='orderInfoTable'>
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
                                <IconButton name='editIcon' onClick={() => handleEdit(orderItem)}>
                                    <Edit />
                                </IconButton>
                                <IconButton name='deleteIcon' onClick={() => handleDeleteOrderItem(orderItem.id)}>
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
            name='orderInfoSnackbar'
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

export default OrderInfo;