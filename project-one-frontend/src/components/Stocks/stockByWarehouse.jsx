import React, { useState, useEffect } from 'react';
import '../../components/Forms.css';
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, Typography, Box,
    TextField, Button, IconButton, Snackbar, Alert,
    FormControl, InputLabel, Select, MenuItem,
} from '@mui/material';
import Edit from '@mui/icons-material/Edit';
import Delete from '@mui/icons-material/Delete';

const WarehouseStocks = () => {
    const apiUrl = import.meta.env.VITE_API_URL;
    const stockUrl = `${apiUrl}/stock`;
    const warehouseUrl = `${apiUrl}/warehouse`;
    const candyUrl = `${apiUrl}/candy`;

    const [stocks, setStocks] = useState([]);
    const [warehouses, setWarehouses] = useState([]);
    const [candies, setCandies] = useState([]);
    const [newStock, setNewStock] = useState({
        candyId: '',
        warehouseId: '',
        quantity: '',
    });

    const [candyIds, setCandyIds] = useState([]);
    const [warehouseIds, setWarehouseIds] = useState([]);
    const [warehouseCapacities, setWarehouseCapacities] = useState({});

    const [loaded, setLoaded] = useState(false);
    const [error, setError] = useState(null);
    const [validationErrors, setValidationErrors] = useState({});
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
            .then(warehouseData => {
                setWarehouses(warehouseData);
                const warehouseArr = warehouseData.map(warehouse => ({
                    id: warehouse.id,
                    location: warehouse.location,
                    capacity: warehouse.capacity, // Make sure warehouse data includes capacity
                }));
                setWarehouseIds(warehouseArr);
                // Create a map of warehouseId to capacity
                const capacities = warehouseData.reduce((acc, warehouse) => {
                    acc[warehouse.id] = warehouse.capacity;
                    return acc;
                }, {});
                setWarehouseCapacities(capacities);
            })
            .catch(err => setError(err));

        // Fetch candies
        fetch(candyUrl)
            .then(response => response.json())
            .then(candyData => {
                setCandies(candyData);
                const candyArr = candyData.map(candy => ({
                    id: candy.candyId,
                    name: candy.name
                }));
                setCandyIds(candyArr);
            })
            .catch(err => setError(err));
    }, []);

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        if (editingStock) {
            setEditingStock({...editingStock, [name]: value});
        } else {
            setNewStock({...newStock, [name]: value});
        }
    };

    const validateStockData = (data) => {
        const errors = {};
        const quantity = parseFloat(data.quantity);
        const warehouseCapacity = warehouseCapacities[data.warehouseId];
        
        if (isNaN(quantity) || quantity < 0) {
            errors.quantity = 'Quantity must be a non-negative number';
        } else if (quantity > warehouseCapacity) {
            errors.quantity = `Quantity cannot exceed warehouse capacity of ${warehouseCapacity}`;
        }

        return errors;
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const stockData = editingStock || newStock;
        const validationErrors = validateStockData(stockData);

        if (Object.keys(validationErrors).length > 0) {
            setValidationErrors(validationErrors);
            return;
        }

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
            if (text) {
                return JSON.parse(text);
            } else {
                return {};
            }
        })
        .then(data =>{
            if (editingStock) {
                setStocks(stocks.map(s => s.id === data.id ? data : s));
                setEditingStock(null);
            } else {
                setStocks([...stocks, data]);
            }
            setNewStock({candyId: '', warehouseId: '', quantity: ''});
            setSuccessMessage(editingStock ? 'Updated stock successfully!' : 'Added stock successfully!');
            refreshWarehouseDetails();
            
        })
        .catch(err => setError('Failed to update stock'));
    };

    const refreshWarehouseDetails = () => {
        fetch(stockUrl)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(returnedData => {
                setStocks(returnedData);
                setLoaded(true);
            })
            .catch(err => {
                setError("Failed to fetch");
                setLoaded(true);
            });
    }


    const handleEdit = (stock) => {
        setEditingStock(stock);
    };

    const handleDeleteStock = (id) => {
        fetch(`${stockUrl}/${id}`,{
            method: 'DELETE'
        })
        .then(() => {
            setStocks(stocks.filter(s => s.id !== id));
            setSuccessMessage('Stock deleted successfully!');
        })
        .catch(err => setError(err));
    };

    const handleCloseSnackbar = () => {
        setSuccessMessage('');
    };

    if (error) {
        return <Typography>Error: {error.message}</Typography>;
    }

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
            <Box name='warehouseStockBox' component='form' onSubmit={handleSubmit} sx={{ mb: 2, mt: 8, padding: 2, borderRadius: 1, boxShadow: 10 }}>
                <FormControl fullWidth sx={{ backgroundColor: '#e6e6fa' }}>
                    <InputLabel id="selectCandyId">Candy Name*</InputLabel>
                    <Select 
                        labelId="selectCandyId"
                        id="candyIdSelect"
                        value={editingStock ? editingStock.candyId : newStock.candyId}
                        name="candyId"
                        required
                        onChange={handleInputChange}
                        sx={{ textAlign:'left' }}
                    >
                        {candyIds.map(candy => (
                            <MenuItem key={candy.id} value={candy.id}>{candy.name}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <FormControl fullWidth sx={{ backgroundColor: '#e6e6fa', marginTop: 2 }}>
                    <InputLabel id="selectWarehouseId">Warehouse Location*</InputLabel>
                    <Select 
                        labelId="selectWarehouseId"
                        id="warehouseIdSelect"
                        value={editingStock ? editingStock.warehouseId : newStock.warehouseId}
                        name="warehouseId"
                        required
                        onChange={handleInputChange}
                        sx={{ textAlign:'left' }}
                    >
                        {warehouseIds.map(warehouse => (
                            <MenuItem key={warehouse.id} value={warehouse.id}>{warehouse.location}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <TextField
                    label='Quantity'
                    name='quantity'
                    value={editingStock ? editingStock.quantity : newStock.quantity}
                    onChange={handleInputChange}
                    required
                    fullWidth
                    margin='normal'
                    className='textField'
                    error={!!validationErrors.quantity}
                    helperText={validationErrors.quantity}
                />
                <Button name='warehouseStockBtn' type='submit' variant='contained' color='primary'>
                    {editingStock ? 'Update stock' : 'Add stock'}
                </Button>
                {editingStock && (
                    <Button name='cancelEditBtn' variant='contained' color='secondary' onClick={() => setEditingStock(null)}>
                        Cancel edit
                    </Button>
                )}
            </Box>
            <Typography name='warehouseStockTitle' variant='h4' gutterBottom>
                Stock Inventory
            </Typography>
            <TableContainer component={Paper}>
                <Table name='warehouseStockTable'>
                    <TableHead>
                        <TableRow>
                            <TableCell>Warehouse</TableCell>
                            <TableCell>Candy Name</TableCell>
                            <TableCell>Quantity</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody name='tableBody'>
                        {stocks.map((stock) => (
                            <TableRow key={stock.id}>
                                <TableCell>{getWarehouseName(stock.warehouseId)}</TableCell>
                                <TableCell>{getCandyName(stock.candyId)}</TableCell>
                                <TableCell>{stock.quantity}</TableCell>
                                <TableCell>
                                    <IconButton name='editIcon' onClick={() => handleEdit(stock)}>
                                        <Edit />
                                    </IconButton>
                                    <IconButton name='deleteIcon' onClick={() => handleDeleteStock(stock.id)}>
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
                name='warehouseStockSnackbar'
                onClose={handleCloseSnackbar}
            > 
                <Alert onClose={handleCloseSnackbar} severity='success'>
                    {successMessage}
                </Alert>
            </Snackbar>
        </>
    );
};

export default WarehouseStocks;