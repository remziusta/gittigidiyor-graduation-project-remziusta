import React, { Component } from "react";
import "antd/dist/antd.css";
import { Form, Input, Button, Row, Col, InputNumber } from "antd";
import { updateCustomer, decodeMessage } from "../client/Client";
import alertify from "alertifyjs";
import "alertifyjs/build/css/alertify.css";
import 'antd-button-color/dist/css/style.css';

export default class UpdateCustomer extends Component {
  constructor(props) {
    super(props);
    this.state = { customerInfo: this.props.location.state };
  }

  onFinish = (customer) => {
    updateCustomer(this.state.customerInfo.nationalId, customer)
      .then((data) => {
        alertify.success("Update customer info");
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
                label="FirstName"
                rules={[
                  {
                    required: true,
                    message: "Please enter first name.",
                  },
                  {
                    pattern:
                      "^[a-zA-ZıüöşçğİÜÖŞÇĞ]+\\S$|^[a-zA-ZıüöşçğİÜÖŞÇĞ]+?([a-zA-ZıüöşçğİÜÖŞÇĞ]+\\s+[a-zA-ZıüöşçğİÜÖŞÇĞ]+)+$",
                    message: "Format is wrong",
                  },
                ]}
              >
                <Input
                  defaultValue={this.state.customerInfo.firstName}
                  placeholder="Please enter first name."
                />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="lastName"
                label="Last Name"
                rules={[
                  {
                    required: true,
                    message: "Please enter last name.",
                  },
                  {
                    pattern:
                      "^[a-zA-ZıüöşçğİÜÖŞÇĞ]+\\S$|^[a-zA-ZıüöşçğİÜÖŞÇĞ]+?([a-zA-ZıüöşçğİÜÖŞÇĞ]+\\s+[a-zA-ZıüöşçğİÜÖŞÇĞ]+)+$",
                    message: "Format is wrong",
                  },
                ]}
              >
                <Input
                  defaultValue={this.state.customerInfo.lastName}
                  placeholder="Please enter last name."
                />
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
                    pattern:
                      "^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$",
                    message: "Format is wrong",
                  },
                ]}
              >
                <Input
                  defaultValue={this.state.customerInfo.phoneNumber}
                  placeholder="Please enter phone number."
                />
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
                <InputNumber
                  min={1}
                  defaultValue={this.state.customerInfo.monthlyIncome}
                  placeholder="Please enter your salary."
                />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}></Row>
          <Row>
            <Col span={12}>
              <Form.Item>
                <Button type="primary" htmlType="submit">
                  Update
                </Button>
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </div>
    );
  }
}
