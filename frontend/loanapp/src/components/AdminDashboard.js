import Card from 'react-bootstrap/Card';
import CardGroup from 'react-bootstrap/CardGroup';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import { Container } from 'react-bootstrap';

function AdminDashboard() {
  // const [admin, setAdmin] = useState();
  // const [user, setUser] = React.useState("");
  // React.useEffect(() => {
  //     const loggedInUser = sessionStorage.getItem("username");
  //     if (loggedInUser) {
  //         setUser(loggedInUser);
  //     }
  // }, [user]);  

  return (
    <Container className="d-flex justify-content-center align-items-center ">
      <CardGroup style={{ width: '80%', marginTop: '30px', borderRadius: '10px' }}>
        <Card>

          <Card.Body>
            <Card.Title>
              <Card.Text>
                Customer Data Management
              </Card.Text>

            </Card.Title>
            <Link to="/admin/employee/add">
              <Button variant="primary">Add Employee</Button>
            </Link>
            <Link to="/admin/employee/edit">
              <Button variant="primary">Edit employee</Button>
            </Link>

          </Card.Body>
          {/* <Card.Footer>
          {/* <small className="text-muted">Last updated 3 mins ago</small> }
        </Card.Footer> */}
        </Card>
        <Card>

          <Card.Body>
            <Card.Title>
              <Card.Text>
                Loan Card Management
              </Card.Text>

            </Card.Title>
            <Link to="/admin/loan/add">
              <Button variant="primary">Add Loan Card</Button>
            </Link>
            <Link to="/admin/loan/edit">
              <Button variant="primary">Edit Loan Card</Button>
            </Link>

          </Card.Body>
          {/* <Card.Footer>
          <small className="text-muted">Last updated 3 mins ago</small>
        </Card.Footer> */}
        </Card>

        <Card>

          <Card.Body>
            <Card.Title>
              <Card.Text>
                Item Data Management
              </Card.Text>

            </Card.Title>
            <Link to="/admin/item/add">
              <Button variant="primary">Add Items</Button>
            </Link>
            <Link to="/admin/item/edit">
              <Button variant="primary">Edit Items</Button>
            </Link>

          </Card.Body>

        </Card>
      </CardGroup>
    </Container>
  );
}

export default AdminDashboard;