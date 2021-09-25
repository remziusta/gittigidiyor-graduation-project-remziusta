import React, { Component } from "react";
import { Table,Space, Input } from "antd";
import { getRequestByNationalId, decodeMessage } from "../client/Client";
import alertify from "alertifyjs";
import 'alertifyjs/build/css/alertify.css';
import 'antd-button-color/dist/css/style.css';

const { Search } = Input;

export default class GetRequestAll extends Component {
  constructor(props) {
    super(props);
    this.state = { requests: [], value: 0, visible: false };
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
          this.setState({requests:dat});
          alertify.success('Applications for National Id have been brought');
      })
      .catch((err) => {
        err.response.json().then((res) => {
          if(nationalId==="")
            alertify.error('Please enter a national id.');
          else{
            const decode = decodeMessage(res.message);
            alertify.error(" \n Error Message : " + decode[0].message);
          }
        });
        this.setState({requests:[]});
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
            placeholder="Input search national id."
            allowClear
            enterButton="Search"
            size="large"
            onSearch={this.getRequestByNationalID}
          />
        </Space>
        <Table style={{marginTop:"10px"}} columns={this.columns} dataSource={this.state.requests}></Table>
      </div>
    );
  }
}