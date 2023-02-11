// src/App.js

import React from "react";
import styled from "styled-components";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Sidebar from "./components/Sidebar";
import Home from "./pages/Home";
import Profile from "./components/Profile";
import Messages from "./pages/Messages";
import User from "./pages/User";

const Center = styled.div`
  height: 92vh;
  display: flex;
  flex-direction: row;
`;
const Layout = styled.div`
  width: 50%;
`;
const ProfileLayout = styled.div`
  margin-top: auto;
`;

class App extends React.Component {
  render() {
    return (
      <BrowserRouter>
        <Center>
          <Sidebar />
          <Layout>
            <Routes>
              <Route exact path="/" element={<Home></Home>} />
              <Route path="/messages" element={<Messages></Messages>} />
              <Route path="/Profile" element={<User></User>} />
            </Routes>
          </Layout>
          <Profile></Profile>
        </Center>
      </BrowserRouter>
    );
  }
}

export default App;
