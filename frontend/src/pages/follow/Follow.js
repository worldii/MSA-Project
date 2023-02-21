import axios from "axios";
import { memo, useCallback, useEffect, useState } from "react";
import styled, { createGlobalStyle } from "styled-components";
import FollowerBox from "./FollowerBox";
import Loader from "./Loader";

const AppWrap = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: top;
  text-align: center;
  align-items: center;

  .Target-Element {
    width: 100vw;
    height: 140px;
    display: flex;
    justify-content: center;
    text-align: center;
    align-items: center;
  }
`;

const Follow = () => {
  const [target, setTarget] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [itemLists, setItemLists] = useState([]);
  let index = Number.MAX_SAFE_INTEGER;

  useEffect(() => {
    const url = "http://localhost:8000/follow-service/user/6/follower";
    axios.get(url).then((response) => {
      console.log(response.data);
      setItemLists((itemLists) => itemLists.concat(response.data));
    });
  }, []);

  useEffect(() => {
    console.log("itemLists");
    console.log(itemLists);
  }, [itemLists]);

  return (
    <>
      <AppWrap>
        {itemLists.map((v, i) => {
          return <FollowerBox item={itemLists[i]} key={i} />;
        })}
      </AppWrap>
    </>
  );
};

export default Follow;
