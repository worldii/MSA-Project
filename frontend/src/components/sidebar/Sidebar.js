import React from "react";
import { NavLink } from "react-router-dom";
import styled from "styled-components";
import SidebarItem from "./SidebarItem";
import imgA from "../../assets/Instagram_Logo_2016.png";
import { RxHamburgerMenu } from "react-icons/rx";
const Side = styled.div`
  display: flex;
  border-right: 1px solid #e0e0e0;
  flex-direction: column;
  align-items: center;

  width: 15%;
`;
const SideLayout = styled.div`
  margin-top: 30px;
  width: 150px;
  display: flex;
  flex-direction: column;
`;

const MyBarLayout = styled.div`
  display: flex;

  margin-top: auto;
`;
const MoreLayout = styled.div`
  margin-right: auto;

  padding: 10px;
`;
const ProfileLayout = styled.div`
  padding-top: 20px;
`;
function Sidebar() {
  const sidebarList = [
    { name: "Home", path: "/" },
    { name: "Search", path: "/Search" },
    { name: "Messages", path: "/chatlist" },
    { name: "Notifications", path: "/Notification" },
    { name: "Create", path: "/post" },
    { name: "Profile", path: "/profile" },
  ];

  return (
    <Side>
      <ProfileLayout>
        <img width="105spx" height="30px" src={imgA}></img>
      </ProfileLayout>
      <SideLayout>
        {sidebarList.map((bar, index) => {
          return (
            <NavLink
              exact
              style={{ color: "black", textDecoration: "none" }}
              to={bar.path}
              key={index}
              activeStyle={{ color: "black" }}
            >
              <SidebarItem item={bar} />
            </NavLink>
          );
        })}
      </SideLayout>

      <MyBarLayout>
        <MoreLayout>
          {" "}
          <RxHamburgerMenu></RxHamburgerMenu>
        </MoreLayout>
        <MoreLayout>more</MoreLayout>
      </MyBarLayout>
    </Side>
  );
}

export default Sidebar;
