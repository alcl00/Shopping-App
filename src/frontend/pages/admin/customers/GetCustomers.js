import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';

export default function GetCustomers() {
    const [customers, setCustomers] = useState([])

    useEffect(() => {
        loadCustomers();
    }, []);

    const loadCustomers = async() => {
        const result = await axios.get("http://localhost:8080/api/customer")
        setCustomers(result.data)
    };

    const deleteCustomer = async(id) => {
        await axios.delete(`http://localhost:8080/api/customer/${id}`)
        loadCustomers()
    }

  return (
    <div className='container'>
        <div className="py-4">
            <table className="table border shadow">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">First</th>
                        <th scope="col">Last</th>
                        <th scope="col">Zip Code</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        customers.map((customer) => (
                        <tr>
                            <th scope="row" key={customer.id}>{customer.id}</th>
                            <td>{customer.firstName}</td>
                            <td>{customer.lastName}</td>
                            <td>{customer.zipCode}</td>
                            <td>
                                <Link className="btn btn-primary mx-2" to={`/viewcustomer/${customer.id}`}>View</Link>
                                <Link className="btn btn-outline-primary mx-2" to={`/editcustomer/${customer.id}`}>Edit</Link>
                                <button className="btn btn-danger mx-2"
                                onClick={()=>deleteCustomer(customer.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                    
                </tbody>
            </table>
        </div>
    </div>
  )
}
