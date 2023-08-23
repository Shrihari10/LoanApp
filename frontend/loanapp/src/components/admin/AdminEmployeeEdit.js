import React from 'react'
import {  Modal, Form } from 'react-bootstrap';
import axios from 'axios';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { ChakraProvider } from "@chakra-ui/react"
import { extendTheme } from "@chakra-ui/react"
import { Heading, Text } from "@chakra-ui/react"
import { Card, CardBody, CardFooter, CardHeader } from "@chakra-ui/react"
import { Button } from "@chakra-ui/react"
import { SimpleGrid } from "@chakra-ui/react"
import { Stack, HStack } from "@chakra-ui/react"
import { EditIcon, DeleteIcon, CloseIcon, CheckIcon } from "@chakra-ui/icons"

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
    
    <>
    <Modal show={showEditForm} onHide={handleClose}
      zindex="2"
      >
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
           <Button variant="ghost" colorScheme="red" onClick={handleClose}> <CloseIcon/> Close</Button>
           <Button variant="ghost" colorScheme="green"onClick={handleEditSubmit}> <CheckIcon/> Save Changes</Button>
         </Modal.Footer>
      </Modal>

      <div className=" justify-content-center align-items-center"style={{ paddingTop: '2em',paddingLeft:'1rem',width: '90%' }}>
        <SimpleGrid columns={1} spacing={1} >
        <Card borderBottomWidth="1px" align="flex-start" borderRadius="lg" textAlign="left" direction={{ base: 'column', sm: 'row' }}
          overflow='hidden'
          variant='outline'
          position="sticky"
          top="1"
          zIndex="1"
          padding="0.5em"
          boxShadow="lg"
          backgroundColor="gray.100"
        >
          <CardHeader
            fontSize="md"
            fontWeight="bold"
            width="9em"
            >
            Employee ID
            </CardHeader>
            <CardBody fontSize="md" fontWeight="semibold" width="4em">
              Employee Details
            </CardBody>
            
          </Card>
          {employees.map((employee) => (
            <Card borderBottomWidth="1px" align="left" borderRadius="lg" textAlign="left" direction={{ base: 'column', sm: 'row' }}
              
              variant='outline'
              >
              <CardHeader
                fontSize="md"
                fontWeight="bold"
                width="7em"
                >
                {employee.employeeID}
              </CardHeader>
              <CardBody fontSize="md"  width="2em">
                <Text> Employee Name: {employee.employeeName}</Text>  
                <Text> Designation: {employee.designation}</Text>
                <Text> Department: {employee.department}</Text>
              </CardBody>
              <CardBody fontSize="md"  width="2em">
                <Text> Gender: {employee.gender}</Text>
                <Text> Date of Birth: {employee.dateOfBirth}</Text>
                <Text> Date of Joining: {employee.dateOfJoining}</Text>
              </CardBody>
              <CardFooter fontSize="md" fontWeight="semibold">
                <HStack >
                  <Button variant='ghost' colorScheme="blue" onClick={() => handleEdit(employee.employeeID)}> <EditIcon/> Edit</Button>
                  <Button variant='ghost' colorScheme="red" onClick={() => handleDelete(employee.employeeID)}> <DeleteIcon/> Delete</Button>
                </HStack>
              </CardFooter>

              </Card>
          ))}
        </SimpleGrid>
      </div>
    </>
  )
}

export default AdminEmployeeEdit;