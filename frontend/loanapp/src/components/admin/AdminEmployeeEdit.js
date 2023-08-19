import React from 'react'
import { Button, Modal, Form } from 'react-bootstrap';
import axios from 'axios';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function AdminEmployeeEdit() {
  const userName = sessionStorage.getItem("username");
  const [employees, setEmployees] = useState([]);
  const [showEditForm, setShowEditForm] = useState(false);

  const [editingEmployeeId, setEditingEmployeeId] = useState();
  const [editingEmployeeName, setEditingEmployeeName] = useState();
  const [editingEmployeeDesignation, setEditingEmployeeDesignation] = useState();
  const [editingEmployeeDepartment, setEditingEmployeeDepartment] = useState();
  const [editingEmployeeGender, setEditingEmployeeGender] = useState();
  const [editingEmployeeDob, setEditingEmployeeDob] = useState();
  const [editingEmployeeDoj, setEditingEmployeeDoj] = useState();

  useEffect(() => {
    fetchAllEmployee();
  }, []);

  const fetchAllEmployee = () => {
    axios.get(`http://localhost:8080/employee/all?userName=${userName}`)
      .then((res) => {
        setEmployees(res.data.body);
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err);
        alert("Error: " + err);
      });
  }
  

  const handleDelete = (id) => {

    axios.delete(`http://localhost:8080/employee/${id}?userName=${userName}`)
      .then((res) => {
        alert(res.data.message);
        fetchAllEmployee();
      })
      .catch((err) => {
        alert("Error: " + err);

      });
  }

  const handleEdit = (id) => {

    const filteredEmployee = employees.filter((employee) => id === employee.employeeID);
    if(filteredEmployee.length == 0){
      alert("Invalid Employee selected to edit!")
    }

    const editingEmployee = filteredEmployee[0];
    setEditingEmployeeId(editingEmployee.employeeID);
    setEditingEmployeeName(editingEmployee.employeeName);
    setEditingEmployeeDesignation(editingEmployee.designation);
    setEditingEmployeeDepartment(editingEmployee.department);
    setEditingEmployeeGender(editingEmployee.gender);
    setEditingEmployeeDob(editingEmployee.dateOfBirth);
    setEditingEmployeeDoj(editingEmployee.dateOfJoining);
    handleOpen();
  }

  const handleEditSubmit =()=>{

    const requestBody = {
      employeeID: editingEmployeeId,
      employeeName: editingEmployeeName,
      designation: editingEmployeeDesignation,
      department: editingEmployeeDepartment,
      gender: editingEmployeeGender,
      dateOfBirth: editingEmployeeDob,
      dateOfJoining: editingEmployeeDoj,
    };
    axios.put(`http://localhost:8080/employee/${editingEmployeeId}?userName=${userName}`, requestBody)
      .then((res) => {
        alert(res.data.message);
        fetchAllEmployee();
        handleClose();
      })
      .catch((err) => {
        alert("Error: " + err);

      });
  }

  const handleClose = () => setShowEditForm(false);
  const handleOpen = () => setShowEditForm(true);


  return (
    <div>
      <h1>Employee List</h1>
      <Modal show={showEditForm} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Edit Employee</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={(e) => e.preventDefault()} className="p-3 bg-light align-items-center" style={{ width: '50%' }}>

            <Form.Group controlId="id">
              <Form.Label>Employee Id</Form.Label>
              <Form.Control
                type="text"
                value={editingEmployeeId}
                disabled
              />
            </Form.Group>
            <Form.Group controlId="name">
              <Form.Label>Name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Name"
                value={editingEmployeeName}
                onChange={(e) => setEditingEmployeeName(e.target.value)}
              />
            </Form.Group>

            <Form.Group controlId="designation">
              <Form.Label>Designation</Form.Label>
              <Form.Control
                type="text"
                placeholder="Designation"
                value={editingEmployeeDesignation}
                onChange={(e) => setEditingEmployeeDesignation(e.target.value)}
              />
            </Form.Group>

            <Form.Group controlId="department">
              <Form.Label>Department</Form.Label>
              <Form.Control
                type="text"
                placeholder="Department"
                value={editingEmployeeDepartment}
                onChange={(e) => setEditingEmployeeDepartment(e.target.value)}
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
                  checked={editingEmployeeGender === 'M'}
                  onChange={(e) => setEditingEmployeeGender(e.target.value)}
                  inline
                />
                <Form.Check
                  type="radio"
                  label="Female"
                  name="gender"
                  value="F"
                  checked={editingEmployeeGender === 'F'}
                  onChange={(e) => setEditingEmployeeGender(e.target.value)}
                  inline
                />
              </div>
            </Form.Group>

            <Form.Group controlId="dob">
              <Form.Label>Date of Birth</Form.Label>
              <Form.Control
                type="date"
                value={editingEmployeeDob}
                onChange={(e) => setEditingEmployeeDob(e.target.value)}
              />
            </Form.Group>

            <Form.Group controlId="doj">
              <Form.Label>Date of Joining</Form.Label>
              <Form.Control
                type="date"
                value={editingEmployeeDoj}
                onChange={(e) => setEditingEmployeeDoj(e.target.value)}
              />
            </Form.Group>
          </Form>

        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>Close</Button>
          <Button variant="primary" onClick={handleEditSubmit}>Save Changes</Button>
        </Modal.Footer>
      </Modal>

      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Employee ID</th>
            <th>Employee Name</th>
            <th>Designation</th>
            <th>Department</th>
            <th>Gender</th>
            <th>Date of Birth</th>
            <th>Date of Joining</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {employees.map((employee) => (
            <tr key={employee.employeeID}>
              <td>{employee.employeeID}</td>
              <td>{employee.employeeName}</td>
              <td>{employee.designation}</td>
              <td>{employee.department}</td>
              <td>{employee.gender}</td>
              <td>{employee.dateOfBirth}</td>
              <td>{employee.dateOfJoining}</td>
              <td>
                <Button variant='primary' onClick={() => handleEdit(employee.employeeID)}>Edit</Button>
                <Button variant='danger' onClick={() => handleDelete(employee.employeeID)}>Delete</Button>
              </td>
            </tr>
          ))}
        </tbody>

      </table>
      </div>
  )
}

export default AdminEmployeeEdit;