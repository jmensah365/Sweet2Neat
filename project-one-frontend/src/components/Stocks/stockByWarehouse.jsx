import React, { useState, useEffect } from 'react';
import '../../components/Forms.css';
import {
    Table, TableBody, TableCell, TableContainer, TablePagination, Tooltip, Fade,
    TableHead, TableRow, Paper, Typography, Box,
    TextField, Button, IconButton, Snackbar, Alert,
    FormControl, InputLabel, Select, MenuItem, Dialog, DialogTitle, DialogContent, DialogActions
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
    const [editingStock, setEditingStock] = useState(null);
    const [successMessage, setSuccessMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    //state for pagination
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

    //pageinate the data
    const paginatedData = stocks.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage);

    //state for opening modals
    const [open, setOpen] = useState(false);
    const [selectStockId, setSelectStockId] = useState(null);


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
        let errorMessages = [];

        if (!data.candyId) {
            errorMessages.push('Candy Name is required');
        }

        if(!data.warehouseId){
            errorMessages.push("Warehouse Location is required")
        }
    
        const quantityStr = typeof data.quantity === 'string' ? data.quantity.trim() : data.quantity;
        if (parseFloat(quantityStr) <= 0 || quantityStr === '') {
            errorMessages.push('Quantity must be a number and greater than zero');
        }
        if (isNaN(parseFloat(quantityStr))) {
            errorMessages.push('Quantity cannot contain letters');
        }
    
        const warehouseCapacity = warehouseCapacities[data.warehouseId];
        if (warehouseCapacity && data.quantity > warehouseCapacity) {
            errorMessages.push(`Quantity exceeds warehouse capacity of ${warehouseCapacity}`);
        }

        if (errorMessages.length > 0) {
            setErrorMessage(errorMessages.join(', and '));
            return false;
        }

        
        return true; // No errors
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const stockData = editingStock || newStock;

        // Validate candy data
        const isValid = validateStockData(stockData);
        if (!isValid) {
            return; // Stop form submission if validation fails
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

    const handleOpenModal = (id) => {
        setSelectStockId(id);
        setOpen(true);
    };

    // Close the modal
    const handleCloseModal = () => {
        setSelectStockId(null);
        setOpen(false);
    };

    const handleConfirmDelete = () => {
        handleDeleteStock(selectStockId);
        handleCloseModal();
    };

    const handleCloseSnackbar = () => {
        setSuccessMessage('');
        setErrorMessage('');
    };


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
                        onChange={handleInputChange}
                        sx={{ textAlign:'left' }}
                    >
                        <MenuItem value="" style={{ color: 'red' }}>Clear Field</MenuItem>
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
                        onChange={handleInputChange}
                        sx={{ textAlign:'left' }}
                    >
                        <MenuItem value="" style={{ color: 'red' }}>Clear Field</MenuItem>
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
                    fullWidth
                    margin='normal'
                    className='textField'
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
                        {paginatedData.map((stock) => (
                            <TableRow key={stock.id} sx={{ '&:hover': { backgroundColor: 'grey.200' }}}>
                                <TableCell>{getWarehouseName(stock.warehouseId)}</TableCell>
                                <TableCell>{getCandyName(stock.candyId)}</TableCell>
                                <TableCell>{stock.quantity}</TableCell>
                                <TableCell>
                                    <Tooltip title='Edit Stock'>
                                        <IconButton name='editIcon' onClick={() => handleEdit(stock)}>
                                            <Edit />
                                        </IconButton>
                                    </Tooltip>
                                    <Tooltip title='Delete Stock'>
                                        <IconButton name='deleteIcon' onClick={() => handleOpenModal(stock.id)}>
                                            <Delete />
                                        </IconButton>
                                    </Tooltip>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
                
                    <Dialog open={open} onClose={handleCloseModal}>
                        <DialogTitle>Confirm Deletion</DialogTitle>
                            <DialogContent>
                                <Typography>Are you sure you want to delete this stock?</Typography>
                            </DialogContent>
                        <DialogActions>
                            <Button onClick={handleCloseModal} color='primary'>Cancel</Button>
                            <Button onClick={handleConfirmDelete} style={{color: 'red'}} >Delete</Button>
                        </DialogActions>
                    </Dialog> 
                
                <TablePagination
                    component={'div'}
                    count={stocks.length}
                    page={page}
                    rowsPerPage={rowsPerPage}
                    onPageChange={handleChangePage}
                    onRowsPerPageChange={handleChangeRowsPerPage}
                    rowsPerPageOptions={[5, 10, 25, {label: 'All', value: stocks.length}]}
                />
            </TableContainer>
            <Snackbar 
                open={!!successMessage}
                autoHideDuration={2000}
                name='warehouseStockSnackbar'
                onClose={handleCloseSnackbar}
            > 
                <Alert onClose={handleCloseSnackbar} severity='success'>
                    {successMessage}
                </Alert>
            </Snackbar>
            <Snackbar 
            open={!!errorMessage}
            name='warehouseStockSnackbarError'
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

export default WarehouseStocks;
