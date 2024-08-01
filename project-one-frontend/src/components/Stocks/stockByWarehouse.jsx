import React, { useState, useEffect } from 'react';
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, CircularProgress, Typography, Box,
    TextField, Alert, Button, IconButton, Snackbar
} from '@mui/material';
import Edit from '@mui/icons-material/Edit';
import Delete from '@mui/icons-material/Delete';


const WarehouseStocks = () => {
    const stockUrl = 'http://localhost:8080/stock';
    const warehouseUrl = 'http://localhost:8080/warehouse';
    const candyUrl = 'http://localhost:8080/candy';
    const [stocks, setStocks] = useState([]);
    const [warehouses, setWarehouses] = useState([]);
    const [candies, setCandies] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [error, setError] = useState(null);
    const [newStock, setNewStock] = useState({
        candyId: '',
        warehouseId: '',
        quantity: '',
    });

    const [editingStock, setEditingStock] = useState(null);
    const [successMessage, setSuccessMessage] = useState('');

    useEffect(() => {
        // Fetch stocks
        fetch(stockUrl)
            .then(response => response.json())
            .then(stockData => {
                setStocks(stockData);
                setLoaded(true);
            })
            .catch(err => setError(err));

        // Fetch warehouses
        fetch(warehouseUrl)
            .then(response => response.json())
            .then(warehouseData => setWarehouses(warehouseData))
            .catch(err => setError(err));

        // Fetch candies
        fetch(candyUrl)
            .then(response => response.json())
            .then(candyData => setCandies(candyData))
            .catch(err => setError(err));
    }, []);

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        if(editingStock) {
            setEditingStock({...editingStock, [name]: value});
        } else{
            setNewStock({...newStock, [name]: value});
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const stockData = editingStock || newStock;
        const method = editingStock ? 'PUT' : 'POST';
        const endpoint = editingStock ? `${stockUrl}/${editingStock.id}` : stockUrl;

        fetch(endpoint, {
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(stockData),
        })
        .then(response => response.text())
        .then(text => {
            if(text) {
                return JSON.parse(text);
            } else {
                return {};
            }
        })
        .then(data =>{
            if(editingStock) {
                setStocks(stocks.map(s =>s.id === data.id ? data : s))
                setEditingStock(null);
            } else{
                setStocks([...stocks, data]); //update the state with the new stock
            }
            setNewStock({candyId: '', warehouseId: '', quantity: ''}); // reset form
            setSuccessMessage(editingStock ? 'Updated stock successfully!' : 'Added stock successfully');
        })
        .catch(err => setError('Failed to update stock'));
    }

    const handleEdit = (stock) => {
        setEditingStock(stock);
    }

    const handleDeleteStock = (id) => {
        fetch(`${stockUrl}/${id}`,{
            method: 'DELETE'
        })
        .then (() => {
            setStocks(stocks.filter(s => s.id !== id));
            setSuccessMessage('Warehouse deleted successfully');
        })
        .catch(err => setError(err));
    }

    const handleCloseSnackbar = () => {
        setSuccessMessage('');
    }

    if (!loaded) {
        return <CircularProgress />;
    }

    if (error) {
        return <Typography>Error: {error.message}</Typography>;
    }

    // Helper functions to get warehouse and candy names
    const getWarehouseName = (id) => {
        const warehouse = warehouses.find(w => w.id === id);
        return warehouse ? warehouse.location : 'Unknown Warehouse';
    };

    const getCandyName = (id) => {
        const candy = candies.find(c => c.candyId === id);
        return candy ? candy.name : 'Unknown Candy';
    };

    return (
        <>
        <Box component='form' onSubmit={handleSubmit} sx={{mb:2}}>
            <TextField
                label='Candy Name'
                name='candyId'
                value={editingStock ? editingStock.candyId : newStock.candyId}
                onChange={handleInputChange}
                required
                fullWidth
                margin='normal'
            />
            <TextField
                label='Warehouse Address'
                name='warehouseId'
                value={editingStock ? editingStock.warehouseId : newStock.warehouseId}
                onChange={handleInputChange}
                required
                fullWidth
                margin='normal'
            />
            <TextField
                label='Quantity'
                name='quantity'
                value={editingStock ? editingStock.quantity : newStock.quantity}
                onChange={handleInputChange}
                required
                fullWidth
                margin='normal'
            />
            <Button type='submit' variant='contained' color='primary'>
                    {editingStock ? 'Update stock' : 'Add stock'}
                </Button>
                {editingStock && (
                    <Button variant='contained' color='secondary' onClick={() => setEditingStock(null)}>
                        Cancel edit
                    </Button>
                )}
        </Box>
        <Typography variant='h4' gutterBottom>
            Stock Inventory</Typography>
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Warehouse</TableCell>
                        <TableCell>Candy Name</TableCell>
                        <TableCell>Quantity</TableCell>
                        <TableCell>Actions</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {stocks.map((stock) => (
                        <TableRow key={stock.id}>
                            <TableCell>{getWarehouseName(stock.warehouseId)}</TableCell>
                            <TableCell>{getCandyName(stock.candyId)}</TableCell>
                            <TableCell>{stock.quantity}</TableCell>
                            <TableCell>
                                <IconButton onClick={() => handleEdit(stock)}>
                                    <Edit />
                                </IconButton>
                                <IconButton onClick={() => handleDeleteStock(stock.id)}>
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

export default WarehouseStocks;
