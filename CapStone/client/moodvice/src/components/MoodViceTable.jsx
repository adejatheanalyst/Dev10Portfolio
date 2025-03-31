import{useNavigate, useParams} from "react-router-dom";
import{useState} from "react";
import ReplyList from "./ReplyList";
import CreateMoodVice from "./CreateMoodVice";
import moodviceLogo from '../assets/moodvice-transparent.png'
import{Button, Flex, Container, Box, Text, Card, VStack, For, Avatar, Input, Popover, Portal, Collapsible} from "@chakra-ui/react";
import { motion } from "framer-motion";

export default function MoodViceTable({moodVices,setMoodVices, loggedIn}){
    const navigate = useNavigate();
    const formatDate = (date) => {
        return new Date(date).toLocaleDateString("en-US", {
          month: "2-digit",
          day: "2-digit",
          year: "numeric",
        });
      };
      //Handle delete function
      const handleDelete = (moodVice) => {
        console.log(moodVice.moodViceId);
        if(loggedIn.userId === moodVice.userId){
        fetch(`http://localhost:8080/api/moodVice/${moodVice.moodViceId}`, {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
            Autorization: loggedIn.userId,
          },
        }).then(() => {
          setMoodVices(moodVices.filter((m) => m.moodViceId !== moodVice.moodViceId));
          setConfirmDelete(null);
        });
      }
    }
      const [confirmDelete, setConfirmDelete] = useState(null);
  


    return(

        <div className="moodViceTable" >
        <VStack spacing={10} 
    maxWidth="1000px" 
    width="100%" 
    margin="auto" 
    align="stretch">
      <Collapsible.Root>
          <For each={moodVices}>
            {(moodVice) => (
                <motion.div
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ duration: 1 }} >       
          <Card.Root width="1000px" 
            p={5} 
            borderWidth="1px" 
            borderRadius="lg" 
            boxShadow="md" 
            _hover={{ boxShadow: "lg" }} 
            moodVice={moodVice} 
            key={moodVice.moodViceId} className="card"> <Collapsible.Trigger paddingY="3">{moodVice.title}</Collapsible.Trigger>
             <Collapsible.Content>
            <Card.Body gap="2">
         
              <Card.Description className="description">{moodVice.body}</Card.Description>
              <Text  fontSize="sm" color="white">{formatDate(moodVice.created_at)}</Text>
            </Card.Body>
            <Card.Footer justifyContent="space-between">
            {moodVice.userId === loggedIn.userId ?
             <>
             <Button colorPalette="purple" variant="surface" size="sm"
             onClick={ () => navigate(`/edit/${moodVice.moodViceId}`)}>Edit
             </Button>
             <Popover.Root>
      <Popover.Trigger asChild>
        <Button size="sm" variant="outline" colorScheme="red" confirmDelete={confirmDelete} onClick={() => setConfirmDelete(moodVice.moodViceId)}>
          Delete
        </Button>
      </Popover.Trigger>
      <Portal>
        <Popover.Positioner>
          <Popover.Content>
            <Popover.Arrow />
            <Popover.Body>
              <Text className="errors" mb="4">
                Are you sure you want to delete this MoodVice?
              </Text>
              <Popover.Root>
                  <Button variant="outline" colorScheme="red" size="xs" onClick={() => handleDelete(moodVice)}>
                    Delete Entry
                  </Button>
                <Popover.Positioner>
                  <Popover.Content>
                    <Popover.Arrow />
                    <Popover.Body/>
                  </Popover.Content>
                </Popover.Positioner>
              </Popover.Root>
            </Popover.Body>
          </Popover.Content>
        </Popover.Positioner>
      </Portal>
    </Popover.Root>
            </>
            : <>
            <div className="replyContainer"
            style={{ 
              display: "flex", 
              justifyContent: "center", 
              width: "100%", 
              marginTop: "10px" 
            }}>
            <Card.Root
           width="80%"
           p={3} 
           bg="gray.50" 
           borderRadius="md" 
           size="sm" 
           height="auto" 
           className="replyCard">
            <ReplyList moodViceId={moodVice.moodViceId} loggedIn={loggedIn}/>
            </Card.Root>
            </div>
            </>}
            </Card.Footer>
            </Collapsible.Content> 
          </Card.Root>
         
          </motion.div>
          
        )}
        
        </For>
       
        </Collapsible.Root>
     </VStack> 
    
    </div>

    )

}

