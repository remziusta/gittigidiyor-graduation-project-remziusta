import React, { Component } from "react";
import { Table,Space, Input } from "antd";
import { getRequestByNationalId } from "../client/Client";
import {errorNotification} from "../client/Notification";

const { Search } = Input;
export default class GetRequestAll extends Component {
  constructor(props) {
    super(props);
    this.state = { requests: [], value: 0 };
  }

  getRequestByNationalID = (nationalId) =>
    getRequestByNationalId(nationalId)
      .then((res) => res.json())
      .then(data => {
          const dat = data.map(x => {
            return (
              {
                nationalId:x.nationalId,
                limit: x.limit,
                status: x.status?"CONFIRM":"UNCONFIRM"
              }
            )
          })
          this.setState({requests:dat})
      })
      .catch((err) => {
        if(nationalId===""){
          errorNotification(
            "Please enter a value"
        )
        this.setState({requests:[]})
        }
      });

  columns = [
    {
      title: "National ID",
      dataIndex: "nationalId",
      key: "nationalId",
    },
    {
      title: "Limit",
      dataIndex: "limit",
      key: "limit",
    },
    {
      title: "Status",
      dataIndex: "status",
      key: "status",
    },
  ];
  render() {
    return (
      <div>
        <Space direction="vertical">
          <Search
            placeholder="input search text"
            allowClear
            enterButton="Search"
            size="large"
            onSearch={this.getRequestByNationalID}
          />
        </Space>
        <Table columns={this.columns} dataSource={this.state.requests}></Table>
      </div>
    );
  }
}
