import React, { useState } from "react";
import { AppBar, Toolbar, IconButton, Typography, Button, Menu, MenuItem, Drawer, List, ListItemButton, Divider } from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import { styled } from '@mui/system';
import candyImage from '../../assets/CandyPics/logo2.png'
import { Navigate, useNavigate } from "react-router-dom";


const AppBarComponent = () => {
    const [anchorEl, setAnchorEl] = useState(null);
    const [drawerOpen, setDrawerOpen] = useState(false);
    const [productsMenuAnchorEl, setProductsMenuAnchorEl] = useState(null);
    const [warehouseMenuAnchorEl, setWarehouseMenuAnchorEl] = useState(null);
    const [ordersMenuAnchorEl, setOrdersMenuAnchorEl] = useState(null);
    const navigate = useNavigate();

    const handleMenuOpen = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleMenuClose = () => {
        setAnchorEl(null);
    };

    const toggleDrawer = () => {
        setDrawerOpen(!drawerOpen);
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
        setDrawerOpen(false);
    };


    return (
        <>
        <AppBar position="fixed">
            <Toolbar>
                <IconButton
                    edge="start"
                    color="inherit"
                    aria-label="menu"
                    onClick={() => navigateTo('/')}
                >
                    <img src={candyImage} alt="Menu Icon" style={{width: 24, height: 24}} />
                </IconButton>
                <Button
                    color="inherit"
                    onClick={handleProductsMenuOpen}
                >
                    Candy
                </Button>
                <Menu
                    anchorEl={productsMenuAnchorEl}
                    open={Boolean(productsMenuAnchorEl)}
                    onClose={handleProductsMenuClose}
                >
                    <MenuItem onClick={handleProductsMenuClose}>Candy List</MenuItem>
                    <MenuItem onClick={handleProductsMenuClose}>Add a Candy</MenuItem>
                    <MenuItem onClick={handleProductsMenuClose}>Delete a Candy</MenuItem>
                </Menu>

                <Button
                    color="inherit"
                    onClick={handleWarehouseMenuOpen}
                >
                    Warehouses
                </Button>
                <Menu
                    anchorEl={warehouseMenuAnchorEl}
                    open={Boolean(warehouseMenuAnchorEl)}
                    onClose={handleWarehouseMenuClose}
                >
                    <MenuItem onClick={() => navigateTo('/warehouses')}>Warehouse List</MenuItem>
                    <MenuItem onClick={handleWarehouseMenuClose}>Add a Warehouse</MenuItem>
                    <MenuItem onClick={handleWarehouseMenuClose}>Delete a Warehouse</MenuItem>
                </Menu>
                <Button
                    color="inherit"
                    onClick={handleOrdersMenuOpen}
                >
                    Orders
                </Button>
                <Menu
                    anchorEl={ordersMenuAnchorEl}
                    open={Boolean(ordersMenuAnchorEl)}
                    onClose={handleOrdersMenuClose}
                >
                    <MenuItem onClick={handleOrdersMenuClose}>List of Orders</MenuItem>
                    <MenuItem onClick={handleOrdersMenuClose}>Order Info</MenuItem>
                </Menu>
                <Button
                    color="inherit"
                    onClick={() => navigateTo('/about')}
                >
                    About
                </Button>
            </Toolbar>
        </AppBar>
        {/* <Drawer
            anchor="right"
            open={drawerOpen}
            onClose={toggleDrawer}
        >
            <div>
                <IconButton onClick={toggleDrawer}>
                    <img src={candyImage} alt="Menu Icon" style={{width: 24, height: 24}} />
                </IconButton>
                <Divider />
                <List>
                    <ListItemButton onClick={toggleDrawer}>
                        <Typography variant="body1">Home</Typography>
                    </ListItemButton>
                    <ListItemButton onClick={toggleDrawer}>
                        <Typography variant="body1">About</Typography>
                    </ListItemButton>
                </List>
            </div>
        </Drawer> */}
        </>
    );
};

export default AppBarComponent;