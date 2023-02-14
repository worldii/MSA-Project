import React from "react";

function SidebarItem({ item }) {
  return (
    <div className="sidebar-item">
      <p>{item.name}</p>
    </div>
  );
}

export default SidebarItem;
