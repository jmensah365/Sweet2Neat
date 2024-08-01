import React, { useState } from "react";
import { AppBar, Toolbar, IconButton, Typography, Button, Menu, MenuItem, Drawer, List, ListItemButton, Divider } from "@mui/material";
import candyImage from '../../assets/CandyPics/CandyLogos/logo.png'
import { useNavigate } from "react-router-dom";
import './AppBar.css'


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
            <Toolbar className="appbar">
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
                    onClick={handleProductsMenuOpen}
                >
                    Candy
                </Button>
                <Menu
                    anchorEl={productsMenuAnchorEl}
                    open={Boolean(productsMenuAnchorEl)}
                    onClose={handleProductsMenuClose}
                >
                    <MenuItem onClick={() => navigateTo('/candy')}>Candy Inventory</MenuItem>
                    <MenuItem onClick={() => navigateTo('/candyTypes')}>Candy categories</MenuItem>
                </Menu>

                <Button
                    color="inherit"
                    className="menuItems"
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
                    <MenuItem onClick={() => navigateTo('/stocks')}>Warehouse Stock</MenuItem>
                </Menu>
                <Button
                    color="inherit"
                    className="menuItems"
                    onClick={handleOrdersMenuOpen}
                >
                    Orders
                </Button>
                <Menu
                    anchorEl={ordersMenuAnchorEl}
                    open={Boolean(ordersMenuAnchorEl)}
                    onClose={handleOrdersMenuClose}
                >
                    <MenuItem onClick={() => navigateTo('/orders')}>List of Orders</MenuItem>
                    <MenuItem onClick={() => navigateTo('/orderInfo')}>Order Info</MenuItem>
                </Menu>
                <Button
                    color="inherit"
                    className="menuItems"
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