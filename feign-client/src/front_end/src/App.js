import "./App.css";
import "antd/dist/antd.css";
import "./index.css";
import React, { Component } from "react";
import { Layout, Menu } from "antd";
import { Link, Switch, Route,Redirect,Router } from "react-router-dom";
import CustomerList from "./component/CustomerList";
import UpdateCustomer from "./component/UpdateCustomer";
import AddCustomer from "./component/AddCustomer";
import RequestNewCustomer from "./component/RequestNewCustomer";

const { Header, Content, Footer } = Layout;

export default class App extends Component {
  render() {
    return (
      <div>
          <Layout>
            <Header className="header">
              <Menu theme="dark" mode="horizontal" defaultSelectedKeys={["1"]}>
                <Menu.Item key="1">
                  <Link to="/customers">Customers</Link>
                </Menu.Item>
                <Menu.Item key="2">
                  <Link to="/addcustomer">Add Customer</Link>
                </Menu.Item>
                <Menu.Item key="3">
                  <Link to="/applynewcustomer">Apply credit with Customer</Link>
                </Menu.Item>
              </Menu>
            </Header>
            <Content style={{ padding: "12px 50px" }}>
              <Layout
                  className="site-layout-background"
                  style={{ padding: "24px 0" }}
              >
                <Content style={{ padding: "0 24px", minHeight: 280 }}>
                  <Switch>

                    <Route
                        exact
                        path="/"
                        render={() => {
                          return (
                              <Redirect to="/customers"/>
                          )
                        }}
                    />
                    <Route exact path="/customers" component={CustomerList}/>
                    <Route exact path="/addcustomer" component={AddCustomer}/>
                    <Route exact path="/applynewcustomer" component={RequestNewCustomer}/>
                    <Route exact path="/edit" component={UpdateCustomer}/>
                  </Switch>
                </Content>
              </Layout>
            </Content>
            <Footer style={{ textAlign: "center" }}>
              Remzi USTA GittiGidiyor Bootcamp Graduate Homework.
            </Footer>
          </Layout>
      </div>
    );
  }
}