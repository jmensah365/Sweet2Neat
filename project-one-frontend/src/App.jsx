import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import AppBarComponent from './components/AppBar/AppBar';
import WarehouseList from './components/Warehouse/WarehouseList'
import Home from './components/Home/Home'
import About from './components/About/About'
import CandyList from './components/Candy/CandyList'
import Orders from './components/Orders/orderList'
import OrderInfo from './components/Orders/orderInfo';
import GummyCandy from './components/Candy/GummyCandy';
import CandyTypes from './components/Candy/CandyTypes';
import ChocolateCandy from './components/Candy/ChocolateCandy';
import SourCandy from './components/Candy/SourCandy';
import TaffyCandy from './components/Candy/TaffyCandy'
import LollipopCandy from './components/Candy/LollipopCandy'
import BubblegumCandy from './components/Candy/BubblegumCandy'
import WarehouseStocks from './components/Stocks/stockByWarehouse';
import Favorites from './components/Favorites/Favorites';
import './App.css'

function App() {

  return (
    <>
      <Router>
      <AppBarComponent />
        <Routes>
          <Route path='/home' element={<Home/>} />
          <Route path='/orders' element={<Orders/>} />
          <Route path='/order information' element={<OrderInfo/>} />
          <Route path='/candy' element={<CandyList/>} />
          <Route path='/warehouses' element={<WarehouseList />} /> 
          <Route path='/gummy candy' element={<GummyCandy/>} />
          <Route path='/candy types' element={<CandyTypes/>} />
          <Route path='/chocolate candy' element={<ChocolateCandy/>} />
          <Route path='/sour candy' element={<SourCandy/>} />
          <Route path='/taffy candy' element={<TaffyCandy/>} />
          <Route path='/lollipop candy' element={<LollipopCandy/>} />
          <Route path='/bubblegum' element={<BubblegumCandy/>} />
          <Route path='/stocks' element={<WarehouseStocks/>} />
          <Route path='/favorites' element={<Favorites/>} />
          <Route path='/about' element={<About/>} />
        </Routes>
      </Router> 
    </>
  )
}

export default App
