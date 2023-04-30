import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import React from 'react';
import Navbar from "./layout/Navbar"
import Home from './pages/Home';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom"
import AddCustomer from './pages/admin/customers/AddCustomer';
import EditCustomer from './pages/admin/customers/EditCustomer';
import ViewCustomer from './pages/admin/customers/ViewCustomer';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route exact path="/" element={<Home/>} />
          <Route exact path="/addcustomer" element={<AddCustomer />} />
          <Route exact path="/editcustomer/:id" element={<EditCustomer />} />
          <Route exact path="/viewcustomer/:id" element={<ViewCustomer />} />
        </Routes>
      </Router>
      
    </div>
  );
}

export default App;
