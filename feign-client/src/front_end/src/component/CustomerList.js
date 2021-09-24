import React, { Component } from "react";
import { Table, Radio, Popconfirm } from "antd";
import {
  creditRequestNationalId,
  deleteCustomer,
  getAllCustomers,
} from "../client/Client";
import { Link } from "react-router-dom";
import { successNotification } from "../client/Notification";

export default class CustomerList extends Component {
  constructor(props) {
    super(props);
    this.state = { customers: [] };
    this.visible = false;
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
            <Radio.Button value="small">Delete</Radio.Button>
          </Popconfirm>

          <Radio.Button value="small">
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
          </Radio.Button>
          <Radio.Button onClick={() => this.fetchCreditRequestNationalId(customer.nationalId)} value="small">Credit Request</Radio.Button>
        </div>
      ),
    },
  ];

  fetchAllCustomer = () =>
    getAllCustomers()
      .then((res) => res.json())
      .then((data) => {
        this.setState({ customers: data });
      })
      .catch((err) => {
        console.log(err.response);
      });

  fetchCreditRequestNationalId = (nationalId) =>
    creditRequestNationalId(nationalId)
      .then((response) => response.json())
      .then((data) => {
        successNotification(
          "Credit Request successfully",
          `Credit Result for ${data.nationalID} : Credit Request: 
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
      .catch((err) => console.log(err));

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
      alert("Employee deleted", `Employee with ${nationalId} was deleted`);
      callback();
    })
    .catch((err) => {
      err.response.json().then((res) => {
        console.log(res);
      });
    });
};
