import React from 'react';
import { Container, Typography, Card, CardContent, Box, List, ListItem, ListItemIcon, ListItemText } from '@mui/material';
import { Inventory, Warehouse, ShoppingCart, Info } from '@mui/icons-material';

const About = () => {
  return (
    <Container maxWidth="md" sx={{ marginTop: 4 }}>
      <Card elevation={3}>
        <CardContent>
          <Typography variant="h4" component="h1" align="center" gutterBottom>
            Welcome to Candy Inventory Management
          </Typography>
          <Typography variant="body1" color="textSecondary" align="center" gutterBottom>
            Your one-stop shop where we take your candy inventory from sweet to neat!
          </Typography>

          <Box marginTop={2}>
            <Typography variant="h5" component="h2" gutterBottom>
              What You Can Do
            </Typography>
            <List>
              <ListItem>
                <ListItemIcon>
                  <Inventory />
                </ListItemIcon>
                <ListItemText primary="Add or remove candy products in your inventory." />
              </ListItem>

              <ListItem>
                <ListItemIcon>
                  <Warehouse />
                </ListItemIcon>
                <ListItemText primary="Manage warehouses, their capacities, and stock levels." />
              </ListItem>

              <ListItem>
                <ListItemIcon>
                  <ShoppingCart />
                </ListItemIcon>
                <ListItemText primary="Track customers and their associated orders." />
              </ListItem>
            </List>
          </Box>

          <Box marginTop={4}>
            <Typography variant="body2" color="textSecondary" align="center">
              Stay on top of your candy business with ease and efficiency!
            </Typography>
          </Box>
        </CardContent>
      </Card>
    </Container>
  );
};

export default About;
