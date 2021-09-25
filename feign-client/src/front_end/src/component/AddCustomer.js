import React, { Component } from "react";
import "antd/dist/antd.css";
import { Form, Input, Button, Row, Col } from "antd";
import { addNewCustomer, decodeMessage } from "../client/Client";
import 'alertifyjs/build/css/alertify.css';
import alertify from "alertifyjs";
import 'antd-button-color/dist/css/style.css';

export default class AddCustomer extends Component {
    
  onFinish = (customer) => {
    addNewCustomer(customer)
      .then((response) => response.json())
      .then((data) => {
        alertify.success(customer.firstName + ' ' + customer.lastName+' has been added as a new customer.');
      })
      .catch((err) => {
        err.response.json().then((res) => {
          const decode = decodeMessage(res.message);
          alertify.error(" \n Error Message : " + decode[0].message);
        });
      });
  };
  onFinishFailed = () => {};

  render() {
    return (
      <div>
        <Form
          layout="vertical"
          onFinishFailed={this.onFinishFailed}
          onFinish={this.onFinish}
          hideRequiredMark
        >
          <Row gutter={16}>
            <Col span={12}>
              <Form.Item
                name="firstName"
                label="First Name"
                rules={[
                  {
                    required: true,
                    message: "Please enter first name."
                  },
                  {
                    pattern: "^[a-zA-ZıüöşçğİÜÖŞÇĞ]+\\S$|^[a-zA-ZıüöşçğİÜÖŞÇĞ]+?([a-zA-ZıüöşçğİÜÖŞÇĞ]+\\s+[a-zA-ZıüöşçğİÜÖŞÇĞ]+)+$",
                    message:"Format is wrong"
                  }
                ]}
              >
                <Input placeholder="Please enter first name." />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="lastName"
                label="Last Name"
                rules={[
                    {
                      required: true,
                      message: "Please enter last name."
                    },
                    {
                      pattern: "^[a-zA-ZıüöşçğİÜÖŞÇĞ]+\\S$|^[a-zA-ZıüöşçğİÜÖŞÇĞ]+?([a-zA-ZıüöşçğİÜÖŞÇĞ]+\\s+[a-zA-ZıüöşçğİÜÖŞÇĞ]+)+$",
                      message:"Format is wrong"
                    }
                ]}
              >
                <Input placeholder="Please enter last name." />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={12}>
              <Form.Item
                name="phoneNumber"
                label="Phone Number"
                rules={[
                  {
                    required: true,
                    message: "Please enter your phone number.",
                  },
                  {
                    pattern: "^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$",
                    message: "Format is wrong"
                  }
                ]}
              >
                <Input placeholder="Please enter phone number." />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="monthlyIncome"
                label="Salary"
                rules={[
                  { 
                    required: true, 
                    message: "Please enter your salary."
                  },
                  {
                    pattern : "^[0-9]+$",
                    message: "Only number."
                  }
                ]}
              >
                <Input type="number" min={1} placeholder="Please enter your salary." />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={12}>
            <Form.Item
              name="nationalId"
              label="National Id"
              rules={[
                {
                  required: true,
                  message: "Please enter your national id.",
                },
                {
                  pattern: "^[1-9]{1}[0-9]{9}[02468]{1}$",
                  message: "Format is wrong"
                }
              ]}
            >
              <Input placeholder="Please enter national id." />
            </Form.Item>
            </Col>
          </Row>
          <Row>
            <Col span={12}>
              <Form.Item>
                <Button type="warning" htmlType="submit">
                  Add
                </Button>
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </div>
    );
  }
}