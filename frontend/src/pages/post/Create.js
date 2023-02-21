import React, {useCallback, useRef, useState} from "react";
import {Box, Button , Form, FormField, Grommet, TextInput} from "grommet";
import {CheckBox} from "grommet";
import axios from "axios";


const SimpleCheckBox = ({ checked: checkedProp, ...rest }) => {
    const [checked, setChecked] = useState(!!checkedProp);
      const onChange = (event) => setChecked(event.target.checked);
      return (
            <Box gap="small" align="center" pad="large">
             <CheckBox {...rest} checked={checked} onChange={onChange} />
        </Box>
    );
};

export default function CreatePost() {
    const [visibleComments, setVisibleComments] = useState(true);
    const [visibleLikes, setVisibleLikes] = useState(true)
    const [images, setImages] = useState([])
    const [description, setDescription] = useState("")
    const inputRef = useRef<HTMLInputElement | null>(null)
    const userId = 1

    const onUploadImage = useCallback(async (e: React.ChangeEvent<HTMLInputElement>) => {
        if (!e.target.files) {
            return
        }
        let imageInfo = []
        let i = 1
        for(const file of e.target.files) {
            console.log(file)
            const response = await axios.post("/media-server/media", {
                userId: 1,
                file: file
            }, {
                headers: {'Content-Type': 'multipart/form-data'}
            })
            console.log(response)
            console.log(response.data.data)
            if (response?.data) {
                imageInfo.push({
                    userId: userId,
                    url: response.data.data.url,
                    order: i
                })
                i++
            }
        }
        setImages(imageInfo)
    }, []);

    const onClickUpload = useCallback(() => {
        if (!inputRef.current) {
            return;
        }
        inputRef.current.click();
    }, []);

    const uploadPost = async (description) => {
        console.log(images)
        await axios.post("/content-command/contents", {
            userId: userId,
            text: description,
            visibleComments: visibleComments,
            visibleLikes: visibleLikes,
            imageUrl: images
        })
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        uploadPost(description).then()
    }

    const handleDescription = (e) => {
        setDescription(e.currentTarget.value)
    }

    const handleComment = (e) => {
        setVisibleComments(e.currentTarget.value)
    }

    const handleLikes = (e) => {
        setVisibleLikes(e.currentTarget.value)
    }

  return (
      <>
      <Grommet>
              <Box fill align="center" justify="center">
                  <Form onSubmit={handleSubmit}>
                      <input type="file" id="fileUpload"
                             accept="image/*"
                             onChange={onUploadImage}
                             multiple={true}/>
                      <Button label="media upload" onClick={onClickUpload}/>
                        <br/><br/>
                      <input name="description" type="description"
                                 onChange={handleDescription}
                                 value={description}
                             placeholder="본문 입력..."
                             style={{width:"100%", height: "200px"}}
                      />
                      <Box direction="row">
                          <SimpleCheckBox label="댓글 활성화"
                                          value={visibleComments}
                                          onChange={handleComment}
                                          reverse />
                          <SimpleCheckBox label="좋아요 숨기기"
                                          value={visibleLikes}
                                          onChange={handleLikes}
                                          reverse />
                      </Box>
                    <Box direction="row" justify="between" margin={{ top: 'medium' }}>
                     <Button type="submit" label="추가" primary />
                    </Box>
                  </Form>
              </Box>
      </Grommet>
      </>
  )
};

