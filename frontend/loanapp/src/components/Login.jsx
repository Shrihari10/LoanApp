import React, {useEffect, useState} from "react";
import {Form, Button, Container, Row, Col} from "react-bootstrap";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Login = ({ user, setUser}) => {
    const [employeeID, setEmployeeID] = useState("");
    const [password, setPassword] = useState("")
    const navigate = useNavigate();

    useEffect(() => {
        if(user!=null && user.length > 0)
        {
            navigate('/');
        }
    },[user])

    const handleUserLogin = (e) => {
        e.preventDefault();
        const requestBody = {
            employeeID,
            password
        };
        
        setUser(employeeID);

        // axios.post("http://localhost:8080/login", requestBody)
        //     .then((res) => {
        //         // setUser(res.data.username);
        //         setUser("Admin");
        //         alert("Login Successful");
        //     })
        //     .catch((err) => {
        //         console.log(err);
        //         alert("Error: " + err);
        //     });
    };

    
    const handleAdminLogin = (e) => {
        e.preventDefault();
        const requestBody = {
            employeeID,
            password
        };
        
        setUser(employeeID);

        // axios.post("http://localhost:8080/login", requestBody)
        //     .then((res) => {
        //         // setUser(res.data.username);
        //         setUser("Admin");
        //         alert("Login Successful");
        //     })
        //     .catch((err) => {
        //         console.log(err);
        //         alert("Error: " + err);
        //     });
    };

    return (
        <Container className="d-flex justify-content-center align-items-center min-vh-100">
            <Form  className="p-3 bg-light align-items-center" style={{width: '50%'}}>
                <h3 className="text-warning bg-danger text-center mb-3">Login</h3>
                <Form.Group controlId="employeeID">
                    <Form.Label>Employee ID</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Employee ID"
                        value={employeeID}
                        onChange={(e) => setEmployeeID(e.target.value)}
                    />
                </Form.Group>
                <Form.Group controlId="password">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <br/>
                </Form.Group>
                <div className="text-center">
                    <Button variant="primary" type="submit" onClick={handleUserLogin}>
                        Login as User
                    </Button>
                    {' '}
                    <Button variant="danger" type="submit" onClick={handleAdminLogin}>
                        Login as Admin
                    </Button>
                    <p className="forgot-password text-right">
                        <a href="#">Forgot password?</a>
                    </p>
                </div>
            </Form>
        </Container>
    );
};

export default Login;