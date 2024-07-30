import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import WarehouseList from './components/Warehouse/WarehouseList'
import Home from './components/Home/Home'
import './App.css'

function App() {

  return (
    <>
      <Router>
        <Routes>
          <Route path='/' element={<Home/>} />
          <Route path='/warehouses' element={<WarehouseList />} /> 
        </Routes>
      </Router> 
    </>
  )
}

export default App
