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
import WarehouseStocks from './components/Stocks/stockByWarehouse';
import './App.css'

function App() {

  return (
    <>
      <Router>
      <AppBarComponent />
        <Routes>
          <Route path='/' element={<Home/>} />
          <Route path='/orders' element={<Orders/>} />
          <Route path='/orderInfo' element={<OrderInfo/>} />
          <Route path='/candy' element={<CandyList/>} />
          <Route path='/warehouses' element={<WarehouseList />} /> 
          <Route path='/gummyCandy' element={<GummyCandy/>} />
          <Route path='/candyTypes' element={<CandyTypes/>} />
          <Route path='/chocolateCandy' element={<ChocolateCandy/>} />
          <Route path='/sourCandy' element={<SourCandy/>} />
          <Route path='/taffyCandy' element={<TaffyCandy/>} />
          <Route path='/lollipopCandy' element={<LollipopCandy/>} />
          <Route path='/stocks' element={<WarehouseStocks/>} />
          <Route path='/about' element={<About/>} />
        </Routes>
      </Router> 
    </>
  )
}

export default App
