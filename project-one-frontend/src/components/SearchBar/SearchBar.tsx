import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { TextField, Autocomplete, Box, IconButton, InputAdornment } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import ClearIcon from '@mui/icons-material/Clear';

const searchOptions = [
  'candy',
  'warehouses',
  'stocks',
  'orders',
  'about',
  'candy types',
  'order information',
  'favorites',
];

const SearchBar = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const navigate = useNavigate();

  const handleSearch = () => {
    if (searchTerm.trim()) {
      navigate(`/${searchTerm}`);
    }
  };

  const handleClearSearch = () => {
    setSearchTerm('');
  };

  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      handleSearch();
    }
  };

  return (
    <Box 
      sx={{ 
        display: 'flex', 
        alignItems: 'center', 
        justifyContent: 'center', 
        p: 2,
        width: '70%', 
      }}
    >
      <Autocomplete
        freeSolo={true}
        options={searchOptions}
        inputValue={searchTerm}
        onInputChange={(e, newValue) => setSearchTerm(newValue)}
        
        renderInput={(params) => (
          <TextField
            {...params}
            label="Search..."
            variant="outlined"
            onKeyDown={handleKeyPress}
            InputProps={{
              ...params.InputProps,
              endAdornment: (
                <InputAdornment position="end">
                  {searchTerm && (
                    <IconButton onClick={handleClearSearch}>
                      <ClearIcon />
                    </IconButton>
                  )}
                  <IconButton onClick={handleSearch}>
                    <SearchIcon />
                  </IconButton>
                </InputAdornment>
              ),
            }}
            sx={{
              width: '500px', 
              height: '44px', 
              '& .MuiOutlinedInput-root': { 
                borderRadius: '25px', // Rounded corners
                paddingRight: '2px',  // Adjust padding near the icon
              },
            }}
          />
        )}
      />
    </Box>
  );
};

export default SearchBar;
