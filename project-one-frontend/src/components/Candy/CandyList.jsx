import React from 'react';
import '../../components/Forms.css'
import { useState, useEffect } from "react";
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, Typography, Alert, AlertTitle,
    TextField, Button, Box, IconButton, Snackbar,
} from '@mui/material';
    import EditIcon from '@mui/icons-material/Edit';
    import DeleteIcon from '@mui/icons-material/Delete';
const CandyList = () => {
    const url = "http://sweet2neat.us-east-1.elasticbeanstalk.com/candy";
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
                setCandy(returnedData);
                setLoaded(true);
            })
            .catch(err => {
                setError(err);
                setLoaded(true);
            });
    }, []);

    //handle input changes for both new candy and editing candy
    const handleInputChange = (e) => {
        const {name, value} = e.target;
        if (editingCandy){
            setEditingCandy({...editingCandy, [name]: value});
        } else {
            setNewCandy({...newCandy, [name]: value});
        }
    };

    //handle form submission for adding or updating candy
    const handleSubmit = (e) => {
        e.preventDefault();
        const candyData = editingCandy || newCandy;
        const method = editingCandy ? 'PUT' : 'POST';
        const endpoint = editingCandy ? `${url}/${editingCandy.candyId}` : url;
    
        fetch(endpoint, {
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(candyData),
        })
        .then(response => response.text())
        .then(text => {
            if(text){
                console.log(text);
                return JSON.parse(text);
            }else{
                return {};
            }
        })
        .then(data => {
            if(editingCandy) {
                console.group(editingCandy);
                setCandy(candy.map(c => c.candyId === data.candyId ? data : c));
                setEditingCandy(null);
            } else {
                setCandy([...candy, data]);
            }
            setNewCandy({flavor: '', name: '', price: '', type: '', weight: ''});
            setSuccessMessage(editingCandy ? 'Successfully updated candy!' : 'Successfully added candy!');
        })
        .catch(err => {
            setError(err);
        });
    };

    //set the candy to edit
    const handleEdit = (candy) => {
        setEditingCandy(candy);
    }

    //handle deletion of a candy
    const handleDelete = (id) => {
        fetch(`${url}/${id}`,{
            method: 'DELETE',
        })
        .then(response => {
            if (!response.ok){
                throw new Error('Network response was unsuccessful');
            }
            //remove the deleted candy from the list
            setCandy(candy.filter(c => c.candyId !== id));
            setSuccessMessage('Candy was deleted successfully!');
        })
        .catch(err => setError(err));
    };

    const handleCloseSnackbar = () => {
        setSuccessMessage('');
    }

    if (error) {
        return <Alert severity='error'>
            <AlertTitle>Error</AlertTitle>
            Sorry could not get candy inventory
            </Alert>;
    }

    return(
        <>
            
            <Box name = 'candyListBox' component='form' onSubmit={handleSubmit} sx={{ mb: 2, mt: 8, padding: 2, borderRadius: 1, boxShadow: 10 }}>
                <TextField
                    label='Name'
                    name='name'
                    value={editingCandy ? editingCandy.name : newCandy.name}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                    className='textField'
                />
                <TextField
                    label='Type'
                    name='type'
                    value={editingCandy ? editingCandy.type : newCandy.type}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                    className='textField'
                />
                <TextField
                    label='Flavor'
                    name='flavor'
                    value={editingCandy ? editingCandy.flavor : newCandy.flavor}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                    className='textField'
                />
                <TextField
                    label='Price'
                    name='price'
                    value={editingCandy ? editingCandy.price : newCandy.price}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                    className='textField'
                />
                <TextField
                    label='Weight'
                    name='weight'
                    value={editingCandy ? editingCandy.weight : newCandy.weight}
                    onChange={handleInputChange}
                    fullWidth
                    required
                    margin='normal'
                    className='textField'
                />
                <Button name='candyBtn' type='submit' variant='contained' color='primary'>
                    {editingCandy ? 'Update Candy' : 'Add Candy'}
                </Button>
                {editingCandy && (
                    <Button name = 'cancelEditBtn' variant='contained' color='secondary' onClick={() => setEditingCandy(null)}>
                        Cancel edit
                    </Button>
                )}
            </Box>
            <Typography name = 'candyInventoryTitle' variant="h4" gutterBottom>
                Candy Inventory
            </Typography>
            <TableContainer component={Paper}>
                <Table name='candyTable'>
                    <TableHead>
                        <TableRow>
                        <TableCell>Candy ID</TableCell>
                            <TableCell>Candy Name</TableCell>
                            <TableCell>Candy Type</TableCell>
                            <TableCell>Flavor</TableCell>
                            <TableCell>Price ($)</TableCell>
                            <TableCell>Weight (oz.)</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {candy.map(candy => (
                            <TableRow key={candy.candyId}>
                                <TableCell>{candy.candyId}</TableCell>
                                <TableCell>{candy.name}</TableCell>
                                <TableCell>{candy.type}</TableCell>
                                <TableCell>{candy.flavor}</TableCell>
                                <TableCell>{candy.price}</TableCell>
                                <TableCell>{candy.weight}</TableCell>
                                <TableCell>
                                    <IconButton name = 'editIcon' onClick={() => handleEdit(candy)}>
                                        <EditIcon />
                                    </IconButton>
                                    <IconButton name = 'deleteIcon' onClick={() => handleDelete(candy.candyId)}>
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
            name='candyListSnackbar'
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
        </>
    );
};

export default CandyList;