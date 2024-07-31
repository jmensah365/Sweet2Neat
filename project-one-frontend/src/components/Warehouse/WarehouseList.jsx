import React from 'react';
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper,
    CircularProgress, Typography, Alert, AlertTitle,
    TextField, Button, Box, IconButton, Snackbar
} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit'
import DeleteIcon from '@mui/icons-material/Delete'


const WarehouseList = () => {
    //TODO put in env variables
    const url = "http://localhost:8080/warehouse";
    //hook to keep track of warehouses
    const [warehouses, setWarehouses] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [error, setError] = useState(null);
    const [newWarehouse, setNewWarehouse] = useState({
        location: '',
        capacity: '',
        currentStock: ''
    });

    const [editingWarehouse, setEditingWarehouse] = useState(null);
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
                setWarehouses(returnedData);
                setLoaded(true);
            })
            .catch(err => {
                setError(err);
                setLoaded(true);
            });
    }, []);

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        if(editingWarehouse){
            setEditingWarehouse({...editingWarehouse, [name]: value});
        } else{
            setNewWarehouse({...newWarehouse, [name]: value});
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const warehouseData = editingWarehouse || newWarehouse
        const method = editingWarehouse ? 'PUT' : 'POST';
        const endpoint = editingWarehouse ? `${url}/${editingWarehouse.id}` : url;

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
            } else{
                return {};
            }
        })
        .then(data => {
            if (editingWarehouse) {
                setWarehouses(warehouses.map(w => w.id === data.id ? data : w))
                setEditingWarehouse(null);
                console.log(warehouses.length);
            }else{
                setWarehouses([...warehouses, data]);
            }
            setNewWarehouse({location: '', capacity: '', currentStock: ''});
            setSuccessMessage(editingWarehouse ? 'Warehouse updated successfully!' : 'Warehouse added successfully!');
        })
        .catch(err => {
            console.log(err);
            setError('Failed to update warehouse');
        });
    };

    const handleEdit = (warehouse) => {
        console.log('Editing warehouse:',warehouse);
        console.log(warehouses.length);
        setEditingWarehouse(warehouse);
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
    };

    if (!loaded) {
        return <CircularProgress />;
    }

    if (error) {
        return <Alert severity='error'>
            <AlertTitle>Error</AlertTitle>
            {error.message}
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
            
            <Box component="form" onSubmit={handleSubmit} sx={{mb: 2}}>
                <TextField
                    label='Location'
                    name='location'
                    value={editingWarehouse ? editingWarehouse.location : newWarehouse.location}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                />
                <TextField
                    label='Capacity'
                    name='capacity'
                    value={editingWarehouse ? editingWarehouse.capacity : newWarehouse.capacity}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                />
                <TextField
                    label='Current Stock'
                    name='currentStock'
                    value={editingWarehouse ? editingWarehouse.currentStock : newWarehouse.currentStock}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                />
                <Button type='submit' variant='contained' color='primary'>
                    {editingWarehouse ? 'Update warehouse' : 'Add warehouse'}
                </Button>
                {editingWarehouse && (
                    <Button variant='contained' color='secondary' onClick={() =>setEditingWarehouse(null)}>
                        Cancel edit
                    </Button>
                )}
            </Box>
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
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {warehouses.map(warehouse => (
                            <TableRow key={warehouse.id}>
                                <TableCell>{warehouse.location}</TableCell>
                                <TableCell>{warehouse.capacity}</TableCell>
                                <TableCell>{warehouse.currentStock}</TableCell>
                                <TableCell>
                                    <IconButton onClick={() => handleEdit(warehouse)}>
                                        <EditIcon />
                                    </IconButton>
                                    <IconButton onClick={() => handleDelete(warehouse.id)}>
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

export default WarehouseList;