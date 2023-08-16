import React, {useEffect, useState} from "react";
import {Form, Button, Container} from "react-bootstrap";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const ViewLoan = ({ user, loginUser}) => {

    const [loanDetails, setLoanDetails] = useState([]);
    const [username, setUsername] = useState("");
   

    useEffect(() => {
        if(user!=null && user.length > 0)
        {
            
            // console.log(user);
            const loanDetails=fetchLoanDetails(user);
            //show loan details in table
            

        }else {
            navigate('/login');
        }
        
    },[user]);
    const navigate = useNavigate();

    const fetchLoanDetails = (user) => {
        axios.get(`http://localhost:8080/employeeIssue/${user}/all`)
        .then((res) => {
            setLoanDetails(res.data);
            // console.log(res.data);
        })
        .catch((err) => {
            console.log(err);
            alert("Error: " + err);
        });
    };

    return (
       <>
       <h1>Loan</h1>
         <table className="table table-bordered">
            {
                loanDetails.map((loan) => () => {
                    <tr>
                        <td>{loan.item}</td>
                        <td>{loan.issueId}</td>
                        <td>{loan.issueDate}</td>
                        <td>{loan.returnDate}</td>
                    </tr>
                }
                )
            }
            </table>   
       </>
    );
};

export default ViewLoan;