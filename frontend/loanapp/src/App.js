
import "bootstrap/dist/css/bootstrap.min.css"
import "./App.css"
import { BrowserRouter, Routes, Route } from "react-router-dom"
import Register from "./components/Register"

import { Navbar, Nav } from 'react-bootstrap';
import Login from "./components/Login"; 


function App() {
  return (
    <div className="App">
      <Navbar bg="light" expand="lg" className="Nav" >
        <Navbar.Brand href="/home" className="Brand">Loan App</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ml-auto">
            <Nav.Link href="/home">Home</Nav.Link>
            <Nav.Link href="/register">Register</Nav.Link>

            <Nav.Link href="/login">Login</Nav.Link>

          </Nav>
        </Navbar.Collapse>
      </Navbar>

      <BrowserRouter>
        <Routes>
          <Route path="/register" element={<Register />} />

          <Route path="/login" element={<Login />} />

        </Routes>
      </BrowserRouter>
    </div>

  )
}

export default App
