import React, { useState, useEffect } from "react";
import { AppBar, Toolbar, IconButton, Button, Menu, MenuItem, Badge } from "@mui/material";
import candyImage from '../../assets/CandyPics/CandyLogos/logo.png'
import { useNavigate } from "react-router-dom";
import './AppBar.css'
import WarehouseIcon from '@mui/icons-material/Warehouse';


const AppBarComponent = () => {
    const [anchorEl, setAnchorEl] = useState(null);
    const [productsMenuAnchorEl, setProductsMenuAnchorEl] = useState(null);
    const [warehouseMenuAnchorEl, setWarehouseMenuAnchorEl] = useState(null);
    const [ordersMenuAnchorEl, setOrdersMenuAnchorEl] = useState(null);
    const navigate = useNavigate();
    const url = "http://sweet2neat.us-east-1.elasticbeanstalk.com/warehouse";
    const [warehouses, setWarehouses] = useState([]);
    const [error, setError] = useState(null);
    const [loaded, setLoaded] = useState(false);

    const handleMenuOpen = (event) => {
        setAnchorEl(event.currentTarget);
    };

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


    const handleMenuClose = () => {
        setAnchorEl(null);
    };


    const handleProductsMenuOpen = (event) => {
        setProductsMenuAnchorEl(event.currentTarget)
    };

    const handleProductsMenuClose = () => {
        setProductsMenuAnchorEl(null);
    };

    const handleWarehouseMenuOpen = (event) => {
        setWarehouseMenuAnchorEl(event.currentTarget)
    };

    const handleWarehouseMenuClose = () => {
        setWarehouseMenuAnchorEl(null);
    };

    const handleOrdersMenuOpen = (event) => {
        setOrdersMenuAnchorEl(event.currentTarget)
    };

    const handleOrdersMenuClose = () => {
        setOrdersMenuAnchorEl(null);
    };

    const navigateTo = (path) => {
        navigate(path);
        handleProductsMenuClose();
        handleMenuClose();
    };


    return (
        <>
        <AppBar position="fixed">
            <Toolbar className="appbar" style={{justifyContent: 'space-between'}}>
                <div>
                <IconButton
                    edge="start"
                    color="inherit"
                    aria-label="menu"
                    className="spin"
                    onClick={() => navigateTo('/')}
                >
                    <img src={candyImage} alt="Menu Icon" style={{width: 24, height: 24}} />
                </IconButton>
                <Button
                    color="inherit"
                    className="menuItems"
                    name='candy'
                    onClick={handleProductsMenuOpen}
                >
                    Candy
                </Button>
                <Menu
                    anchorEl={productsMenuAnchorEl}
                    open={Boolean(productsMenuAnchorEl)}
                    onClose={handleProductsMenuClose}
                >
                    <MenuItem name='candyRoute' onClick={() => navigateTo('/candy')}>Candy Inventory</MenuItem>
                    <MenuItem name='candyTypesRoute' onClick={() => navigateTo('/candyTypes')}>Candy categories</MenuItem>
                </Menu>

                <Button
                    color="inherit"
                    className="menuItems"
                    name='warehouses'
                    onClick={handleWarehouseMenuOpen}
                >
                    Warehouses
                </Button>
                <Menu
                    anchorEl={warehouseMenuAnchorEl}
                    open={Boolean(warehouseMenuAnchorEl)}
                    onClose={handleWarehouseMenuClose}
                >
                    <MenuItem name='warehousesRoute' onClick={() => navigateTo('/warehouses')}>Warehouse List</MenuItem>
                    <MenuItem name='warehousesStockRoute' onClick={() => navigateTo('/stocks')}>Warehouse Stock</MenuItem>
                </Menu>
                <Button
                    color="inherit"
                    className="menuItems"
                    name='orders'
                    onClick={handleOrdersMenuOpen}
                >
                    Orders
                </Button>
                <Menu
                    anchorEl={ordersMenuAnchorEl}
                    open={Boolean(ordersMenuAnchorEl)}
                    onClose={handleOrdersMenuClose}
                >
                    <MenuItem name='orderListRoute' onClick={() => navigateTo('/orders')}>List of Orders</MenuItem>
                    <MenuItem name='orderInfoRoute' onClick={() => navigateTo('/orderInfo')}>Order Info</MenuItem>
                </Menu>
                <Button
                    color="inherit"
                    className="menuItems"
                    name='about'
                    onClick={() => navigateTo('/about')}
                >
                    About
                </Button>
                </div>
                <div>
                <Button name='homeAddAWarehouseBtn' variant='contained' color='primary' size='large' onClick={() => navigateTo('/warehouses')}>
                    Add a warehouse
                </Button>
                <Badge
                    badgeContent={warehouses.length}
                    color="primary"
                    sx={{
                        '& .MuiBadge-dot': {
                        width: 30,  
                        height: 30, 
                        },
                        '& .MuiBadge-icon': {
                        fontSize: '2rem', 
                        },
                    }}
                    >
                <WarehouseIcon color="action" sx={{ fontSize: 40 }} /> 
                </Badge>
                </div>
            </Toolbar>
        </AppBar>
        </>
    );
};

export default AppBarComponent;