import React from 'react';
import { 
  Container, Typography, Card, CardContent, Box, 
  List, ListItem, ListItemIcon, ListItemText, Paper 
} from '@mui/material';
import { Inventory, Warehouse, ShoppingCart } from '@mui/icons-material';
import { styled } from '@mui/system';

const GradientBox = styled(Box)({
  backgroundColor: 'rgb(111, 105, 187)',
  borderRadius: '8px',
  padding: '20px',
});

const About = () => {
  return (
    <Container maxWidth="md" sx={{ marginTop: 8 }}>
      <Paper 
        elevation={4} 
        sx={{ borderRadius: 4, overflow: 'hidden', backgroundColor: '#1E1E1E' }}
      >
        <GradientBox>
          <Typography 
            variant="h3" 
            component="h1" 
            align="center" 
            gutterBottom 
            sx={{ fontWeight: 'bold', color: '#E0E0E0' }}
          >
            🍬 Welcome to Candy Inventory Management
          </Typography>
          <Typography 
            variant="body1" 
            align="center" 
            sx={{ color: 'rgba(255, 255, 255, 0.8)' }}
          >
            Your one-stop shop where we take your candy inventory from <strong>sweet</strong> to <strong>neat!</strong>
          </Typography>
        </GradientBox>

        <CardContent sx={{ padding: 4 }}>
          <Box marginTop={2}>
            <Typography 
              variant="h5" 
              component="h2" 
              align="center" 
              gutterBottom 
              sx={{ fontWeight: 'medium', color: '#BDBDBD' }}
            >
              What You Can Do
            </Typography>
            <List>
              <ListItem>
                <ListItemIcon>
                  <Inventory sx={{ color: '#FF4081' }} />
                </ListItemIcon>
                <ListItemText 
                  primary="Add or remove candy products in your inventory." 
                  primaryTypographyProps={{ variant: 'body1', sx: { color: '#B0BEC5' } }} 
                />
              </ListItem>

              <ListItem>
                <ListItemIcon>
                  <Warehouse sx={{ color: '#64B5F6' }} />
                </ListItemIcon>
                <ListItemText 
                  primary="Manage warehouses, their capacities, and stock levels." 
                  primaryTypographyProps={{ variant: 'body1', sx: { color: '#B0BEC5' } }}
                />
              </ListItem>

              <ListItem>
                <ListItemIcon>
                  <ShoppingCart sx={{ color: '#81C784' }} />
                </ListItemIcon>
                <ListItemText 
                  primary="Track customers and their associated orders." 
                  primaryTypographyProps={{ variant: 'body1', sx: { color: '#B0BEC5' } }}
                />
              </ListItem>
            </List>
          </Box>

          <Box marginTop={4} textAlign="center">
            <Typography variant="body2" sx={{ color: '#9E9E9E' }}>
              Stay on top of your candy business with ease and efficiency!
            </Typography>
          </Box>
        </CardContent>
      </Paper>
    </Container>
  );
};

export default About;
