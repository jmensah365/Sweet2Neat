import React from 'react';
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, Typography, Alert, AlertTitle,
    TextField, Button, Box, IconButton, Snackbar
} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit'
import DeleteIcon from '@mui/icons-material/Delete'

const WarehouseList = () => {
    const apiUrl = import.meta.env.VITE_API_URL;
    const url = `${apiUrl}/warehouse`;

    const [warehouses, setWarehouses] = useState([]);
    const [newWarehouse, setNewWarehouse] = useState({
        location: '',
        capacity: '',
        currentStock: ''
    });
    const [editingWarehouse, setEditingWarehouse] = useState(null);
    const [successMessage, setSuccessMessage] = useState('');
    const [error, setError] = useState(null);
    const [loaded, setLoaded] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    


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
                setWarehouses(sortedData);
                setLoaded(true);
            })
            .catch(err => {
                setError(err);
                setLoaded(true);
            });
    }, []);

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        if (editingWarehouse) {
            setEditingWarehouse({...editingWarehouse, [name]: value});
        } else {
            setNewWarehouse({...newWarehouse, [name]: value});
        }
    };

    const validateWarehouseData = (data) => {
        let errorMessages = [];

        if (data.location.trim() === '') {
            errorMessages.push('Location is required');
        }
    
        if (data.capacity < 0 || data.capacity.trim() === '') {
            errorMessages.push('Capacity must be a positive number');
        }

        if (isNaN(data.capacity)) {
            errorMessages.push('Capacity cannot contain letters');
        }

        if (errorMessages.length > 0) {
            setErrorMessage(errorMessages.join(', and '));
            return false;
        }

        
        return true; // No errors
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const warehouseData = editingWarehouse || newWarehouse;
        
        const method = editingWarehouse ? 'PUT' : 'POST';
        const endpoint = editingWarehouse ? `${url}/${editingWarehouse.id}` : url;

        // Validate warehouse data
        const isValid = validateWarehouseData(warehouseData);
        if (!isValid) {
            return; // Stop form submission if validation fails
        }

        fetch(endpoint, {
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(warehouseData),
        })
        .then(response => response.text())
        .then(text => {
            if (text) {
                return JSON.parse(text);
            } else {
                return {};
            }
        })
        .then(data => {
            if (editingWarehouse) {
                setWarehouses(warehouses.map(w => w.id === data.id ? data : w))
                setEditingWarehouse(null);
            } else {
                setWarehouses([...warehouses, data]);
            }
            setNewWarehouse({location: '', capacity: '', currentStock: ''});
            setSuccessMessage(editingWarehouse ? 'Warehouse updated successfully!' : 'Warehouse added successfully!');
            refreshWarehouseDetails();
        })
        .catch(err => {
            console.log(err);
            setError('Failed to update warehouse');
        });
    };

    const refreshWarehouseDetails = () => {
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
    }

    const handleEdit = (warehouse) => {
        setEditingWarehouse(warehouse);
        setErrorMessage('');
    };

    const handleDelete = (id) => {
        fetch(`${url}/${id}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            setWarehouses(warehouses.filter(w => w.id !== id));
            setSuccessMessage('Warehouse deleted successfully!');
        })
        .catch(err => setError(err));
    };

    const handleCloseSnackbar = () => {
        setSuccessMessage('');
        setErrorMessage('');
    };

    if (error) {
        return <Alert severity='error'>
            <AlertTitle>Error</AlertTitle>
            {error.message}
        </Alert>;
    }

    return(
        <>
            <Box name='warehouseListBox' component="form" onSubmit={handleSubmit} sx={{ mb: 2, mt: 8, padding: 2, borderRadius: 1, boxShadow: 10 }}>
                <TextField
                    label='Location'
                    name='location'
                    value={editingWarehouse ? editingWarehouse.location : newWarehouse.location}
                    onChange={handleInputChange}
                    fullWidth
                    margin='normal'
                    className='textField'
                />
                <TextField
                    label='Capacity'
                    name='capacity'
                    value={editingWarehouse ? editingWarehouse.capacity : newWarehouse.capacity}
                    onChange={handleInputChange}
                    fullWidth
                    margin='normal'
                    className='textField'
                />
                <Button name='warehouseBtn' type='submit' variant='contained' color='primary'>
                    {editingWarehouse ? 'Update warehouse' : 'Add warehouse'}
                </Button>
                {editingWarehouse && (
                    <Button name='cancelEditBtn' variant='contained' color='secondary' onClick={() => setEditingWarehouse(null)}>
                        Cancel edit
                    </Button>
                )}
            </Box>
            <Typography name='warehouseListTitle' variant="h4" gutterBottom>
                Warehouse List
            </Typography>
            <TableContainer component={Paper}>
                <Table name='warehouseTable'>
                    <TableHead>
                        <TableRow>
                            <TableCell>Warehouse Id</TableCell>
                            <TableCell>Location</TableCell>
                            <TableCell>Stock/Capacity</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody name='warehouseTBody'>
                        {warehouses.map(warehouse => (
                            <TableRow key={warehouse.id}>
                                <TableCell>{warehouse.id}</TableCell>
                                <TableCell>{warehouse.location}</TableCell>
                                <TableCell>{warehouse.currentStock}/{warehouse.capacity}</TableCell>
                                <TableCell>
                                    <IconButton name='editIcon' onClick={() => handleEdit(warehouse)}>
                                        <EditIcon />
                                    </IconButton>
                                    <IconButton name='deleteIcon' onClick={() => handleDelete(warehouse.id)}>
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
                name='WarehouseListSnackbar'
                autoHideDuration={6000}
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
            name='warehouseListSnackbarError'
            autoHideDuration={6000}
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

export default WarehouseList;