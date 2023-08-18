import React from 'react'
import {Button} from 'react-bootstrap';
import axios from 'axios';
import { useState, useEffect } from 'react';
import {useNavigate} from 'react-router-dom';

function AdminEmployeeEdit() {
  const userName = sessionStorage.getItem("username");
  const [employees, setEmployees] = useState([])

  const fetchAllEmployee = ()=>{
    axios.get(`http://localhost:8080/employee/all?userName=${userName}`)
    .then((res) => {
        setEmployees(res.data);
        console.log(res.data);
    })
    .catch((err) => {
        console.log(err);
        alert("Error: " + err);
    });
  }
  useEffect(() => {
    fetchAllEmployee();
}, [])

  const handleDelete = (id) => {

    axios.delete(`http://localhost:8080/employee/${id}?userName=${userName}`)
    .then((res) => {
      alert(res.data);
      fetchAllEmployee();
    })
    .catch((err)=>{
      alert("Error: " + err);

    });
  }

  const handleEdit = () =>{
    axios.put(``,)
    .then((res) => {
      alert(res.data);
      fetchAllEmployee();
    })
    .catch((err)=>{
      alert("Error: " + err);

    });
  }
  return (
      <>
      <h1>Employee List</h1>
      <table className="table table-striped">
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
                        <Button variant='primary'>Edit</Button>
                        <Button variant='danger' onClick={()=>handleDelete(employee.employeeID)}>Delete</Button>
                        </td>
                    </tr>
                ))}
            </tbody>

            </table>
      </>
  )
}

export default AdminEmployeeEdit