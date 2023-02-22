import axios from "axios";
import { memo, useEffect, useState } from "react";
import styled, { createGlobalStyle } from "styled-components";
import ItemBox from "./ItemBox";
import Loader from "./Loader";
import Header from "../../components/instance/Header";
import EventSourceObject from "../../components/instance/EventSource";
import header from "../../components/instance/Header";
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

const Home = () => {
  const [target, setTarget] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [itemLists, setItemLists] = useState([]);
  let index = Number.MAX_SAFE_INTEGER;
  let eventSource = new EventSourceObject();
  const getMoreData = async (userId) => {
    userId = 1;
    const url = `http://localhost:8000/content-query/slicing?c=${index}&u=${userId}&size=10`;
    const res = await axios.get(url);
    return res;
  };

  const getMoreItem = async (contentId, userId) => {
    userId = 1;
    setIsLoaded(true);
    await new Promise((resolve) => setTimeout(resolve, 1500));
    getMoreData(contentId, userId).then((response) => {
      if (response.data.last === false) {
        index =
            response.data.content[response.data.content.length - 1].contentId;
        setItemLists((itemLists) => itemLists.concat(response.data.content));
      } else if (
          response.data.last === true &&
          response.data.numberOfElements > 0
      ) {
        index =
            response.data.content[response.data.content.length - 1].contentId;
        setItemLists((itemLists) => itemLists.concat(response.data.content));
        index = -1;
      } else {
        index = -1;
      }
    });
    setIsLoaded(false);
  };

  const onIntersect = async ([entry], observer) => {
    if (entry.isIntersecting && !isLoaded && index >= 0) {
      observer.unobserve(entry.target);
      await getMoreItem(index);
      observer.observe(entry.target);
    }
  };

  useEffect(() => {
    let observer;
    if (target) {
      observer = new IntersectionObserver(onIntersect, {
        threshold: 0,
      });
      observer.observe(target);
    }
    const accessToken = localStorage.getItem("accessToken");

    axios
        .get("/user-service/accessToken/get-pk", {
          headers: header(accessToken),
        })
        .then((res) => {
          eventSource.subscribe(res.data);
        })
        .catch((err) => console.log(err));
    return () => observer && observer.disconnect();
  }, [target]);

  return (
      <>
        <AppWrap>
          {itemLists.map((v, i) => {
            return <ItemBox item={itemLists[i]} key={i} />;
          })}
          <div ref={setTarget} className="Target-Element">
            {<Loader />}
          </div>
        </AppWrap>
      </>
  );
};

export default memo(Home);
