// src/App.js

import React from "react";
import styled from "styled-components";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Sidebar from "./components/sidebar/Sidebar";
import Home from "./pages/home/Home";
import Profile from "./components/profile/Profile";
import User from "./pages/user/User";
import Follow from "./pages/follow/Follow";
import Search from "./components/search/Search";
import Notification from "./pages/notification/Notification";
import Create from "./pages/post/Create";
import HashTagSearch from "./components/search/HashTagSearch";
import ChatRoomList from "./pages/message/ChatRoomList";
import ChatRoom from "./pages/message/ChatRoom";
import CreatePost from "./pages/post/Create";

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
              <Route path={"/chatlist/:userId"} element={<ChatRoomList/>} />
              <Route path={"/chatroom/:userId/:chatroomId"} element={<ChatRoom/>} />
              <Route path="/post" element={<CreatePost />} />
              <Route path="/Search" element={<Search></Search>} />
              <Route
                path="/Notification"
                element={<Notification></Notification>}
              />
              <Route path="/Profile" element={<User></User>} />
              <Route path="/Follow" element={<Follow></Follow>} />
              <Route path="/search/tags/:hashTag" element={<HashTagSearch/>} />
            </Routes>
          </Layout>
          <Profile></Profile>
        </Center>
      </BrowserRouter>
    );
  }
}

export default App;
