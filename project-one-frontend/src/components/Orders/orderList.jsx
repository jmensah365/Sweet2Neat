import React from 'react';
import '../../components/Forms.css'
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer, TablePagination,
    TableHead, TableRow, Paper, Typography, Alert, AlertTitle,
    TextField, Button, IconButton, Snackbar, Box,
    FormControl, InputLabel, Select, MenuItem, Dialog, DialogTitle, DialogContent, DialogActions
} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit'
import DeleteIcon from '@mui/icons-material/Delete'

const Orders = () => {
    
    const apiUrl = import.meta.env.VITE_API_URL;
    const url = `${apiUrl}/orders`;
    const [order, setOrder] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [error, setError] = useState(null);
    const [newOrder, setNewOrder] = useState({ // removed orderDate
        customerName: '',
        status: '',
        customerAddress: '',
    }); //setting the state for the new order form


    const [editingOrders, setEditingOrders] = useState(null);
    const [successMessage, setSuccessMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    //const state for pagination
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(5);

    //handle page change
    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    //handle rows per page change
    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    //paginate the data
    const paginatedData = order.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage);

    //state for opening modals
    const [open, setOpen] = useState(false);
    const [selectOrderId, setSelectOrderId] = useState(null);

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
                const sortedData = returnedData.sort((a,b) => a.id - b.id);
                setOrder(sortedData);
                setLoaded(true);
            })
            .catch(err => {
                setError(err);
                setLoaded(true);
            });
    }, []);

    //validating order data before submission
    const validateOrdersData = (data) => { // removed order date validation
        let errorMessages = [];

        if (data.customerName.trim() === '') {
            errorMessages.push('Customer Name is required');
        }

        if (!data.status) {
            errorMessages.push('Status is required');
        }

        if (data.customerAddress.trim() === '') {
            errorMessages.push('Customer Address is required');
        }

        if (errorMessages.length > 0) {
            setErrorMessage(errorMessages.join(', and '));
            return false;
        }

        
        return true; // No errors
    };

    //handles the form submission for adding or updating an order
    const handleSubmit = (e) => {
        e.preventDefault();
        const orderData = editingOrders || newOrder;
        const method = editingOrders ? 'PUT' : 'POST';
        const endpoint = editingOrders ? `${url}/${editingOrders.id}` : url;

        // Validate order information data
        const isValid = validateOrdersData(orderData);
        if (!isValid) {
            return; // Stop form submission if validation fails
        }

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
            setNewOrder({customerName: '', status: '', customerAddress: '',}); // removed saving orderdate state
            setSuccessMessage(editingOrders ? 'Order updated successfully!' : 'Order added successfully!');
            refreshOrdersDetails();
        })
        .catch(err => {
            console.log(err);
            setError('Failed to update order');
        });
    };


    //refreshing order table after an action
    const refreshOrdersDetails = () => {
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(returnedData => {
                const sortedData = returnedData.sort((a,b) => a.id - b.id);
                setOrder(sortedData);
                setLoaded(true);
            })
            .catch(err => {
                setError(err);
                setLoaded(true);
            });
    }

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

    const handleOpenModal = (id) => {
        setSelectOrderId(id);
        setOpen(true);
    };

    const handleCloseModal = () => {
        setSelectOrderId(null);
        setOpen(false);
    };

    const handleConfirmDelete = () => {
        handleDelete(selectOrderId);
        handleCloseModal();
    };

    const handleCloseSnackbar = () => {
        setSuccessMessage('');
        setErrorMessage('');
    };


    return(
        <>
            <Box name='orderListBox' component="form" onSubmit={handleSubmit} sx={{ mb: 2, mt: 8, padding: 2, borderRadius: 1, boxShadow: 10 }}>
                <TextField
                    label='Customer Name'
                    name='customerName'
                    value={editingOrders ? editingOrders.customerName : newOrder.customerName}
                    onChange={handleInputChange}
                    fullWidth
                    margin='normal'
                    className='textField'
                />

                {/* LocalizationProvider makes sure the date is localized */}
                {/* <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DatePicker
                        label="Order Date"
                        name="datePicker"
                        // editingOrders?.orderData checks if editingOrders is exists and has an orerDate
                        // DatePicker needs its value to be a Day.js object so convert it here.
                        // the last condition for null is to allow it to start from a null state, without it, it outlines the datepicker in red
                        value={editingOrders?.orderDate ? dayjs(editingOrders.orderDate) : (newOrder?.orderDate ? dayjs(newOrder.orderDate) : null)}
                        // calls handleOrderDate to structure the object so it can be applied like a regular form input
                        onChange={handleOrderDate}
                        sx={{
                            backgroundColor: '#e6e6fa',
                            width: '100%',
                            marginTop:1
                        }}
                        slotProps={{
                            textField: {
                                inputProps: {
                                    name: "orderDatePicker",
                                },
                            },
                        }}
                    />
                </LocalizationProvider> */}
                <FormControl fullWidth sx={{ backgroundColor: '#e6e6fa', marginTop:2, marginBottom:1 }}>
                    <InputLabel id="selectStatus" >Status*</InputLabel>
                    <Select 
                        labelId="selectStatus"
                        id="statusSelect"
                        value={editingOrders ? editingOrders.status : newOrder.status}
                        name="status"
                        onChange={handleInputChange}
                        sx={{
                            textAlign:'left',
                        }}
                    >
                        <MenuItem name="clear" value="" style={{color: 'red'}}>Clear Field</MenuItem>
                        <MenuItem name="ordered" value={"Ordered"}>Ordered</MenuItem>
                        <MenuItem name="pending" value={"Pending"}>Pending</MenuItem>
                        <MenuItem name="completed" value={"Completed"}>Completed</MenuItem>
                    </Select>
                </FormControl>
                <TextField
                    label='Customer Address'
                    name='customerAddress'
                    value={editingOrders ? editingOrders.customerAddress : newOrder.customerAddress}
                    onChange={handleInputChange}
                    fullWidth
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
                            <TableCell>Customer Name</TableCell>
                            <TableCell>Order Date</TableCell>
                            <TableCell>Status</TableCell>
                            <TableCell>Customer Address </TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody name="tableBody">
                        {paginatedData.map(order => (
                            <TableRow key={order.id}>
                                <TableCell>{order.customerName}</TableCell>
                                <TableCell>{order.orderDate}</TableCell>
                                <TableCell>{order.status}</TableCell>
                                <TableCell>{order.customerAddress}</TableCell>
                                <TableCell>
                                    <IconButton name='editIcon' onClick={() => handleEdit(order)}>
                                        <EditIcon />
                                    </IconButton>
                                    <IconButton name='deleteIcon' onClick={() => handleOpenModal(order.id)}>
                                        <DeleteIcon />
                                    </IconButton>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
                <Dialog open={open} onClose={handleCloseModal}>
                    <DialogTitle>Confirm Deletion</DialogTitle>
                    <DialogContent>
                        <Typography>Are you sure you want to delete this order?</Typography>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleCloseModal} color='primary'>
                            Cancel
                        </Button>
                        <Button onClick={handleConfirmDelete} style={{color:'red'}}>
                            Delete
                        </Button>
                    </DialogActions>
                </Dialog>
                <TablePagination
                    component={'div'}
                    count={order.length}
                    page={page}
                    onPageChange={handleChangePage}
                    rowsPerPage={rowsPerPage}
                    onRowsPerPageChange={handleChangeRowsPerPage}
                    rowsPerPageOptions={[5, 10, 25, { label: 'All', value: order.length }]}
                />
            </TableContainer>
            <Snackbar 
            open={!!successMessage}
            autoHideDuration={2000}
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
            <Snackbar 
            open={!!errorMessage}
            name='orderListSnackbarError'
            autoHideDuration={2000}
            onClose={handleCloseSnackbar}
            > 
                <Alert
                onClose={handleCloseSnackbar}
                severity='error'
                >
                    {errorMessage}
                </Alert>
            </Snackbar>
        </>
    );
};

export default Orders;
