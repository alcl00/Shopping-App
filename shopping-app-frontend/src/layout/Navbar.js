import React from 'react'
import {Link, useParams} from 'react-router-dom'

export default function navbar() {

  const {modelName} = useParams;

  return (
    <div>
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
            <div className="container-fluid">
                <Link className="navbar-brand" to="/">The A-Shop</Link>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <Link className="btn btn-outline-light" to="/addcustomer">Add {modelName}</Link>
            </div>
        </nav>
    </div>
  )
}
