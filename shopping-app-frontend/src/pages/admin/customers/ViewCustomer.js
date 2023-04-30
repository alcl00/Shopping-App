import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'

export default function ViewCustomer() {

  const {id} = useParams()

  const [customer, setCustomer] = useState({
    firstName: "",
    lastName: "",
    address: "",
    city: "",
    zipCode: ""
  })

  const loadCustomer = async() => {
    const result = await axios.get(`http://localhost:8080/api/customer/${id}`)
    setCustomer(result.data);
  }

  useEffect(() => {
    loadCustomer();
  });

  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 mb-2 shadow'>
                <h2 className='text-center m-4'>Customer Info</h2>
                <div className='card'>
                    <div className='card-header'>
                      Details of customer {customer.id} :
                      <ul className='list-group list-group-flush'>
                        <li className='list-group-item'>
                          <b>First Name: </b>
                          {customer.firstName}
                        </li>
                        <li className='list-group-item'>
                          <b>Last Name: </b>
                          {customer.lastName}
                        </li>
                        <li className='list-group-item'>
                          <b>Address: </b>
                          {customer.address}
                        </li>
                        <li className='list-group-item'>
                          <b>City: </b>
                          {customer.city}
                        </li>
                        <li className='list-group-item'>
                          <b>Zip Code: </b>
                          {customer.zipCode}
                        </li>
                      </ul>
                    </div>
                </div>
                <Link className="btn btn-primary my-2" to={"/"}>Back to Home</Link>
            </div>
        </div>
    </div>
  )
}
