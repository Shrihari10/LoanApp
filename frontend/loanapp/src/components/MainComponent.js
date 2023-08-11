import React, { useState } from 'react'
import LoginComponent from './LoginComponent'
import AboutUsComponent from './AboutUsComponent'
import HomeComponent from './HomeComponent'
import Register from './Register'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import NotFoundComponent from './NotFoundComponent';
import Login from './Login'
import NavbarComponent from './NavbarComponent'

const MainComponent = () => {

  const [user,setUser] = useState("");


  return (
      <div>
        <NavbarComponent user={user} setUser = {setUser}/>
        <div className="auth-wrapper">
          <div className="auth-inner">
            <Routes>
              <Route path="/" element={<HomeComponent user={user} setUser = {setUser}/>} />
              <Route path="/about" element={<AboutUsComponent />} />
              <Route path="/register" element={<Register  user={user} setUser = {setUser}/>} />
              <Route path="/login" element={<Login  user={user} setUser = {setUser}/>} />
              <Route path="*" element={<NotFoundComponent />} />
            </Routes>
          </div>
        </div>
      </div>
    
    
  )

}

export default MainComponent