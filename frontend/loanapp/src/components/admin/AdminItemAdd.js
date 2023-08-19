import React, { useEffect, useState } from 'react'
import { Form,Button, Container } from 'react-bootstrap'
import axios from 'axios'
import { Link,useParams } from 'react-router-dom'

const AdminItemAdd = () => {
  const [username, setUsername] = useState(sessionStorage.getItem("username"));
  const [loanCards, setLoanCards] = useState([]);
  const [itemDescription, setItemDescription] = useState("");
  const [itemCategory, setItemCategory] = useState("");
  const [itemMake, setItemMake] = useState("");
  const [itemValue, setItemValue] = useState("");

  useEffect(()=>{
    axios.get("http://localhost:8080/loanCard/all")
  .then((res) => {
    
    const loanCard = res.data.body.map((loanCard) => loanCard.loanType);
    setLoanCards(loanCard);
  })
  .catch((err) => {
    console.log(err);
    alert("Error: " + err);
  });
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    const item = {
      issueStatus: '1',
      itemDescription: itemDescription,
      itemCategory: itemCategory,
      itemMake: itemMake,
      itemValuation: itemValue
    };
    //set params
    
    // console.log(item);
    axios.post('http://localhost:8080/item/add?userName='+username, item)
    .then((response) => {
      // console.log(response);
      // console.log(response.data);
      alert("Item Added Successfully");
    })
    .catch((error) => {
      console.log(error);
      alert("Item Addition Failed");
    });

  };

  return (
    <Container className="d-flex justify-content-center align-items-center ">
      <Form onSubmit={handleSubmit} className="p-3 bg-light align-items-center form-inline" style={{ width: '50%',marginTop:'30px',borderRadius:'10px' }}>
      <h3 className="text-warning bg-danger text-center mb-3">Add Item</h3>
      <Form.Group controlId='ItemCategory'>
          <Form.Label>Item Category</Form.Label>
          <Form.Control as="select" onChange={(e) => setItemCategory(e.target.value)}>
            <option value="">Select</option>
            {loanCards.map((itemCategory) => (
              <option value={itemCategory}>{itemCategory}</option>
            ))}
          </Form.Control>
        </Form.Group>
        <Form.Group controlId='ItemDescription'>
          <Form.Label>Item Description</Form.Label>
          <Form.Control
            type='text'
            placeholder='Enter Item Description'
            value={itemDescription}
            onChange={(e) => setItemDescription(e.target.value)}
          ></Form.Control>
        </Form.Group>
        <Form.Group controlId='ItemMake'>
          <Form.Label>Item Make</Form.Label>
          <Form.Control
            type='text'
            placeholder='Enter Item Make' 
            value={itemMake}
            onChange={(e) => setItemMake(e.target.value)}
          ></Form.Control>
        </Form.Group>
        <Form.Group controlId='ItemValue'>
          <Form.Label>Item Value</Form.Label>
          <Form.Control
            type='text'
            placeholder='Enter Item Value'
            value={itemValue}
            onChange={(e) => setItemValue(e.target.value)}
          ></Form.Control>
        </Form.Group>
        <Button type='submit' variant='primary'>
          Add Item
        </Button>
      </Form>
    </Container>

  );


}

export default AdminItemAdd