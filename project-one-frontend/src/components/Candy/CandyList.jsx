import React from 'react';
import '../../components/Forms.css'
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer, TablePagination,
    TableHead, TableRow, Paper, Typography, Alert,
    TextField, Button, Box, IconButton, Snackbar,
    FormControl,
    InputLabel,
    MenuItem, Select, Dialog, DialogActions, DialogContent, DialogTitle, Tooltip
} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import FavoriteIcon from '@mui/icons-material/Favorite';
import SearchBar from '../SearchBar/SearchBar';

const CandyList = () => {

    const apiUrl = import.meta.env.VITE_API_URL;
    const url = `${apiUrl}/candy`;
    const [candy, setCandy] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [error, setError] = useState(null);
    //state to hold new data
    const [newCandy, setNewCandy] = useState({
        name: '',
        type: '',
        flavor: '',
        price: '',
        weight: '',
    });

    const [editingCandy, setEditingCandy] = useState(null);
    const [successMessage, setSuccessMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    // State for search query
    const [searchQuery, setSearchQuery] = useState('');

    //state for pagination
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(5);

    //state for opening modals
    const [open, setOpen] = useState(false);
    const [selectCandyId, setSelectCandyId] = useState(null);



    //handle page change
    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    //handle rows per page change
    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    //Paginate the data 
    const paginatedData = candy.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage);

    //state for favorite candy
    const [favoriteCandies, setFavoriteCandies] = useState([]);


    //fetch the candy data from the API endpoint when the component mounts
    useEffect(() => {
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(returnedData => {
                const sortedData = returnedData.sort((a, b) => a.candyId - b.candyId);
                setCandy(sortedData);
                setLoaded(true);
            })
            .catch(err => {
                setError(err);
                setLoaded(true);
            });
    }, []);

    //handle input changes for both new candy and editing candy
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        if (editingCandy) {
            setEditingCandy({ ...editingCandy, [name]: value });
        } else {
            setNewCandy({ ...newCandy, [name]: value });
        }
    };

    //validate candy information before form submission
    const validateCandyData = (data) => {
        let errorMessages = [];

        if (data.name.trim() === '') {
            errorMessages.push('Name is required');
        }

        if (!data.type) {
            errorMessages.push("Candy type is required")
        }

        if (data.flavor.trim() === '') {
            errorMessages.push('Flavor is required');
        }

        // Check if price is a string before trimming
        const priceStr = typeof data.price === 'string' ? data.price.trim() : data.price;
        if (priceStr === '' || parseFloat(priceStr) < 0) {
            errorMessages.push('Price must be a positive number');
        }

        if (isNaN(parseFloat(priceStr))) {
            errorMessages.push('Price cannot contain letters');
        }

        // Check if weight is a string before trimming
        const weightStr = typeof data.weight === 'string' ? data.weight.trim() : data.weight;
        if (weightStr === '' || parseFloat(weightStr) <= 0) {
            errorMessages.push('Weight must be a positive number');
        }

        if (isNaN(parseFloat(weightStr))) {
            errorMessages.push('Weight cannot contain letters');
        }

        if (errorMessages.length > 0) {
            setErrorMessage(errorMessages.join(', and '));
            return false;
        }


        return true; // No errors
    };

    //handle form submission for adding or updating candy
    const handleSubmit = (e) => {
        e.preventDefault();
        const candyData = editingCandy || newCandy;
        const method = editingCandy ? 'PUT' : 'POST';
        const endpoint = editingCandy ? `${url}/${editingCandy.candyId}` : url;
        // Validate candy data
        const isValid = validateCandyData(candyData);
        if (!isValid) {
            return; // Stop form submission if validation fails
        }

        fetch(endpoint, {
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(candyData),
        })
            .then(response => response.text())
            .then(text => {
                if (text) {
                    console.log(text);
                    return JSON.parse(text);
                } else {
                    return {};
                }
            })
            .then(data => {
                if (editingCandy) {
                    console.group(editingCandy);
                    setCandy(candy.map(c => c.candyId === data.candyId ? data : c));
                    setEditingCandy(null);
                } else {
                    setCandy([...candy, data]);
                    console.log(candy);
                }
                setNewCandy({ flavor: '', name: '', price: '', type: '', weight: '' });
                setSuccessMessage(editingCandy ? 'Successfully updated candy!' : 'Successfully added candy!');

                refreshCandyDetails();
            })
            .catch(err => {
                setError(err);
            });
    };

    //refresh candy details after an action
    const refreshCandyDetails = () => {
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(returnedData => {
                const sortedData = returnedData.sort((a, b) => a.candyId - b.candyId);
                setCandy(sortedData);
                setLoaded(true);
            })
            .catch(err => {
                setError(err);
                setLoaded(true);
            });
    }


    //set the candy to edit
    const handleEdit = (candy) => {
        setEditingCandy(candy);
    }

    //handle deletion of a candy
    const handleDelete = (id) => {
        fetch(`${url}/${id}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was unsuccessful');
                }
                //remove the deleted candy from the list
                setCandy(candy.filter(c => c.candyId !== id));
                setSuccessMessage('Candy was deleted successfully!');
            })
            .catch(err => setError(err));
    };

    //open the modal and set the candy ID
    const handleOpenModal = (candyId) => {
        setSelectCandyId(candyId);
        setOpen(true);
    };

    //close the modal
    const handleCloseModal = () => {
        setSelectCandyId(null);
        setOpen(false);
    };

    //confirm deletion of candy
    const handleConfirmDelete = () => {
        handleDelete(selectCandyId);
        handleCloseModal();
    };

    const handleCloseSnackbar = () => {
        setSuccessMessage('');
        setErrorMessage('');
    }

    const handleToggleFavorite = (candyId) => {
        if (favoriteCandies.includes(candyId)) {
            setFavoriteCandies(favoriteCandies.filter(fav => fav !== candyId));
        } else {
            setFavoriteCandies([...favoriteCandies, candyId]);
        }
    };


    return (
        <>
            <Box name='candyListBox' component='form' onSubmit={handleSubmit} sx={{ mb: 2, mt: 8, padding: 2, borderRadius: 1, boxShadow: 10 }}>
                <TextField
                    label='Name'
                    name='name'
                    value={editingCandy ? editingCandy.name : newCandy.name}
                    onChange={handleInputChange}
                    fullWidth
                    margin='normal'
                    className='textField'
                />

                {/* Using the set of candy types to create a drop down to limit user error */}
                <FormControl fullWidth sx={{ backgroundColor: '#e6e6fa', marginTop: 1 }}>
                    <InputLabel id="selectCandyType" >Candy Type*</InputLabel>
                    <Select
                        labelId="selectCandyType"
                        id="candyTypeSelect"
                        value={editingCandy ? editingCandy.type : newCandy.type}
                        name="type"
                        onChange={handleInputChange}
                        sx={{
                            textAlign: 'left'
                        }}
                    >
                        <MenuItem value="" style={{ color: 'red' }}>Clear Field</MenuItem>
                        <MenuItem value={"Gummy Candy"}>Gummy Candy</MenuItem>
                        <MenuItem value={"Chocolate Candy"}>Chocolate Candy</MenuItem>
                        <MenuItem value={"Sour Candy"}>Sour Candy</MenuItem>
                        <MenuItem value={"Taffy Candy"}>Taffy Candy</MenuItem>
                        <MenuItem value={"Lollipops"}>Lollipops</MenuItem>
                        <MenuItem value={"Bubble Gum"}>Bubble Gum</MenuItem>
                    </Select>
                </FormControl>
                <TextField
                    label='Flavor'
                    name='flavor'
                    value={editingCandy ? editingCandy.flavor : newCandy.flavor}
                    onChange={handleInputChange}
                    fullWidth
                    margin='normal'
                    className='textField'
                />
                <TextField
                    label='Price'
                    name='price'
                    value={editingCandy ? editingCandy.price : newCandy.price}
                    onChange={handleInputChange}
                    fullWidth
                    margin='normal'
                    className='textField'
                />
                <TextField
                    label='Weight'
                    name='weight'
                    value={editingCandy ? editingCandy.weight : newCandy.weight}
                    onChange={handleInputChange}
                    fullWidth
                    margin='normal'
                    className='textField'
                />
                <Button name='candyBtn' type='submit' variant='contained' color='primary'>
                    {editingCandy ? 'Update Candy' : 'Add Candy'}
                </Button>
                {editingCandy && (
                    <Button name='cancelEditBtn' variant='contained' color='secondary' onClick={() => setEditingCandy(null)}>
                        Cancel edit
                    </Button>
                )}
            </Box>
            <Typography name='candyInventoryTitle' variant="h4" gutterBottom>
                Candy Inventory 🍬
            </Typography>
            <TableContainer component={Paper}>
                <Table name='candyTable'>
                    <TableHead>
                        <TableRow>
                            <TableCell>Candy Name</TableCell>
                            <TableCell>Candy Type</TableCell>
                            <TableCell>Flavor</TableCell>
                            <TableCell>Price ($)</TableCell>
                            <TableCell>Weight (oz.)</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody name='tableBody'>
                        {paginatedData.map(candy => (
                            <TableRow key={candy.candyId}>
                                <TableCell>{candy.name}</TableCell>
                                <TableCell>{candy.type}</TableCell>
                                <TableCell>{candy.flavor}</TableCell>
                                <TableCell>{candy.price.toFixed(2)}</TableCell>
                                <TableCell>{candy.weight.toFixed(2)}</TableCell>
                                <TableCell>
                                    <IconButton name='editIcon' onClick={() => handleEdit(candy)}>
                                        <EditIcon />
                                    </IconButton>
                                    <IconButton name='deleteIcon' onClick={() => handleOpenModal(candy.candyId)}>
                                        <DeleteIcon />
                                    </IconButton>
                                    <IconButton 
                                        name='favoriteIcon' 
                                        onClick={() => handleToggleFavorite(candy.candyId)}
                                        style={{ color: favoriteCandies.includes(candy.candyId) ? 'red' : 'grey' }}
                                        >
                                        <FavoriteIcon />
                                    </IconButton>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
                {/* <Dialog open={open} onClose={handleCloseModal}>
                    <DialogTitle>Add a Candy</DialogTitle>
                    <DialogContent>
                        <Typography>What candy do you want to add?</Typography>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleCloseModal} color='primary'>
                            Cancel
                        </Button>
                        <Button onClick={handleCreation} color='secondary'>
                            Add Candy
                        </Button>
                    </DialogActions>
                </Dialog> */}

                <Dialog open={open} onClose={handleCloseModal}>
                    <DialogTitle>Confirm Deletion</DialogTitle>
                    <DialogContent>
                        <Typography>Are you sure you want to delete this candy?</Typography>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleCloseModal} color='primary'>
                            Cancel
                        </Button>
                        <Button onClick={handleConfirmDelete} style={{ color: 'red' }}>
                            Delete
                        </Button>
                    </DialogActions>
                </Dialog>
                <TablePagination
                    component={'div'}
                    count={candy.length}
                    page={page}
                    onPageChange={handleChangePage}
                    rowsPerPage={rowsPerPage}
                    onRowsPerPageChange={handleChangeRowsPerPage}
                    rowsPerPageOptions={[5, 10, 25, {label: 'All', value: candy.length}]}
                />
            </TableContainer>
            <Snackbar
                open={!!successMessage}
                name='candyListSnackbar'
                autoHideDuration={2000}
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
                name='candyListSnackbarError'
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

export default CandyList;
