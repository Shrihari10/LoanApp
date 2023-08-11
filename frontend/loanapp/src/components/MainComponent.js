import React from 'react'
import LoginComponent from './LoginComponent'
import AboutUsComponent from './AboutUsComponent'
import HomeComponent from './HomeComponent'
import Register from './Register'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import NotFoundComponent from './NotFoundComponent';

const MainComponent = () => {
  return (
      <div>
        <nav className="navbar navbar-expand-lg navbar-light bg-light fixed-top">
          <div className="container">
            <Link to="/" className="navbar-brand">Loan App</Link>
          <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
              <ul className="navbar-nav ml-auto">
                <li className="nav-item"><Link to="/" className="nav-link">Home</Link></li>
                <li className="nav-item"><Link to="/about" className="nav-link">About Us</Link></li>
                <li className="nav-item"><Link to="/login" className="nav-link">Login</Link></li>
                <li className="nav-item"><Link to="/register" className="nav-link">Register</Link></li>
              </ul>
            </div>
          </div>
        </nav>
        <div className="auth-wrapper">
          <div className="auth-inner">
            <Routes>
              <Route path="/" element={<HomeComponent />} />
              <Route path="/about" element={<AboutUsComponent />} />
              <Route path="/login" element={<LoginComponent />} />
              <Route path="/register" element={<Register />} />
              <Route path="*" element={<NotFoundComponent />} />
            </Routes>
          </div>
        </div>
      </div>
    
    
  )

}

export default MainComponent