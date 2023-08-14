import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Button, Form, Container } from 'react-bootstrap';

function ApplyLoan() {
    let [empId, setEmpId] = useState(sessionStorage.getItem("username"));
    let [itemCategory, setItemCategory] = useState([]);
    let [selectedItemCategory, setSelectedItemCategory] = useState();
    let [itemDescription, setItemDescription] = useState([]);
    let [selectedItemDescription, setSelectedItemDescription] = useState();
    let [itemValue, setItemValue] = useState('');
    let [itemMake, setItemMake] = useState([]);
    let [selectedItemMake, setSelectedItemMake] = useState();

    useEffect(()=>{
        axios.get("http://localhost:8080/getAllItems")
      .then((res) => {
        
        setItemCategory(res);
        console.log(JSON.stringify(res.data)); 
      })
      .catch((err) => {
        console.log(err);
        alert("Error: " + err);
      });
    }, []);
    const handleSubmit = () =>{

    }



    

  return (
    <Container className="d-flex justify-content-center align-items-center min-vh-100">
      <Form onSubmit={handleSubmit} className="p-3 bg-light align-items-center" style={{ width: '50%' }}>
        <h3 className="text-warning bg-danger text-center mb-3">Select Product and Apply for Loan</h3>
        
        
        <Form.Group controlId="empId">
          <Form.Label> Employee ID</Form.Label>
          <Form.Control
            type="text"
            value={empId}
            disabled
          />
        </Form.Group>

        <Form.Group controlId="Item Category">
          <Form.Label>Item Category</Form.Label>
          <Form.Select aria-label="Default select example">
      <option>Open this select menu</option>
      {itemCategory.map(item => {
            <option value={item.itemCategory} onClick={setSelectedItemCategory(item)}>{item.itemCategory}</option>
      })}
      
      
    </Form.Select>
        </Form.Group>

        <Form.Group controlId="Item Description">
          <Form.Label>Item Description</Form.Label>
          <Form.Select aria-label="Default select example">
      <option>Open this select menu</option>
      {itemDescription.map(item => {
            <option value={item.itemDescription} onClick={setSelectedItemDescription(item)}>{item.itemDescription}</option>
      })}

      
    </Form.Select>
        </Form.Group>

        <Form.Group controlId="Item Value">
          <Form.Label>Item Value</Form.Label>
          <Form.Control
            type="text"
            placeholder="Item Value"
            value={itemValue}
            
          />
        </Form.Group>

        <Form.Group controlId="Item Make">
          <Form.Label>Item Make</Form.Label>
          <Form.Select aria-label="Default select example">
      <option>Open this select menu</option>
      {itemMake.map(item => {
            <option value={item.itemMake} onClick={setSelectedItemMake(item)}>{item.itemMake}</option>
      })}
      
    </Form.Select>
        </Form.Group>
        

        <div className="text-center">
          <Button variant="primary" type="submit">
            Apply Loan
          </Button>
        </div>
      </Form>
    </Container>
    )
  }

export default ApplyLoan