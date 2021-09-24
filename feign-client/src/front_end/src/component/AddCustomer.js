import React, { Component } from "react";
import "antd/dist/antd.css";
import { Form, Input, Button, Row, Col, InputNumber } from "antd";
import { addNewCustomer } from "../client/Client";
import { successNotification } from "../client/Notification";

export default class AddCustomer extends Component {
    
  onFinish = (customer) => {
    addNewCustomer(customer)
      .then((response) => response.json())
      .then((data) => {
        successNotification(
          "Customer successfully added",
          `${data.firstName} was added to the system`
        );
      })
      .catch((err) => console.log(err));
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
                  { required: true, message: "Please enter first name." },
                ]}
              >
                <Input placeholder="Please enter first name." />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="lastName"
                label="Last Name"
                rules={[{ required: true, message: "Please enter last name." }]}
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
                  { required: true, message: "Please enter your salary." },
                ]}
              >
                <InputNumber min={1} placeholder="Please enter your salary." />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
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
          </Row>
          <Row>
            <Col span={12}>
              <Form.Item>
                <Button type="primary" htmlType="submit">
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
