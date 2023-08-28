import React, { useState } from 'react'
import { Form, Container } from 'react-bootstrap';
import { useNavigate } from 'react-router';
import { addLoanCard } from '../../api/service';
import { successToast, failureToast } from "../../utils/ToastUtils";
import { Button } from '@chakra-ui/button';

function AdminLoanAdd() {
  const userName = sessionStorage.getItem("username");
  const [loanType, setLoanType] = useState("");
  const [durationOfYears, setDurationOfYears] = useState("");
  const navigate = useNavigate();

  const [error, setError] = useState({
    loanTypeError: '',
    durationOfYearsError: ''
  });


  const validate = () => {
    let newError = {
      loanTypeError: '',
      durationOfYearsError: ''
    };

    if(!loanType||loanType===''){
      newError.loanTypeError = 'Loan Type is Required';
    }
    if(!durationOfYears || durationOfYears===''){
      newError.durationOfYearsError = 'Duration of Years is Required';
    }else if(isNaN(durationOfYears)){
      newError.durationOfYearsError = 'Duration of Years should be a number';
    }

    setError(newError);
    console.log(newError);
    return Object.values(newError).every((x) => x === '');
  };

  const sendAddLoanCardRequest = () => {
    addLoanCard(userName, {
      loanType,
      durationOfYears
    }).then((res) => {
      successToast("New loan type created with ID: " + res.data.body.loanId);
      navigate("/admin/loan/edit");
    }).catch((err) => {
      failureToast("Error encountered: " + err.response.data.message);
    });
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const isValid = validate();
    if(!isValid) {
      return;
    }
    sendAddLoanCardRequest();
  }

  return (
    <Container className="d-flex justify-content-center align-items-center min-vh-100">
      <Form onSubmit={handleSubmit} className="p-3 bg-light align-items-center" style={{ width: '50%' }}>
        <h3 className="text-danger text-center mb-3">Add New Loan Type</h3>
        
        
        <Form.Group controlId="loanType">
          <Form.Label>Loan Type</Form.Label>
          <Form.Control
            type="text"
            value={loanType}
            onChange={(e) => setLoanType(e.target.value)}
            isInvalid={error.loanTypeError}
          />
          <Form.Control.Feedback type="invalid">
            {error.loanTypeError}
          </Form.Control.Feedback>
        </Form.Group>

        <Form.Group controlId="durationOfYears">
          <Form.Label>Duration in Years</Form.Label>
          <Form.Control
            type="number"
            value={durationOfYears}
            onChange={(e) => setDurationOfYears(e.target.value)}
            isInvalid={error.durationOfYearsError}
          />
          <Form.Control.Feedback type="invalid">
            {error.durationOfYearsError}
          </Form.Control.Feedback>
          
        </Form.Group>
        

        <div className="text-center pt-2">
          <Button colorScheme="blue" variant="outline" type="submit">
            Add New Loan
          </Button>
        </div>
      </Form>
    </Container>
    )
}

export default AdminLoanAdd