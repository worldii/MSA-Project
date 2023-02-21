import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import ContentModal from "../contents/ContentModal";
import ContentFeedType from "../contents/ContentFeedType";

export default function HashTagSearch() {
    const { hashTag } = useParams()

    const [contentIds, setContentIds] = useState([])
    const [me, setMe] = useState({
        id: 1,
        profileImage: "https://ssl.pstatic.net/sstatic/search/mobile/img/ico_common_ban2_cdf26fbe.png",
        name: "asdf",
        nickname: "hello hi"
    })
    const [contents, setContents] = useState([{
        nickname: "asdf",
        contentId: 1,
        userId: 1,
        profileImage: "https://ssl.pstatic.net/sstatic/search/mobile/img/ico_common_ban4_b8f1dad2.png",
        imageUrl: ["https://s.pstatic.net/dthumb.phinf/?src=%22https%3A%2F%2Fs.pstatic.net%2Fshop.phinf%2F20230109_292%2F16732614971570lXim_JPEG%2Foutput_2866948687.jpg%22&type=nf340_228"],
        likes: 500,
    }, {
        nickname: "zxcv",
        contentId: 2,
        userId: 2,
        profileImage: "",
        imageUrl: ["https://s.pstatic.net/dthumb.phinf/?src=%22https%3A%2F%2Fs.pstatic.net%2Fshop.phinf%2F20230109_292%2F16732614971570lXim_JPEG%2Foutput_2866948687.jpg%22&type=nf340_228"],
        likes: 22,
    }, {
        nickname: "asdfgsdf",
        contentId: 3,
        userId: 3,
        profileImage: "",
        imageUrl: ["https://s.pstatic.net/dthumb.phinf/?src=%22https%3A%2F%2Fs.pstatic.net%2Fshop.phinf%2F20230109_292%2F16732614971570lXim_JPEG%2Foutput_2866948687.jpg%22&type=nf340_228"],
        likes: 55,
    }, {
        nickname: "sdrerqwrefg",
        contentId: 4,
        userId: 4,
        profileImage: "",
        imageUrl: ["https://s.pstatic.net/dthumb.phinf/?src=%22https%3A%2F%2Fs.pstatic.net%2Fshop.phinf%2F20230109_292%2F16732614971570lXim_JPEG%2Foutput_2866948687.jpg%22&type=nf340_228"],
        likes: 66,
    }])

    const getContentsByHashTag = async () => {
        const response = await axios.get("/search-service/contents?q=" + hashTag)
        if (response?.data) {
            setContentIds(response?.data.map(c => c.contentId ))
        }
    }

    const getContentsByIds = async () => {
        const response = await axios.get("/content-query/contents/all",
            {params: {ids: contentIds.join(',')}}
        )

        if (response?.data) {
            setContents(response?.data)
        }
    }
    useEffect(() => {
        getContentsByHashTag()
    }, [])

    useEffect(() => {
        if (contentIds.length) {
            // getContentsByIds()
        }
    }, [contentIds])

    return (
        <div>
            <h1>#{hashTag}</h1>
            <div style={{display: "grid",
                gridTemplateColumns: "1fr 1fr 1fr",
                gridTemplateRows: "1fr",
                padding: "30px",
                width: "400px"}}>
                {contents.map(content => {
                    return (
                        <div style={{margin: "10px", height: "200px"}}>
                            <ContentModal content={content}
                                          user={me}
                                          isLiked={false}
                                          isBookmarked={true}/>
                        </div>
                    )
                })}

            </div>
        </div>
    )
}