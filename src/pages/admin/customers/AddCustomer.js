import React, { useState } from 'react'
import axios from 'axios'
import { Link, useNavigate } from 'react-router-dom'

export default function AddCustomer() {

  let navigate = useNavigate();

  const [customer, setCustomer] = useState({
    firstName: "",
    lastName: "",
    address: "",
    city: "",
    zipCode: ""
  })

  const{firstName, lastName, address, city, zipCode}=customer

  const onInputChange= (e) => {
    
    setCustomer({...customer, [e.target.name]: e.target.value});
  }

  const onSubmit= async (e)=>{
    e.preventDefault();
    await axios.post("http://localhost:8080/api/customer", customer)
    navigate("/")
  }

  return (
    <div className='container'>
      <div className='row'>
        <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 mb-2 shadow'>
          <h2 className='text-center m-4'>Register Customer</h2>
          <form onSubmit={(e) => onSubmit(e)}>
          <div className='mb-3'>
            <label htmlFor='FirstName' className='form-label' >
              First Name
            </label>
            <input 
            type={"text"} 
            className='form-control' 
            placeholder='Enter your first name' 
            name="firstName"
            value={firstName} 
            onChange={(e) => onInputChange(e)} />
          </div>
          <div className='mb-3'>
            <label htmlFor='LastName' className='form-label' >
              Last Name
            </label>
            <input 
            type={"text"} 
            className='form-control' 
            placeholder='Enter your last name' 
            name="lastName" 
            value={lastName}
            onChange={(e) => onInputChange(e)} />
          </div>
          <div className='mb-3'>
            <label htmlFor='Address' className='form-label'>
              Address
            </label>
            <input 
            type={"text"} 
            className='form-control' 
            placeholder='Enter your address' 
            name="address" 
            value={address}
            onChange={(e) => onInputChange(e)} />
          </div>

          <div className='mb-3'>
            <label htmlFor='City' className='form-label'>
              City
            </label>
            <input 
            type={"text"} 
            className='form-control' 
            placeholder='Enter your city' 
            name="city"  
            value={city} 
            onChange={(e) => onInputChange(e)} />
          </div>

          <div className='mb-3'>
            <label htmlFor='ZipCode' className='form-label'>
              Zip Code
            </label>
            <input 
            type={"text"} 
            className='form-control' 
            placeholder='Enter your zip code' 
            name="zipCode" 
            value={zipCode}
            onChange={(e) => onInputChange(e)} />
          </div>
          <button type='submit' className='btn btn-outline-primary'>Submit</button>
          <Link className='btn btn-outline-danger mx-2' to="/">Cancel</Link>
          </form>
        </div>
      </div>
    </div>
  )
}
