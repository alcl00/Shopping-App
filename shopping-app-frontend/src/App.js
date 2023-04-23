import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import React from 'react';
import Navbar from "./layout/Navbar"
import Home from './pages/Home';
import AddUser from './users/AddUser'
import {BrowserRouter as Router, Routes, Route} from "react-router-dom"
import EditUser from './users/EditUser';
import ViewUser from './users/ViewUser';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route exact path="/" element={<Home/>} />
          <Route exact path="/addcustomer" element={<AddUser />} />
          <Route exact path="/editcustomer/:id" element={<EditUser />} />
          <Route exact path="/viewcustomer/:id" element={<ViewUser />} />
        </Routes>
      </Router>
      
    </div>
  );
}

export default App;
