
import{useState, useEffect} from 'react';
import {useParams, useNavigate} from "react-router-dom";
import {Cloudinary} from "@cloudinary/url-gen";
import { motion } from "framer-motion";

import { Button, AbsoluteCenter, Center, Circle, Square, Wrap, WrapItem, Text, Image, Card, Blockquote} from '@chakra-ui/react';
import demoSong from '../assets/Happy.mp3';
export default function MoodMedia(){
    const {moodId} = useParams();
    const [moodMedia, setMoodMedia] = useState(null);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();
    const cloudinary = new Cloudinary({
        cloud: {
            cloudName: "ddugswahb",
        }
    });


    useEffect(() => {
          fetch(`http://localhost:8080/api/media/mood/${moodId}`)
          .then(response => response.json())
            .then(data => {
              if(Array.isArray(data)){
                setMoodMedia(data[0]);
              }else{
                setMoodMedia(data);
              }
              setLoading(false);
            })
            .catch(err => console.error('Error fetching media:', err));
    }, [moodId]);
       
      const renderMedia = () => {
        if (moodMedia === null) {
            console.log("No moodMedia available");
            return <p>No media found</p>;
        }
        console.log(moodMedia);
        switch (moodMedia.mediaType) {
          case 'image':
            return <>
            <motion.div
                            initial={{ opacity: 0 }}
                            animate={{ opacity: 1 }}
                            transition={{ duration: 1 }}
                            whileHover={{ scale: 1.05, boxShadow: "0px 6px 30px rgba(128, 9, 248, 0.2)" }} > 
            <Card.Root maxW="md" borderWidth="1px" borderRadius="lg" overflow="hidden" boxShadow="lg" bg="black" color="white">
            <Image src={moodMedia.contentUrl} alt={moodMedia.contentUrl} width={500} height={500} />
            <Card.Body>
            <Text>{moodMedia.description}</Text></Card.Body>
            </Card.Root>
            </motion.div>
            
            </>;

          case 'video':
            return <div> 
               <motion.div
                            initial={{ opacity: 0 }}
                            animate={{ opacity: 1 }}
                            transition={{ duration: 1 }}
                            whileHover={{ scale: 1.05, boxShadow: "0px 6px 30px rgba(128, 9, 248, 0.2)" }} > 
                            <Card.Root maxW="md" borderWidth="1px" borderRadius="lg" overflow="hidden" boxShadow="lg" bg="black" color="white">

              <iframe width="470" height="415" controls src={moodMedia.contentUrl} title="YouTube video player"
            frameborder="0" 
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" 
            referrerpolicy="strict-origin-when-cross-origin" 
            allowfullscreen
            uk-responsive uk-video="automute: true"></iframe>
            <Card.Body><Text>{moodMedia.description}</Text></Card.Body>
            </Card.Root></motion.div>
            </div>
            
            
            ;
          case 'song':
            return <>
            <audio controls src={demoSong}/> <Text>{moodMedia.description}</Text>
            </>
            ;
          case 'poem':
            return <>
            <motion.div
                            initial={{ opacity: 0 }}
                            animate={{ opacity: 1 }}
                            transition={{ duration: 1 }}
                            whileHover={{ scale: 1.05, boxShadow: "0px 6px 30px rgba(128, 9, 248, 0.2)" }} > 
            <Card.Root maxW="md"borderWidth="1px" borderRadius="lg" overflow="hidden" boxShadow="lg" bg="black" color="white" padding="10px" margin="5px">
            <div class="poem-container">
                <Blockquote.Root color={"white"}>
                    <Blockquote.Content cite={moodMedia.description}>
                      {moodMedia.contentUrl}
                    </Blockquote.Content>
                    <Blockquote.Caption>
                    <cite>{moodMedia.description}</cite>
                    </Blockquote.Caption>
              </Blockquote.Root>
            </div></Card.Root></motion.div>
            </>
          default:
            return <p>Unknown media type</p>;
        }
      };
      function handleClick(evt) {
        evt.preventDefault();
        navigate("/charts")
      }
      function handleMoodClick(evt){
        evt.preventDefault();
        navigate("/enterMood")
      }
    
      return (
        <div>
          <h2>{moodMedia ? "" : "Loading media..."}</h2>
            <Wrap justify="center" gap="30px">
              <Center>
              <div class="mx-auto p-2">
              {renderMedia()}
              </div>
              </Center>
            </Wrap>
              <Wrap gap="30px" justify="center">
              <div>
              <Button colorPalette="purple" variant="surface" size ="lg"onClick={handleClick}>View charts</Button>
              </div>
              <div>
              <Button colorPalette="purple" variant="surface" size ="lg"onClick={handleMoodClick}>Enter Another Mood</Button>
              </div>
              </Wrap>
            <div>

          
            </div>
        </div>
       
      );
    }

    <iframe width="560" height="315" src="https://www.youtube.com/embed/xvFZjo5PgG0?si=RiHgWxPGIvwI592X" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>