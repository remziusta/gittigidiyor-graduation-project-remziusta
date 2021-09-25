import React, { Component } from "react";
import { Table, Button, Popconfirm } from "antd";
import 'antd-button-color/dist/css/style.css';

import {
  creditRequestNationalId,
  deleteCustomer,
  getAllCustomers,
  decodeMessage
} from "../client/Client";
import { Link } from "react-router-dom";
import alertify from "alertifyjs";
import 'alertifyjs/build/css/alertify.css';
import "../App.css";

export default class CustomerList extends Component {
  constructor(props) {
    super(props);
    this.state = { customers: [] };
  }
  async componentDidMount() {
    await this.fetchAllCustomer();
  }

  columns = [
    {
      title: "National ID",
      dataIndex: "nationalId",
      key: "nationalId",
    },
    {
      title: "First Name",
      dataIndex: "firstName",
      key: "firtsName",
    },
    {
      title: "Last Name",
      dataIndex: "lastName",
      key: "lastName",
    },
    {
      title: "Phone Number",
      dataIndex: "phoneNumber",
      key: "phoneNumber",
    },
    {
      title: "Salary",
      dataIndex: "monthlyIncome",
      key: "monthlyIncome",
    },
    {
      title: "Actions",
      key: "actions",
      render: (text, customer) => (
        <div>
          <Popconfirm
            placement="topRight"
            title={`Are you sure to delete ${customer.firstName}`}
            onConfirm={() =>
              removeCustomer(customer.nationalId, this.fetchAllCustomer)
            }
            okText="Yes"
            cancelText="No"
          >
            <Button type="danger" style={{marginRight:"5px"}} value="small">Delete</Button>
          </Popconfirm>

          <Button type="info" style={{marginRight:"5px"}} value="small">
            <Link
              to={{
                pathname: "/edit",
                state: {
                  nationalId: customer.nationalId,
                  firstName: customer.firstName,
                  lastName: customer.lastName,
                  phoneNumber: customer.phoneNumber,
                  monthlyIncome: customer.monthlyIncome,
                },
              }}
            >
              Edit
            </Link>
          </Button>
          
          <Button type="success" onClick={() => this.fetchCreditRequestNationalId(customer.nationalId)} value="small">Credit Request</Button>
        </div>
      ),
    },
  ];

  fetchAllCustomer = () =>
    getAllCustomers()
      .then((res) => res.json())
      .then((data) => {
        this.setState({ customers: data });
        alertify.success("All data brought");
      })
      .catch((err) => {
        err.response.json().then((res) => {
          const decode = decodeMessage(res.message);
          alertify.error(" \n Error Message : " + decode[0].message);
        });
      });

  fetchCreditRequestNationalId = (nationalId) =>
    creditRequestNationalId(nationalId)
      .then((response) => response.json())
      .then((data) => {
        alertify.success(
          "Credit Request successfully" +
          `Credit Result for ${data.nationalId} : Credit Request: 
          ${
            data.status ? "CONFIRM." : "UNCOMFIRM"
          } ${
              data.status ? "YOUR LIMIT:" : "."
            } 
          ${
            data.status ? data.limit : ""
          }  `
        );
      })
      .catch((err) => {
        err.response.json().then((res) => {
          const decode = decodeMessage(res.message);
          alertify.error(" \n Error Message : " + decode[0].message);
        });
      });

  render() {
    return (
      <div>
        <Table columns={this.columns} dataSource={this.state.customers}></Table>
      </div>
    );
  }
}

const removeCustomer = (nationalId, callback) => {
  deleteCustomer(nationalId)
    .then(() => {
      alertify.success('The customer with this '+nationalId+' ID number has been deleted')
      callback();
    })
    .catch((err) => {
      err.response.json().then((res) => {
        const decode = decodeMessage(res.message);
          alertify.error(" \n Error Message : " + decode[0].message);
      });
    });
};
