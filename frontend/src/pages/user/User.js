import React from "react";
import EventSourceObject from "../../components/instance/EventSource";
import {useEffect} from "react";

const User = ({}) => {
  const eventSource = new EventSourceObject().getEventSource();
  useEffect(()=>{
    eventSource.addEventListener("sse",function (event){
      const json = JSON.parse(event.data)
      console.log(json)
      new Notification("알림",{body:json.content})
    })
  },[])
  return <div>User 페이지 입니다.</div>;
};

export default User;
