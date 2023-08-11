import React, { useEffect, useState } from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Register = ({user,setUser}) => {
  const [name, setName] = useState('');
  const [designation, setDesignation] = useState('');
  const [department, setDepartment] = useState('');
  const [gender, setGender] = useState('');
  const [dob, setDob] = useState('');
  const [doj, setDoj] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    if(user!=null && user.length > 0)
    {
        navigate('/');
    }
  },[user])

  const handleSubmit = (e) => {
    e.preventDefault();
    const requestBody = {
      name,
      designation,
      department,
      gender,
      dob,
      doj
    };

    axios.post("http://localhost:8080/saveEmployee", requestBody)
      .then((res) => {
        alert("Created user");
      })
      .catch((err) => {
        console.log(err);
        alert("Error: " + err);
      });
  };

  return (
    <Container className="d-flex justify-content-center align-items-center min-vh-100">
      <Form onSubmit={handleSubmit} className="p-3 bg-light align-items-center" style={{ width: '50%' }}>
        <h3 className="text-warning bg-danger text-center mb-3">Register</h3>
        <Form.Group controlId="name">
          <Form.Label>Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="designation">
          <Form.Label>Designation</Form.Label>
          <Form.Control
            type="text"
            placeholder="Designation"
            value={designation}
            onChange={(e) => setDesignation(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="department">
          <Form.Label>Department</Form.Label>
          <Form.Control
            type="text"
            placeholder="Department"
            value={department}
            onChange={(e) => setDepartment(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="gender">
          <Form.Label>Gender</Form.Label>
          <div>
            <Form.Check
              type="radio"
              label="Male"
              name="gender"
              value="M"
              checked={gender === 'M'}
              onChange={(e) => setGender(e.target.value)}
              inline
            />
            <Form.Check
              type="radio"
              label="Female"
              name="gender"
              value="F"
              checked={gender === 'F'}
              onChange={(e) => setGender(e.target.value)}
              inline
            />
          </div>
        </Form.Group>

        <Form.Group controlId="dob">
          <Form.Label>Date of Birth</Form.Label>
          <Form.Control
            type="date"
            value={dob}
            onChange={(e) => setDob(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="doj">
          <Form.Label>Date of Joining</Form.Label>
          <Form.Control
            type="date"
            value={doj}
            onChange={(e) => setDoj(e.target.value)}
          />
        </Form.Group>

        <div className="text-center">
          <Button variant="primary" type="submit">
            Sign Up
          </Button>
          <p className="forgot-password mt-3">
            Already registered? <a href="/sign-in">Sign In</a>
          </p>
        </div>
      </Form>
    </Container>
  );
};

export default Register;
