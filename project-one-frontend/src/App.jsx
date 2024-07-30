import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import AppBarComponent from './components/AppBar/AppBar';
import WarehouseList from './components/Warehouse/WarehouseList'
import Home from './components/Home/Home'
import About from './components/About/About'
import Candy from './components/Candy/CandyList'
import Orders from './components/Orders/orderInfo'
import './App.css'

function App() {

  return (
    <>
      <Router>
      <AppBarComponent />
        <Routes>
          <Route path='/' element={<Home/>} />
          <Route path='/orders' element={<Orders/>} />
          <Route path='/candy' element={<Candy/>} />
          <Route path='/warehouses' element={<WarehouseList />} /> 
          <Route path='/about' element={<About/>} />
        </Routes>
      </Router> 
    </>
  )
}

export default App
