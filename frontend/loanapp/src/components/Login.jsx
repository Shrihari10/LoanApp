import React, {useState} from "react";
import {Form, Button, Container, Row, Col} from "react-bootstrap";
import axios from "axios";

const Login = () => {
    const [employeeID, setEmployeeID] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        const requestBody = {
            employeeID,
            password
        };

        axios.post("http://localhost:8080/login", requestBody)
            .then((res) => {
                alert("Login Successful");
            })
            .catch((err) => {
                console.log(err);
                alert("Error: " + err);
            });
    };

    return (
        <Container className="d-flex justify-content-center align-items-center min-vh-100">
            <Form onSubmit={handleSubmit} className="p-3 bg-light align-items-center" style={{width: '50%'}}>
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
                </Form.Group>
                <div className="text-center">
                    <Button variant="primary" type="submit">
                        Login
                    </Button>
                    <p className="forgot-password text-right">
                        Forgot <a href="#">password?</a>
                    </p>
                </div>
            </Form>
        </Container>
    );
};

export default Login;