import React, { useState } from 'react'
import { Button, Form, Container } from 'react-bootstrap';
import { useNavigate } from 'react-router';
import { addLoanCard } from '../../api/service';
import { successToast, failureToast } from "../../utils/ToastUtils";

function AdminLoanAdd() {
  const userName = sessionStorage.getItem("username");
  const [loanType, setLoanType] = useState("");
  const [durationOfYears, setDurationOfYears] = useState("");
  const navigate = useNavigate();

  const sendAddLoanCardRequest = () => {
    addLoanCard(userName, {
      loanType,
      durationOfYears
    }).then((res) => {
      successToast("new loan type created with id " + res.data.body.loanId);
      navigate("/admin/loan/edit");
    }).catch((err) => {
      failureToast("error " + err);
    });
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    sendAddLoanCardRequest();
  }

  return (
    <Container className="d-flex justify-content-center align-items-center min-vh-100">
      <Form onSubmit={handleSubmit} className="p-3 bg-light align-items-center" style={{ width: '50%' }}>
        <h3 className="text-warning bg-danger text-center mb-3">Add New Loan Type</h3>
        
        
        <Form.Group controlId="loanType">
          <Form.Label>Loan Type</Form.Label>
          <Form.Control
            type="text"
            value={loanType}
            onChange={(e) => setLoanType(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="durationOfYears">
          <Form.Label>Duration in Years</Form.Label>
          <Form.Control
            type="number"
            value={durationOfYears}
            onChange={(e) => setDurationOfYears(e.target.value)}
          />
        </Form.Group>
        

        <div className="text-center">
          <Button variant="primary" type="submit">
            Add New Loan
          </Button>
        </div>
      </Form>
    </Container>
    )
}

export default AdminLoanAdd